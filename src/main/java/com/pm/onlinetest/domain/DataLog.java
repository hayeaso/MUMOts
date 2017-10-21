package com.pm.onlinetest.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DataLog {
	@Id
	@GeneratedValue
	private Integer id;

	private Date date;
	private String content;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<DataLogLines> getLines() {
		return lines;
	}

	public void setLines(List<DataLogLines> lines) {
		this.lines = lines;
	}

	@OneToMany(mappedBy = "log", cascade=CascadeType.ALL)
	private List<DataLogLines> lines;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}