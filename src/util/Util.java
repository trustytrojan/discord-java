package util;

import org.json.simple.JSONValue;

import java.io.FileInputStream;
import java.io.FileReader;

import io.qt.widgets.*;
import io.qt.widgets.QLineEdit.EchoMode;

public class Util {
  public static String getTokenFromFile(String filePath) throws Exception {
    var stream = new FileInputStream(filePath);
    var token = new String(stream.readAllBytes());
    stream.close();
    return token;
  }

  public static Object readJsonValueFromFile(String filePath) throws Exception {
    return JSONValue.parseWithException(new FileReader(filePath));
  }

  public static Object readJsonValueFromString(String s) throws Exception {
    return JSONValue.parseWithException(s);
  }

  public static QLineEdit QLineEdit(EchoMode em) {
    final var le = new QLineEdit();
    if(em != null) le.setEchoMode(em);
    return le;
  }

  public static QHBoxLayout QHBoxLayout(QAbstractButton ...btns) {
    final var hbox = new QHBoxLayout();
    for(final var btn : btns)
      hbox.addWidget(btn);
    return hbox;
  }
}
