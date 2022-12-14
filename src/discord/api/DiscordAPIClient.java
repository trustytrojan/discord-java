package discord.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
//import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletableFuture;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class DiscordAPIClient {
  private final HttpClient http_client = HttpClient.newHttpClient();
  private final String base_url = "https://discord.com/api/v10";
  private String token;

  private static enum HttpMethod { GET, POST, PUT, PATCH, DELETE };

  public DiscordAPIClient() {}

  public DiscordAPIClient(String token) {
    this.token = token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  private HttpRequest buildRequest(final String path, final HttpMethod method, final String body) throws Exception {
    final var url = this.base_url+path;
    System.out.printf("Fetching %s\n", url);
    final var request_builder = HttpRequest.newBuilder(new URI(url));
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

  private HttpRequest buildRequest(final String path, final HttpMethod method) throws Exception {
    return buildRequest(path, method, null);
  }

  // public CompletableFuture<HttpResponse<String>> getAsync(String path) throws Exception {
  //   return this.http_client.sendAsync(buildRequest(path, HttpMethod.GET), BodyHandlers.ofString());
  // }

  public JSONObject get(final String path) throws Exception {
    final var body = this.http_client.send(buildRequest(path, HttpMethod.GET), BodyHandlers.ofString()).body();
    return (JSONObject)JSONValue.parse(body);
  }

  public CompletableFuture<JSONObject> getAsync(final String path) {
    return CompletableFuture.supplyAsync(() -> {
      JSONObject data;
      try { data = this.get(path); }
      catch(Exception e) { e.printStackTrace(); return null; }
      return data;
    });
  }
}