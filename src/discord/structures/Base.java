package discord.structures;

import org.json.simple.JSONObject;

import discord.client.Client;

public class Base {
  public String id;
  protected Client client;

  protected Base(Client client, JSONObject data) {
    this.id = (String)data.get("id");
  }
}
