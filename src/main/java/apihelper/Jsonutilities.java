package apihelper;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.util.Constants;

import io.restassured.response.Response;

public class Jsonutilities {
	public static void storeJsonResponse(Response response, String filename) {
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(response.getBody().prettyPrint());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try (FileWriter file = new FileWriter(Constants.RESPONSEJSONFOLDERPATH + filename + ".json")) {
			file.write(String.valueOf(json));
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getValueByJPath(Response response, String jpath) {
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = (Object) parser.parse(response.getBody().asString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (String s : jpath.split("/"))
			if (!s.isEmpty())
				if (!(s.contains("[") || s.contains("]")))
					obj = ((JSONObject) obj).get(s);
				else if (s.contains("[") || s.contains("]"))
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0]))
							.get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
		return obj.toString();
	}

	// Read Json File by path
	public static String readValueByJPath(String responseJsonFilePath, String filename, String jpath)
			throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(responseJsonFilePath + filename + ".json"));
		for (String s : jpath.split("/"))
			if (!s.isEmpty())
				if (!(s.contains("[") || s.contains("]")))
					obj = ((JSONObject) obj).get(s);
				else if (s.contains("[") || s.contains("]"))
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0]))
							.get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
		return obj.toString();
	}
}
