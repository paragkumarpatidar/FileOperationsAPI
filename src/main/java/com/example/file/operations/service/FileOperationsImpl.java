package com.example.file.operations.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.channels.FileChannel;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.example.file.operations.constants.FileConstants;
import com.example.file.operations.exception.ErrorCodeEnum;
import com.example.file.operations.exception.FileOperationsException;

/***
 * Service Layer Implementation of FileOperaitons API
 * 
 * @author Parag Patidar
 *
 */
@Service
public class FileOperationsImpl implements FileOperations {

	@Value("${file.baseLocation:C:\"Files\"}")
	private String baseLocation;

	private static final Logger LOG = LoggerFactory.getLogger(FileOperationsImpl.class);

	@Override
	public void fileDownload(HttpServletResponse response, String fileName) {

		File file = new File(baseLocation + fileName);
		InputStream inputStream;
		if (file.exists()) {
			try {
				this.setResponseHeader(response, file);
				inputStream = new BufferedInputStream(new FileInputStream(file));
				FileCopyUtils.copy(inputStream, response.getOutputStream());
			} catch (IOException e) {
				throw FileOperationsException.populateFileOperationsException(e, ErrorCodeEnum.TECHNICAL_ERROR);
			}
		} else {
			throw FileOperationsException.populateFileOperationsException(ErrorCodeEnum.NO_FILE_EXISTS);
		}
	}

	@Override
	public void fileDelete(String fileName) {

		File file = new File(baseLocation + fileName);
		if (file.exists()) {
			try {
				if (file.delete()) {
					LOG.info(file.getName() + " is deleted!");
				} else {
					throw FileOperationsException.populateFileOperationsException(ErrorCodeEnum.UNABLE_TO_DELETE_FILE);
				}
			} catch (Exception e) {
				throw FileOperationsException.populateFileOperationsException(e, ErrorCodeEnum.TECHNICAL_ERROR);
			}
		} else {
			throw FileOperationsException.populateFileOperationsException(ErrorCodeEnum.NO_FILE_EXISTS);
		}

	}

	@Override
	public void fileCopy(String fileName) {

		FileInputStream inputStream;
		FileOutputStream outputStream;
		File fileToCopy = new File(baseLocation + fileName);
		File newFile = new File(baseLocation + FileConstants.COPY + fileToCopy.getName());
		try {
			inputStream = new FileInputStream(fileToCopy);
			FileChannel inChannel = inputStream.getChannel();

			outputStream = new FileOutputStream(newFile);
			FileChannel outChannel = outputStream.getChannel();

			inChannel.transferTo(0, fileToCopy.length(), outChannel);
			inputStream.close();
			outputStream.close();
		} catch (IOException e) {
			throw FileOperationsException.populateFileOperationsException(e, ErrorCodeEnum.TECHNICAL_ERROR);
		}

	}

	/**
	 * Set the response headers
	 * 
	 * @param response
	 * @param file
	 */
	private void setResponseHeader(HttpServletResponse response, File file) {
		response.setContentType(getMimeType(file));
		response.setHeader(FileConstants.CONTENT_DISPOSITION,
				String.format(FileConstants.ATTACHMENT + file.getName() + "\""));
		response.setContentLength((int) file.length());
	}

	/**
	 * Detects the file type
	 * 
	 * @param file
	 * @return
	 */
	private String getMimeType(File file) {
		String fileType = URLConnection.guessContentTypeFromName(file.getName());
		if (fileType == null) {
			fileType = FileConstants.DEFAULT_MIME_TYPE;
		}
		return fileType;
	}

}
