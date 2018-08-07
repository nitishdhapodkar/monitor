package common.util.file;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import common.constants.ErrorCode;
import common.exceptions.RandomAccessFileException;


public class RandomFileUtil {
	
	private String dataDir;
	
	private String dataName;
	
	public RandomFileUtil(String dataDir, String dataName) {
		this.dataDir = dataDir;
		this.dataName = dataName;
	}
	
	public void write(String dataLine) throws RandomAccessFileException {
		
		writeToRandomAccessFile(getDataFileName(), dataLine);
	}
	
	public void write(List<String> dataLines) throws RandomAccessFileException {
		
		for(String dataLine : dataLines ) {
			writeToRandomAccessFile(getDataFileName(), dataLine);
		}
	}
	
	public String read(long lineNo) throws RandomAccessFileException {
		
		long linePosition = readIndex(lineNo);
		if(linePosition != -1) {
			return readFromRandomAccessFile(getDataFileName(), linePosition);
		} else {
			return null;
		}
		
	}
	
	public List<String> readAllAfter(long lineNo) throws RandomAccessFileException {
		
		long nextLineNo = lineNo;
		List<String> dataLines = null;
		
		String dataLine;
		while((dataLine = read(nextLineNo)) != null) {
			 
			if(dataLines == null) {
				dataLines = new ArrayList<String>();
			}
			dataLines.add(dataLine);
			nextLineNo++;
		}
		
		return dataLines;
	}

	private String readFromRandomAccessFile(String file, long position) throws RandomAccessFileException {
		String record = null;
		try {
			
			RandomAccessFile fileStore = new RandomAccessFile(file, "r");
			fileStore.seek(position);
			record = fileStore.readUTF();
			fileStore.close();

		} catch (EOFException e) {
			return null;
		} catch (IOException e) {
			throw new RandomAccessFileException("Error reading random access file", e, ErrorCode.RANDOM_ACCESS_FILE_READ);
		} 

		return record;
	}

	private void writeToRandomAccessFile(String file, String record) throws RandomAccessFileException {
		try {
			
			RandomAccessFile fileStore = new RandomAccessFile(file, "rw");
			
			long position = fileStore.length();
			writeIndex(position);
			fileStore.seek(position);
			fileStore.writeUTF(record);
			fileStore.close();

		} catch (IOException e) {
			throw new RandomAccessFileException("Error writing random access file", e, ErrorCode.RANDOM_ACCESS_FILE_WRITE);
		}
	}
	
	private void writeIndex(long position) throws RandomAccessFileException {
		
		try {
			
			RandomAccessFile fileStore = new RandomAccessFile(getIndexFileName(), "rw");
			fileStore.seek(fileStore.length());
			fileStore.writeLong(position);
			fileStore.close();

		} catch (IOException e) {
			throw new RandomAccessFileException("Error writing random access file", e, ErrorCode.RANDOM_ACCESS_FILE_READ);
		}
	}
	
	public void writeClientLineNo(long lineNo) throws RandomAccessFileException {
		
		try {
			
			RandomAccessFile fileStore = new RandomAccessFile(getClientFile(), "rw");
			fileStore.seek(0);
			fileStore.writeLong(lineNo);
			fileStore.close();

		} catch (IOException e) {
			throw new RandomAccessFileException("Error writing random access file", e, ErrorCode.RANDOM_ACCESS_FILE_WRITE);
		}
	}
	
	public long readClientLineNo() throws RandomAccessFileException {
		
		long lineNo = 0;
		try {
			
			RandomAccessFile fileStore = new RandomAccessFile(getClientFile(), "r");
			fileStore.seek(0);
			lineNo = fileStore.readLong();
			fileStore.close();

		} catch (IOException e) {
			//throw new RandomAccessFileException("Error reading random access file", e, ErrorCode.RANDOM_ACCESS_FILE_READ);
		}
		
		return lineNo;
	}
	
	private long readIndex(long lineNo) throws RandomAccessFileException {
		
		long indexPosition = 0;
		
		try {
			
			RandomAccessFile fileStore = new RandomAccessFile(getIndexFileName(), "r");
			fileStore.seek(lineNo * 8);
			indexPosition = fileStore.readLong();
			fileStore.close();

		} catch (EOFException e) {
			return -1;
		}
		catch (IOException e) {
			throw new RandomAccessFileException("Error writing random access file", e, ErrorCode.RANDOM_ACCESS_FILE_READ);
		}
		
		return indexPosition;
	}
	
	private String getDataFileName() {
		return dataDir + File.separator + dataName + ".dat";
	}
	
	private String getIndexFileName() {
		return dataDir + File.separator + dataName + ".inx";
	}
	
	private String getClientFile() {
		return dataDir + File.separator + "client.sync";
	}
	
}
