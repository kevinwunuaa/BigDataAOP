package com.bdaop.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.bdaop.entity.UaamOrg;
import com.bdaop.entity.UaamResource;
import com.bdaop.entity.UaamRole;
import com.bdaop.entity.UaamUser;
import com.bsoft.common.tree.TreeNode;
import com.bsoft.common.util.StringTool;

@Service(value = "configService")
public class ConfigService extends AbstractService{

	public List<TreeNode> getOrganTree(){
		String hql = " from UaamOrg t where t.parentid is null";

		List<UaamOrg> list = (List<UaamOrg>) hDao.getList(hql);
		
		if(list == null || list.size() <= 0){
			return null;
		}

		UaamOrg org = (UaamOrg) list.get(0);

		TreeNode root = new TreeNode();
		root.setId(org.getOrgcode());
		root.setText(org.getOrgname());

		getOrgChildNodes(root, (List<UaamOrg>) hDao.getList("from UaamOrg t"));

		List<TreeNode> nodes = new ArrayList<TreeNode>();

		nodes.add(root);

		return nodes;
	}
	
	private void getOrgChildNodes(TreeNode root, List<UaamOrg> orgs) {
		if (orgs == null || orgs.size() <= 0) {
			return;
		}

		for (UaamOrg o : orgs) {
			if (root.getId().equals(o.getParentid())) {
				TreeNode n = new TreeNode();
				n.setId(o.getOrgcode());
				n.setText(o.getOrgname());
				root.getChildren().add(n);
				getOrgChildNodes(n, orgs);
			}
		}

		if (root.getChildren() != null && root.getChildren().size() > 0) {
			root.setState("closed");
		}
	}
	
	public Map listUsers(String orgCode,String keyText,Integer pageSize,
			Integer pageNo){
		List<Criterion> args = new ArrayList<Criterion>();
		String countHql = "select count(t) from UaamUser t where 1= 1";
		
		if(StringUtils.isNotEmpty(orgCode)){
			args.add(Restrictions.eq("organizecode", orgCode));
			countHql += " and t.organizecode = '" + orgCode + "'";
		}

		if(StringUtils.isNotEmpty(keyText)){
			args.add(Restrictions.or(Restrictions.like("username", keyText, MatchMode.ANYWHERE),Restrictions.like("personname", keyText, MatchMode.ANYWHERE)));
			keyText = "%" + keyText + "%";
			countHql += " and (t.username like '" + keyText + "' or t.personname like '" + keyText + "')";
		}
		
		Long total = (Long) hDao.uniqueResult(countHql);
		
		Order order = Order.asc("userid");
		
		List<UaamUser> list = hDao.findByPage(UaamUser.class, args, order, pageNo, pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
	public Map listUsersByRole(String roleId,Integer pageSize,Integer pageNo){
		String countSql = "select count(1) from uaam_users u,uaam_userroles ur where u.userid = ur.userid and ur.roleid = ?";
		
		BigInteger total = (BigInteger) hDao.uniqueResultBySql(countSql,roleId);
		
		List<UaamUser> list = hDao.findPageBySql(UaamUser.class, "select u.* from uaam_users u,uaam_userroles ur where u.userid = ur.userid and ur.roleid = ?", pageNo, pageSize, roleId);
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("total", total.longValue());
		map.put("rows", list);
		
		return map;
	}
	
	public int unlockUser(String userId){
		String sql = "update sys_users t set t.status = '0',t.pwderrortimes = 0 where t.userid = ?";
		
		return (Integer) hDao.executeUpdate(sql, userId);
	}
	
	public Map listRoles(String roleName,Integer pageSize,Integer pageNo){
		String countHql = "select count(t) from UaamRole t where 1 = 1";
		List<Criterion> args = new ArrayList<Criterion>();
		
		if(StringUtils.isNotEmpty(roleName)){
			countHql += " and t.rolename like '%" + roleName + "%'";
			args.add(Restrictions.like("rolename",roleName,MatchMode.ANYWHERE));
		}
		
		Order order = Order.asc("roleid");
		Long total = (Long) hDao.uniqueResult(countHql);
		List<UaamRole> list = hDao.findByPage(UaamRole.class, args, order, pageNo, pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
	public void saveEntity(Object entity){
		hDao.saveEntity(entity);
	}
	
	public void updateEntity(Object entity){
		hDao.updateEntity(entity);
	}
	
	public <T> T getEntity(Class<T> clazz, Serializable id){
		return hDao.getEntity(clazz, id);
	}
	
	public void deleteEntity(Object entity){
		hDao.deleteEntity(entity);
	}
	
	public void saveRoleUsers(String roleId,String[] addRowIds,String[] delRowIds){
		if(addRowIds != null && addRowIds.length > 0){
			String sql = "insert into uaam_userroles(refid,userid,roleid) values(?,?,?)";
			for(String userid : addRowIds){
				String refid = hDao.uniqueResultBySql("select seq_uaam_userroles.nextval from dual").toString();
				hDao.executeUpdate(sql,refid,userid,roleId);
			}
		}
		
		if(delRowIds != null && delRowIds.length > 0){
			String sql = "delete from uaam_userroles t where t.userid = ? and t.roleid = ?";
			for(String userid : delRowIds){
				hDao.executeUpdate(sql,userid,roleId);
			}
		}
	}
	
	public void saveRoleResources(String roleId,String[] resIds){
		if(resIds != null && resIds.length > 0){
			//删除未被选中节点
			String inStr = "(" + StringTool.stringJoin(resIds, ",") + ")";
			String sql = "delete from uaam_roleresources t where t.resid not in" + inStr + " and t.roleid = ?";
			hDao.executeUpdate(sql, roleId);
			
			//新增节点
			for(int i = 0; i < resIds.length;i ++){
				String resId = resIds[i];
				if("9999".equals(resId)){
					continue;
				}
				sql = "select count(1) from uaam_roleresources t where t.resid = ? and t.roleid = ?";
				int result = ((BigDecimal)hDao.uniqueResultBySql(sql, resId,roleId)).intValue();
				if(result < 1){
					sql = "insert into uaam_roleresources(refid,roleid,resid) values(?,?,?)";
					String refid = hDao.uniqueResultBySql("select seq_uaam_roleresources.nextval from dual").toString();
					hDao.executeUpdate(sql, refid,roleId,resId);
				}
			}
		}
	}
	
	public void removeRole(String id){
		UaamRole r = hDao.getEntity(UaamRole.class, id);
		
		//删除角色下的用户及资源
		String sql = "delete from uaam_userroles t where t.roleid = ?";
		hDao.executeUpdate(sql, id);
		
		sql = "delete from uaam_roleresources t where t.roleid = ?";
		hDao.executeUpdate(sql, id);
		
		hDao.deleteEntity(r);
	}
	
	public List<UaamResource> listResNodes(){
		return  (List<UaamResource>) hDao.getList("from UaamResource t");
		
	}
	
	public List<UaamResource> getSelectedResByRole(String roleId){
		String sql = "select r.* from uaam_resources r,uaam_roleresources rr"
				+ " where r.resid = rr.resid and rr.roleid = ?";
		
		List<UaamResource> selectedList = hDao.findBySql(UaamResource.class, sql, roleId);
		return selectedList;
		
	}
}
