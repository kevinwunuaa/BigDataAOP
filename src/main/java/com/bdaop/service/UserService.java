package com.bdaop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.bdaop.context.AppContext;
import com.bdaop.entity.UaamRole;
import com.bdaop.entity.UaamUser;
import com.bdaop.vo.ResourceNode;

/**
 * 用户服务查询类.
 * <br/>
 * 依赖资源：hibernateTemplate、jdbcTemplate
 * @author Wuyong
 *
 */
@Service(value = "userService")
public class UserService {
	private static final Logger log = Logger.getLogger(UserService.class);

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 通过用户名查询用户.
	 * @param userName 用户名
	 * @return 用户对角
	 */
	public UaamUser getUserByUsername(String userName){
		List<UaamUser> users = (List<UaamUser>) hibernateTemplate.findByNamedParam("from UaamUser t where t.username = :username", "username", userName);
		
		return users != null && users.size() > 0 ? users.get(0) : null;
	}
	
	/**
	 * 通过用户获取角色列表.
	 * @param user 用户对角
	 * @return 角色列表
	 */
	public List<UaamRole> getRolesByUser(UaamUser user){
		String sql = 
				"select r.*\n" +
						"      from uaam_users u, uaam_roles r, uaam_userroles ur\n" + 
						"     where u.userid = ur.userid\n" + 
						"       and r.roleid = ur.roleid\n" + 
						"       and u.userid = ?";
		
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, user.getUserid());
		
		List<UaamRole> roles = new ArrayList<UaamRole>();
		
		if(list == null || list.size() <= 0){
			return null;
		}
		
		for(Map<String,Object> item : list){
			UaamRole r = new UaamRole();
			r.setRoleid(((Integer)item.get("ROLEID")).intValue());
			r.setRolename((String)item.get("ROLENAME"));
			r.setRemark((String)item.get("REMARK"));
			
			roles.add(r);
		}
		
		return roles;
	}
	
	/**
	 * 通过角色列表获取资源.
	 * @param roles 角色列表
	 * @param domain 所属域
	 * @return 资源列表
	 */
	public List<ResourceNode> getResourcesByRole(List<UaamRole> roles,String domain){
		String sql = 
				"select distinct r2.*\n" +
						"    from uaam_roles r1,uaam_resources r2,uaam_roleresources ur\n" + 
						"    where r1.roleid = ur.roleid\n" + 
						"    and r2.resid = ur.resid\n" + 
						"    and r2.resdomain = ?\n";
		
		String inStr = "";
		for(UaamRole r : roles){
			inStr += "'" + r.getRoleid() + "',";
		}

		inStr = inStr.substring(0,inStr.lastIndexOf(","));
		
		sql += " and r1.roleid in(" + inStr + ")";
		
		sql += "    order by r2.resorder";
		
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, domain);
		
		List<ResourceNode> resources = new ArrayList<ResourceNode>();
		
		if(list == null || list.size() <= 0){
			return null;
		}
		
		for(Map<String,Object> m : list){
			ResourceNode node = new ResourceNode();
			node.setResid(((Integer)m.get("RESID")).intValue() + "");
			Object pid = m.get("PARENTID");
			node.setParentid(pid != null ? ((Integer)pid).intValue() + "" : "");
			node.setRestype((String)m.get("RESTYPE"));
			node.setResname((String)m.get("RESNAME"));
			node.setReslevel(((Integer)m.get("RESLEVEL")).intValue());
			node.setResurl((String)m.get("RESURL"));
			node.setResdomain((String)m.get("RESDOMAIN"));
			node.setRemark((String)m.get("REMARK"));
			node.setStatus((String)m.get("STATUS"));
			Object orderValue = m.get("RESORDER");
			if(orderValue != null){
				node.setResorder(((Integer)orderValue).intValue());
			}
			
			resources.add(node);
		}
		
		return resources;
	}
	
	/**
	 * 密码修改.
	 * @param userName 用户名
	 * @param passwd 密码
	 */
	public void modifyPwd(String userName,String passwd){
		UaamUser user = this.getUserByUsername(userName);
		
		user.setPassword(passwd);
		try{
			hibernateTemplate.update(user);
		}catch(Exception e){
			String sql = "update " + AppContext.UAAM_USER_SCHEMA + ".hqx t set t.xmm = ? where t.yhm = ?";
			jdbcTemplate.update(sql, new Object[]{passwd,userName});
		}
	}
	
	/**
	 * 用户锁定.
	 * @param user 用户对象
	 */
	public void lockUser(UaamUser user){
		String sql = "update hqx t set t.mmcwcs = " +  user.getPwderrortimes();
		if(user.getPwderrortimes() >= 3){
			sql += ",t.zt = '2'";
		}
		sql += " where t.id = '" + user.getUserid() + "'";
		
		jdbcTemplate.update(sql);
	}
}
