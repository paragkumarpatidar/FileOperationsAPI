package com.example.file.operations.service;

import javax.servlet.http.HttpServletResponse;

/***
 * Service Interface for FileOperationAPI
 * 
 * @author Parag Patidar
 *
 */
public interface FileOperations {

	void fileDownload(HttpServletResponse response, String fileName);

	void fileDelete(String fileName);

	void fileCopy(String fileName);

}
