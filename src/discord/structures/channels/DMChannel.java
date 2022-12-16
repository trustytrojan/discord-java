package discord.structures.channels;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import discord.client.Client;
import discord.structures.User;

public class DMChannel extends BaseChannel {
  public String last_message_id;
  public User recipient;

  public DMChannel(Client client, JSONObject data) {
    super(client, data);
    this.copyFromJSONObject(data);
  }

  private void copyFromJSONObject(JSONObject data) {
    this.last_message_id = (String)data.get("last_message_id");
    this.recipient = new User(this.client, (JSONObject)((JSONArray)data.get("recipients")).get(0));
  }
}
