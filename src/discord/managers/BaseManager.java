package discord.managers;

import discord.client.Client;

public class BaseManager {
  protected Client client;

  BaseManager(Client client) {
    this.client = client;
  }
}
