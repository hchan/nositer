package com.nositer.server.service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.RemoteSortTreeLoadConfig;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.generated.User;
import com.nositer.client.service.FileService;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.client.widget.directorytree.FolderModel;
import com.nositer.shared.GWTException;
import com.nositer.shared.Global;
import com.nositer.webapp.Application;

@SuppressWarnings({"unchecked", "rawtypes", "serial"})
public class FileServiceImpl extends RemoteServiceServlet implements FileService {

	private File root;
	private String rootDir;
	private FilenameFilter filter;
	private HashMap<File, String> idMap = new HashMap<File, String>();
	private int counter = 0;
	private User user;
	
	public FileServiceImpl() {
		user = Application.getCurrentUser();
		filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return !name.startsWith(".");
			}
		};
	}
	
	public String getUserRelativePath(String absolutePath) {
		String retval = absolutePath;
		retval = absolutePath.substring(root.getAbsolutePath().length());
		retval = FilenameUtils.separatorsToUnix(retval);
		return retval;
	}

	public List<FileModel> getImageFolderChildren(FileModel folder) {
		rootDir = MessageFormat.format(Global.USERDIRTEMPLATE, user.getId());
		root = new File(rootDir);
		try {
			createDirsIfNecessary();
		} catch (IOException e) {
			Application.log.error("", e);
			throw new GWTException(e);
		}
		File[] files = null;
		if (folder == null) {
			files = root.listFiles(filter);
		} else {
			File f = new File(rootDir + "/" + folder.getPath());
			files = f.listFiles(filter);
		}

		List<FileModel> models = new ArrayList<FileModel>();
		for (File f : files) {
			FileModel m = null;
			if (f.isDirectory()) {			
				m = new FolderModel(f.getName(), 
						getUserRelativePath(f.getAbsolutePath()));
				//f.getAbsolutePath());
			} else {
				m = new FileModel(f.getName(), 
						getUserRelativePath(f.getAbsolutePath()));
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

	public void createDirsIfNecessary() throws IOException {
	
		File privateImageDir = new File(MessageFormat.format(Global.USERPRIVATEDIRTEMPLATE, user.getId()));
		if (!privateImageDir.exists()) {
			FileUtils.forceMkdir(privateImageDir);
		}
		File publicImageDir = new File(MessageFormat.format(Global.USERPUBLICDIRTEMPLATE, user.getId()));
		if (!publicImageDir.exists()) {
			FileUtils.forceMkdir(publicImageDir);
		}
	}

	public List<FileModel> getImageFolderChildren(final RemoteSortTreeLoadConfig loadConfig) {
		List<FileModel> models = getImageFolderChildren((FileModel) loadConfig.getParent());
		final String prop = loadConfig.getSortField();
		final boolean desc = loadConfig.getSortDir() == SortDir.DESC;
		if (prop != null) {
			Collections.sort(models, new Comparator<FileModel>() {

				public int compare(FileModel o1, FileModel o2) {
					boolean m1Folder = o1 instanceof FolderModel;
					boolean m2Folder = o2 instanceof FolderModel;

					if (m1Folder && !m2Folder) {
						return -1;
					} else if (!m1Folder && m2Folder) {
						return 1;
					}

					Comparable v1 = o1.get(prop);
					Comparable v2 = o2.get(prop);
					if (v1 == null && v2 != null) {
						return -1;
					} else if (v1 != null && v2 == null) {
						return 0;
					} else if (v1 == null && v2 == null) {
						return o1.getName().compareTo(o2.getName());
					}
					return desc ? v2.compareTo(v1) : v1.compareTo(v2);
				}
			});
		}

		return models;
	}

	public static boolean isValidFileName(String fileName) {
		boolean retval = false;
		String validCharsPattern = "^([A-Za-z0-9_\\.])+$";
		retval = fileName.matches(validCharsPattern);
		if (retval) {
			retval = !fileName.matches(".*\\.\\..*");
		}
		return retval;
	}
	
	@Override
	public void createFolder(String folder) throws GWTException {
		try {
			String baseName = FilenameUtils.getBaseName(folder);
			if (!isValidFileName(baseName)) {
				throw new GWTException(baseName + " has illegal characters");
			}
			String fullFolderPath = MessageFormat.format(Global.USERDIRTEMPLATE, user.getId()) + folder;
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
