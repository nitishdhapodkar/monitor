package common.util.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import common.constants.ErrorCode;
import common.exceptions.SqFolderException;

public class FolderUtil {
	
	public void checkFolderOrCreate(String filePath) throws SqFolderException {
		
		String folderFullPath = getFolderPathForFile(filePath);
		createFolder(folderFullPath);
	}
	
	private void createFolder(String folderFullPath) throws SqFolderException {
		
		try {
			
			List<String> breakedPath = breakFolderPath(folderFullPath);
			String folderPath = null;
			for( String pathPart : breakedPath ) {
				
				if(folderPath == null) {
					folderPath = pathPart;
				} else {
					folderPath += "/" + pathPart;
				}
				createFileSystemFolder(folderPath);
				
			}
			
		} catch (Exception e) {
			throw new SqFolderException("Error creating folder : " + folderFullPath, e, ErrorCode.FOLDER_EXCEPTION);
		}
		
	}
	
	private List<String> breakFolderPath(String folderFullPath) throws SqFolderException {
		
		List<String> breakedPath = null;
		
		try {
			
			String folderPath = folderFullPath;
			folderPath = folderPath.replace("\\", "/");
			folderPath = folderPath.replace("\\\\", "/");
			folderPath = folderPath.replace("//", "/");
			
			String breakedPathArray[] = folderPath.split("/");
			breakedPath = new ArrayList<>();
			for( String pathPart : breakedPathArray ) {
				breakedPath.add(pathPart);
			}
			
		} catch (Exception e) {
			throw new SqFolderException("Error breaking folder path", e, ErrorCode.FOLDER_EXCEPTION);
		}
		
		return breakedPath;
	}
	
	private void createFileSystemFolder(String path) throws SqFolderException {
		
		File theDir = new File(path);

		if (!theDir.exists()) {
			
		    try{
		        theDir.mkdir();
		    } 
		    catch(Exception e){
		    	throw new SqFolderException("Error creating folder : " + path, e, ErrorCode.FOLDER_EXCEPTION);
		    }        
		}
		
	}
	
	private String getFolderPathForFile(String filePath) throws SqFolderException {
		
		String folderPath = null;
		
		try {
			String filePathLocal = filePath;
			filePathLocal = filePathLocal.replace("\\", "/");
			filePathLocal = filePathLocal.replace("\\\\", "/");
			filePathLocal = filePathLocal.replace("//", "/");
			
			int lastIndex = filePathLocal.lastIndexOf("/");
			folderPath = filePathLocal.substring(0, lastIndex);
			
		} catch (Exception e) {
			throw new SqFolderException("Error breaking folder path", e, ErrorCode.FOLDER_EXCEPTION);
		}
		
		return folderPath;
	}
}
