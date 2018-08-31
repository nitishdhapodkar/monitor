package serverCore.machine;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import common.util.mongo.MongoQueryExecutor;
import serverCore.constants.GlobalConstants;
import serverCore.exception.MachineException;

public class MachineImpl implements iMachine {

	@Override
	public void add(MachineVo machineVo) throws MachineException {
		MongoQueryExecutor.insertIntoDB(GlobalConstants.metaMasterDatabase, GlobalConstants.Collection_machine, new Gson().toJson(machineVo));

	}

	@Override
	public void remove(String id) throws MachineException {
		MongoQueryExecutor.removeAll(GlobalConstants.Collection_machine, GlobalConstants.Collection_machine);

	}

	@Override
	public void update(MachineVo machineVo) throws MachineException {
		MongoQueryExecutor.updateById(GlobalConstants.Collection_machine, GlobalConstants.Collection_machine, machineVo.getId(), new Gson().toJson(machineVo));
	}

	@Override
	public MachineVo get(String id) throws MachineException {
		String machineJSON = MongoQueryExecutor.getById(GlobalConstants.metaMasterDatabase, GlobalConstants.Collection_machine, id);
		return new Gson().fromJson(machineJSON, MachineVo.class);
	}

	@Override
	public List<MachineVo> getAll() throws MachineException {
		
		List<MachineVo> machines = null;
		List<String> machineJSONs = MongoQueryExecutor.getDBList(GlobalConstants.metaMasterDatabase, GlobalConstants.Collection_machine, "{}");
		
		for(String machineJSON : machineJSONs) {
			
			MachineVo machine = new Gson().fromJson(machineJSON, MachineVo.class);
			if(machines == null) {
				machines = new ArrayList<>();
			}
			
			machines.add(machine);
		}
		
		return machines;
	}

}
