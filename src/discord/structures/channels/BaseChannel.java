package discord.structures.channels;

import org.json.simple.JSONObject;

import discord.client.Client;
import discord.structures.Base;

public abstract class BaseChannel extends Base {
  public final ChannelType type;

  protected BaseChannel(Client client, JSONObject data) {
    super(client, data);
    this.type = ChannelType.get(Math.toIntExact((long)data.get("type")));
  }
}
