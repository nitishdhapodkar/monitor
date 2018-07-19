package common.logger.config;

import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.writers.FileWriter;

public class Setup {
	
	public static void init() {
		Configurator.defaultConfig()
		   .writer(new FileWriter("test.log"))
		   .level(Level.WARNING)
		   .activate();
	}
	
	public static void setLogingLevel(Level level) {
		Configurator.defaultConfig()
		   .writer(new FileWriter("test.log"))
		   .level(level)
		   .activate();
	}
}
