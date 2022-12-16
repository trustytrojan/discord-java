package discord.managers;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import discord.client.Client;
import discord.structures.channels.BaseChannel;
import discord.structures.channels.DMChannel;
import discord.structures.channels.ChannelType;
import discord.util.Util;

public class ChannelManager extends BaseManager {
  public final HashMap<String, BaseChannel> cache = new HashMap<String, BaseChannel>();

  public ChannelManager(Client client) {
    super(client);
  }

  private BaseChannel createCorrectChannel(JSONObject data) {
    final var type = ChannelType.get(Math.toIntExact((long)data.get("type")));
    switch(type) {
      // GuildText
      case DM: return (BaseChannel)(new DMChannel(this.client, data));
      // ...
      default: return null;
    }
  }

  public CompletableFuture<BaseChannel> fetch(String id, boolean force) {
    return CompletableFuture.supplyAsync(() -> {
      if(!force) {
        final var cached = this.cache.get(id);
        if(cached != null) return cached;
      }
      JSONObject data;
      try { data = Util.parse_object(this.client.api.get("/channels/"+id)); }
      catch(Exception e) { e.printStackTrace(); return null; }
      final var channel = this.createCorrectChannel(data);
      this.cache.put(id, channel);
      return channel;
    });
  }

  public CompletableFuture<Void> fetchDMs() {
    return CompletableFuture.runAsync(() -> {
      JSONArray dms;
      try { dms = Util.parse_array(this.client.api.get("/users/@me/channels")); }
      catch(Exception e) { e.printStackTrace(); return; }
      for(Object dm : dms) {
        final var _dm = this.createCorrectChannel((JSONObject)dm);
        this.cache.put(_dm.id, _dm);
      }
    });
  }
}
