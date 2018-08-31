package serverCore.licence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import common.util.mongo.MongoQueryExecutor;
import serverCore.constants.GlobalConstants;

public class LicenceImpl implements iLicence{

	@Override
	public void add(LicenceVo licenceVo) {
		MongoQueryExecutor.insertIntoDB(GlobalConstants.metaMasterDatabase, GlobalConstants.Collection_licence, new Gson().toJson(licenceVo));
	}

	@Override
	public void update(LicenceVo licenceVo) {
		MongoQueryExecutor.updateById(GlobalConstants.metaMasterDatabase, GlobalConstants.Collection_licence, licenceVo.getId(), new Gson().toJson(licenceVo));
	}

	@Override
	public void remove(String id) {
		MongoQueryExecutor.removeById(GlobalConstants.metaMasterDatabase, GlobalConstants.Collection_licence, id);
		
	}

	@Override
	public LicenceVo get(String id) {
		String licenceJSON = MongoQueryExecutor.getById(GlobalConstants.metaMasterDatabase, GlobalConstants.Collection_licence, id);
		return new Gson().fromJson(licenceJSON, LicenceVo.class);
	}

	@Override
	public List<LicenceVo> getAll() {
		
		List<LicenceVo> licencies = null;
		
		List<String> licenceJSONs = MongoQueryExecutor.getDBList(GlobalConstants.metaMasterDatabase, GlobalConstants.Collection_licence, "{}");
		for(String licenceJSON : licenceJSONs) {
			LicenceVo licenceVo = new Gson().fromJson(licenceJSON, LicenceVo.class);
			if(licencies == null) {
				licencies = new ArrayList<>();
			}
			licencies.add(licenceVo);
		}
		return licencies;
	}

	@Override
	public boolean validate(String id) {
		return islicenceNotExpire(id) && validateNo(id);
	}

	@Override
	public boolean islicenceNotExpire(String id) {
		LicenceVo licenceVo = get(id);
		return licenceVo.getExpiryDate().before(new Date());
	}

	@Override
	public boolean validateNo(String id) {
		LicenceVo licenceVo = get(id);
		return getLicenceCount(id) > licenceVo.getNoOfEndpoints();
	}
	
	private int getLicenceCount(String id) {
		return 0;
	}
	
}
