/**
 * 
 */
package api.ClothingStore.Endpoints;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 */
public class Routes {
	
	public static Properties route = new Properties();
	public static FileInputStream file;
	
	public Properties getURL()
	{
		try (FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/Routes/Routes.properties")) {
	        route.load(file);
	    } catch (FileNotFoundException e) {
	        throw new RuntimeException("Routes.properties file not found", e);
	    } catch (IOException e) {
	        throw new RuntimeException("Error loading Routes.properties", e);
	    }
		return route;
		
	}

}
