package com.bsoft.common.validator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "validator")
public class Validator {
	@XmlElement
	private String name; // 简称
	@XmlElement
	private String text; // 正式名称
	@XmlElement
	private String paramFormat; // 参数格式
	@XmlElement
	private String comments; // 备注

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getParamFormat() {
		return paramFormat;
	}

	public void setParamFormat(String paramFormat) {
		this.paramFormat = paramFormat;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
