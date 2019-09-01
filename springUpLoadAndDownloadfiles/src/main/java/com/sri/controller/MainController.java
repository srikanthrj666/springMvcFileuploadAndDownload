package com.sri.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.cj.jdbc.Blob;
import com.sri.entity.File;
import com.sri.service.FileService;

@Controller
public class MainController {

	@Autowired
	private FileService fileService;

	@RequestMapping("/")
	public String home(Model model) {
		System.out.println("***** HOME METHOD *****");
		List<File> list = fileService.getfiles();
		System.out.println(list);
		model.addAttribute("files", list);
		return "home";
	}

	@RequestMapping(value = "/uploadFileProcess")
	public String uploadFileProcess(@RequestParam("file") MultipartFile file, Model model) {

		System.out.println("***** uploadFileProcess *****");

		System.out.println("File Name: " + file.getOriginalFilename());
		System.out.println("File type: " + file.getContentType());
		System.out.println("File Size: " + file.getSize());

		String status = fileService.upload(file);

		model.addAttribute("status", status);
		List<File> list = fileService.getfiles();
		System.out.println(list);
		model.addAttribute("files", list);
		return "home";

	}

	@RequestMapping("/downloadById/{id}")
	public void downloadById(@PathVariable("id") int id, HttpServletResponse response) throws Exception {
		
		System.out.println("***** downloadById *****");
		System.out.println(id);
		String fname = fileService.getFileNameById(id);
		System.out.println(fname);
		Blob file = fileService.getFileById(id);
		Date date = new Date();
		response.setHeader("Content-Disposition", "attachment; filename=" + date + fname);

		byte[] bytes = file.getBytes(1, (int) file.length());
		response.setContentLength(bytes.length);

		InputStream inputStream = new ByteArrayInputStream(bytes);

		FileCopyUtils.copy(inputStream, response.getOutputStream());

	}

	@RequestMapping("/deleteById/{id}")
	public String deleteById(@PathVariable("id") int id, Model model) {
		
		System.out.println("***** deleteById *****");
		System.out.println(id);
		String status = fileService.deleteById(id);
		System.out.println(status);

		model.addAttribute("status", status);
		List<File> list = fileService.getfiles();
		System.out.println(list);
		model.addAttribute("files", list);
		return "home";
	}

}
