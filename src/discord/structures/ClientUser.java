package discord.structures;

import org.json.simple.JSONObject;

import discord.client.BaseClient;

public class ClientUser extends User {
  

  public ClientUser(BaseClient client, JSONObject data) {
    super(client, data);
    
  }
}
