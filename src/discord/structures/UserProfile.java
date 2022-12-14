package discord.structures;

import java.util.Date;

import org.json.simple.JSONObject;

public class UserProfile {
  public final Object[] connected_accounts;
  public final Date premium_since;
  public final int premium_type;
  public final Date premium_guild_since;
  public final Object[] mutual_guilds;
  
  public UserProfile(JSONObject data) {
    this.connected_accounts = (Object[])data.get("connected_accounts");
    this.premium_since = (Date)data.get("premium_since");
    this.premium_type = (int)data.get("premium_type");
    this.premium_guild_since = (Date)data.get("premium_guild_since");
    this.mutual_guilds = (Object[])data.get("mutual_guilds");
  }
}
