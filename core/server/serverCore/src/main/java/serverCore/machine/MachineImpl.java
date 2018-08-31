package serverCore.machine;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import common.constants.ErrorCode;
import common.util.mongo.MongoQueryExecutor;
import serverCore.constants.GlobalConstants;
import serverCore.exception.MachineException;

public class MachineImpl implements iMachine {

	@Override
	public void add(MachineVo machineVo) throws MachineException {
		try {
			MongoQueryExecutor.insertIntoDB(GlobalConstants.metaMasterDatabase, GlobalConstants.Collection_machine, new Gson().toJson(machineVo));
		} catch (Exception e) {
			throw new MachineException("Error creating machine", e, ErrorCode.MACHINE);
		}

	}

	@Override
	public void remove(String id) throws MachineException {
		try {
			MongoQueryExecutor.removeAll(GlobalConstants.Collection_machine, GlobalConstants.Collection_machine);
		} catch (Exception e) {
			throw new MachineException("Error removing machine", e, ErrorCode.MACHINE);
		}

	}

	@Override
	public void update(MachineVo machineVo) throws MachineException {
		try {
			MongoQueryExecutor.updateById(GlobalConstants.Collection_machine, GlobalConstants.Collection_machine, machineVo.getId(), new Gson().toJson(machineVo));
		} catch (Exception e) {
			throw new MachineException("Error updating machine", e, ErrorCode.MACHINE);
		}
	}

	@Override
	public MachineVo get(String id) throws MachineException {
		
		MachineVo machineVo = null;
		try {
			String machineJSON = MongoQueryExecutor.getById(GlobalConstants.metaMasterDatabase, GlobalConstants.Collection_machine, id);
			machineVo = new Gson().fromJson(machineJSON, MachineVo.class);
		} catch (JsonSyntaxException e) {
			throw new MachineException("Error featching machine", e, ErrorCode.MACHINE);
		}
		
		return machineVo;
	}

	@Override
	public List<MachineVo> getAll() throws MachineException {
		
		List<MachineVo> machines = null;
		
		try {
			List<String> machineJSONs = MongoQueryExecutor.getDBList(GlobalConstants.metaMasterDatabase, GlobalConstants.Collection_machine, "{}");
			
			for(String machineJSON : machineJSONs) {
				MachineVo machine = new Gson().fromJson(machineJSON, MachineVo.class);
				if(machines == null) {
					machines = new ArrayList<>();
				}
				machines.add(machine);
			}
			
		} catch (JsonSyntaxException e) {
			throw new MachineException("Error featching all machine", e, ErrorCode.MACHINE);
		}
		
		return machines;
	}

}
