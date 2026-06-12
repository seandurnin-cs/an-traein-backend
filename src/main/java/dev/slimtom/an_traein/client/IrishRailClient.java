package dev.slimtom.an_traein.client;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Component
public class IrishRailClient {
    private final HttpClient httpClient = HttpClient.newHttpClient();
}
