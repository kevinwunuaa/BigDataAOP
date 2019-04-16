package com.bdaop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.core.appender.rewrite.MapRewritePolicy.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.bdaop.service.PubService;
import com.bsoft.common.pagination.PageObject;

@Controller
public class PubController {
	@Autowired
	PubService pubServ;
	
	@RequestMapping("doc/index.action")
	public String docIndex(){
		return "doc/doc-list";
	}
	
	@RequestMapping("doc/docList.action")
	@ResponseBody
	public PageObject docList(String keyText,Integer pageNumber,Integer pageSize){
		if (pageSize == null) {
			pageSize = 15;
		}

		if (pageNumber == null) {
			pageNumber = 1;
		}
		
		return pubServ.docList(keyText, pageNumber, pageSize);
	}
	
	@RequestMapping("docView.action")
	public String docView(Model model, HttpServletRequest req,
			HttpServletResponse res){
		String swfPath = req.getParameter("swfPath");
		if(!StringUtils.isEmpty(swfPath)){
			model.addAttribute("swfPath", swfPath);
		}
		else{
			model.addAttribute("swfPath", "upload/test.swf");
		}
		return "doc/documentView";
	}
	
	@RequestMapping("doc/myeditor.action")
	public String myeditor(){
		return "doc/myeditor";
	}
}
