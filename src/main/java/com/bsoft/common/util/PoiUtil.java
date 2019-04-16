package com.bsoft.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * excel导出数据统一方法.
 * 
 * @author Wuyong
 * 
 */
public class PoiUtil {
	private static Logger logger = Logger.getLogger(PoiUtil.class);
	private final static String xls = "xls";
	private final static String xlsx = "xlsx";

	/**
	 * 创建工作薄.
	 * 
	 * @param sheetName
	 *            表名
	 * @param title
	 *            标题
	 * @param headTitle
	 *            列头
	 * @param columnIndex
	 *            数据中对应的列名
	 * @param data
	 *            导出的数据
	 * @return {@link HSSFWorkbook}
	 */
	public static HSSFWorkbook createWorkBook(String sheetName, String title,
			String[] headTitle, String[] columnIndex,
			List<Map<String, Object>> data) {
		// 创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row1.createCell(0);
		// 设置单元格内容
		cell.setCellValue(title);
		int cols = headTitle.length;
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, cols - 1));
		// 在sheet里创建第二行,列头
		HSSFRow row2 = sheet.createRow(1);
		// 创建单元格并设置单元格内容
		for (int i = 0; i < cols; i++) {
			row2.createCell(i).setCellValue(headTitle[i]);
		}

		int row = 2;
		// 在sheet填充数据
		for (Map<String, Object> m : data) {
			HSSFRow row3 = sheet.createRow(row++);
			for (int k = 0; k < cols; k++) {
				String key = columnIndex[k];
				String value = m.get(key) == null ? "" : m.get(key).toString();
				row3.createCell(k).setCellValue(value);
			}
		}
		return wb;
	}

	/**
	 * 读入excel文件，解析后返回
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static List<String[]> readExcel(MultipartFile file) throws IOException {
		// 检查文件
		// 判断文件是否存在
		if (null == file || file.isEmpty()) {
			logger.error("文件不存在！");
			throw new FileNotFoundException("文件不存在！");
		}
		// 获得文件名
		String fileName = file.getOriginalFilename();
		// 判断文件是否是excel文件
		if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
			logger.error(fileName + "不是excel文件");
			throw new IOException(fileName + "不是excel文件");
		}
		// 获得Workbook工作薄对象
		Workbook workbook = getWorkBook(file);
		// 创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
		List<String[]> list = new ArrayList<String[]>();
		if (workbook != null) {
			for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
				// 获得当前sheet工作表
				Sheet sheet = workbook.getSheetAt(sheetNum);
				if (sheet == null) {
					continue;
				}
				// 获得当前sheet的开始行
				int firstRowNum = sheet.getFirstRowNum();
				// 获得当前sheet的结束行
				int lastRowNum = sheet.getLastRowNum();
				// 循环除了第一行的所有行
				for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
					// 获得当前行
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					// 获得当前行的开始列
					int firstCellNum = row.getFirstCellNum();
					// 获得当前行的列数
					int lastCellNum = row.getPhysicalNumberOfCells();
					String[] cells = new String[row.getPhysicalNumberOfCells()];
					// 循环当前行
					for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
						Cell cell = row.getCell(cellNum);
						cells[cellNum] = getCellValue(cell);
					}
					list.add(cells);
				}
			}
			workbook.close();
		}
		return list;
	}

	public static Workbook getWorkBook(MultipartFile file) {
		// 创建Workbook工作薄对象，表示整个excel
		Workbook workbook = null;
		InputStream is =  null;
		try {
			is = file.getInputStream();
			workbook = new XSSFWorkbook(is);
			
		} catch (IOException e) {
			try {
				workbook = new HSSFWorkbook(is); 
			} catch (IOException e1) {
				System.err.println(e1.getMessage());
			}
		}
		return workbook;
	}

	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// 把数字当成String来读，避免出现1读成1.0的情况
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
		// 判断数据的类型
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: // 数字
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING: // 字符串
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN: // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA: // 公式
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_BLANK: // 空值
			cellValue = "";
			break;
		case Cell.CELL_TYPE_ERROR: // 故障
			cellValue = "非法字符";
			break;
		default:
			cellValue = "未知类型";
			break;
		}
		return cellValue;
	}

	public static void main(String[] args) throws Exception {
		String path = "D:\\Documents\\Downloads\\dg1.xlsx";
		//List<String[]> list = readExcel(new File(path));
		//System.out.println(list);
	}

}
