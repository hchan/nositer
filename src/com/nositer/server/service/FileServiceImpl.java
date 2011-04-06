package com.nositer.server.service;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.User;
import com.nositer.client.service.FileService;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.client.widget.directorytree.FolderModel;
import com.nositer.server.util.FileUtil;
import com.nositer.shared.FileNameVerifier;
import com.nositer.shared.GWTException;
import com.nositer.shared.Global;
import com.nositer.webapp.Application;

@SuppressWarnings({"serial"})
public class FileServiceImpl extends RemoteServiceServlet implements FileService {
	private User user;

	public FileServiceImpl(User user) {
		this.user = user;
	}

	public FileServiceImpl() {
		user = Application.getCurrentUser();
	}

	@Override
	public List<FileModel> getImageFolderChildren(FileModel folder) {
		String rootDir = MessageFormat.format(Global.USERDIRTEMPLATE, user.getId());
		return getImageFolderChildren(folder, rootDir);
	}
	
	@Override
	public List<FileModel> getImageFolderChildren(FileModel folder, Group group) {
		try {
			FileUtil.createDirsIfNecessary(group);
		} catch (IOException e) {
			Application.log.error("", e);
			throw new GWTException(e);
		}
		String rootDir = MessageFormat.format(Global.GROUPDIRTEMPLATE, group.getId());
		return getImageFolderChildren(folder, rootDir);
	}
	
	private String getRelativePath(File root, String absolutePath) {
		String retval = absolutePath;
		retval = absolutePath.substring(root.getAbsolutePath().length());
		retval = FilenameUtils.separatorsToUnix(retval);
		return retval;
	}

	private List<FileModel> getImageFolderChildren(FileModel folder, String rootDir) {
		File root = new File(rootDir);
		try {
			FileUtil.createDirsIfNecessary(user);
		} catch (IOException e) {
			Application.log.error("", e);
			throw new GWTException(e);
		}
		File[] files = null;
		if (folder == null) {
			files = root.listFiles();
		} else {
			File f = new File(rootDir + "/" + folder.getPath());
			files = f.listFiles();
		}

		int counter = 0;
		HashMap<File, String> idMap = new HashMap<File, String>();
		List<FileModel> models = new ArrayList<FileModel>();
		for (File f : files) {
			FileModel m = null;
			if (f.isDirectory()) {			
				m = new FolderModel(f.getName(), 
						getRelativePath(root, f.getAbsolutePath()));
				//f.getAbsolutePath());
			} else {
				m = new FileModel(f.getName(), 
						getRelativePath(root, f.getAbsolutePath()));
				//f.getAbsolutePath());
				m.set(FileModel.Attribute.size.toString(), f.length());
				m.set(FileModel.Attribute.date.toString(), new Date(f.lastModified()));
			}

			if (idMap.containsKey(f)) {
				m.set(FileModel.Attribute.id.toString(), idMap.get(f));
			} else {
				String id = String.valueOf(counter++);
				idMap.put(f, id);
				m.set(FileModel.Attribute.id.toString(), id);
			}

			models.add(m);
		}

		Collections.sort(models, new Comparator<FileModel>() {
			public int compare(FileModel o1, FileModel o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return models;
	}

	

	@Override
	public void createFolder(String folder) throws GWTException {
		createFolder(folder, MessageFormat.format(Global.USERDIRTEMPLATE, user.getId()) + folder);
	}

	@Override
	public void createFolder(String folder, Group group) {
		createFolder(folder, MessageFormat.format(Global.GROUPDIRTEMPLATE, group.getId()) + folder);
	}

	private void createFolder(String folder, String fullFolderPath) throws GWTException {
		try {
			String baseName = FilenameUtils.getBaseName(folder);
			if (!FileNameVerifier.isValidFileName(baseName)) {
				throw new GWTException(baseName + " has illegal characters");
			}		
			File dirToCreate = new File(fullFolderPath);
			if (dirToCreate.exists()) {
				throw new GWTException(baseName + " already exists");
			}
			dirToCreate.mkdir();
		} catch (GWTException e) {
			throw e;
		} catch (Exception e) {	
			Application.log.error("", e);
			throw new GWTException("Server Error");
		}
	}


}
