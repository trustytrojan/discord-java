package discord.managers;

import discord.client.Client;

public abstract class BaseManager {
  protected final Client client;

  BaseManager(Client client) {
    this.client = client;
  }
}
