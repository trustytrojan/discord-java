package discord.structures;

import org.json.simple.JSONObject;

import discord.client.Client;

public class ClientUser extends User {
  

  public ClientUser(Client client, JSONObject data) {
    super(client, data);
    
  }
}
