package json;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonTraitement {
	
	public static void createJsonFromObject(String filename, JSONObject object) throws Exception {
	    Files.write(Paths.get(filename), object.toString().getBytes());
	}
	
	public static JSONObject convertJsonToObject(String filename) throws FileNotFoundException, JSONException {
		try (Scanner useDelimiter = new Scanner(new File(filename)).useDelimiter("\\Z")) {
			String myJson = useDelimiter.next();
			JSONObject myJsonobject = new JSONObject(myJson);
			return myJsonobject;
		}
	}
}
