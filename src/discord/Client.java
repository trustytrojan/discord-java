package discord;

import discord.managers.UserManager;

public class Client {
  final public DiscordAPIClient api = new DiscordAPIClient(10);
  final public UserManager users = new UserManager(this);
  public User user;

  public User login(String token) throws Exception {
    this.api.setToken(token);
    this.user = this.users.fetchMe().get();
    if(this.user.bot) {
      
    }
    return this.user;
  }
}