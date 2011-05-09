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

import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.dto.generated.User;
import com.nositer.client.service.FileService;
import com.nositer.client.widget.directorytree.FileModel;
import com.nositer.client.widget.directorytree.FolderModel;
import com.nositer.server.util.FileUtil;
import com.nositer.shared.FileNameUtil;
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
	public List<FileModel> getFolderChildren(FileModel folder) {
		String rootDir = MessageFormat.format(Global.USERDIRTEMPLATE, user.getId());
		try {
			FileUtil.createDirsIfNecessary(user);
		} catch (IOException e) {
			Application.log.error("", e);
			throw new GWTException(e);
		}
		return getFolderChildren(folder, rootDir);
	}

	@Override
	public List<FileModel> getFolderChildren(FileModel folder, GroupPlusView groupPlusView) {
		try {
			FileUtil.createDirsIfNecessary(groupPlusView);
		} catch (IOException e) {
			Application.log.error("", e);
			throw new GWTException(e);
		}
		String rootDir = MessageFormat.format(Global.GROUPDIRTEMPLATE, groupPlusView.getId());
		return getFolderChildren(folder, rootDir);
	}

	private String getRelativePath(File root, String absolutePath) {
		String retval = absolutePath;
		retval = absolutePath.substring(root.getAbsolutePath().length());
		retval = FilenameUtils.separatorsToUnix(retval);
		return retval;
	}

	private List<FileModel> getFolderChildren(FileModel folder, String rootDir) {
		List<FileModel> retval = new ArrayList<FileModel>();
		File root = new File(rootDir);

		File[] files = null;
		if (folder == null) {
			files = root.listFiles();
		} else {
			File f = new File(rootDir + "/" + folder.getPath());
			files = f.listFiles();
		}

		if (files != null) {
			int counter = 0;
			HashMap<File, String> idMap = new HashMap<File, String>();

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

				retval.add(m);
			}

			Collections.sort(retval, new Comparator<FileModel>() {
				public int compare(FileModel o1, FileModel o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
		}
		return retval;
	}



	@Override
	public void createFolder(String folder) throws GWTException {
		createFolder(folder, MessageFormat.format(Global.USERDIRTEMPLATE, user.getId()) + folder);
	}

	@Override
	public void createFolder(String folder, GroupPlusView groupPlusView) {
		createFolder(folder, MessageFormat.format(Global.GROUPDIRTEMPLATE, groupPlusView.getId()) + folder);
	}

	private void createFolder(String folder, String systemFolderPath) throws GWTException {
		try {
			String baseName = FilenameUtils.getBaseName(folder);
			if (!FileNameUtil.isValidFileName(baseName)) {
				throw new GWTException(baseName + " has illegal characters");
			}		
			File dirToCreate = new File(systemFolderPath);
			if (dirToCreate.exists()) {
				throw new GWTException(baseName + " already exists");
			}
			dirToCreate.mkdir();
		} catch (GWTException e) {
			throw e;
		} catch (Exception e) {	
			Application.log.error("", e);
			throw new GWTException(e);
		}
	}




	private void renameFolderInSystem(String systemPath, 
			String oldRelativeFolder,
			String newRelativeFolder) {
		try {
			String baseName = FilenameUtils.getBaseName(systemPath);
			if (!FileNameUtil.isValidFileName(baseName)) {
				throw new GWTException(baseName + " has illegal characters");
			}	
			if (("/" + oldRelativeFolder).equals(Global.PUBLICFOLDER) ||
					("/" + oldRelativeFolder).equals(Global.PRIVATEFOLDER)) {
				throw new GWTException("Cannot rename " + Global.PUBLICFOLDER + " or " + Global.PRIVATEFOLDER);
			} else {
				File srcDir = new File(systemPath + "/" + oldRelativeFolder);
				File destDir = new File(systemPath + "/" + newRelativeFolder);
				FileUtils.moveDirectory(srcDir, destDir);
			} 
		} catch (GWTException e) {
			throw e;
		}
		catch (FileExistsException e) {
			throw new GWTException(newRelativeFolder + " already exists");
		} catch (Exception e) {
			Application.log.error("", e);
			throw new GWTException(e);
		}
	}

	@Override
	public void renameFolder(String pathName, String oldRelativeFolder,
			String newRelativeFolder) throws GWTException {
		String systemPath = MessageFormat.format(Global.USERDIRTEMPLATE, user.getId()) + pathName;
		renameFolderInSystem(systemPath, oldRelativeFolder, newRelativeFolder);
	}

	@Override
	public void renameFolder(String pathName, String oldRelativeFolder,
			String newRelativeFolder, GroupPlusView groupPlusView) throws GWTException {
		String systemPath = MessageFormat.format(Global.GROUPDIRTEMPLATE, groupPlusView.getId()) + pathName;
		renameFolderInSystem(systemPath, oldRelativeFolder, newRelativeFolder);
	}


	@Override
	public void deleteFolder(FolderModel folderModel) throws GWTException {
		String systemPath = MessageFormat.format(Global.USERDIRTEMPLATE, user.getId()) + folderModel.getPath();
		deleteFolderInSystem(systemPath, folderModel.getPath());

	}

	@Override
	public void deleteFolder(FolderModel folderModel,
			GroupPlusView groupPlusView) throws GWTException {
		String systemPath = MessageFormat.format(Global.GROUPDIRTEMPLATE, groupPlusView.getId()) + folderModel.getPath();
		deleteFolderInSystem(systemPath, folderModel.getPath());

	}

	private void deleteFolderInSystem(String systemPath, String relativePath) {
		try {
			if (relativePath.equals(Global.PUBLICFOLDER) ||
					relativePath.equals(Global.PRIVATEFOLDER)) {
				throw new GWTException("Cannot delete " + Global.PUBLICFOLDER + " or " + Global.PRIVATEFOLDER);
			} else {
				FileUtils.deleteDirectory(new File(systemPath));
			} 
		} catch (GWTException e) {
			throw e;
		}
		catch (Exception e) {
			Application.log.error("", e);
			throw new GWTException(e);
		}
	}

	@Override
	public void renameFile(String pathName, String oldRelativeFile,
			String newRelativeFile) throws GWTException {
		String systemPath = MessageFormat.format(Global.USERDIRTEMPLATE, user.getId()) + pathName;
		renameFileInSystem(systemPath, oldRelativeFile, newRelativeFile);
	}

	@Override
	public void renameFile(String pathName, String oldRelativeFile,
			String newRelativeFile, GroupPlusView groupPlusView)
	throws GWTException {
		String systemPath = MessageFormat.format(Global.GROUPDIRTEMPLATE, groupPlusView.getId()) + pathName;
		renameFileInSystem(systemPath, oldRelativeFile, newRelativeFile);
	}

	private void renameFileInSystem(String systemPath, 
			String oldRelativeFile,
			String newRelativeFile) {
		try {			
			if (!FileNameUtil.isValidFileName(newRelativeFile)) {
				throw new GWTException(newRelativeFile + " has illegal characters");
			}	

			File srcDir = new File(systemPath + "/" + oldRelativeFile);
			File destDir = new File(systemPath + "/" + newRelativeFile);
			FileUtils.moveFile(srcDir, destDir);

		} catch (GWTException e) {
			throw e;
		}
		catch (FileExistsException e) {
			throw new GWTException(newRelativeFile + " already exists");
		} catch (Exception e) {
			Application.log.error("", e);
			throw new GWTException(e);
		}
	}

	@Override
	public void deleteFile(FileModel fileModel) throws GWTException {
		String systemPath = MessageFormat.format(Global.USERDIRTEMPLATE, user.getId()) + fileModel.getPath();
		deleteFileInSystem(systemPath, fileModel.getPath());
	}

	@Override
	public void deleteFile(FileModel fileModel, GroupPlusView groupPlusView)
	throws GWTException {
		String systemPath = MessageFormat.format(Global.GROUPDIRTEMPLATE, groupPlusView.getId()) + fileModel.getPath();
		deleteFileInSystem(systemPath, fileModel.getPath());
	}

	private void deleteFileInSystem(String systemPath, String relativePath) {
		try {
			File file = (new File(systemPath));
			file.delete();
		} catch (GWTException e) {
			throw e;
		}
		catch (Exception e) {
			Application.log.error("", e);
			throw new GWTException(e);
		}
	}

}
