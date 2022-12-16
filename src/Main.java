import discord.client.Client;
import discord.util.Util;

// import io.qt.widgets.*;
// import io.qt.widgets.QLineEdit.EchoMode;

public class Main {
  static final Client client = new Client();

  public static void main(String[] args) throws Exception {
    client.login(Util.read_entire_file("token")).get();
    client.channels.fetchDMs().get();
    System.out.println(client.channels.cache);
  }
}