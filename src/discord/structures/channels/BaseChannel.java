package discord.structures.channels;

import org.json.simple.JSONObject;

import discord.client.BaseClient;
import discord.structures.Base;

public abstract class BaseChannel extends Base {
  public ChannelType type;

  protected BaseChannel(BaseClient client, JSONObject data) {
    super(client, data);
    this.copyFromJSONObject(data);
  }

  private void copyFromJSONObject(JSONObject data) {
    this.type = ChannelType.correctChannelType((byte)data.get("type"));
  }
}
