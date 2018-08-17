package clientcore.test.command;

import clientcore.external.controller.Controller;
import common.jobs.Command;
import common.jobs.CommandType;
import common.jobs.CronJob;

public class TestJobWrite {
	
	public static void main(String[] args) {
		Controller controller = new Controller();
		
		controller.addJob(new CronJob("test1", "test job for every 10 min", "0 0/10 * * * ?", null, new Command(CommandType.PROCESSES, "Get-Process | Format-list")));
		controller.addJob(new CronJob("test2", "test job", "* * * * * ?", null, new Command(CommandType.PROCESSES, "Get-Process | Format-list")));
		controller.addJob(new CronJob("test3", "test job", "0 * * * * ?", null, new Command(CommandType.PROCESSES, "Get-Process | Format-list")));
		controller.addJob(new CronJob("HWINFO", "Hardware Informations", "* * * * * ?", null, new Command(CommandType.HWDINFO, "systeminfo /FO CSV")));
	}

}
