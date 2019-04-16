package com.bdaop.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bdaop.entity.SysOrganization;
import com.bdaop.service.BaseService;
import com.bsoft.common.util.DateUtil;
import com.bsoft.common.util.PropertyUtil;
import com.bsoft.common.util.StringTool;
import com.bsoft.dao_support.HibernateDaoSupport;

@Controller
public class BaseController {
	@Autowired
	BaseService baseServ;

	/**
	 * 机构信息维护
	 * 
	 * @return
	 */
	@RequestMapping("base/organization.action")
	public String organization() {
		return "base/base_organization";
	}

	@RequestMapping("base/loadOrgByCode.action")
	@ResponseBody
	public SysOrganization loadOrgByCode(String orgCode) {
		return baseServ.loadOrgByCode(orgCode);
	}
	
	@RequestMapping("base/ajaxLogoUpload.action")
	@ResponseBody
	public Map uploadFile(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile myfile = (CommonsMultipartFile) multipartRequest
				.getFile("myfile");
		Map result = new HashMap();
		if (myfile != null
				&& StringUtils.isNotBlank(myfile.getOriginalFilename())) {
			try {
				// 文件名
				String fileName = myfile.getOriginalFilename();
				// 文件路径
				String uploadFilePath = PropertyUtil.getPropertyValue("sys",
						"logoFilePath");
				String logoName = PropertyUtil.getPropertyValue("sys",
						"logoName");
				String contextRealPath = request.getServletContext()
						.getRealPath(uploadFilePath);
				String uuid = StringTool.uuid();
				String extension = fileName
						.substring(fileName.lastIndexOf('.') + 1);
				if (StringUtils.isEmpty(logoName)) {
					logoName = uuid + DateUtil.formatDate(new Date());
				}
				String newFileName = logoName + "." + extension;
				String filePath = contextRealPath + File.separator
						+ newFileName;

				myfile.transferTo(new File(filePath));

				result.put("success", true);
				result.put("msg", "文件上传成功");
				result.put("fileName", newFileName);
			} catch (Exception e) {
				result.put("success", false);
				result.put("msg", "文件上传失败");
				return result;
			}
		} else {
			result.put("success", false);
			result.put("msg", "文件上传失败");
			return result;
		}
		return result;
	}

	@RequestMapping("base/saveOrg.action")
	@ResponseBody
	public Map saveOrg(SysOrganization org, String action) {

		baseServ.saveOrg(org, action);
		Map result = new HashMap();
		result.put("success", true);
		result.put("msg", "保存成功！");
		return result;
	}
	
	/**
	 * 员工信息维护.
	 * @return
	 */
	@RequestMapping("base/employee.action")
	public String employee() {
		return "base/base_employee";
	}
}
