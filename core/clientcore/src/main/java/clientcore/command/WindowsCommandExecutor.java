package clientcore.command;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;

import clientcore.exceptions.ErrorCode;
import clientcore.exceptions.SqCommandException;

public class WindowsCommandExecutor implements CommandExecutor{

	@Override
	public String executeCommand(String command) throws SqCommandException {
		String consoleOut = null;
		
		try {
			PowerShellResponse response = PowerShell.executeSingleCommand(command);
			consoleOut = response.getCommandOutput().toString().toLowerCase();

		} catch (Exception e) {
			throw new SqCommandException("Error in executing command :" + command, e, ErrorCode.COMMAND_EXECUTION);
		}
		
		return consoleOut;
	}

}
