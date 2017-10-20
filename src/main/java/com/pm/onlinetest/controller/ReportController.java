package com.pm.onlinetest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pm.onlinetest.domain.Assignment;
import com.pm.onlinetest.domain.Choice;
import com.pm.onlinetest.domain.Subcategory;
import com.pm.onlinetest.domain.Test;
import com.pm.onlinetest.export.AssignmentResultExport;
import com.pm.onlinetest.service.AssignmentService;
import com.pm.onlinetest.service.ChoiceService;
import com.pm.onlinetest.service.GradeService;
import com.pm.onlinetest.service.SearchService;

@Controller
public class ReportController {
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	SearchService searchService;

	@Autowired
	AssignmentService assignmentService;

	@Autowired
	GradeService gradeService;

	@Autowired
	ChoiceService choiceService;

	@RequestMapping(value = { "/coach/result/{id}", "/admin/result/{id}" }, method = RequestMethod.GET)
	public String testResult(@PathVariable("id") int id, Model model, HttpServletRequest request) {

		HashMap<Subcategory, String> report = new HashMap<>();

		Set<Subcategory> subcats = new HashSet<>();
		Assignment assignment = assignmentService.findById(id);
		Set<Test> tests = assignment.getTests();

		for (Test test : tests) {
			subcats.add(test.getQuestion().getSubcategory());
		}

		int numberofQuestions = tests.size();
		int overAllTotal = 0;
		double overAllAverage = 0;
		for (Subcategory subcat : subcats) {

			int scorePerCategory = 0;
			int totalQuestionsPerCategory = 0;
			for (Test testQuestion : tests) {
				if (testQuestion.getQuestion().getSubcategory().equals(subcat)) {
					totalQuestionsPerCategory++;

					if (testQuestion.getAnswer() != null) {
						int choiceID = 0;
						for (Choice ch : testQuestion.getQuestion().getChoices()) {
							if (ch.getAnswer()) {
								choiceID = ch.getId();
								break;
							}
						}
						if (testQuestion.getAnswer() == choiceID) {
							scorePerCategory++;
							overAllTotal++;
						}
					}

				}
			}
			//
			report.put(subcat, scorePerCategory + " / " + totalQuestionsPerCategory + "  |   "
					+ scorePerCategory * 100 / totalQuestionsPerCategory + "%  ");
			overAllAverage = 0;
			totalQuestionsPerCategory = 0;
		}
		model.addAttribute("reports", report);
		model.addAttribute("total", overAllTotal);
		model.addAttribute("questions", numberofQuestions);
		model.addAttribute("studentAssignment", assignment);
		model.addAttribute("grade", gradeService.getGradeAsStringFromInteger(100 / numberofQuestions * overAllTotal));
		String mapping = request.getServletPath();
		String mappingIDRemoved = mapping.substring(0, mapping.length() - Integer.toString(id).length() - 1);
		return mappingIDRemoved;
	}

	@RequestMapping(value = { "/coach/resultDetail/{id}", "/admin/resultDetail/{id}" }, method = RequestMethod.GET)
	public String testResultDetail(@PathVariable("id") int id, Model model, HttpServletRequest request) {

		HashMap<Test, Boolean> reportDetail = new HashMap<>();
		Assignment assignment = assignmentService.findById(id);
		Set<Test> tests = assignment.getTests();
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
					reportDetail.put(testQuestion, answer);
				}
			}
			reportDetail.put(testQuestion, answer);
		}

		model.addAttribute("answers", reportDetail);
		model.addAttribute("student", assignmentService.findById(id).getStudentId());
		model.addAttribute("score", score + "/" + tests.size());
		double testPercent = 100 / tests.size() * score;
		model.addAttribute("percent", testPercent);
		model.addAttribute("assignemtId", id);

		String mapping = request.getServletPath();
		String mappingIDRemoved = mapping.substring(0, mapping.length() - Integer.toString(id).length() - 1);
		return mappingIDRemoved;
	}

	@RequestMapping(value = { "/coach/assignments", "/admin/assignments" }, method = RequestMethod.GET)
	public String assignmentDetail(Model model, HttpServletRequest request) {

		List<Assignment> assignments = assignmentService.findAll();
		model.addAttribute("assignments", assignments);

		String mapping = request.getServletPath();
		return mapping;
	}

	@RequestMapping(value = { "/coach/resultlist", "/admin/resultlist" }, method = RequestMethod.GET)
	public String resultList(Model model, HttpServletRequest request) {

		HashMap<Assignment, Long> reports = new HashMap<>();
		List<Assignment> finisedAssignmentList = new ArrayList<>();
		List<Assignment> assignments = assignmentService.findAll();
		// filter only the finished tests
		for (Assignment assign : assignments) {
			if (assign.isFinished() == true) {
				finisedAssignmentList.add(assign);
			}
		}

		int score = 0;
		for (Assignment assignment : finisedAssignmentList) {
			System.out.println(assignment.getStudentId());
			for (Test testQuestion : assignment.getTests()) {
				if (testQuestion.getAnswer() != null) {
					int choiceID = 0;
					for (Choice ch : testQuestion.getQuestion().getChoices()) {
						if (ch.getAnswer()) {
							choiceID = ch.getId();
							break;
						}
					}
					if (testQuestion.getAnswer() == choiceID) {
						score++;
					}
				}

			}
			long testPercent = 100 * score / assignment.getTests().size();
			System.out.println("----" + testPercent);
			reports.put(assignment, testPercent);
			model.addAttribute("reports", reports);
			score = 0;

		}
		String mapping = request.getServletPath();
		return mapping;
	}

	@RequestMapping(value = "/coach/feedback", method = RequestMethod.GET)
	public String giveFeedback(Model model) {
		return "feedback";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
	@RequestMapping(value = { "/assignments/export"}, method = RequestMethod.GET)
	public void exportAsExcel(HttpServletResponse response, @RequestParam("ids") List<Integer> ids) {
		List<Assignment> assignments = assignmentService.findByIds(ids);
		logger.debug("exportAsExcel() ids:  " + assignments);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
	@RequestMapping(value = { "/assignments/download"}, method = RequestMethod.GET)
	public void dowloadExcel(HttpServletResponse response, @RequestParam("ids") List<Integer> ids) {

		List<Assignment> assignments = assignmentService.findByIds(ids);
		logger.debug("exportAsExcel() ids:  " + ids);

		try {
			AssignmentResultExport resultExport = new AssignmentResultExport(assignments);
			// Create an output stream to stream data to the client.
			ServletOutputStream out = response.getOutputStream();
			response.setContentType("application/octet-stream");
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", AssignmentResultExport.FILE_NAME);
			response.setHeader(headerKey, headerValue);

			// POI has a write() method on a HSSFWorkbook object
			// that takes a ServletOutputStream as a parameter
			// and streams the contents to the client.
			resultExport.getWorkbook().write(out);

			out.flush();
			out.close();
		} catch (Exception ex) {
			logger.error("Can't not export file: " + ex.getMessage());
		}
	}
	
}