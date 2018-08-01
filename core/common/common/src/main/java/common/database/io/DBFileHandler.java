package common.database.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Objects;

public class DBFileHandler<E> {
	
	public void writeObjects(List<E> objects, String fileName) {
		
		FileOutputStream fileOutputStream;
		ObjectOutputStream objectOutputStream;
		
		try {
			
			fileOutputStream = new FileOutputStream(fileName);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			
			for(E object : objects) {
				objectOutputStream.writeObject(object);
			}

			objectOutputStream.close();
			fileOutputStream.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public List<E> readObjects(String fileName) {
		List<E> objects = null;
		
		FileInputStream fileInputStream = null;
		ObjectInputStream oInputStream = null;
		
		try {
			fileInputStream = new FileInputStream(new File(fileName));
			oInputStream = new ObjectInputStream(fileInputStream);
			
//			while(oInputStream.readObject()) {
//				
//			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return objects;
	}

}
