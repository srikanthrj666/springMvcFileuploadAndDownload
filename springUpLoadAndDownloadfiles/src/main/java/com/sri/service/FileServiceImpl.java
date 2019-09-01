package com.sri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.cj.jdbc.Blob;
import com.sri.dao.FileDao;
import com.sri.entity.File;

@Service
public class FileServiceImpl implements FileService{

	@Autowired
	private FileDao dao;
	
	@Override
	public String upload(MultipartFile file) {
		
		return dao.upload(file);
	}

	@Override
	public List<File> getfiles() {
		
		return dao.getfiles();
	}

	@Override
	public Blob getFileById(int id) {
		
		return dao.getFileById(id);
	}

	@Override
	public String getFileNameById(int id) {
		return dao.getFileNameById(id);
	}

	@Override
	public String deleteById(int id) {
		return dao.deleteById(id);
	}

}
