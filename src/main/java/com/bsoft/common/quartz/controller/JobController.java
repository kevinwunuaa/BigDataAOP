package com.bsoft.common.quartz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.common.context.EnvironmentContext;
import com.bsoft.common.pagination.PageObject;
import com.bsoft.common.quartz.QuartzJob;
import com.bsoft.common.quartz.QuartzJobList;
import com.bsoft.common.quartz.QuartzManager;
import com.bsoft.common.util.Dom4jImpl;
import com.bsoft.common.util.JaxbUtil;
import com.bsoft.common.util.StringTool;


@Controller
public class JobController {
	@RequestMapping("job/jobIndex.action")
	public String jobIndex(){
		return "job/job_list";
	}
	
	@RequestMapping("job/jobList.action")
	@ResponseBody
	public PageObject jobList(String name,Integer pageNumber,Integer pageSize){
		if (pageSize == null) {
			pageSize = 15;
		}

		if (pageNumber == null) {
			pageNumber = 1;
		}

		int startRow = (pageNumber - 1) * pageSize;
		int endRow = startRow + pageSize;

		List<QuartzJob> list = EnvironmentContext.jobList.getJobs();

		if (StringUtils.isNotEmpty(name)) {
			list = filter(name, list);
		}

		PageObject page = new PageObject();
		page.setTotal(list.size());
		page.setRows(list.subList(startRow, Math.min(endRow, list.size())));
		
		return page;
	}
	
	private List<QuartzJob> filter(String name, List<QuartzJob> list) {
		List<QuartzJob> list1 = new ArrayList<QuartzJob>();

		for (QuartzJob v : list) {
			if (v.getName().indexOf(name) >= 0) {
				list1.add(v);
			}
		}

		return list1;
	}
	
	@RequestMapping("job/saveJob.action")
	@ResponseBody
	public Map saveJob(QuartzJob job) {
		boolean exist = false;
		Map result = new HashMap();
		result.put("success", true);
		result.put("msg", "OK");
		
		QuartzJob tempJob = null;

		List<QuartzJob> list = EnvironmentContext.jobList.getJobs();
		for (QuartzJob v : list) {
			if (v.getId().equals(job.getId())) {
				tempJob = v;
				exist = true;
				break;
			}
		}

		if (exist) {
			BeanUtils.copyProperties(job, tempJob);
		}else{
			job.setId(StringTool.uuid());
			list.add(job);
		}
		try {
			saveJobList(EnvironmentContext.jobList);
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", "保存失败!");
			return result;
		}
		return result;
	}
	
	@RequestMapping("job/deleteJob.action")
	@ResponseBody
	public Map deleteJob(String id){
		boolean exist = false;
		Map result = new HashMap();
		result.put("success", true);
		result.put("msg", "OK");
		
		QuartzJob tempJob = null;

		List<QuartzJob> list = EnvironmentContext.jobList.getJobs();
		for (QuartzJob v : list) {
			if (v.getId().equals(id)) {
				tempJob = v;
				exist = true;
				break;
			}
		}

		if (exist) {
			list.remove(tempJob);
		}
		try {
			saveJobList(EnvironmentContext.jobList);
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", "保存失败!");
			return result;
		}
		return result;
	}

	private void saveJobList(QuartzJobList vl) throws Exception {
		Dom4jImpl dom = new Dom4jImpl();
		String xml = JaxbUtil.convertToXml(vl);
		Document doc = dom.stringToXmlDoc(xml);

		dom.saveDocument(
				doc,
				this.getClass()
						.getResource(EnvironmentContext.JOB_PATH)
						.getPath());
	}
	
	@RequestMapping("job/runJob.action")
	@ResponseBody
	public Map runJob(String id,String runStatus){
		Map result = new HashMap();
		result.put("success", true);
		result.put("msg", "OK");
		List<QuartzJob> jobs = EnvironmentContext.jobList.getJobs();
		
		QuartzJob job = null;
		
		for(QuartzJob j : jobs){
			if(j.getId().equals(id)){
				job = j;
				break;
			}
		}
		
		if(job != null){
			Map<String,Object> jobArgs = new HashMap<String,Object>();
			
			String prefix = job.getId() + "_" + job.getName();
			String jobName = prefix + "_job";
			String jobGroupName = prefix + "_jobgroup";
			String triggerName = prefix + "_trigger";
			String triggerGroupName = prefix + "_triggergroup";
			String jobClass = job.getJobClass();
			
			String jsonParam = job.getJsonParam();
			
			jobArgs.put("jsonParam", jsonParam);
			
			Class clazz = null;
			try {
				clazz = Class.forName(jobClass);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			job.setStatus(runStatus);
			
			if("1".equals(runStatus)){
				QuartzManager.addJob(jobName, jobGroupName, triggerName, triggerGroupName, clazz, job.getCronConfig(),jobArgs);
			}else{
				QuartzManager.removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
			}
		}
		return result;
	}
}
