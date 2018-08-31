package serverCore.machine;

import java.util.List;

import serverCore.exception.MachineException;

public interface iMachine {
	
	public void add(MachineVo machineVo) throws MachineException;
	
	public void remove(String id) throws MachineException;
	
	public void update(MachineVo machineVo) throws MachineException;

	public MachineVo get(String id) throws MachineException;
	
	public List<MachineVo> getAll() throws MachineException;
}
