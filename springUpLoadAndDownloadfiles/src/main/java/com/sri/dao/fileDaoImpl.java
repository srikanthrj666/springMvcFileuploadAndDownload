package com.sri.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.cj.jdbc.Blob;
import com.sri.entity.File;

@Repository
public class fileDaoImpl  implements FileDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public String upload(MultipartFile file) {
		
		String sql = "INSERT INTO files (F_DATA, F_NAME) VALUES (?,?)";
		
		try {
			int i = jdbcTemplate.update(sql, file.getBytes(),file.getOriginalFilename());
		
			if (i==1) {
				return "File Uploaded Successfully";
			}
		} catch (Exception e) {
			System.out.println(e);
			return "FAIL";
		}
		return "FAIL";
		
	}


	@Override
	public List<File> getfiles() {
		
		String sql = "SELECT F_ID,F_NAME FROM files";
		
		List<File> files = jdbcTemplate.query(sql, new RowMapper<File>() {

			@Override
			public File mapRow(ResultSet rs, int rowNum) throws SQLException {
				File file = new File();
				file.setId(rs.getInt("F_ID"));
				file.setfName(rs.getString("F_NAME"));
				return file;
			}
		});
		
		return files;
	}


	@Override
	public Blob getFileById(int id) {


		String sql = "SELECT F_DATA FROM files WHERE F_ID=?";
		
		Blob fileData= jdbcTemplate.queryForObject(sql, new Object[] { id }, Blob.class);
		
		return fileData;
	}


	@Override
	public String getFileNameById(int id) {


		String sql = "SELECT F_NAME FROM files WHERE F_ID=?";
		
		String fname = jdbcTemplate.queryForObject(sql, new Object[] { id }, String.class);
		
		return fname;
	}


	@Override
	public String deleteById(int id) {


		String sql = "DELETE FROM files WHERE F_ID =?";
		int i = jdbcTemplate.update(sql, id);
		
		if (i==1) {
			return " File Deleted Successfully";
		}
		else {
			return "FAIL";
		}
		
	}

}
