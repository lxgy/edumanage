package com.seehope.util.poi;

import com.seehope.entity.Evaluation;
import com.seehope.entity.Stage_content;
import com.seehope.entity.Stage_content_student;
import com.seehope.entity.vo.StudentStageVo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ExcelPoiUtil {
	public static <T> List<T> excelToObjects(String path, String templatePath, Class<T> clazz) throws Exception {
		List<T> list = null;
		if (path.endsWith(".xlsx")) {
			list = readXlsx(path, templatePath, clazz);
		} else if (path.endsWith(".xls")) {
			list = readXls(path, templatePath, clazz);
		}
		return list;
	}

	private static <T> List<T> readXls(String path, String templatePath, Class<T> clazz) throws Exception {
		List<T> list = new ArrayList();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(path));
		HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
		if (sheet == null) {
			return null;
		}
		SAXReader reader = new SAXReader();
		Document document = reader.read(templatePath);
		Element root = document.getRootElement();
		List<Element> ths = root.element("thead").elements("th");
		for (int i = 2; i < sheet.getLastRowNum(); i++) {
			HSSFRow row = sheet.getRow(i);
			T t = clazz.newInstance();
			for (int j = 0; j < ths.size(); j++) {
				String field = ((Element) ths.get(j)).attributeValue("field");
				if ((field != null) && (!"".equals(field))) {
					String methodName = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);

					HSSFCell cell = row.getCell(j);
					if (cell != null) {
						Method m = clazz.getMethod(methodName, new Class[] { String.class });
						m.invoke(t, new Object[] { getCellValue(cell) });
					}
				}
			}
			list.add(t);
		}
		return list;
	}

	private static <T> List<T> readXlsx(String path, String templatePath, Class<T> clazz) throws Exception {
		List<T> list = new ArrayList();

		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(path));
		XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
		if (sheet == null) {
			return null;
		}
		SAXReader reader = new SAXReader();
		Document document = reader.read(templatePath);
		Element root = document.getRootElement();
		List<Element> ths = root.element("thead").elements("th");
		for (int i = 2; i < sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			if (row != null) {
				T t = clazz.newInstance();
				for (int j = 0; j < ths.size(); j++) {
					String field = ((Element) ths.get(j)).attributeValue("field");
					if ((field != null) && (!"".equals(field))) {
						String methodName = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);

						XSSFCell cell = row.getCell(j);
						if (cell != null) {
							Method m = clazz.getMethod(methodName, new Class[] { String.class });
							m.invoke(t, new Object[] { getCellValue(cell) });
						}
					}
				}
				list.add(t);
			}
		}
		return list;
	}

	private static String getCellValue(Cell cell) {
		String val = "";
		switch (cell.getCellType()) {
		case 0:
			if (DateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				val = fmt.format(cell.getDateCellValue());
			} else {
				DecimalFormat df = new DecimalFormat("0");
				val = df.format(cell.getNumericCellValue());
			}
			break;
		case 1:
			val = cell.getStringCellValue();
			break;
		case 4:
			val = String.valueOf(cell.getBooleanCellValue());
			break;
		case 3:
			val = cell.getStringCellValue();
			break;
		case 5:
			val = "����";
			break;
		case 2:
			try {
				val = String.valueOf(cell.getStringCellValue());
			} catch (IllegalStateException e) {
				val = String.valueOf(cell.getNumericCellValue());
			}
		default:
			val = cell.getRichStringCellValue() == null ? null : cell.getRichStringCellValue().toString();
		}
		return val.toString();
	}

	public static <T> String objectsToExcel(String createPath, String templatePath, List<T> list, Class<T> clazz)
			throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(templatePath);
		Element root = document.getRootElement();

		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		XSSFSheet sheet = xssfWorkbook.createSheet();

		int rownum = 0;

		List<Element> colgroup = root.element("colgroup").elements("col");
		setColumnWidth(sheet, colgroup);

		Element title = root.element("title");
		if (title != null) {
			CellStyle title_style = xssfWorkbook.createCellStyle();
			title_style.setAlignment((short) 2);
			Font font = xssfWorkbook.createFont();
			font.setBoldweight((short) 700);
			title_style.setFont(font);

			title_style.setBorderBottom((short) 1);
			title_style.setBorderLeft((short) 1);
			title_style.setBorderTop((short) 1);
			title_style.setBorderRight((short) 1);

			Row row = sheet.createRow(rownum);
			Cell cell = row.createCell(0);
			Attribute rowspan = title.attribute("rowspan");
			Attribute colspan = title.attribute("colspan");
			Attribute value = title.attribute("value");
			if (value != null) {
				cell.setCellValue(value.getValue());
				cell.setCellStyle(title_style);

				int lastrow = Integer.parseInt(rowspan.getValue()) - 1 + rownum;
				int lastcol = Integer.parseInt(colspan.getValue());
				sheet.addMergedRegion(new CellRangeAddress(lastrow, lastrow, 0, lastcol));
			}
			rownum++;
		}
		Element thead = root.element("thead");
		List<Element> ths = thead.elements("th");
		Row row = sheet.createRow(rownum);
		CellStyle thead_style = xssfWorkbook.createCellStyle();
		Font font = xssfWorkbook.createFont();
		font.setBoldweight((short) 700);
		thead_style.setFont(font);
		thead_style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		thead_style.setFillPattern((short) 1);

		thead_style.setBorderBottom((short) 1);
		thead_style.setBorderLeft((short) 1);
		thead_style.setBorderTop((short) 1);
		thead_style.setBorderRight((short) 1);
		Cell cell = row.createCell(0);
		cell.setCellValue("����");
		cell.setCellStyle(thead_style);
		for (int colnum = 0; colnum < ths.size(); colnum++) {
			cell = row.createCell(colnum + 1);
			Attribute value = ((Element) ths.get(colnum)).attribute("value");
			if (value != null) {
				cell.setCellValue(value.getValue());
				cell.setCellStyle(thead_style);
			}
		}
		rownum++;
		if (list != null) {
			CellStyle tbody_style = xssfWorkbook.createCellStyle();
			tbody_style.setBorderBottom((short) 1);
			tbody_style.setBorderLeft((short) 1);
			tbody_style.setBorderTop((short) 1);
			tbody_style.setBorderRight((short) 1);
			for (int num = 0; num < list.size(); num++) {
				T t = list.get(num);
				row = sheet.createRow(rownum);
				cell = row.createCell(0);
				cell.setCellType(1);
				cell.setCellStyle(tbody_style);
				cell.setCellValue(num + 1);
				for (int colnum = 0; colnum < ths.size(); colnum++) {
					cell = row.createCell(colnum + 1);
					Attribute field = ((Element) ths.get(colnum)).attribute("field");
					if (field != null) {
						Class<?> class1 = clazz;
						Object obj = t;
						String name = field.getValue();
						String[] fieldNames = name.split("\\.");
						for (int i = 0; i < fieldNames.length; i++) {
							String methodName = "get" + fieldNames[i].substring(0, 1).toUpperCase()
									+ fieldNames[i].substring(1);
							Method m = class1.getMethod(methodName, new Class[0]);
							obj = m.invoke(obj, new Object[0]);
							if (obj != null) {
								class1 = obj.getClass();
							} else {
								obj = "";
								break;
							}
						}
						cell.setCellType(1);
						cell.setCellValue(obj.toString());
					}
					cell.setCellStyle(tbody_style);
				}
				rownum++;
			}
		}
		String fileName = UUID.randomUUID().toString() + ".xlsx";
		xssfWorkbook.write(new FileOutputStream(createPath + fileName));
		return fileName;
	}

	private static void setColumnWidth(Sheet sheet, List<Element> colgroup) {
		for (int i = 0; i < colgroup.size(); i++) {
			Attribute width = ((Element) colgroup.get(i)).attribute("width");
			int v = 0;
			if (width != null) {
				String unit = width.getValue().replaceAll("[0-9,\\.]", "");
				String value = width.getValue().replaceAll(unit, "");
				if ((unit == "") || ("px".equals(unit))) {
					v = Math.round(Float.parseFloat(value) * 37.0F);
				} else if ("em".equals(unit)) {
					v = Math.round(Float.parseFloat(value) * 267.5F);
				}
			} else {
				v = Math.round(Float.parseFloat("17") * 267.5F);
			}
			sheet.setColumnWidth(i, v);
		}
	}

	public static String StudentStageToExcel(String createPath, List<List<StudentStageVo>> lists,
			List<Evaluation> evaluations) throws Exception {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();

		CellStyle thead_style = xssfWorkbook.createCellStyle();
		Font font = xssfWorkbook.createFont();
		font.setBoldweight((short) 700);
		thead_style.setFont(font);
		thead_style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		thead_style.setFillPattern((short) 1);

		thead_style.setBorderBottom((short) 1);
		thead_style.setBorderLeft((short) 1);
		thead_style.setBorderTop((short) 1);
		thead_style.setBorderRight((short) 1);

		CellStyle tbody_style = xssfWorkbook.createCellStyle();
		tbody_style.setBorderBottom((short) 1);
		tbody_style.setBorderLeft((short) 1);
		tbody_style.setBorderTop((short) 1);
		tbody_style.setBorderRight((short) 1);

		tbody_style.setVerticalAlignment((short) 1);

		XSSFSheet sheet1 = xssfWorkbook.createSheet("��������");
		StudentStageToExcelSheet(sheet1, (List) lists.get(0), (Evaluation) evaluations.get(0), thead_style, tbody_style,
				xssfWorkbook);
		XSSFSheet sheet2 = xssfWorkbook.createSheet("��������");
		StudentStageToExcelSheet(sheet2, (List) lists.get(1), (Evaluation) evaluations.get(1), thead_style, tbody_style,
				xssfWorkbook);
		XSSFSheet sheet3 = xssfWorkbook.createSheet("��������");
		StudentStageToExcelSheet(sheet3, (List) lists.get(2), (Evaluation) evaluations.get(2), thead_style, tbody_style,
				xssfWorkbook);

		String fileName = UUID.randomUUID().toString() + ".xlsx";
		xssfWorkbook.write(new FileOutputStream(createPath + fileName));
		return fileName;
	}

	public static void StudentStageToExcelSheet(Sheet sheet, List<StudentStageVo> list, Evaluation evaluation,
			CellStyle thead_style, CellStyle tbody_style, Workbook workbook) throws Exception {
		int rownum = 1;
		Cell cell = null;

		sheet.setColumnWidth(0, Math.round(Float.parseFloat("17") * 267.5F));
		sheet.setColumnWidth(1, Math.round(Float.parseFloat("17") * 267.5F));
		sheet.setColumnWidth(2, Math.round(Float.parseFloat("17") * 267.5F));
		sheet.setColumnWidth(3, Math.round(Float.parseFloat("17") * 267.5F));
		sheet.setColumnWidth(4, Math.round(Float.parseFloat("17") * 267.5F));
		sheet.setColumnWidth(5, Math.round(Float.parseFloat("17") * 267.5F));

		Row row = sheet.createRow(rownum);

		cell = row.createCell(0);
		cell.setCellValue("������");
		cell.setCellStyle(thead_style);
		cell = row.createCell(1);
		cell.setCellValue("����");
		cell.setCellStyle(thead_style);
		cell = row.createCell(2);
		cell.setCellValue("��������");
		cell.setCellStyle(thead_style);
		cell = row.createCell(3);
		cell.setCellValue("��������");
		cell.setCellStyle(thead_style);
		cell = row.createCell(4);
		cell.setCellValue("��������");
		cell.setCellStyle(thead_style);
		cell = row.createCell(5);
		cell.setCellValue("��������");
		cell.setCellStyle(thead_style);

		rownum++;
		if (list != null) {
			Iterator localIterator2;
			for (Iterator localIterator1 = list.iterator(); localIterator1.hasNext(); localIterator2.hasNext()) {
				StudentStageVo studentStageVo = (StudentStageVo) localIterator1.next();
				row = sheet.createRow(rownum);
				cell = row.createCell(0);
				cell.setCellStyle(tbody_style);
				cell.setCellType(1);
				cell.setCellValue(studentStageVo.getName());

				CellRangeAddress cra = new CellRangeAddress(rownum,
						rownum + studentStageVo.getStudent_evaluation().size() - 1, 0, 0);
				sheet.addMergedRegion(cra);

				RegionUtil.setBorderBottom(1, cra, sheet, workbook);
				RegionUtil.setBorderLeft(1, cra, sheet, workbook);
				RegionUtil.setBorderRight(1, cra, sheet, workbook);
				RegionUtil.setBorderTop(1, cra, sheet, workbook);

				localIterator2 = studentStageVo.getStudent_evaluation().iterator();
				continue;
			}
		}
		if (evaluation.getContent() == null) {
			evaluation.setContent("");
		}
		if (evaluation.getTeacher_evaluation() == null) {
			evaluation.setTeacher_evaluation("");
		}
		rownum++;
		row = sheet.createRow(rownum);
		cell = row.createCell(0);
		cell.setCellValue("��������");
		cell.setCellStyle(thead_style);

		cell = row.createCell(1);
		cell.setCellType(1);
		cell.setCellValue(evaluation.getContent());
		cell.setCellStyle(tbody_style);

		CellRangeAddress cra1 = new CellRangeAddress(rownum, rownum, 1, 5);
		sheet.addMergedRegion(cra1);

		RegionUtil.setBorderBottom(1, cra1, sheet, workbook);
		RegionUtil.setBorderLeft(1, cra1, sheet, workbook);
		RegionUtil.setBorderRight(1, cra1, sheet, workbook);
		RegionUtil.setBorderTop(1, cra1, sheet, workbook);

		rownum++;
		row = sheet.createRow(rownum);
		cell = row.createCell(0);
		cell.setCellValue("��������");
		cell.setCellStyle(thead_style);

		cell = row.createCell(1);
		cell.setCellType(1);
		cell.setCellValue(evaluation.getTeacher_evaluation());
		cell.setCellStyle(tbody_style);

		CellRangeAddress cra2 = new CellRangeAddress(rownum, rownum, 1, 5);
		sheet.addMergedRegion(cra2);

		RegionUtil.setBorderBottom(1, cra2, sheet, workbook);
		RegionUtil.setBorderLeft(1, cra2, sheet, workbook);
		RegionUtil.setBorderRight(1, cra2, sheet, workbook);
		RegionUtil.setBorderTop(1, cra2, sheet, workbook);
	}

	public static void main(String[] args) {
	}
}
