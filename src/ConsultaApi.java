import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConectorApi {
    public ResponseConvertion responseConvertion(
            String refCoin, String targetCoin, String amount){
        String apiKey = "4f51301084b608181dfbf724";
        String urlBase = "https://v6.exchangerate-api.com/v6/";

        URI addressUrl = URI.create(urlBase + apiKey + "/pair/" + refCoin + "/" + targetCoin + "/" + amount);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(addressUrl)
                .build();
        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), ResponseConvertion.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
