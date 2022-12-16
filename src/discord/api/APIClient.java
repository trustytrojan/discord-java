package discord.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

public class APIClient {
  private final HttpClient http_client = HttpClient.newHttpClient();
  private final String base_url = "https://discord.com/api/v10";
  private String token;

  private static enum HttpMethod { GET, POST, PUT, PATCH, DELETE };

  public APIClient() {}

  public APIClient(String token) {
    this.token = token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  private HttpRequest buildRequest(String path, HttpMethod method, String body) throws Exception {
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
    return request_builder.build();
  }

  private HttpRequest buildRequest(String path, HttpMethod method) throws Exception {
    return buildRequest(path, method, null);
  }

  // public CompletableFuture<HttpResponse<String>> getAsync(String path) throws Exception {
  //   return this.http_client.sendAsync(buildRequest(path, HttpMethod.GET), BodyHandlers.ofString());
  // }

  public String get(String path) throws Exception {
    return this.http_client.send(buildRequest(path, HttpMethod.GET), BodyHandlers.ofString()).body();
  }

  public CompletableFuture<String> getAsync(String path) {
    return CompletableFuture.supplyAsync(() -> {
      try { return this.get(path); }
      catch(Exception e) { e.printStackTrace(); return null; }
    });
  }
}