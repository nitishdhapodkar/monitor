package clientcore.output.parser;

public class SoftwareVo {

	private String id;
	
	private String name;
	
	private String vender;
	
	private String version;
	
	private String caption;
	
	private String installedLocation;

	public SoftwareVo(String id, String name, String vender, String version, String caption, String installedLocation) {
		this.id = id;
		this.name = name;
		this.vender = vender;
		this.version = version;
		this.caption = caption;
		this.installedLocation = installedLocation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVender() {
		return vender;
	}

	public void setVender(String vender) {
		this.vender = vender;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getInstalledLocation() {
		return installedLocation;
	}

	public void setInstalledLocation(String installedLocation) {
		this.installedLocation = installedLocation;
	}
}
