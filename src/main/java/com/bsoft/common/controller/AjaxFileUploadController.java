package com.bsoft.common.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bsoft.common.util.DateUtil;
import com.bsoft.common.util.PropertyUtil;
import com.bsoft.common.util.StringTool;

/**
 * <h3 style="display:inline">功能:</h3>
 * <span>支持ajax文件上传的控制类，基于ajaxfileupload.js</span>
 * 
 * @author Wuyong
 * 
 */
@Controller
public class AjaxFileUploadController {
	/**
	 * <h3 style="display:inline">功能:</h3> <span>上传文件方法</span>. <br/>
	 * <h3 style="display:inline">用法:</h3>
	 * <dl>
	 * <dt>在页面中定义文件输入控件</dt>
	 * <dd>{@code <input type="file" id="myfile" name="myfile"/>}<font
	 * color=red>(注意：此处的name必须为myfile)</font></dd>
	 * <dt>在应用中定义存放文件的路径</dt>
	 * <dd>
	 * 在类路径下的sys.properties文件中定义uploadFilePath=/photo,此处定义的路径为/photo,该路径为web路径
	 * ,可根据需要自行调整</dd>
	 * <dt>ajax上传处理</dt>
	 * <dd>
	 * 
	 * <pre>
	 * function upload() {
	 * 		if ($("#myfile").val() == "") {
	 * 			alert("Please select a file to upload");
	 * 			return;
	 * 		}
	 * 		$.ajaxFileUpload({
	 * 			url : 'ajaxFileUpload.action',
	 * 			type : 'post',
	 * 			secureuri : false,
	 * 			fileElementId : 'myfile',
	 * 			dataType : 'json',
	 * 			async : true, //是否是异步
	 * 			success : function(data, status) {
	 * 				if (data.success) {
	 * 					$("#photo").val("photo/" + data.fileName);
	 * 					$("#img_photo").attr(
	 * 							"src",
	 * 							"${pageContext.request.contextPath}/photo/"
	 * 									+ data.fileName);
	 * 				} else {
	 * 					$.messager.show({
	 * 						title : "error",
	 * 						msg : data.msg
	 * 					});
	 * 				}
	 * 				$('#myfile').val('');
	 * 				$("#photo-dlg").dialog("close");
	 * 			}
	 * 		});
	 * 	 }
	 * </pre>
	 * 
	 * </dd>
	 * </dl>
	 * 
	 * @param request
	 *            页面请求对象
	 * @return json格式：{success:true,msg:'消息',fileName:'上传后的文件名'}
	 */
	@RequestMapping("ajaxFileUpload.action")
	@ResponseBody
	public Map uploadFile(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile myfile = (CommonsMultipartFile) multipartRequest.getFile("myfile");
		Map result = new HashMap();
		if (myfile != null
				&& StringUtils.isNotBlank(myfile.getOriginalFilename())) {
			try {
				// 文件名
				String fileName = myfile.getOriginalFilename();
				// 文件路径
				String uploadFilePath = PropertyUtil.getPropertyValue("sys", "uploadFilePath");
				String contextRealPath = request.getServletContext().getRealPath(uploadFilePath);
				String uuid = StringTool.uuid();
				String extension = fileName
						.substring(fileName.lastIndexOf('.') + 1);
				String newFileName = uuid + DateUtil.formatDate(new Date())
						+ "." + extension;
				String filePath = contextRealPath + File.separator
						+ newFileName;

				myfile.transferTo(new File(filePath));

				result.put("success", true);
				result.put("msg", "文件上传成功");
				result.put("fileName", newFileName);
			} catch (Exception e) {
				result.put("success", false);
				result.put("msg", "文件上传失败");
				return result;
			}
		} else {
			result.put("success", false);
			result.put("msg", "文件上传失败");
			return result;
		}
		return result;
	}

	/**
	 * 
	 * @Title: download
	 * @Description: 下载本地文件
	 * @param @param path
	 * @param @param response
	 * @param @param request
	 * @return void
	 * @throws
	 */
	@RequestMapping(value = "download.action")
	public void download(String dir, String fileName,
			HttpServletResponse response, HttpServletRequest request) {

		String path = request.getServletContext().getRealPath("/");

		String url = path + dir + "\\" + fileName;

		try {

			// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
			response.setContentType("multipart/form-data");

			// 2.设置文件头：最后一个参数是设置下载文件名
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("gb2312"), "ISO-8859-1")); // 支持中文文件名

			// 通过文件路径获得File对象
			File file = new File(url);

			FileInputStream in = new FileInputStream(file);
			// 3.通过response获取OutputStream对象(out)
			OutputStream out = new BufferedOutputStream(
					response.getOutputStream());

			int b = 0;
			byte[] buffer = new byte[2048];
			while ((b = in.read(buffer)) != -1) {
				out.write(buffer, 0, b); // 4.写到输出流(out)中
			}

			in.close();
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出Excel.
	 * @param template
	 * @param dataUrl
	 * @param exportFileName
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("exportExcel.action")
	public String exportExcel(String template, String dataUrl,
			String exportFileName, HttpServletRequest request,
			HttpServletResponse response) {
		/*
		 * List<Map<String, Object>> data =
		 * dataQueryService.summaryReport(t1,t2); HSSFWorkbook book =
		 * ExcelExportUtil.createWorkBook("汇总统计", "汇总统计", new String[] { "医院名称",
		 * "数据总数", "校验通过","校验不通过","不通过率"}, new String[] { "YLJGMC",
		 * "HZZS","TGZL","BTLSL", "BTGL"}, data);
		 */

		List<Map<String, Object>> data = null;
		HSSFWorkbook book = null;

		try {
			OutputStream output = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ exportFileName);
			response.setContentType("application/msexcel");
			book.write(output);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
