package com.pm.onlinetest.export;

import java.awt.Color;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pm.onlinetest.domain.Assignment;
import com.pm.onlinetest.domain.Choice;
import com.pm.onlinetest.domain.Test;

/** This class is use for generate Excel for Assignment result detail
 * 
 * @author kloem
 */
public class AssignmentResultExport {
	private static final Logger logger = LoggerFactory.getLogger(AssignmentResultExport.class);	
	public static final String FILE_NAME = "AssignmentList_Exported.xls";
	private static final String TITLE = "Self Assessment System - Student Test Result";
	private static final String FONT_NAME = "Calibri";
	HSSFWorkbook workbook; 

	private HSSFCellStyle titleStyle;
	private HSSFCellStyle rowHeaderStyle;
	private HSSFCellStyle columnHeaderStyle;
	private HSSFCellStyle dateCellStyle;
	private HSSFCellStyle textCellStyle;
	private HSSFCellStyle encodingCellStyle;
	private final short bodyNormal = HSSFColor.WHITE.index;
	private final short borderThin = HSSFCellStyle.BORDER_THIN;
	private int rowNumber = 0;

	private String fileID;
	List<Assignment> selectedAssignments = new ArrayList<Assignment>();

	public static enum COLUMNFIELDS {
		No, Qustion
	}

	public static final HashMap<COLUMNFIELDS, String> COLUMNHEADERSMAP = new HashMap<COLUMNFIELDS, String>();
	static {
		COLUMNHEADERSMAP.put(COLUMNFIELDS.No, "No");
		COLUMNHEADERSMAP.put(COLUMNFIELDS.Qustion, "Question");		
	}
	//public enum ChoiceLable {A, B, C, D, E };
	private String[] stuInfoArr = {"Student ID:", "Student Name:", "Entry:", "Score:", "Percent:", "Taken Date:"};
	private String[] choiceLabelArr = {"A. ", "B. ", "C. ", "D. ", "E. ", "F. "}; // current logic a question contains 5 choice
	
	// -- Constructor
	public AssignmentResultExport(List<Assignment> assignments) {		
		if (assignments.size() == 0)
			return;
		try {
			logger.debug("===========Start Building....======================");
			HSSFWorkbook workbook = buildExcelReport(assignments);						
			this.setWorkbook(workbook);
	        workbook.close();
	        logger.debug("===========Finish............======================");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("AssignmentResultExport() couldn't build excel file");
		}
	}

	/**
	 * Export Assignment Result Details into excel
	 */
	public HSSFWorkbook buildExcelReport(List<Assignment> assignments) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {			
			HSSFSheet sheet = workbook.createSheet("Result"); //Sheet name
			sheet.setZoom(3, 4); //75%
			setupCellStyles(workbook);
			
			this.buildTitleRow(workbook, sheet);
			for (Assignment assignment : assignments) {
				StudentResultInfo stuResult = new StudentResultInfo(assignment);
				buildStudentInfoRows(workbook, sheet, stuResult);
				if (assignment.getEnd_date() != null) {
					buildHeaderRow(workbook, sheet);
					buildResultDetailRow(workbook, sheet, stuResult);
				}
				this.buildBarRow(workbook, sheet);
			}						

			writeReportToFile(workbook);			
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("buildExcelReport() failed " + ex.getMessage());
		}
		return workbook;
	}
	
	/**
	 * This function create a row bar for separating student result
	 * @param workbook2
	 * @param sheet
	 */
	private void buildBarRow(HSSFWorkbook workbook2, HSSFSheet sheet) {
		HSSFRow barRow = sheet.createRow(this.rowNumber++);
		HSSFCell barCell = barRow.createCell(0);		
		HSSFCellStyle cellStyle = workbook2.createCellStyle();
		
		cellStyle.setFillForegroundColor(getIndexcolor("#C0C0C0", workbook2));
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);		
		barCell.setCellStyle(cellStyle);
		int colCount = AssignmentResultExport.COLUMNHEADERSMAP.size();
		sheet.addMergedRegion(new CellRangeAddress(this.rowNumber -1 , this.rowNumber - 1, 0, colCount-1));				
	}

	/**
	 * Define cell styles
	 * 
	 * @param workbook
	 */
	private void setupCellStyles(HSSFWorkbook workbook) {		
		//HSSFDataFormat dataFormat = workbook.createDataFormat();
		//final short dateFormat = dataFormat.getFormat("yyyy-MM-dd HH:mm:ss");
		// -- Title font style --
		HSSFFont titleFont = workbook.createFont();
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleFont.setFontHeightInPoints((short) 18);
		titleFont.setFontName(AssignmentResultExport.FONT_NAME);
		// -- Title cell style --
		titleStyle = workbook.createCellStyle();
		titleStyle.setFont(titleFont);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle = setStyleBorder(titleStyle, false);
		// -- Row header font style --
		HSSFFont rowHeaderFont = workbook.createFont();
		rowHeaderFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		rowHeaderFont.setFontHeightInPoints((short) 14);
		rowHeaderFont.setColor(HSSFColor.DARK_RED.index);
		rowHeaderFont.setFontName(AssignmentResultExport.FONT_NAME);
		// -- Row header cell style --
		rowHeaderStyle = workbook.createCellStyle();
		rowHeaderStyle.cloneStyleFrom(titleStyle);
		rowHeaderStyle.setFont(rowHeaderFont);
		// -- Column header font style --
		HSSFFont columnHeaderFont = workbook.createFont();
		columnHeaderFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		columnHeaderFont.setFontHeightInPoints((short) 14);
		columnHeaderFont.setFontName(AssignmentResultExport.FONT_NAME);
		// -- Row header cell style --
		columnHeaderStyle = workbook.createCellStyle();
		columnHeaderStyle.cloneStyleFrom(titleStyle);
		columnHeaderStyle.setFont(columnHeaderFont);
		columnHeaderStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		HSSFFont detailsFont = workbook.createFont();
		detailsFont.setFontHeightInPoints((short) 12);
		detailsFont.setFontName(AssignmentResultExport.FONT_NAME);
		// -- Text style --
		textCellStyle = workbook.createCellStyle();
		textCellStyle = setStyleBorder(textCellStyle, true);
		textCellStyle.setFont(detailsFont);
		// -- Date style --
		dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("MM/dd/yyyy HH:mm:ss"));
		// --Symbol Style
		HSSFFont symbolFont;
		symbolFont = workbook.createFont();
		symbolFont.setCharSet(HSSFFont.SYMBOL_CHARSET);		
		symbolFont.setFontName(AssignmentResultExport.FONT_NAME);
		encodingCellStyle = workbook.createCellStyle();
		encodingCellStyle.setFont(symbolFont);
		encodingCellStyle = setStyleBorder(encodingCellStyle, false);
	}

	/**
	 * First Row: Title
	 * 
	 * @param workbook
	 * @param sheet
	 */
	private void buildTitleRow(HSSFWorkbook workbook, HSSFSheet sheet) {
		HSSFRow titleRow = sheet.createRow(this.rowNumber++);
		HSSFCell titleCell = titleRow.createCell(0);
		titleCell.setCellValue(new HSSFRichTextString(AssignmentResultExport.TITLE));
		
		titleStyle.setFillForegroundColor(getIndexcolor("#FFCC00", workbook));
		titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		titleStyle.setWrapText(true);
		sheet.setColumnWidth(1, 7500);
		//sheet.autoSizeColumn(1, true);
		titleCell.setCellStyle(titleStyle);
		int colCount = AssignmentResultExport.COLUMNHEADERSMAP.size();		
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, colCount - 1));
		this.rowNumber = this.rowNumber + 2; //add a row separate from title (value here should be 3)
	}
	
	/**
	 * Create student information such ID, Name, Entry, Score
	 * @param workbook
	 * @param sheet
	 * @param assignments
	 */
	private void buildStudentInfoRows(HSSFWorkbook workbook, HSSFSheet sheet, StudentResultInfo stuResult) {
		int studInfoIndex = this.rowNumber;
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		
		//Student ID
		this.createStuInfoRow(sheet, studInfoIndex++, 0, this.stuInfoArr[0], stuResult.getStudent().getStudentId(), updateCellStyleColor(cellStyle, bodyNormal));
		//Student Name
		this.createStuInfoRow(sheet, studInfoIndex++, 0, this.stuInfoArr[1], stuResult.getFullName(), updateCellStyleColor(cellStyle, bodyNormal));
		//Entry
		this.createStuInfoRow(sheet, studInfoIndex++, 0, this.stuInfoArr[2], stuResult.getStudent().getEntry(), updateCellStyleColor(cellStyle, bodyNormal));
		//Score
		this.createStuInfoRow(sheet, studInfoIndex++, 0, this.stuInfoArr[3], stuResult.getScoreResult(), updateCellStyleColor(cellStyle, bodyNormal));
		//Percent
		this.createStuInfoRow(sheet, studInfoIndex++, 0, this.stuInfoArr[4], stuResult.getPercent() + "%", updateCellStyleColor(cellStyle, bodyNormal));					
		//Taken Date need to covert to Date for format
		if (stuResult.getTakenDate() != null) {
			ZonedDateTime zdt = stuResult.getTakenDate().atZone(ZoneId.systemDefault());
			Date takenDate = Date.from(zdt.toInstant());
			this.createStuInfoRow(sheet, studInfoIndex++, 0, this.stuInfoArr[5], takenDate,  updateCellStyleColor(dateCellStyle, bodyNormal));
		} else {
			this.createStuInfoRow(sheet, studInfoIndex++, 0, "Status:", "Not Complete", updateCellStyleColor(cellStyle, bodyNormal));
		}
		// -- Autofit column --
		for (int i = 0; i < this.stuInfoArr.length; i++) {
			sheet.autoSizeColumn((short) i, true);
		}
		this.rowNumber = studInfoIndex;
	}

	/**
	 * HEADER ROW
	 * 
	 * @param workbook
	 * @param sheet
	 */

	private void buildHeaderRow(HSSFWorkbook workbook, HSSFSheet sheet) {		
		int headerColIndex = 0;
		int rowIndex = this.rowNumber;
		HSSFRow headerRow = sheet.createRow(rowIndex++); // 1
		HSSFFont titleFont = workbook.createFont();
		titleFont.setFontName(AssignmentResultExport.FONT_NAME);
		for (AssignmentResultExport.COLUMNFIELDS columns : COLUMNFIELDS.values()) {
			String labelName = COLUMNHEADERSMAP.get(columns);
			HSSFCell headerCell = headerRow.createCell(headerColIndex);
			titleFont.setColor(getIndexcolor("#000084", workbook));
			rowHeaderStyle.setFillForegroundColor(getIndexcolor("#6d58a7", workbook));
			rowHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			//rowHeaderStyle.setWrapText(true);
			rowHeaderStyle.setFont(titleFont);
			headerCell.setCellStyle(rowHeaderStyle);
			headerCell.setCellValue(new HSSFRichTextString(labelName));
			headerColIndex++;
		}
		sheet.autoSizeColumn((short) 0, true);
		this.rowNumber = rowIndex;
	}

	/**Result details refers to resultdetail.jsp
	 *
	 * @param workbook
	 * @param sheet
	 */
	private void buildResultDetailRow(HSSFWorkbook workbook, HSSFSheet sheet, StudentResultInfo stuResult) {
		int rowIndex = this.rowNumber;		
		HSSFCellStyle cellStyle = workbook.createCellStyle();		
				
		int qNum = 1;
		for (Entry<Test, Boolean> answerEntry: stuResult.getReportDetail().entrySet()) {
			Test answer = answerEntry.getKey();
			
			HSSFRow entryRow = sheet.createRow(rowIndex);
			HSSFCell cell = entryRow.createCell(0);			
			HSSFCellStyle numStyle = workbook.createCellStyle();			

			String questionNum = String.valueOf(qNum++);		
			numStyle = qNumberMergeCellstyle(numStyle, false);
			cell.setCellStyle(numStyle);
			cell.setCellValue(new HSSFRichTextString(questionNum));	 
			//merge-- firstRow, lastRow, firstCol, lastCol //need to merge based on question choices			
			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex + answer.getQuestion().getChoices().size(), 0, 0));
			
			String questionDesc = (answerEntry.getValue() ? "✅ " : "❌ ") + answer.getQuestion().getDescription();
			createCell(entryRow, cell, 1, new HSSFRichTextString(questionDesc), updateCellStyleColor(this.encodingCellStyle, getIndexcolor("#FFFF66", workbook)));					
			
			rowIndex++;			
			//✅ correct &#x2705;
			//❌ wrong &#10060;
			int ind = 0;
			for (Choice choice : answer.getQuestion().getChoices()) {
				HSSFRow choiceRow = sheet.createRow(rowIndex++);
				HSSFCell cellChoice = choiceRow.createCell(0);
				HSSFCellStyle choiceStyle = workbook.createCellStyle();
				
				createCell(choiceRow, cell, 0, "", numStyle);				
				createCell(choiceRow, cellChoice, 1, this.choiceLabelArr[ind++] + choice.getDescription(), 
						updateAnswerCellStyleColor(choiceStyle, choiceAnswerColorIndex(choice, answer, workbook), workbook));
				
				choiceStyle = setStyleBorder(choiceStyle, false);
			}							
		}
		
		cellStyle = setStyleBorder(cellStyle, true);		
		
		// -- Autofit column --
		for (int i = 0; i < AssignmentResultExport.COLUMNHEADERSMAP.size(); i++) {
			sheet.autoSizeColumn((short) i, true);
		}
		this.rowNumber = rowIndex + 1;
		// sheet.createFreezePane(1, 2);
	}
	
	private HSSFCellStyle qNumberMergeCellstyle(HSSFCellStyle myCellStyle, boolean isWrap) {
		myCellStyle = setStyleBorder(myCellStyle, isWrap);
		myCellStyle.setFillForegroundColor(bodyNormal);
		myCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		myCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		myCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
		return myCellStyle;
	}
	
	private HSSFCellStyle setStyleBorder(HSSFCellStyle myCellStyle, boolean isWrap) {
		if (isWrap) {
			myCellStyle.setWrapText(true);
		}
		myCellStyle.setBorderBottom(borderThin);
		myCellStyle.setBorderLeft(borderThin);
		myCellStyle.setBorderRight(borderThin);
		myCellStyle.setBorderTop(borderThin);
		
		return myCellStyle;
	}

	/** 
	 * @param workbook
	 * @return current workbook
	 */
	public HSSFWorkbook writeReportToFile(HSSFWorkbook workbook) { //this may no longer need
		this.setFileID(AssignmentResultExport.FILE_NAME);
		return workbook;
	}
	
	public void setFileID(String fileID) {
		this.fileID = fileID;
	}

	public String getFileID() {
		return this.fileID;
	}

	private HSSFCell createCell(HSSFRow entryRow, HSSFCell cell, int cellnumber, String value, HSSFCellStyle cellStyle) {
		cell = entryRow.createCell(cellnumber);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(value);
		return cell;
	}
	
	private HSSFCell createCell(HSSFRow entryRow, HSSFCell cell, int cellnumber, HSSFRichTextString value, HSSFCellStyle cellStyle) {
		cell = entryRow.createCell(cellnumber);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(value);
		return cell;
	}
	
	private HSSFRow createStuInfoRow(HSSFSheet sheet, int studInfoIndex, int cellnumber, String label, Object value, HSSFCellStyle cellStyle) {
		HSSFRow entryRow = sheet.createRow(studInfoIndex); //studInfoIndex++
		HSSFCell cell = entryRow.createCell(0);
		createCell(entryRow, cell, cellnumber, label, cellStyle);	
		createCell(entryRow, cell, cellnumber + 1, value.toString(), cellStyle);	
		cellStyle.setWrapText(true);
		return entryRow;
	}
	
	private HSSFCellStyle updateCellStyleColor(HSSFCellStyle style, short indexColor) {
		style.setFillForegroundColor(indexColor);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return style;
	}

	private HSSFCellStyle updateAnswerCellStyleColor(HSSFCellStyle style, short indexColor, HSSFWorkbook workbook) {
		HSSFFont titleFont = workbook.createFont();
		titleFont.setColor(indexColor);
		style.setFont(titleFont);		
		return style;
	}

	private short getIndexcolor(String hexColorCode, HSSFWorkbook workbook) {
		short palIndex;		
		HSSFPalette palette = workbook.getCustomPalette();
		Color rgbColor = Color.decode(hexColorCode);
		HSSFColor myColor = palette.findSimilarColor(rgbColor.getRed(), rgbColor.getGreen(), rgbColor.getBlue());
		// get the palette index of that color
		palIndex = myColor.getIndex();

		return palIndex;
	}
	
	public HSSFWorkbook getWorkbook() {
		return this.workbook;
	}

	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	private short choiceAnswerColorIndex(Choice choice, Test test, HSSFWorkbook workbook) {
		//#e74c3c
		if (choice.getId() == test.getAnswer() && choice.getAnswer() == true) {			
			return getIndexcolor("#18bc9c", workbook); //green			
		} else if (choice.getId() == test.getAnswer() && choice.getAnswer() != true) {			
			return getIndexcolor("#e74c3c", workbook); //red			
		} else if (choice.getId() != test.getAnswer() && choice.getAnswer() == true) {			
			return getIndexcolor("#18bc9c", workbook); //green			
		}
		return getIndexcolor("#000000", workbook); //black
	}	
}
