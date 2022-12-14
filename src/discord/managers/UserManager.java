package discord.managers;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import org.json.simple.JSONObject;

import discord.client.BaseClient;
import discord.structures.ClientUser;
import discord.structures.User;

public class UserManager extends BaseManager {
  public final HashMap<String, User> cache = new HashMap<String, User>();

  public UserManager(BaseClient client) {
    super(client);
  }

  public CompletableFuture<User> fetch(String id, boolean force) {
    return CompletableFuture.supplyAsync(() -> {
      if(!force) {
        final var cached = this.cache.get(id);
        if(cached != null) return cached;
      }
      JSONObject data;
      try { data = this.client.api.get("/users/"+id); }
      catch(Exception e) { e.printStackTrace(); return null; }
      final var user = new User(this.client, data);
      this.cache.put(id, user);
      return user;
    });
  }

  public CompletableFuture<ClientUser> fetchMe() {
    return CompletableFuture.supplyAsync(() -> {
      JSONObject data;
      try { data = this.client.api.get("/users/@me"); }
      catch(Exception e) { e.printStackTrace(); return null; }
      final var me = new ClientUser(this.client, data);
      this.cache.put(me.id, me);
      return me;
    });
  }
}
