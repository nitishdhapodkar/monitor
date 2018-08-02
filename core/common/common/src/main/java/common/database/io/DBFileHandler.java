package common.database.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.constants.ErrorCode;
import common.exceptions.SqInmenoryDBException;

public class DBFileHandler {
	
	public void writeObject(List<Object> objects, String fileName) throws SqInmenoryDBException {
		
		FileOutputStream fileOutputStream;
		ObjectOutputStream objectOutputStream;
		
		try {
			
			fileOutputStream = new FileOutputStream(fileName);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			
			for(Object object : objects) {
				objectOutputStream.writeObject(object);
			}

			objectOutputStream.close();
			fileOutputStream.close();
			
		} catch (Exception e) {
			throw new SqInmenoryDBException("Error while writing objects in file", e, ErrorCode.DATABASE_WRITE);
		}
		
	}
	
	public void writeObject(Map<String, Object> object, String fileName) throws SqInmenoryDBException {
		
		FileOutputStream fileOutputStream;
		ObjectOutputStream objectOutputStream;
		
		try {
			
			fileOutputStream = new FileOutputStream(fileName);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(object);

			objectOutputStream.close();
			fileOutputStream.close();
			
		} catch (Exception e) {
			throw new SqInmenoryDBException("Error while writing objects in file", e, ErrorCode.DATABASE_WRITE);
		}
		
	}
	
	@SuppressWarnings({ "resource" })
	public List<Object> readObjects(String fileName) throws SqInmenoryDBException {
		List<Object> objects = null;
		
		FileInputStream fileInputStream = null;
		ObjectInputStream oInputStream = null;
		
		try {
			fileInputStream = new FileInputStream(new File(fileName));
			oInputStream = new ObjectInputStream(fileInputStream);
			
			Object object;
			while( ( object = (Object) oInputStream.readObject() ) != null) {
				
				if( objects == null ) {
					objects = new ArrayList<>();
				}
				objects.add(object);
			}
			
		} catch (Exception e) {
			throw new SqInmenoryDBException("Error while reading objects from file", e, ErrorCode.DATABASE_WRITE);
		}
		
		return objects;
	}
	
	@SuppressWarnings({ "unchecked", "resource" })
	public Map<String, Object> readObject(String fileName) throws SqInmenoryDBException {
		Map<String, Object> object = null;
		
		FileInputStream fileInputStream = null;
		ObjectInputStream oInputStream = null;
		
		try {
			fileInputStream = new FileInputStream(new File(fileName));
			oInputStream = new ObjectInputStream(fileInputStream);
			
			object = (Map<String, Object>) oInputStream.readObject();
		} catch (Exception e) {
			throw new SqInmenoryDBException("Error while reading objects from file", e, ErrorCode.DATABASE_WRITE);
		}
		
		return object;
	}
	
	public void deleteFile(String fileName) throws SqInmenoryDBException {
		
		try {
			
    		File file = new File(fileName);
        	
    		if(!file.delete()){
    			throw new SqInmenoryDBException("Error while deleting file", ErrorCode.DATABASE_DELETE);
    		}
    	   
    	}catch(Exception e){
    		throw new SqInmenoryDBException("Error while deleting file", e, ErrorCode.DATABASE_DELETE);
    	}
	}

}
