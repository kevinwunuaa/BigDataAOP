package com.bdaop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdaop.context.AppContext;
import com.bdaop.entity.UaamRole;
import com.bdaop.entity.UaamUser;
import com.bdaop.service.UserService;
import com.bdaop.vo.ResourceNode;
import com.bdaop.vo.ResourceTree;
import com.bdaop.vo.User;
import com.bsoft.common.util.MD5;

/**
 * 通用用户管理控制类.
 * 
 * @author Wuyong
 * 
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 用户登录.
	 * 
	 * @param req
	 *            请求对象
	 * @param res
	 *            响应对角
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param isEncrypt
	 *            密码是否加密
	 * @return 视图逻辑名名称
	 */
	@RequestMapping("login.action")
	public String login(Model model, HttpServletRequest req,
			HttpServletResponse res, String username, String password,
			boolean isEncrypt) {
		User user = new User();
		HttpSession session = req.getSession();

		if (StringUtils.isNotEmpty(username)) {
			UaamUser uaamUser = userService.getUserByUsername(username);
			if (uaamUser == null) {
				model.addAttribute("message", "用户不存在！");
				return "forward:/loginPage.action";
			} else {
				String md5Pwd = password;
				if (!isEncrypt) {
					md5Pwd = MD5.createPassword(password);
				}
				if (!md5Pwd.equals(uaamUser.getPassword())) {
					model.addAttribute("message", "密码错误,输错三次系统将自动锁定用户！");

					int errTimes = uaamUser.getPwderrortimes();

					errTimes++;

					uaamUser.setPwderrortimes(errTimes);

					userService.lockUser(uaamUser);

					return "forward:/loginPage.action";
				}

				String status = uaamUser.getStatus();
				if ("1".equals(status)) {
					model.addAttribute("message", "用户被停用,请联系管理员处理！");
					return "forward:/loginPage.action";
				} else if ("2".equals(status)) {
					model.addAttribute("message", "用户被锁定,请联系管理员处理！");
					return "forward:/loginPage.action";
				} else
					;

				List<UaamRole> roles = userService.getRolesByUser(uaamUser);

				if (roles == null || roles.size() <= 0) {
					model.addAttribute("message", "用户未配置角色！");
					return "forward:/loginPage.action";
				}

				List<ResourceNode> resources = userService.getResourcesByRole(
						roles, AppContext.DOMAIN);

				if (resources == null || resources.size() <= 0) {
					model.addAttribute("message", "用户未配置资源！");
					return "forward:/loginPage.action";
				}

				ResourceTree tree = new ResourceTree();

				tree.getResourceTree(resources);

				try {
					BeanUtils.copyProperties(user, uaamUser);
				} catch (Exception e) {
					e.printStackTrace();
				}

				user.setRoles(roles);
				user.setResourceTree(tree);
				session.setAttribute(AppContext.SESSION_USER, user);
				AppContext.put(AppContext.SESSION_USER, user);
				model.addAttribute("user", user);
			}
		}

		return "forward:/index.action";
	}

	/**
	 * 用户登录.
	 * 
	 * @param req
	 *            请求对象
	 * @param res
	 *            响应对角
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param isEncrypt
	 *            密码是否加密
	 * @return json格式登录信息
	 */
	// @RequestMapping("login.action")
	// @ResponseBody
	public Map login1(HttpServletRequest req, HttpServletResponse res,
			String username, String password, boolean isEncrypt) {
		User user = new User();
		HttpSession session = req.getSession();
		// System.out.println(session.getId());
		Map result = new HashMap();
		result.put("success", true);
		result.put("msg", "登录成功！");

		if (StringUtils.isNotEmpty(username)) {
			UaamUser uaamUser = userService.getUserByUsername(username);
			if (uaamUser == null) {
				result.put("success", false);
				result.put("msg", "用户不存在！");
				return result;
			} else {
				String md5Pwd = password;
				if (!isEncrypt) {
					md5Pwd = MD5.createPassword(password);
				}
				if (!md5Pwd.equals(uaamUser.getPassword())) {
					result.put("success", false);
					result.put("msg", "密码错误,输错三次系统将自动锁定用户！");

					int errTimes = uaamUser.getPwderrortimes();

					errTimes++;

					uaamUser.setPwderrortimes(errTimes);

					userService.lockUser(uaamUser);

					return result;
				}

				String status = uaamUser.getStatus();
				if ("1".equals(status)) {
					result.put("success", false);
					result.put("msg", "用户被停用,请联系管理员处理！");
					return result;
				} else if ("2".equals(status)) {
					result.put("success", false);
					result.put("msg", "用户被锁定,请联系管理员处理！");
					return result;
				} else
					;

				List<UaamRole> roles = userService.getRolesByUser(uaamUser);

				if (roles == null || roles.size() <= 0) {
					result.put("success", false);
					result.put("msg", "用户未配置角色！");
					return result;
				}

				List<ResourceNode> resources = userService.getResourcesByRole(
						roles, AppContext.DOMAIN);

				if (resources == null || resources.size() <= 0) {
					result.put("success", false);
					result.put("msg", "用户未配置资源！");
					return result;
				}

				ResourceTree tree = new ResourceTree();

				tree.getResourceTree(resources);

				try {
					BeanUtils.copyProperties(user, uaamUser);
				} catch (Exception e) {
					e.printStackTrace();
				}

				user.setRoles(roles);
				user.setResourceTree(tree);
				session.setAttribute(AppContext.SESSION_USER, user);
				AppContext.put(AppContext.SESSION_USER, user);
				result.put("user", user);
			}
		}

		if (!(Boolean) result.get("success")) {
			System.out.println(result.get("msg"));
		}
		return result;
	}

	/**
	 * 单点登录方式.
	 * 
	 * @param req
	 * @param res
	 * @param userName
	 * @param pwd
	 * @return 页面
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping("ssoLogon.action")
	public String ssoLogon(HttpServletRequest req, HttpServletResponse res,
			String userName, String pwd) {
		// http://localhost:8080/platform/ssoLogon.action?uid=system&originUserId=1001&userName=system&personName=%E5%90%B4%E5%8B%87&cardNum=522425198712258157&regionCode=9999&urt=3301%40system&pwd=e6e061838856bf47e1de730719fb2609&sso_ticket=3068b435ffc0d08785879c8fc456da05
		// "uid" : $.trim(res.propRoles[0].pId),
		// "originUserId" : $.trim(res.propRoles[0].userId),
		// "userName" : $.trim(res.propRoles[0].userName),
		// "personName" : $.trim(res.propRoles[0].personName),
		// "cardNum" : $.trim(res.propRoles[0].cardNum),
		// "regionCode" : $.trim(res.propRoles[0].regionCode),
		// "urt" : $.trim(res.propRoles[0].id),
		// "pwd" : $.trim(res.propRoles[0].pwd)
		Map resultMap = login1(req, res, userName, pwd, true);
		// try {
		// req.getRequestDispatcher("home.action").forward(req, res);
		// } catch (ServletException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return "home";
	}

	/**
	 * 退出登录.
	 * 
	 * @param req
	 * @param res
	 * @return 退出消息
	 */
	@RequestMapping("logout.action")
	@ResponseBody
	public Map logout(HttpServletRequest req, HttpServletResponse res) {
		Map map = new HashMap();

		HttpSession session = req.getSession();
		session.setAttribute(AppContext.SESSION_USER, null);
		session.invalidate();

		map.put("success", true);
		map.put("msg", "操作成功！");
		return map;
	}
	
	@RequestMapping("modifyPwdPage.action")
	public String modifyPwdPage()
	{
		return "modify-pwd";
	}

	/**
	 * 密码修改.
	 * 
	 * @param req
	 * @param res
	 * @param ymm
	 *            原密码
	 * @param psw
	 *            新的密码
	 * @param confirmpsw
	 *            确认密码
	 * @return 操作消息
	 */
	@RequestMapping("modifyPwd.action")
	@ResponseBody
	public Map modifyPwd(HttpServletRequest req, HttpServletResponse res,
			String ymm, String psw, String confirmpsw) {
		Map resultMap = new HashMap();
		HttpSession session = req.getSession();
		User userInfo = (User) session.getAttribute(AppContext.SESSION_USER);
		String userName = userInfo.getUsername();
		String password = userInfo.getPassword();
		if (!password.equals(MD5.createPassword(ymm))) {
			resultMap.put("success", false);
			resultMap.put("msg", "原始密码不正确！");
			return resultMap;
		} else if (StringUtils.isEmpty(psw)) {
			resultMap.put("success", false);
			resultMap.put("msg", "新密码不能为空！");
			return resultMap;
		} else if (!psw.equals(confirmpsw)) {
			resultMap.put("success", false);
			resultMap.put("msg", "两次密码不一致！");
			return resultMap;
		}

		psw = MD5.createPassword(psw);
		userService.modifyPwd(userName, psw);

		userInfo.setPassword(psw);
		session.setAttribute(AppContext.SESSION_USER, userInfo);
		resultMap.put("success", true);

		return resultMap;
	}

	/**
	 * 根据当前登录用户获取角色.
	 * 
	 * @param req
	 * @param res
	 * @return 角色信息
	 */
	@RequestMapping("uaam/getRoles.action")
	@ResponseBody
	public Map getRoles(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute(AppContext.SESSION_USER);
		List<UaamRole> roles = user.getRoles();
		Map role = new HashMap();
		role.put("roles", roles);
		return role;
	}

	/**
	 * 根据当前登录用户获取资源树.
	 * 
	 * @param req
	 * @param res
	 * @return ResourceTree 资源树
	 */
	@RequestMapping("uaam/getResourceTree.action")
	@ResponseBody
	public ResourceTree getResourceTree(HttpServletRequest req,
			HttpServletResponse res) {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute(AppContext.SESSION_USER);
		ResourceTree tree = user.getResourceTree();
		return tree;
	}
}
