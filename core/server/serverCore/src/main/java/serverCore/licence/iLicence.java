package serverCore.licence;

import java.util.List;

import serverCore.exception.LicenceException;

public interface iLicence {
	
	public void add(LicenceVo licenceVo) throws LicenceException;
	
	public void update(LicenceVo licenceVo) throws LicenceException;
	
	public void remove(String id) throws LicenceException;
	
	public LicenceVo get(String id) throws LicenceException;
	
	public List<LicenceVo> getAll() throws LicenceException;
	
	public boolean validate(String id) throws LicenceException;
	
	public boolean islicenceNotExpire(String id) throws LicenceException;
	
	public boolean validateNo(String id) throws LicenceException;

}
