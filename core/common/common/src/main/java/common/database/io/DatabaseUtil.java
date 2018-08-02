package common.database.io;

import java.util.UUID;

public class DatabaseUtil {
	
	public static String getNewId() {
		return UUID.randomUUID().toString();
	}
	
}
