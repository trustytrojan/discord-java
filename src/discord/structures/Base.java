package discord.structures;

import org.json.simple.JSONObject;

import discord.client.BaseClient;

public class Base {
  public String id;
  protected final BaseClient client;

  protected Base(BaseClient client, JSONObject data) {
    this.client = client;
    this.id = (String)data.get("id");
  }
}
