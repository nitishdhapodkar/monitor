package serverCore.licence;

import java.util.List;

public interface iLicence {
	
	public void addLicence(LicenceVo licenceVo);
	
	public void updateLicence(LicenceVo licenceVo);
	
	public void removeLicence(String id);
	
	public LicenceVo get(String id);
	
	public List<LicenceVo> getAll();
	
	public boolean validate(String id);
	
	public boolean islicenceExpired(String id);
	
	public boolean validateNo(String id);

}
