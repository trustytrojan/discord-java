package discord.structures;

import java.util.concurrent.CompletableFuture;

import org.json.simple.JSONObject;

import discord.client.Client;

public class User extends Base {
  public String username;
  public String discriminator;
  public String avatar;
  public boolean bot;
  public String banner;
  public int accent_color;

  // properties obtained from /users/{this.id}/profile
  public String bio;

  public User(Client client, JSONObject data) {
    super(client, data);
    System.out.println(data);
    this.username = (String)data.get("username");
    this.discriminator = (String)data.get("discriminator");
  }

  public String tag() {
    return this.username + '#' + this.discriminator;
  }

  public CompletableFuture<User> fetch() {
    return CompletableFuture.supplyAsync(() -> {
      JSONObject data;
      try { data = this.client.api.get("/users/"+this.id); }
      catch(Exception e) { e.printStackTrace(); return null; }
      this.copyFromJSONObject(data);
      return this;
    });
  }

  private void copyFromJSONObject(JSONObject data) {
    this.username = (String)data.get("username");
    this.discriminator = (String)data.get("discriminator");
    this.avatar = (String)data.get("avatar");
    this.bio = (String)data.get("bio");
    this.bot = (boolean)data.get("bot");
  }
}
