package common.logger.config;

import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;
import org.pmw.tinylog.writers.FileWriter;

public class Setup {
	
	public static void init() {
		Configurator.defaultConfig()
		   .writer(new FileWriter("d:\test.log"))
		   .level(Level.WARNING)
		   .activate();
	}
	
	public static void setLogingLevel(Level level) {
		Configurator.defaultConfig()
		   .writer(new FileWriter("test.log"))
		   .level(level)
		   .activate();
	}
	
	public static void main(String[] args) {
		Logger.warn("12");
		Logger.info("12");
	}
}
