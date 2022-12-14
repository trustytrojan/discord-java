package discord.util;

import org.json.simple.JSONValue;

import java.io.FileInputStream;
import java.io.FileReader;

public class Util {
  public static String getTokenFromFile(final String filePath) throws Exception {
    final var stream = new FileInputStream(filePath);
    final var token = new String(stream.readAllBytes());
    stream.close();
    return token;
  }

  public static Object readJsonValueFromFile(final String filePath) throws Exception {
    return JSONValue.parseWithException(new FileReader(filePath));
  }

  public static Object readJsonValueFromString(final String s) throws Exception {
    return JSONValue.parseWithException(s);
  }
}
