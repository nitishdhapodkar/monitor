package common.util.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import common.database.io.DatabaseUtil;

public class ZipUtil {
	
	private static FileSystem fs;
	
	private static void initDatabase(String zipFilePath) {
		
		Map<String, String> env = new HashMap<>(); 
		env.put("create", "true");
		Path path = Paths.get(zipFilePath);
		URI uri = URI.create("jar:" + path.toUri());
		try {
			fs = FileSystems.newFileSystem(uri, env);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void closeDatabase() {
		try {
			fs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static synchronized void addFile(String fileName, String content) {
		
		try {
			Path nf = fs.getPath(fileName);
		    try (Writer writer = Files.newBufferedWriter(nf, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
		        writer.write(content);
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static synchronized String readFile(String zipFilePath, String fileName) {
		
		String fileBytes = null;
		try {
			Path nf = fs.getPath(fileName);
		    try (BufferedReader bufferedReader = Files.newBufferedReader(nf, StandardCharsets.UTF_8)) {
		    	fileBytes = bufferedReader.readLine();
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (Exception e) {
				fs = null;
			}
		}
		
		return fileBytes;
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		initDatabase("D:\\temp\\zipdb\\test.zip");
		
		for(int i = 0; i < 100000; i++) {
			String fileName = DatabaseUtil.getNewId();
			StringBuffer content = new StringBuffer();
			for( int j = 0; j < 100000; j++) {
				content.append(DatabaseUtil.getNewId());
			}
			addFile(fileName, content.toString());
			System.out.println(i);
//			Thread.sleep(500);
//			String data = readFile("D:\\temp\\zipdb\\test.zip", fileName);
//			System.out.println(data);
		}
		closeDatabase();
		
	}

}
