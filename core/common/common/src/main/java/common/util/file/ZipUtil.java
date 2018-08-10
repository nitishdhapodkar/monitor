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
	
//	public static void main(String[] args) throws IOException {
//		Map<String, String> env = new HashMap<>(); 
//		env.put("create", "true");
//		Path path = Paths.get("D:\\temp\\zipdb\\test.zip");
//		URI uri = URI.create("jar:" + path.toUri());
//		try (FileSystem fs = FileSystems.newFileSystem(uri, env))
//		{
//			for(int i = 0; i < 10; i ++) {
//				Path nf = fs.getPath(DatabaseUtil.getNewId());
//			    try (Writer writer = Files.newBufferedWriter(nf, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
//			        writer.write(DatabaseUtil.getNewId());
//			    }
//			    if( (i % 10000) == 0) {
//			    	System.out.println(i);
//			    }
//			}
//		    
//		}
//	}
	
	public static void addFile(String zipFilePath, String fileName, String content) {
		
		Map<String, String> env = new HashMap<>(); 
		env.put("create", "true");
		Path path = Paths.get(zipFilePath);
		URI uri = URI.create("jar:" + path.toUri());
		try (FileSystem fs = FileSystems.newFileSystem(uri, env))
		{
			Path nf = fs.getPath(fileName);
		    try (Writer writer = Files.newBufferedWriter(nf, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
		        writer.write(content);
		    }
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String readFile(String zipFilePath, String fileName) {
		
		Map<String, String> env = new HashMap<>(); 
		env.put("create", "true");
		Path path = Paths.get(zipFilePath);
		URI uri = URI.create("jar:" + path.toUri());
		String fileBytes = null;
		try (FileSystem fs = FileSystems.newFileSystem(uri, env))
		{
			Path nf = fs.getPath(fileName);
		    try (BufferedReader bufferedReader = Files.newBufferedReader(nf, StandardCharsets.UTF_8)) {
		    	fileBytes = bufferedReader.readLine();
		    }
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileBytes;
	}
	
	public static void main(String[] args) {
		
		for(int i = 0; i < 100; i++) {
			String fileName = DatabaseUtil.getNewId();
			addFile("D:\\temp\\zipdb\\test.zip", fileName, "test content.........." + DatabaseUtil.getNewId());
			String data = readFile("D:\\temp\\zipdb\\test.zip", fileName);
			System.out.println(data);
		}
		
	}

}
