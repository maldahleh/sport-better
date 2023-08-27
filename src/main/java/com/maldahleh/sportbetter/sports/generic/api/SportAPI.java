package com.maldahleh.sportbetter.sports.generic.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maldahleh.sportbetter.sports.generic.model.SeasonType;
import com.maldahleh.sportbetter.sports.mlb.models.Game;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

public abstract class SportAPI {

  protected abstract String getBaseUrl();
  protected abstract String getSchedulePath(String year, SeasonType seasonType);

  public List<Game> getGames(String year, SeasonType seasonType)
      throws IOException, InterruptedException {
    String uri = getBaseUrl() + getSchedulePath(year, seasonType);
    List<Game> games = getList(uri);
  }

  public <T> T getObject(String uri) throws IOException, InterruptedException {
    String response = getResponse(uri);
    return newObjectMapper().readValue(response, T.class);
  }

  public <T> List<T> getList(String uri) throws IOException, InterruptedException {
    String response = getResponse(uri);
    return (List<T>) newObjectMapper().readValue(response, List.class);
  }

  private ObjectMapper newObjectMapper() {
    return new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  private String getResponse(String uri) throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(uri))
        .build();

    return HttpClient.newHttpClient()
        .send(request, BodyHandlers.ofString())
        .body();
  }
}
