package com.pm.onlinetest.export;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.pm.onlinetest.domain.Assignment;
import com.pm.onlinetest.domain.Choice;
import com.pm.onlinetest.domain.Student;
import com.pm.onlinetest.domain.Test;

/**
 * Helper class for Assignment Excel Export
 */
public class StudentResultInfo {
	private String scoreResult;
	private Map<Test, Boolean> reportDetail = new HashMap<>();
	private Student student;
	private Assignment assignment;	
	private double percent = 0.00;
	private LocalDateTime takenDate;

	public StudentResultInfo(Assignment assignment) {
		this.assignment = assignment;
		this.populateData();
	}

	public String getScoreResult() {
		return scoreResult;
	}

	public void setScoreResult(String scoreResult) {
		this.scoreResult = scoreResult;
	}

	public Map<Test, Boolean> getReportDetail() {
		return reportDetail;
	}

	public void setReportDetail(Map<Test, Boolean> reportDetail) {
		this.reportDetail = reportDetail;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}	

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public LocalDateTime getTakenDate() {
		return takenDate;
	}

	public void setTakenDate(LocalDateTime takenDate) {
		this.takenDate = takenDate;
	}

	private void populateData() {
		Set<Test> tests = this.assignment.getTests();
		int score = 0;
		for (Test testQuestion : tests) {
			boolean answer = false;
			int choiceID = 0;
			if (testQuestion.getAnswer() != null) {
				for (Choice ch : testQuestion.getQuestion().getChoices()) {
					if (ch.getAnswer()) {
						choiceID = ch.getId();
						break;
					}
				}
				if (testQuestion.getAnswer() == choiceID) {
					answer = true;
					score++;
					this.reportDetail.put(testQuestion, answer);
				}
			}
			this.reportDetail.put(testQuestion, answer);
		}

		this.setReportDetail(reportDetail);
		this.setStudent(assignment.getStudentId());
		this.setScoreResult(score + "/" + tests.size());
		double testPercent = tests.size() == 0 ? 0 : (100 / tests.size() * score);
		this.setPercent(testPercent);
		this.setTakenDate(assignment.getStart_date());
	}
	
	public String getFullName() {
		return this.student == null ? "" : this.student.getFirstName() +  " " + this.student.getLastName();
	}

}
