package com.bdaop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ToolsController {

	@RequestMapping("tools/album.action")
	public String album(){
		return "tools/album";
	}
	
	@RequestMapping("tools/table.action")
	public String table(){
		return "tools/table";
	}
	
	@RequestMapping("tools/repo.action")
	public String repo(){
		return "tools/repo";
	}
}
