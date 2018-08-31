package serverCore.licence;

import java.util.List;

public interface iLicence {
	
	public void add(LicenceVo licenceVo);
	
	public void update(LicenceVo licenceVo);
	
	public void remove(String id);
	
	public LicenceVo get(String id);
	
	public List<LicenceVo> getAll();
	
	public boolean validate(String id);
	
	public boolean islicenceNotExpire(String id);
	
	public boolean validateNo(String id);

}
