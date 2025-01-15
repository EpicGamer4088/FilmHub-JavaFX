package com.filmhub.filmhubjavafx.services;

import com.filmhub.filmhubjavafx.utils.ApiKeyManager;
import com.filmhub.filmhubjavafx.utils.ApiKeySetup;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class TMDBService
{
    private static final String BASE_URL = "https://api.themoviedb.org/3";

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private String apiKey;

    public TMDBService()
    {
        setApiKey(initializeApiKey());
    }

    public List<String> searchMovies(String query) throws IOException, InterruptedException
    {
        String url = String.format("%s/search/movie?api_key=%s&query=%s", BASE_URL, getApiKey(), query.replace(" ", "%20"));
        return makeRequest(url, "title");
    }

    public JsonObject getMovieDetails(int movieId) throws IOException, InterruptedException
    {
        String url = String.format("%s/movie/%d?api_key=%s", BASE_URL, movieId, getApiKey());
        return makeDetailRequest(url);
    }

    public List<String> searchTVShows(String query) throws IOException, InterruptedException
    {
        String url = String.format("%s/search/tv?api_key=%s&query=%s", BASE_URL, getApiKey(), query.replace(" ", "%20"));
        return makeRequest(url, "name");
    }

    public JsonObject getTVShowDetails(int tvId) throws IOException, InterruptedException
    {
        String url = String.format("%s/tv/%d?api_key=%s", BASE_URL, tvId, getApiKey());
        return makeDetailRequest(url);
    }

    private List<String> makeRequest(String url, String titleKey) throws IOException, InterruptedException
    {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray results = jsonResponse.getAsJsonArray("results");

        List<String> searchResults = new ArrayList<>();
        for (int i = 0; i < results.size(); i++)
        {
            JsonObject item = results.get(i).getAsJsonObject();
            String name = item.has(titleKey) ? item.get(titleKey).getAsString() : "Unknown";
            searchResults.add(name);
        }

        return searchResults;
    }

    private JsonObject makeDetailRequest(String url) throws IOException, InterruptedException
    {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return JsonParser.parseString(response.body()).getAsJsonObject();
    }

    private String initializeApiKey()
    {
        String tempApiKey = null;

        try
        {
            tempApiKey = ApiKeyManager.loadApiKey();
        } catch (IOException | ClassNotFoundException e)
        {
            ApiKeySetup.setupApiKey();
            try
            {
                tempApiKey = ApiKeyManager.loadApiKey();
            } catch (IOException | ClassNotFoundException ex)
            {
                throw new RuntimeException("API-Key could not be loaded, even after setup.", ex);
            }
        }

        return tempApiKey;
    }

    public HttpClient getHttpClient()
    {
        return httpClient;
    }

    public String getApiKey()
    {
        return apiKey;
    }

    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }
}
