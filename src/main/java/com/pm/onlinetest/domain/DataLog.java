package com.pm.onlinetest.domain;

import java.util.List;
import java.util.Date;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Limport javax.persistence.OneToMany;
taLog {
	@Id
    @GeneratedValue
    private	@GeneratedValue
	private Integer id;

String getType	private String content;
	private String type;

{
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Lob
	private String Content;
	
	private String type;

	public I	public List<DataLogLines> getLines() {
		return lines;
	}

	public void setLines(List<DataLogLines> lines) {
		this.lines = lines;
	}

	@OneToMany(mappedBy = "log", cascade=CascadeType.ALL)
	private List<DataLogLines> lines;

	public Integer getId() {
ger id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
