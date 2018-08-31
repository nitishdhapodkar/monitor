package serverCore.licence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import common.constants.ErrorCode;
import common.util.mongo.MongoQueryExecutor;
import serverCore.constants.GlobalConstants;
import serverCore.exception.LicenceException;

public class LicenceImpl implements iLicence{

	@Override
	public void add(LicenceVo licenceVo) throws LicenceException {
		
		try {
			MongoQueryExecutor.insertIntoDB(GlobalConstants.metaMasterDatabase, GlobalConstants.Collection_licence, new Gson().toJson(licenceVo));
		} catch (Exception e) {
			throw new LicenceException("Error creating new licence", e, ErrorCode.LICENCE);
		}
	}

	@Override
	public void update(LicenceVo licenceVo) throws LicenceException {
		try {
			MongoQueryExecutor.updateById(GlobalConstants.metaMasterDatabase, GlobalConstants.Collection_licence, licenceVo.getId(), new Gson().toJson(licenceVo));
		} catch (Exception e) {
			throw new LicenceException("Error updating licence", e, ErrorCode.LICENCE);
		}
	}

	@Override
	public void remove(String id) throws LicenceException {
		try {
			MongoQueryExecutor.removeById(GlobalConstants.metaMasterDatabase, GlobalConstants.Collection_licence, id);
		} catch (Exception e) {
			throw new LicenceException("Error removing licence", e, ErrorCode.LICENCE);
		}
		
	}

	@Override
	public LicenceVo get(String id) throws LicenceException {
		LicenceVo licenceVo = null;
		
		try {
			String licenceJSON = MongoQueryExecutor.getById(GlobalConstants.metaMasterDatabase, GlobalConstants.Collection_licence, id);
			licenceVo = new Gson().fromJson(licenceJSON, LicenceVo.class);
		} catch (Exception e) {
			throw new LicenceException("Error featching licence", e, ErrorCode.LICENCE);
		}
		return licenceVo;
	}

	@Override
	public List<LicenceVo> getAll() throws LicenceException {
		
		List<LicenceVo> licencies = null;
		
		try {
			List<String> licenceJSONs = MongoQueryExecutor.getDBList(GlobalConstants.metaMasterDatabase, GlobalConstants.Collection_licence, "{}");
			for(String licenceJSON : licenceJSONs) {
				LicenceVo licenceVo = new Gson().fromJson(licenceJSON, LicenceVo.class);
				if(licencies == null) {
					licencies = new ArrayList<>();
				}
				licencies.add(licenceVo);
			}
		} catch (JsonSyntaxException e) {
			throw new LicenceException("Error featching all licencies", e, ErrorCode.LICENCE);
		}
		return licencies;
	}

	@Override
	public boolean validate(String id) throws LicenceException {
		return islicenceNotExpire(id) && validateNo(id);
	}

	@Override
	public boolean islicenceNotExpire(String id) throws LicenceException {
		
		boolean isNotExpired = false; 
		try {
			LicenceVo licenceVo = get(id);
			isNotExpired = licenceVo.getExpiryDate().before(new Date());
		} catch (Exception e) {
			throw new LicenceException("Error validating licencies", e, ErrorCode.LICENCE);
		}
		
		return isNotExpired;
	}

	@Override
	public boolean validateNo(String id) throws LicenceException {
		
		boolean isProperNo = false;
		
		try {
			LicenceVo licenceVo = get(id);
			isProperNo = getLicenceCount(id) > licenceVo.getNoOfEndpoints();
		} catch (Exception e) {
			throw new LicenceException("Error validating licencies", e, ErrorCode.LICENCE);
		}
		
		return isProperNo;
	}
	
	private int getLicenceCount(String id) throws LicenceException {
		
		try {
			
		} catch (Exception e) {
			throw new LicenceException("Error validating licencies", e, ErrorCode.LICENCE);
		}
		return 0;
	}
	
}
