package discord;

import org.json.simple.JSONObject;

public class Base {
  public String id;
  protected Client client;

  protected Base(Client client, JSONObject data) {
    this.id = (String)data.get("id");
  }
}
