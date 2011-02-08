package com.nositer.server.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import com.nositer.client.dto.generated.User;
import com.nositer.shared.Global;
import com.nositer.webapp.Application;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {

	private long FILE_SIZE_LIMIT = 20 * 1024 * 1024; // 20 MiB

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		doPost(req, resp);
	}
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		User user = null;
		try {
			user = getUseridViaHttpCall(req, resp);
			ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
			fileUpload.setSizeMax(FILE_SIZE_LIMIT);

			List<FileItem> items = fileUpload.parseRequest(req);

			for (FileItem item : items) {
				/*
				if (item.isFormField()) {
					logger.log(Level.INFO, "Received form field:");
					logger.log(Level.INFO, "Name: " + item.getFieldName());
					logger.log(Level.INFO, "Value: " + item.getString());
				} else {
					logger.log(Level.INFO, "Received file:");
					logger.log(Level.INFO, "Name: " + item.getName());
					logger.log(Level.INFO, "Size: " + item.getSize());
				}
				 */
				if (!item.isFormField()) {
					if (item.getSize() > FILE_SIZE_LIMIT) {
						resp.sendError(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE,
						"File size excedes limit");

						return;
					}


					InputStream in = item.getInputStream();
					FileOutputStream fos = new FileOutputStream("/temp/tempupload" + "/" + item.getName());
					byte buf[]=new byte[1024];
					int len;
					while ((len=in.read(buf)) > 0) {
						fos.write(buf,0,len);
					}
					fos.close();
					in.close();


					if (!item.isInMemory())
						item.delete();
				}
			}
		} catch (Exception e) {
			Application.log.debug("", e);
		}
	}

	private User getUseridViaHttpCall(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		User retval = null;
		HttpClient httpClient = new HttpClient();
		String urlPost = "http://" + req.getServerName() + ":" + req.getServerPort() + Global.CURRENTUSERASJSON;
		PostMethod method = new PostMethod(urlPost);
		method.setRequestHeader("Cookie", "JSESSIONID=" + req.getParameter(Global.UPLOADCREDENTIALKEY));
		try {
			httpClient.executeMethod(method);
			String jsonUser = method.getResponseBodyAsString();
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			retval = (User) xstream.fromXML(jsonUser);
		} finally {
			method.releaseConnection();
		}
		return retval;
	}
}
