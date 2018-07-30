package clientcore.output.parser;

public class DiskUsages {

	private String diskName;
	
	private float totalSize;
	
	private float UsedSize;

	

	public DiskUsages(String diskName, float totalSize, float usedSize) {
		this.diskName = diskName;
		this.totalSize = totalSize;
		UsedSize = usedSize;
	}

	public String getDiskName() {
		return diskName;
	}

	public void setDiskName(String diskName) {
		this.diskName = diskName;
	}

	public float getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(float totalSize) {
		this.totalSize = totalSize;
	}

	public float getUsedSize() {
		return UsedSize;
	}

	public void setUsedSize(float usedSize) {
		UsedSize = usedSize;
	}
}
