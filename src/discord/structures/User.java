package discord.structures;

import java.util.concurrent.CompletableFuture;

import org.json.simple.JSONObject;

import discord.client.Client;
import discord.util.Util;

public class User extends Base {
  public String username;
  public String discriminator;
  public String avatar;
  public Boolean bot;
  public String banner;
  public String banner_color;
  public Integer accent_color;
  public UserProfile profile;

  public User(Client client, JSONObject data) {
    super(client, data);
    this.copyFromJSONObject(data);
    this.fetchProfile();
  }

  public String tag() {
    return this.username + '#' + this.discriminator;
  }

  public String toString() {
    return this.tag();
  }

  public CompletableFuture<User> fetch() {
    return CompletableFuture.supplyAsync(() -> {
      JSONObject data;
      try { data = Util.parse_object(this.client.api.get("/users/"+this.id)); }
      catch(Exception e) { e.printStackTrace(); return null; }
      this.copyFromJSONObject(data);
      return this;
    });
  }

  public CompletableFuture<UserProfile> fetchProfile() {
    return CompletableFuture.supplyAsync(() -> {
      JSONObject data;
      try { data = Util.parse_object(this.client.api.get("/users/"+this.id+"/profile")); }
      catch(Exception e) { e.printStackTrace(); return null; }
      this.profile = new UserProfile(data);
      return this.profile;
    });
  }

  private void copyFromJSONObject(JSONObject data) {
    this.username = (String)data.get("username");
    this.discriminator = (String)data.get("discriminator");
    this.avatar = (String)data.get("avatar");
    this.bot = (Boolean)data.get("bot");
    this.banner = (String)data.get("banner");
    this.banner_color = (String)data.get("banner_color");
    final var accent_color = data.get("accent_color");
    if(accent_color != null)
      this.accent_color = Math.toIntExact((long)accent_color);
  }
}
