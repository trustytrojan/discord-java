package discord.managers;

import discord.Client;

public class BaseManager {
  protected Client client;

  BaseManager(Client client) {
    this.client = client;
  }
}
