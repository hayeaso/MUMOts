package com.pm.onlinetest.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pm.onlinetest.domain.Assignment;
import com.pm.onlinetest.domain.Category;
import com.pm.onlinetest.domain.Choice;
import com.pm.onlinetest.domain.Question;
import com.pm.onlinetest.domain.Subcategory;
import com.pm.onlinetest.domain.Test;
import com.pm.onlinetest.service.AssignmentService;
import com.pm.onlinetest.service.CategoryService;
import com.pm.onlinetest.service.QuestionService;
import com.pm.onlinetest.service.SubCategoryService;
import com.pm.onlinetest.service.TestService;

import helpers.CategorySelectDto;
import helpers.CurrentQuestion;

@Controller
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	QuestionService questionService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	TestService testService;

	@Autowired
	AssignmentService assignmentService;

	@Autowired
	SubCategoryService subCategoryService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String readme() {
		return "read";
	}

	@RequestMapping(value = "/readme", method = RequestMethod.GET)
	public String read() {
		return "read";
	}

	@RequestMapping(value = "/acess", method = RequestMethod.GET)
	public String showAccessPage() {
		return "access";
	}

	@RequestMapping(value = "/access", method = RequestMethod.POST)
	public String verifyAccess(@RequestParam("access_code") String accesscode, RedirectAttributes attr,
			HttpServletRequest request) {

		System.out.println(accesscode);

		Assignment assgnmentObj;

		// Check if Student has been assigned a test using the supplied Access
		// Code
		if ((assgnmentObj = assignmentService.getAssignment(accesscode)) != null) {
			// Add Assignment Object to Request attributes
			attr.addFlashAttribute("assignment", assgnmentObj);
			request.getSession().setAttribute("assignmentId", assgnmentObj.getId());
			// Check if Student has previously finished test
			if (assgnmentObj.isFinished()) {
				attr.addFlashAttribute("errormessage", "This test has been completed.");
				return "redirect:/test/acess";
			} else { // If Student has not previously finished,

				// Check if maximum number of attempts has not been exceeded
				if (assgnmentObj.getCount() < 3) {

					// Update Count attribute of Assignment object in database
					assgnmentObj.setCount(assgnmentObj.getCount() + 1);
					assignmentService.updateAccessCount(assgnmentObj);

					if (assgnmentObj.isStarted()) {// Check if Student has
													// started Test previously

						LocalDateTime currentDate = LocalDateTime.now();
						// Check if time is still remaining
						long minutes = ChronoUnit.MINUTES.between(assgnmentObj.getStart_date(), currentDate);
						System.out.println("minutes:" + minutes);
						if (minutes < 160) {

							// Authenticate Student and redirect to test page
							GrantedAuthority aut = new SimpleGrantedAuthority("ROLE_STUDENT");
							List<GrantedAuthority> roles = new ArrayList<>();
							roles.add(aut);
							Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
									assgnmentObj.getStudentId(), accesscode, roles);
							SecurityContextHolder.getContext().setAuthentication(authenticationToken);

							return "redirect:/test/test";
						} else {
							attr.addFlashAttribute("errormessage", "No more time remaining for this test.");
							return "redirect:/test";
						}
					} else
						// If Student has not previously started, show page to
						// select Categories
						return "redirect:/test/showcategories";

				} else {
					attr.addFlashAttribute("errormessage", "This test has expired. You tried more than 3 times.");
					return "redirect:/test/acess";
				}
			}

		}

		// throw error if access code isnot found
		attr.addFlashAttribute("errormessage", "Invalid Access Code");
		return "redirect:/test/acess";
	}

	// Access denied page mapping
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String test403(Model model) {

		model.addAttribute("errormessage", "You are not authorized to perform this operation.");
		return "test/403";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String showErrorPage(Model model) {

		return "test/error";
	}

	@RequestMapping(value = "/showcategories", method = RequestMethod.GET)
	public String selectCategoriesView(Model model, HttpServletRequest request, RedirectAttributes attr) {

		// Assignment obj = (Assignment) request.getAttribute("assignment");
		//
		// if (obj == null) {
		// attr.addFlashAttribute("errormessage", "Invalid Operation");
		// return "redirect:/test/error";
		// }

		CategorySelectDto dto = new CategorySelectDto();
		// List<Subcategory> subcategories = new ArrayList<>();
		List<Category> categories = categoryService.findAllEnabled();

		for (Category cat : categories) {
			for (Subcategory subCat : cat.getSubcategories()) {
				// System.out.println("in");
				if (subCat.isEnabled() && questionService.findBySubcategory(subCat).size() < 20) {
					if (cat.getSubcategories().contains(subCat)) {
						//System.out.println("I am removing " + subCat);
						subCat.setEnabled(false);

						//System.out.println(subCat.getName());
					}
				}
			}
		}
		dto.setCategories(categories);
		
		// dto.setCategories(categoryService.findAllEnabled());

		model.addAttribute("categoryDto", dto);
		// model.addAttribute("subs", subcategories);
		return "categoryselect";
	}

	@RequestMapping(value = "/setcategories", method = RequestMethod.POST)
	public String setCategories(@ModelAttribute("categoryDto") CategorySelectDto dto, BindingResult resultDto,
			HttpServletRequest request) {

		Integer assignmentId = Integer.parseInt(request.getSession().getAttribute("assignmentId").toString());
		Assignment assignment = assignmentService.findById(assignmentId);

		List<Test> existingTest = testService.findByAssignment(assignment);
		System.out.println("ExistingTest: " + existingTest.size());
		if (existingTest.size() == 0) {
			List<Integer> subcategories = dto.getSelectedSubCategories();

			Subcategory subcategory = null;
			// Integer totalQuestions = 80;
			Random rand = new Random();
			for (Integer subcat_id : subcategories) {
				subcategory = subCategoryService.findOne(subcat_id);

				List<Question> subcategoryQuestions = questionService.findBySubcategory(subcategory);
				// for (int i = 0; i < totalQuestions / subcategories.size();
				// i++) {
				for (int i = 0; i < 20; i++) {

					int index = 0;
					if (subcategoryQuestions.size() > 0) {
						index = rand.nextInt(subcategoryQuestions.size());
					}

					Test test = new Test();
					test.setQuestion(subcategoryQuestions.remove(index));
					test.setAssignment(assignment);
					testService.save(test);
				}
			}
		}

		return "redirect:/test/test";

	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model, HttpServletRequest request) {
		Integer assignmentId = Integer.parseInt(request.getSession().getAttribute("assignmentId").toString());
		Assignment assignment = assignmentService.findById(assignmentId);
		assignment.setStart_date(LocalDateTime.now());
		assignment.setStarted(true);
		assignmentService.saveAssignment(assignment);
		List<Test> tests = new ArrayList<Test>();

		tests = testService.findByAssignment(assignment);
		request.getSession().setAttribute("tests", tests);
		model.addAttribute("test", tests.get(0));
		model.addAttribute("indexCount", tests.get(0).getId());
		model.addAttribute("assignment", assignment);
		model.addAttribute("totalTestCount", tests.size());
		request.getSession().setAttribute("min", 120);
		request.getSession().setAttribute("sec", 00);
		return "test";
	}

	@RequestMapping(value = "/setAnswer", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject setAnswer(HttpServletRequest request, @RequestBody CurrentQuestion jsonString) {

		CurrentQuestion currentQuestion = jsonString;
		List<Test> tests = (List<Test>) request.getSession().getAttribute("tests");
		Test test = tests.get(currentQuestion.getQuestionNum());
		if (currentQuestion.getAnswer() == null) {
			test.setAnswer(null);
		} else {
			test.setAnswer(Integer.parseInt(currentQuestion.getAnswer()));
		}
		testService.save(test);
		Test nextTest = testService.findOne(tests.get(currentQuestion.getNewQuestionNum()).getId());

		JSONObject obj = new JSONObject();
		obj.put("description", nextTest.getQuestion().getDescription());
		int i = 1;
		for (Choice ch : nextTest.getQuestion().getChoices()) {
			obj.put("ch" + i, ch.getDescription());
			obj.put("ch" + i + "_id", ch.getId());
			i++;
		}
		obj.put("answer", nextTest.getAnswer());
		return obj;
	}

	@RequestMapping(value = "/finishTest", method = RequestMethod.POST)
	@ResponseBody
	public void finishTest(HttpServletRequest request, @RequestBody CurrentQuestion jsonString) {

		CurrentQuestion currentQuestion = jsonString;
		List<Test> tests = (List<Test>) request.getSession().getAttribute("tests");
		Test test = tests.get(currentQuestion.getQuestionNum());
		if (currentQuestion.getAnswer() == null) {
			test.setAnswer(null);
		} else {
			test.setAnswer(Integer.parseInt(currentQuestion.getAnswer()));
		}
		testService.save(test);

		Integer assignmentId = Integer.parseInt(request.getSession().getAttribute("assignmentId").toString());
		Assignment assignment = assignmentService.findById(assignmentId);
		assignment.setFinished(true);
		assignment.setEnd_date(LocalDateTime.now());
		assignmentService.saveAssignment(assignment);
	}

	@RequestMapping(value = "/completed", method = RequestMethod.GET)
	public String aftercompletion() {
		return "completed";
	}

}