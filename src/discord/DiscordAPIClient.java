package discord;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
//import java.util.concurrent.CompletableFuture;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class DiscordAPIClient {
  private HttpClient http_client = HttpClient.newHttpClient();
  private String base_url = "https://discord.com/api/v";
  private String token;

  private static enum HttpMethod { GET, POST, PUT, PATCH, DELETE };

  public DiscordAPIClient(int api_version) {
    this.base_url += api_version;
  }

  public DiscordAPIClient(int api_version, String token) {
    this.base_url += api_version;
    this.token = token;
  }

  public void setToken(String token) {
    this.token = "Bot "+token;
  }

  private HttpRequest buildRequest(String path, HttpMethod method, String body) throws Exception {
    System.out.printf("Fetching %s\n", this.base_url+path);
    final var request_builder = HttpRequest.newBuilder(new URI(this.base_url+path));
    BodyPublisher bp = null;
    if(body != null)
      bp = BodyPublishers.ofString(body);
    switch(method) {
      case GET: request_builder.GET(); break;
      case POST: request_builder.POST(bp); break;
      case PUT: request_builder.PUT(bp); break;
      case PATCH: request_builder.method("PATCH", bp);
      case DELETE: request_builder.DELETE();
    }
    if(this.token != null)
      request_builder.header("authorization", this.token);
    HttpRequest r;
    System.out.println(r = request_builder.build());
    return r;
  }

  private HttpRequest buildRequest(String path, HttpMethod method) throws Exception {
    return buildRequest(path, method, null);
  }

  // public CompletableFuture<HttpResponse<String>> getAsync(String path) throws Exception {
  //   return this.http_client.sendAsync(buildRequest(path, HttpMethod.GET), BodyHandlers.ofString());
  // }

  public JSONObject get(String path) throws Exception {
    final var body = this.http_client.send(buildRequest(path, HttpMethod.GET), BodyHandlers.ofString()).body();
    return (JSONObject)JSONValue.parse(body);
  }
}