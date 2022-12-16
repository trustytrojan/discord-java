package discord.client;

import java.util.concurrent.CompletableFuture;

import discord.api.DiscordAPIClient;
import discord.managers.UserManager;
import discord.managers.ChannelManager;
import discord.structures.ClientUser;

public class Client {
  public final DiscordAPIClient api = new DiscordAPIClient();
  public final UserManager users = new UserManager(this);
  public final ChannelManager channels = new ChannelManager(this);
  //public final GuildManager guilds = new GuildManager(this);
  public ClientUser user;

  public CompletableFuture<ClientUser> login(String token) {
    this.api.setToken(token);
    return CompletableFuture.supplyAsync(() -> {
      ClientUser user;
      try { user = this.users.fetchMe().get(); }
      catch(Exception e) { e.printStackTrace(); return null; }
      return (this.user = user);
    });
  }
}
