package com.postalservice.client;
import java.net.http.*; import java.net.*; import java.nio.charset.StandardCharsets;
public class ApiClient {
  private final HttpClient http = HttpClient.newHttpClient();
  private final String base = "http://localhost:8080/api";
  public void sendLetter(String country, String name) throws Exception {
    HttpRequest req = HttpRequest.newBuilder(URI.create(base+"/letter/"+country+"/"+URLEncoder.encode(name, StandardCharsets.UTF_8)))
        .POST(HttpRequest.BodyPublishers.noBody()).build();
    http.send(req, HttpResponse.BodyHandlers.discarding());
  }
  public void sendParcel(double weight, String name) throws Exception {
    HttpRequest req = HttpRequest.newBuilder(URI.create(base+"/package/"+weight+"/"+URLEncoder.encode(name, StandardCharsets.UTF_8)))
        .POST(HttpRequest.BodyPublishers.noBody()).build();
    http.send(req, HttpResponse.BodyHandlers.discarding());
  }
  public String status() throws Exception {
    HttpRequest req = HttpRequest.newBuilder(URI.create(base+"/status")).GET().build();
    return http.send(req, HttpResponse.BodyHandlers.ofString()).body();
  }
}
