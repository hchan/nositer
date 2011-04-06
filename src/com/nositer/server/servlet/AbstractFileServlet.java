package com.nositer.server.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;

import com.nositer.server.service.FileServiceImpl;
import com.nositer.shared.FileNameUtil;
import com.nositer.shared.Global;
import com.nositer.webapp.Application;

@SuppressWarnings({"serial"})
public abstract class AbstractFileServlet extends HttpServlet {


	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		// Get requested image by path info.
		String requestedFile = request.getPathInfo();
		Application.log.info("requestedFile: " + requestedFile);

		try {
			if (!isValidRequestedFile(requestedFile)) {
				Application.log.info("requestedFile is invalid");
				response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
				return;
			}
		} catch (PrivateException e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN); // 404.
			return;
		}

		// Check if file name is actually supplied to the request URI.
		if (requestedFile == null) {
			// Do your thing if the image is not supplied to the request URI.
			// Throw an exception, or send 404, or show default/warning image, or just ignore it.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		// Decode the file name (might contain spaces and on) and prepare file object.
		File file = new File(getRootDir() + "/" + requestedFile);

		// Check if file actually exists in filesystem.
		if (!file.exists()) {

			if (FileNameUtil.isImageFile(requestedFile)) {
				file = new File(Application.getRealPath(Global.IMAGENOTFOUND));
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
				return;
			}
		}

		// Get content type by filename.
		String contentType = getServletContext().getMimeType(file.getName());
		/*
		// Check if file is actually an image (avoid download of other files by hackers!).
		// For all content types, see: http://www.w3schools.com/media/media_mimeref.asp
		if (contentType == null || !contentType.startsWith("image")) {
			// Do your thing if the file appears not being a real image.
			// Throw an exception, or send 404, or show default/warning image, or just ignore it.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}
		 */

		// Init servlet response.
		response.reset();
		response.setBufferSize(DEFAULT_BUFFER_SIZE);
		response.setContentType(contentType);
		response.setHeader("Content-Length", String.valueOf(file.length()));
		response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");

		// Prepare streams.
		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {
			// Open streams.
			input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
			output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

			// Write file contents to response.
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
		} finally {
			// Gently close streams.
			close(output);
			close(input);
		}
	}


	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				Application.log.error("", e);
			}
		}
	}

	private boolean isValidRequestedFile(String str) throws PrivateException {
		boolean retval = true;
		try {
			String[] dirPaths = str.split("/");
			String userid = dirPaths[1];
			String accessPath = dirPaths[2];
			if (!userid.matches("^\\d+$")) {
				retval = false;
			} else {
				if (!("/" + accessPath).equals(getPublicDir())) {		
					throw new PrivateException();
				} else {
					for (int i = 1; i < dirPaths.length; i++) {
						String dirPath = dirPaths[i];
						if (!FileNameUtil.isValidFileName(dirPath)) {
							retval = false;
							break;
						}
					}			
				}
			}		
		} catch (PrivateException e) {
			throw e;	
		}
		catch (Exception e) {
			Application.log.error("", e);
			retval = false;
		}
		return retval;
	}

	public abstract String getRootDir();
	public abstract String getPublicDir();



}