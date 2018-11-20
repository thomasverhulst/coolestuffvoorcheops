package com.cheops.candidatemanager.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cheops.candidatemanager.models.MyUploadForm;

public class MyFileUploadController {

	Logger logger = Logger.getLogger(MyFileUploadController.class);

	// GET: Show upload form page.
	@GetMapping(value = "/uploadOneFile")
	public String uploadOneFileHandler(Model model) {

		MyUploadForm myUploadForm = new MyUploadForm();
		model.addAttribute("myUploadForm", myUploadForm);

		return "uploadOneFile";
	}

	// POST: Do Upload
	@PostMapping(value = "/uploadOneFile")
	public String uploadOneFileHandlerPOST(HttpServletRequest request, //
			Model model, //
			@ModelAttribute("myUploadForm") MyUploadForm myUploadForm) {

		return this.doUpload(request, model, myUploadForm);

	}

	// GET: Show upload form page.
	@GetMapping(value = "/uploadMultiFile")
	public String uploadMultiFileHandler(Model model) {

		MyUploadForm myUploadForm = new MyUploadForm();
		model.addAttribute("myUploadForm", myUploadForm);

		return "uploadMultiFile";
	}

	// POST: Do Upload
	@PostMapping(value = "/uploadMultiFile")
	public String uploadMultiFileHandlerPOST(HttpServletRequest request, //
			Model model, //
			@ModelAttribute("myUploadForm") MyUploadForm myUploadForm) {

		return this.doUpload(request, model, myUploadForm);

	}

	private String doUpload(HttpServletRequest request, Model model, //
			MyUploadForm myUploadForm) {

		String description = myUploadForm.getDescription();

		// Root Directory.
		String uploadRootPath = request.getServletContext().getRealPath("upload");

		File uploadRootDir = new File(uploadRootPath);
		// Create directory if it not exists.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		MultipartFile[] fileDatas = myUploadForm.getFileDatas();
		//
		List<File> uploadedFiles = new ArrayList<File>();
		List<String> failedFiles = new ArrayList<String>();

		for (MultipartFile fileData : fileDatas) {

			// Client File Name
			String name = fileData.getOriginalFilename();

			if (name != null && name.length() > 0) {
				try {
					// Create the file at server
					File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					//
					uploadedFiles.add(serverFile);
					logger.debug("Write file: " + serverFile);
				} catch (Exception e) {
					logger.debug("Error Write file: " + name);
					failedFiles.add(name);
				}
			}
		}
		model.addAttribute("description", description);
		model.addAttribute("uploadedFiles", uploadedFiles);
		model.addAttribute("failedFiles", failedFiles);
		return "uploadResult";
	}
}
