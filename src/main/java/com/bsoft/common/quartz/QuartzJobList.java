package com.bsoft.common.quartz;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "job-list")
public class QuartzJobList {
	@XmlElement(name = "job")
	private List<QuartzJob> jobs;

	public List<QuartzJob> getJobs() {
		return jobs;
	}

	public void setJobs(List<QuartzJob> jobs) {
		this.jobs = jobs;
	}

}
