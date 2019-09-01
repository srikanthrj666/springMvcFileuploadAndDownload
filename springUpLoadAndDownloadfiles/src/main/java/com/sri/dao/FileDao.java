package com.sri.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mysql.cj.jdbc.Blob;
import com.sri.entity.File;

public interface FileDao {

	String upload(MultipartFile file);

	List<File> getfiles();

	Blob getFileById(int id);

	String getFileNameById(int id);

	String deleteById(int id);

}
