package discord.gateway;

import java.net.URI;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class GatewayClient extends WebSocketClient {
  public GatewayClient(URI uri) {
    super(uri);
  }

  public void onOpen(ServerHandshake handshake) {
    System.out.println("Connected to Discord Gateway");
  }

  public void onMessage(String message) {
    System.out.println("From Discord Gateway: "+message);
  }

  public void onClose(int code, String reason, boolean remote) {
    System.out.println("Disconnected from Discord Gateway");
  }

  public void onError(Exception e) {
    e.printStackTrace();
  }
}
