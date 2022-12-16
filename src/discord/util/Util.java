package discord.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.FileInputStream;

public class Util {
  public static String read_entire_file(final String filePath) throws Exception {
    final var stream = new FileInputStream(filePath);
    final var token = new String(stream.readAllBytes());
    stream.close();
    return token;
  }

  public static JSONObject parse_object(final String s) throws Exception {
    return (JSONObject)JSONValue.parseWithException(s);
  }

  public static JSONArray parse_array(final String s) throws Exception {
    return (JSONArray)JSONValue.parseWithException(s);
  }
}
