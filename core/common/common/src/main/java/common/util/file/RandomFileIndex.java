package common.util.file;

import java.io.Serializable;

public class RandomFileIndex implements Serializable{
	
	private static final long serialVersionUID = -6620484006045783427L;

	private long lineNo;
	
	private long position;

	public RandomFileIndex(long lineNo, long position) {
		this.lineNo = lineNo;
		this.position = position;
	}

	public long getLineNo() {
		return lineNo;
	}

	public void setLineNo(long lineNo) {
		this.lineNo = lineNo;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}
}
