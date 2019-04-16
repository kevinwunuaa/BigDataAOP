package com.bdaop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bdaop.entity.UaamResource;
import com.bdaop.entity.UaamRole;
import com.bdaop.entity.UaamUser;
import com.bdaop.service.ConfigService;
import com.bsoft.common.tree.TreeNode;
import com.bsoft.common.util.PoiUtil;

@Controller
public class ConfigController {
	@Autowired
	private ConfigService configServ;
	
	@RequestMapping("config/user.action")
	public String user(){
		return "config/user";
	}
	
	@RequestMapping("config/orgTree.action")
	@ResponseBody
	public List<TreeNode> getOrganTree() {
		return configServ.getOrganTree();
	}
	
	/**
	 * 数据导入.
	 * @param request
	 * @return
	 */
	@RequestMapping("config/importUsers.action")
	@ResponseBody
	public Map importUsers(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile myfile = (CommonsMultipartFile) multipartRequest.getFile("myfile");
		Map result = new HashMap();
		result.put("success", true);
		if (myfile != null){
			try {
				List<String[]> rows = PoiUtil.readExcel(myfile);
				List<UaamUser> data = new ArrayList<UaamUser>();
				
				if(rows != null && rows.size() > 0){
					for(String []items : rows){
						UaamUser user = new UaamUser();
						user.setUserid(items[0]);
						user.setUsername(items[1]);
						user.setPersonname(items[2]);
						user.setGender(items[3]);
						user.setPassword(items[4]);
						user.setCardnum(items[5]);
						user.setOrganizecode(items[6]);
						user.setStatus(items[7]);
						user.setRemark(items[8]);
						
						data.add(user);
					}
				}
				
				// TODO 保存到数据库
				
				//返回前台展示
				result.put("data", data);
			} catch (IOException e) {
				result.put("success", false);
				result.put("msg", e.getMessage());
				return result;
			}
		}
			
		return result;
	}
	
	@RequestMapping("config/listUsers.action")
	@ResponseBody
	public Map listUsers(String orgCode,String keyText,Integer pageSize,
			Integer pageNo){
		
		if (StringUtils.isEmpty(orgCode)) {
			return null;
		}

		if (pageSize == null) {
			pageSize = 15;
		}

		if (pageNo == null) {
			pageNo = 1;
		}
		
		return configServ.listUsers(orgCode, keyText, pageSize, pageNo);
	}
	
	/**
	 * 通过角色查询用户
	 * @param roleId
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("config/listUsersByRole.action")
	@ResponseBody
	public Map listUsersByRole(String roleId,Integer pageSize,Integer pageNo){
		if (StringUtils.isEmpty(roleId)) {
			return null;
		}

		if (pageSize == null) {
			pageSize = 15;
		}

		if (pageNo == null) {
			pageNo = 1;
		}
		
		return configServ.listUsersByRole(roleId, pageSize, pageNo);
	}
	
	@RequestMapping("config/unlockUser.action")
	@ResponseBody
	public Map unlockUser(String userId){
		configServ.unlockUser(userId);
		Map result = new HashMap();
		result.put("success", true);
		result.put("msg", "操作成功！");
		
		return result;
	}
	
	@RequestMapping("config/role.action")
	public String role(){
		return "config/role";
	}
	
	@RequestMapping("config/listRoles.action")
	@ResponseBody
	public Map listRoles(String roleName,Integer pageSize,Integer pageNo){
		if (pageSize == null) {
			pageSize = 15;
		}

		if (pageNo == null) {
			pageNo = 1;
		}
		
		return configServ.listRoles(roleName, pageSize, pageNo);
	}
	
	@RequestMapping("config/saveRole.action")
	@ResponseBody
	public Map saveRole(String action,UaamRole role){
		if("add".equals(action)){
			role.setRoleid(null);
			configServ.saveEntity(role);
		}else if("update".equals(action)){
			Integer roleid = role.getRoleid();
			UaamRole r = configServ.getEntity(UaamRole.class, roleid);
			try {
				BeanUtils.copyProperties(role, r);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			configServ.updateEntity(r);
		}else;
		
		Map result = new HashMap();
		result.put("success", true);
		result.put("msg", "操作成功！");
		
		return result;
	}
	
	/**
	 * 配置用户角色关系
	 * @param roleId
	 * @param addRowIds
	 * @param delRowIds
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("config/saveRoleUsers.action")
	@ResponseBody
	public Map saveRoleUsers(String roleId,String[] addRowIds,String[] delRowIds){
		Map map = new HashMap();
		
		configServ.saveRoleUsers(roleId, addRowIds, delRowIds);
		
		map.put("success", true);
		map.put("msg", "保存成功！");
		
		return map;
	}
	
	/**
	 * 配置角色资源关系
	 * @param roleId
	 * @param resIds
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("config/saveRoleResources.action")
	@ResponseBody
	public Map saveRoleResources(String roleId,String[] resIds){
		Map map = new HashMap();
		
		configServ.saveRoleResources(roleId, resIds);
		map.put("success", true);
		map.put("msg", "保存成功！");
		
		return map;
	}
	
	@RequestMapping("config/removeRole.action")
	@ResponseBody
	public Map removeRole(String id){
		configServ.removeRole(id);
		
		Map result = new HashMap();
		result.put("success", true);
		result.put("msg", "操作成功！");
		
		return result;
	}
	
	@RequestMapping("config/resource.action")
	public String resource(){
		return "config/resource";
	}
	
	@RequestMapping("config/saveResource.action")
	@ResponseBody
	public Map saveResource(UaamResource res){
		Integer resId =  res.getResid();
		if(resId == null){
			configServ.saveEntity(res);
			resId = res.getResid();
		}else{
			UaamResource res1 = configServ.getEntity(UaamResource.class, resId);
			BeanUtils.copyProperties(res, res1,new String[]{"resid"});
			configServ.updateEntity(res1);
		}
		
		Map result = new HashMap();
		result.put("success", true);
		result.put("msg", "操作成功！");
		result.put("nodeId", resId);
		return result;
	}
	
	@RequestMapping("config/removeResource.action")
	@ResponseBody
	public Map removeResource(Integer resId){
		UaamResource r = configServ.getEntity(UaamResource.class, resId);
		configServ.deleteEntity(r);
		Map result = new HashMap();
		result.put("success", true);
		result.put("msg", "操作成功！");
		return result;
	}
	
	private List<TreeNode> listResNodes(){
		List<UaamResource> resList = configServ.listResNodes();
		
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		
		for(UaamResource r : resList){
			TreeNode n = new TreeNode();
			n.setId(r.getResid() + "");
			n.setText(r.getResname());
			Map attrs = new HashMap();
			attrs.put("parentid", r.getParentid() == null ? "" : r.getParentid() + "");
			attrs.put("restype",r.getRestype());
			attrs.put("resurl", r.getResurl());
			attrs.put("resdomain", r.getResdomain());
			attrs.put("remark", r.getRemark());
			attrs.put("status", r.getStatus());
			attrs.put("reslevel", r.getReslevel());
			attrs.put("resorder", r.getResorder());
			
			n.setAttributes(attrs);
			n.setChecked(false);
			
			nodeList.add(n);
		}
		
		return nodeList;
	}
	
	/**
	 * 获取资源树
	 * @return
	 */
	@RequestMapping("config/getResTree.action")
	@ResponseBody
	public List<TreeNode> getResTree(){
		List<TreeNode> nodes = listResNodes();
		
		TreeNode root = null;
		
		for(TreeNode n1 : nodes){
			String id = n1.getId();
			for(TreeNode n2 : nodes){
				if(n2.getAttributes() != null){
					if(id.equals(n2.getAttributes().get("parentid"))){
						n1.getChildren().add(n2);
					}
				}
			}
			
			if(n1.getChildren().size() > 0){
				n1.setState("closed");
			}
			
			if(n1.getAttributes() != null){
				if(StringUtils.isEmpty((String)n1.getAttributes().get("parentid"))){
					root = n1;
				}
			}
		}
		
		List<TreeNode> tree = new ArrayList<TreeNode>();
		tree.add(root);
		
		return tree;
	}
	
	/**
	 * 根据角色获取资源授权树
	 * @param roleId
	 * @return
	 */
	@RequestMapping("config/getResTreeByRole.action")
	@ResponseBody
	public List<TreeNode> getResTreeByRole(String roleId){
		if(StringUtils.isEmpty(roleId)){
			return null;
		}
		
		
		List<TreeNode> nodeList = listResNodes();
		
		List<UaamResource> selectedList = configServ.getSelectedResByRole(roleId);
		
		if(selectedList != null && selectedList.size() > 0){
			for(UaamResource r : selectedList){
				for(TreeNode n : nodeList){
					if(r.getResid() == Integer.valueOf(n.getId())){
						n.setChecked(true);
						break;
					}
				}
			}
		}
		
		TreeNode root = null;
		
		for(TreeNode n1 : nodeList){
			String id = n1.getId();
			for(TreeNode n2 : nodeList){
				if(n2.getAttributes() != null){
					if(id.equals(n2.getAttributes().get("parentid"))){
						n1.getChildren().add(n2);
					}
				}
			}
			
			if(n1.getChildren().size() > 0){
				n1.setState("closed");
				n1.setChecked(false);
			}
			
			if(n1.getAttributes() != null){
				if(StringUtils.isEmpty((String)n1.getAttributes().get("parentid"))){
					root = n1;
				}
			}
		}
		
		root.setChecked(false);
		
		TreeNode node = new TreeNode();
		node.setId("9999");
		node.setText("资源授权");
		node.setChecked(false);
		node.getChildren().add(root);
		
		List<TreeNode> list = new ArrayList<TreeNode>();
		
		list.add(node);
		
		return list;
	}
}
