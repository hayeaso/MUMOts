package com.pm.onlinetest.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pm.onlinetest.domain.Category;
import com.pm.onlinetest.domain.Choice;
import com.pm.onlinetest.domain.DataLog;
import com.pm.onlinetest.domain.DataLogLines;
import com.pm.onlinetest.domain.Question;
import com.pm.onlinetest.domain.Student;
import com.pm.onlinetest.domain.Subcategory;
import com.pm.onlinetest.domain.User;
import com.pm.onlinetest.exception.DuplicateCategoryNameException;
import com.pm.onlinetest.exception.UserNotFoundException;
import com.pm.onlinetest.service.AuthorityService;
import com.pm.onlinetest.service.CategoryService;
import com.pm.onlinetest.service.ChoiceService;
import com.pm.onlinetest.service.DataLogService;
import com.pm.onlinetest.service.QuestionService;
import com.pm.onlinetest.service.StudentService;
import com.pm.onlinetest.service.SubCategoryService;
import com.pm.onlinetest.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	UserService userService;
	@Autowired
	AuthorityService authorityService;
	@Autowired
	StudentService studentService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	SubCategoryService subCategoryService;
	@Autowired
	QuestionService questionService;
	@Autowired
	ChoiceService choiceService;
	@Autowired
	DataLogService dataLogService;

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "admin-home";
	}

	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public String getUsers(Locale locale, Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "users";
	}

	@RequestMapping(value = { "/admin/students", "/coach/students" }, method = RequestMethod.GET)
	public String getStudents(Model model, HttpServletRequest request) {
		List<Student> students = studentService.findAll();
		model.addAttribute("students", students);
		String mapping = request.getServletPath();
		System.out.println(mapping);
		return mapping;
	}

	@RequestMapping(value = "/admin/register", method = RequestMethod.GET)
	public String register(@ModelAttribute("loginUser") User user) {
		return "register";
	}

	@RequestMapping(value = "/admin/register", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("loginUser") User user, BindingResult result,
			RedirectAttributes redirectAttr) {
		if (result.hasErrors()) {
			return "register";
		}

		if (null != userService.findByUsername(user.getUsername())) {
			redirectAttr.addFlashAttribute("error", "Error");
			redirectAttr.addFlashAttribute("model", user);

			return "redirect:/admin/register";
		} else {
			user.setEnabled(true);
			userService.save(user);

			redirectAttr.addFlashAttribute("success", "Success");
			redirectAttr.addFlashAttribute("titleMessage", "User Added");
			redirectAttr.addFlashAttribute("bodyMessage",
					"User " + user.getUsername() + " SuccessFully Added to the database");

			return "redirect:/admin/users";
		}

	}
	
	@RequestMapping(value = { "/admin/studentIdChecker", "/coach/studentIdChecker" })
	@ResponseBody public int studentIdChecker(@RequestParam ("studId") String id){		
		Student student = studentService.findByStudentId(id);
		return (student!=null ? 1 : 0); //0 for not exists, 1 for exists
	}
	

	@RequestMapping(value = { "/admin/registerStudent", "/coach/registerStudent" }, method = RequestMethod.GET)
	public String getStudent(@ModelAttribute("student") Student student, HttpServletRequest request) {
		String mapping = request.getServletPath();
		return mapping;
	}

	@RequestMapping(value = { "/admin/registerStudent", "/coach/registerStudent" }, method = RequestMethod.POST)
	public String registerStudent(@ModelAttribute("student") Student student, BindingResult result,
			RedirectAttributes redirectAttr, HttpServletRequest request) {
		String mapping = request.getServletPath();
		if (result.hasErrors()) {
			return mapping;
		}
		if (null != studentService.findByStudentId(student.getStudentId())) {
			redirectAttr.addFlashAttribute("error", "Error");
			redirectAttr.addFlashAttribute("model", student);
			return "redirect:" + mapping;
		} else {
			studentService.save(student);
			redirectAttr.addFlashAttribute("success", "Success");
			redirectAttr.addFlashAttribute("titleMessage", "Student Added");
			redirectAttr.addFlashAttribute("bodyMessage", "Student " + student.getFirstName() + " "
					+ student.getLastName() + " SuccessFully Added to the database");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String role = auth.getAuthorities().toString();
			if (role.equals("[ROLE_ADMIN]"))
				return "redirect:/admin/students";
			else
				return "redirect:/coach/students";
		}
	}

	@RequestMapping(value = { "/admin/editStudent/{id}", "/coach/editStudent/{id}" }, method = RequestMethod.GET)
	public String editStudent(@ModelAttribute("student") Student student, HttpServletRequest request, Model model,
			@PathVariable("id") int id) {
		model.addAttribute("student", studentService.findByUserId(id));
		String mapping = request.getServletPath();
		String mappingIDRemoved = mapping.substring(0, mapping.length() - Integer.toString(id).length() - 1);
		return mappingIDRemoved;
	}

	@RequestMapping(value = { "/admin/editStudent", "/coach/editStudent" }, method = RequestMethod.POST)
	public String editStudent(@ModelAttribute("student") Student student, BindingResult result,
			RedirectAttributes redirectAttr, HttpServletRequest request) {
		String mapping = request.getServletPath();
		if (result.hasErrors()) {
			return mapping;
		}

		if (null != studentService.findByStudentIdExceptThis(student.getStudentId(), student.getUserId())) {
			redirectAttr.addFlashAttribute("error", "Error");
		} else {
			studentService.save(student);

			redirectAttr.addFlashAttribute("success", "Success");
		}

		return "redirect:" + mapping + "/" + student.getUserId();
	}

	// @RequestMapping(value = {"/admin/editStudent", "/coach/editStudent"},
	// method = RequestMethod.POST)
	// public String editStudent(@ModelAttribute("student") Student student,
	// BindingResult result,
	// RedirectAttributes redirectAttr, HttpServletRequest request) {
	// String mapping = request.getServletPath();
	// if (result.hasErrors()) {
	// return mapping;
	// }
	// if(null != studentService.findByStudentId(student.getStudentId())){
	// redirectAttr.addFlashAttribute("error", "Error");
	// }else{
	// studentService.save(student);
	// redirectAttr.addFlashAttribute("success", "Success");
	// }
	// return "redirect:"+mapping;
	// }

	@RequestMapping(value = { "/admin/deleteUser", "/coach/deleteUser" }, method = RequestMethod.POST)
	public String DeleteUser(HttpServletRequest request, @RequestParam("userid") Integer userId)
			throws UserNotFoundException {
		User user = userService.findByUserId(userId);
		if (user == null) {
			logger.debug("user id is invalid");
			throw new UserNotFoundException("User id is not valid");
		}
		userService.updateStatus(userId, !user.isEnabled());
		return "admin/users";
	}

	// @RequestMapping(value = "/admin/assign", method = RequestMethod.GET)
	// public String assignCoach(Model model) {
	// List<Student> students = studentService.findAll();
	// List<User> coaches = userService.findByAuthority("ROLE_COACH");
	//
	// model.addAttribute("students", students);
	// model.addAttribute("coaches", coaches);
	//
	// return "assignCoach";
	// }

	@RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
	public String getCategory(Model model) {
		List<Category> categories = categoryService.findAllEnabled();
		model.addAttribute("categories", categories);
		return "categories";
	}

	@RequestMapping(value = "/admin/createCategory", method = RequestMethod.GET)
	public String createCategory(@ModelAttribute("Category") Category category) {
		return "createCategory";
	}

	@RequestMapping(value = "/admin/createCategory", method = RequestMethod.POST)
	public String createCategoryPost(@ModelAttribute("Category") Category category, BindingResult result,
			RedirectAttributes redirectAttr) {

		if (result.hasErrors()) {
			return "createCategory";
		}

		try {
			categoryService.save(category);
			redirectAttr.addFlashAttribute("success", "Successfully added new category!");
			redirectAttr.addFlashAttribute("titleMessage", "CATEGORY ADDED!");
			redirectAttr.addFlashAttribute("bodyMessage", "Successfully added new category " + category.getName());
		} catch (DuplicateCategoryNameException ex) {			
			redirectAttr.addFlashAttribute("alertErrorMsg", "[" + category.getName() + "]: " + ex.getMessage());			
			return "redirect:/admin/createCategory";
		}

		return "redirect:/admin/categories";
	}

	@RequestMapping(value = "/admin/subCategories", method = RequestMethod.GET)
	public String getSubCategory(Model model) {
		List<Subcategory> subCategories = subCategoryService.findAllEnabled();
		model.addAttribute("subCategories", subCategories);
		return "subCategories";
	}

	@RequestMapping(value = { "/admin/deleteCategory" }, method = RequestMethod.POST)
	public void DeleteCategory(HttpServletRequest request) {
		String id = request.getParameter("id").toString();
		categoryService.softDelete(Integer.parseInt(id));
	}

	@RequestMapping(value = "/admin/createSubCategory", method = RequestMethod.GET)
	public String createSubCategory(@ModelAttribute("Subcategory") Subcategory subcategory, Model model) {
		List<Category> categories = categoryService.findAllEnabled();
		model.addAttribute("categories", categories);
		return "createSubCategory";
	}

	@RequestMapping(value = "/admin/createSubCategory", method = RequestMethod.POST)
	public String createSubCategoryPost(@ModelAttribute("Subcategory") Subcategory subcategory, BindingResult result,
			RedirectAttributes redirectAttr) {
		if (result.hasErrors()) {
			return "createSubCategory";
		}

		subcategory.setCategory(categoryService.findOne(subcategory.getCategoryId()));
		subCategoryService.save(subcategory);
		redirectAttr.addFlashAttribute("success", "Successfully added new Subcategory!");
		redirectAttr.addFlashAttribute("titleMessage", "SUB CATEGORY ADDED!");
		redirectAttr.addFlashAttribute("bodyMessage", "Successfully added new Subcategory " + subcategory.getName());
		return "redirect:/admin/subCategories";
	}

	@RequestMapping(value = { "/admin/deleteSubCategory" }, method = RequestMethod.POST)
	public void DeleteSubCategory(HttpServletRequest request) {
		String id = request.getParameter("id").toString();
		subCategoryService.softDelete(Integer.parseInt(id));
	}

	@RequestMapping(value = { "/admin/importStudentData" }, method = RequestMethod.GET)
	public String importStudents(HttpServletRequest request) {
		System.out.println("tttttttttt");
		String mapping = request.getServletPath();
		System.out.println(mapping);
		return mapping;
	}

	@RequestMapping(value = { "/admin/importStudentData" }, method = RequestMethod.POST)
	public String importStudents(Model model, @RequestParam("ExcelFile") MultipartFile excelfile,
			RedirectAttributes redirectAttr, HttpServletRequest request) {

		String mapping = request.getServletPath();
		String log = "";
		int rows = 0;
		int insertedRows = 0;
		// String toadd = "";
		DataLog dl = new DataLog();
		List<DataLogLines> ll = new ArrayList<>();
		
		try {
			// Using XSSF for xlsx format, for xls use HSSF
			Workbook workbook = new XSSFWorkbook(excelfile.getInputStream());
			int numberOfSheets = workbook.getNumberOfSheets();
			// looping over each workbook sheet
			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rowIterator = sheet.iterator();

				// iterating over each row
				if (rowIterator.hasNext()) {
					rowIterator.next();
				}
				while (rowIterator.hasNext()) {

					rows++;

					Student student = new Student();
					Row row = rowIterator.next();
					Boolean validRow= true;
					for (int j = 0; j < 6; j++) {
						if (row.getCell(j).toString().isEmpty()) {
							validRow=false;
						}
					}
					
					
					if (studentService.findByStudentId(row.getCell(0).toString()) == null && validRow==true) {
						student.setStudentId(row.getCell(0).toString());
						student.setFirstName(row.getCell(1).toString());
						student.setLastName(row.getCell(2).toString());
						student.setEmail(row.getCell(3).toString());
						student.setEntry(row.getCell(4).toString());
						if (row.getCell(5).toString().equalsIgnoreCase("Active")) {
							student.setEnabled(true);
						} else {
							student.setEnabled(false);
						}
						studentService.save(student);
						insertedRows++;
					} else {
						String text = "The Student Entry at row number " + row.getRowNum()
								+ " was not inserted :--> ID: " + row.getCell(0).toString() + "\n";
						DataLogLines dll=new DataLogLines();
						dll.setContent(text);
						System.out.println(dll.getContent());
						ll.add(dll);
						continue;
					}
				}
				//log += ("\t  \t  \t " + insertedRows + " rows added out of " + rows + "\n\n\n\n");
				workbook.close();
			}
			log += ("\t  \t  \t " + insertedRows + " row(s) added out of  " + rows + " \n\n\n\n");
			

			excelfile.getInputStream().close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// log+=(insertedRows + " rows added out of " + rows + "\n");
		// log+=toadd;

		// System.out.println("------------------------------------------------------");
		System.out.println(log);

		dl.setContent(log);
		Date date = new Date();
		System.out.println(ll);
		dl.setDate(date);
		dl.setType("Student");
		dl.setLines(ll);
		
		dataLogService.save(dl);
		
		//System.out.println(dl.getLines().toString());
		redirectAttr.addFlashAttribute("success", "Success");
		redirectAttr.addFlashAttribute("titleMessage", "Welcome to the Log for Students Insertion");
		redirectAttr.addFlashAttribute("bodyMessage", dl);
		redirectAttr.addFlashAttribute("lines", ll);

		return "redirect:" + mapping;

	}

	@RequestMapping(value = { "/admin/importData", "/coach/importData", "/dba/importData" }, method = RequestMethod.GET)
	public String importDataGet(HttpServletRequest request) {
		String mapping = request.getServletPath();
		return mapping;
	}

	@RequestMapping(value = { "/admin/importData", "/coach/importData",
			"/dba/importData" }, method = RequestMethod.POST)
	public String processExcel2007(Model model, @RequestParam("ExcelFile") MultipartFile excelfile,
			RedirectAttributes redirectAttr, HttpServletRequest request) {

		String mapping = request.getServletPath();
		String log = "";
		int rows = 0;
		int insertedRows = 0;
		// String toadd = "";
		DataLog dl = new DataLog();
		List<DataLogLines> ll = new ArrayList<>();
		try {

			// Using XSSF for xlsx format, for xls use HSSF
			Workbook workbook = new XSSFWorkbook(excelfile.getInputStream());

			int numberOfSheets = workbook.getNumberOfSheets();

			// looping over each workbook sheet
			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rowIterator = sheet.iterator();

				// iterating over each row
				while (rowIterator.hasNext()) {

					rows++;
					Question question = new Question();
					List<Choice> choices = new ArrayList<Choice>();
					Row row = rowIterator.next();

					// Set the Sub-Category
					Subcategory subC;
					// System.out.println(row.getCell(8).toString());
					if (!subCategoryService.findSubCategoryByName(row.getCell(9).toString()).isEmpty()) {

						// System.out.println(subCategoryService.findSubCategoryByName(row.getCell(8).toString()));
						List<Subcategory> subCs = subCategoryService.findSubCategoryByName(row.getCell(9).toString());
						
						subC = subCs.get(0);

						question.setSubcategory(subC);
						question.setDescription(row.getCell(0).toString());
						question.setCategory(row.getCell(8).toString());
						for (int j = 1; j < 7; j++) {
							Choice choice = new Choice();
							choice.setDescription(row.getCell(j).toString());
							choice.setQuestion(question);
							choices.add(choice);

						}
						String rightAnswer = row.getCell(7).toString();
						int rightAnswerIndex = getRightAnswerIndex(rightAnswer);
						if (rightAnswerIndex != -1) {
							choices.get(rightAnswerIndex).setAnswer(true);
						}
						questionService.save(question);
						choiceService.save(choices);
						insertedRows++;
					} else {
						String text = "The Question at row number " + row.getRowNum() + " was not inserted :-->"
								+ row.getCell(0).toString() + "\n";
						DataLogLines dll=new DataLogLines();
						dll.setContent(text);
						ll.add(dll);
						continue;
					}

				}
				// System.out.println("//////////////////////////////////////");
				// System.out.println(toadd);
				//log += ("\t  \t  \t " + insertedRows + " rows added out of " + rows + "\n\n\n\n");
				workbook.close();
			}
			log += ("\t  \t  \t " + insertedRows + " rows added out of " + rows + "\n\n\n\n");
			excelfile.getInputStream().close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// log+=(insertedRows + " rows added out of " + rows + "\n");
		// log+=toadd;

		// System.out.println("------------------------------------------------------");
		System.out.println(log);

		dl.setContent(log);
		Date date = new Date();
		System.out.println(date);
		dl.setDate(date);
		dl.setType("Question");
		dataLogService.save(dl);
		dl.setLines(ll);
		redirectAttr.addFlashAttribute("success", "Success");
		redirectAttr.addFlashAttribute("titleMessage", "Welcome to the Log for Questions Insertion");
		redirectAttr.addFlashAttribute("bodyMessage", dl);
		redirectAttr.addFlashAttribute("lines", ll);

		return "redirect:" + mapping;
	}
	
	@RequestMapping(value = { "/dba/subcategories/{catId}", "/coach/subcategories/{catId}",
			"/admin/subcategories/{catId}" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject setAnswer(HttpServletRequest request, @PathVariable("catId") Integer catId) {

		List<Subcategory> listSubCategory = new ArrayList<>();
		listSubCategory.addAll(subCategoryService.findByCategoryId(categoryService.findOne(catId)));

		JSONObject obj = new JSONObject();

		String str = "<select id='idSubCategory' path='subcategory.id' name='subcategory.id' class='form-control placeholder-no-fix'>";
		for (Subcategory sc : listSubCategory) {
			str += "<option value=" + sc.getId() + ">" + sc.getName() + "</option>";
		}
		str += "</select>";
		obj.put("subcat", str);
		return obj;
	}

	@RequestMapping(value = "/admin/list/category", method = RequestMethod.GET)
	public @ResponseBody List<String> getCategories() {
		// may need get all the categories
		return categoryService.findAllEnableCategoryNames();
	}

	// bind to edit button in users.jsp
	@RequestMapping(value = "/admin/editUser/{id}", method = RequestMethod.GET)
	public String editUser(@ModelAttribute("loginUser") User user, Model model, @PathVariable("id") int id) {
		// System.out.println("GET: enter into Edit User ");
		// System.out.println("userid=" + id);
		model.addAttribute("user", userService.findByUserId(id));// assign user
		return "editUser";
	}

	// bind to submit button in editUser.jsp
	@RequestMapping(value = "/admin/editUser", method = RequestMethod.POST)
	public String editUser(@Valid @ModelAttribute("loginUser") User user, BindingResult result,
			RedirectAttributes redirectAttr) {
		System.out.println("POST: enter into Edit User");
		if (result.hasErrors()) {
			return "editUser";
		}

		if (null != userService.findByUsernameExceptThis(user.getUsername(), user.getUserId())) {
			redirectAttr.addFlashAttribute("error", "Error");
		} else {
			user.setEnabled(true);
			userService.save(user);
			redirectAttr.addFlashAttribute("success", "Success");
			redirectAttr.addFlashAttribute("titleMessage", "User EDITED");
			redirectAttr.addFlashAttribute("bodyMessage", "User " + user.getUsername() + " successfully edited!");

			return "redirect:/admin/users";
		}
		return "redirect:/admin/editUser/" + user.getUserId();
	}

	private int getRightAnswerIndex(String rightAnswer) {
		int rightAnswerIndex = -1;
		switch (rightAnswer) {
		case "B":
			rightAnswerIndex = 0;
			break;
		case "C":
			rightAnswerIndex = 1;

			break;
		case "D":
			rightAnswerIndex = 2;

			break;
		case "E":
			rightAnswerIndex = 3;

			break;
		case "F":
			rightAnswerIndex = 4;

			break;
		case "G":
			rightAnswerIndex = 5;

			break;

		}
		return rightAnswerIndex;
	}
}
