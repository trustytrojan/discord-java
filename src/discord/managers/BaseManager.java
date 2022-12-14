package discord.managers;

import discord.client.BaseClient;

public abstract class BaseManager {
  protected final BaseClient client;

  BaseManager(BaseClient client) {
    this.client = client;
  }
}
