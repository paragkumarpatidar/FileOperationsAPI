package com.example.file.operations.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.file.operations.service.FileOperations;

/***
 * @author Parag Patidar
 *
 */
@RestController
@RequestMapping("/file")
public class FileDownloadController {

	@Autowired
	private FileOperations fileOperations;

	/**
	 * Method for Download File when filename is provided
	 * 
	 * @param response
	 * @param fileName
	 * @throws IOException
	 */
	@GetMapping("/download/{fileName:.+}")
	public void downloadFile(HttpServletResponse response, @PathVariable("fileName") String fileName)
			throws IOException {
		fileOperations.fileDownload(response, fileName);
	}

	/**
	 * Method for Delete File when FileName is provided
	 * 
	 * @param response
	 * @param fileName
	 */
	@DeleteMapping("/delete/{fileName:.+}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFile(HttpServletResponse response, @PathVariable("fileName") String fileName) {
		fileOperations.fileDelete(fileName);
	}

	/**
	 * Method to create a Copy File for the filename which is provided
	 * 
	 * @param fileName
	 * @return string SuccessMessage
	 * @throws IOException
	 */
	@GetMapping("/create/{fileName:.+}")
	public String createFile(@PathVariable("fileName") String fileName) throws IOException {
		fileOperations.fileCopy(fileName);
		return "File Created";
	}

}
