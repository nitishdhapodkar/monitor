package core.util.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import clientcore.exceptions.ErrorCode;
import clientcore.exceptions.SqFileException;

public class FileHandler {
	
	public static List<String> getFileLines(String fileName) throws SqFileException {
		
		BufferedReader br = null;
		String strLine = "";
		List<String> fileContent = null;

		try {
			br = new BufferedReader(new FileReader(fileName));

			while ((strLine = br.readLine()) != null) {
				if( fileContent == null ) {
					fileContent = new ArrayList<>();
				}
				fileContent.add(strLine);
			}

			br.close();

		} catch (Exception e) {
			throw new SqFileException("Error in reading file : " + fileName, e, ErrorCode.FILE_READ);
		}

		return fileContent;
	}
	
	public static void appendToFile(String fileName, String text) throws SqFileException {
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		PrintWriter pw = null;

		try {
			fw = new FileWriter(fileName, true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			pw.println(text);

		} catch (IOException e) {
			throw new SqFileException("Error in appending file : " + fileName, e, ErrorCode.FILE_APPEND);
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
				if (pw != null)
					pw.close();
			} catch (IOException ex) {
				throw new SqFileException("Error in closing file : " + fileName, ex, ErrorCode.FILE_WRITE);
			}
		}
	}
	
	public static void appendToFile(String fileName, List<String> text) throws SqFileException {
		BufferedWriter bw = null;
		FileWriter fw = null;
		PrintWriter pw = null;

		try {
			fw = new FileWriter(fileName, true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			for( String line : text) {
				pw.println(line);
			}

		} catch (IOException e) {
			throw new SqFileException("Error in appending file : " + fileName, e, ErrorCode.FILE_APPEND);
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
				if (pw != null)
					pw.close();
			} catch (IOException ex) {
				throw new SqFileException("Error in closing file : " + fileName, ex, ErrorCode.FILE_WRITE);
			}
		}
	}
	
	public static void writeToFile(String fileName, String text) throws SqFileException {
		
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			bw.write(text);

		} catch (IOException e) {
			throw new SqFileException("Error in writing file : " + fileName, e, ErrorCode.FILE_WRITE);
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				throw new SqFileException("Error in closing file : " + fileName, ex, ErrorCode.FILE_WRITE);
			}
		}
	}
	
	public static void writeToFile(String fileName, List<String> text) throws SqFileException {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			for( String line : text) {
				bw.write(line);
			}
			
		} catch (IOException e) {
			throw new SqFileException("Error in writing file : " + fileName, e, ErrorCode.FILE_WRITE);
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				throw new SqFileException("Error in closing file : " + fileName, ex, ErrorCode.FILE_WRITE);
			}
		}
	}

}
