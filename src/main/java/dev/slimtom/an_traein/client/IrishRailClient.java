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

    public String getRawStationData(String stationName) throws Exception {
        String encodedStationName = URLEncoder.encode(stationName, StandardCharsets.UTF_8);
        String url = "https://api.irishrail.ie/realtime/realtime.asmx/getStationDataByNameXML?StationDesc=" + encodedStationName;

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
