package com.priceseries.frontend.service;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:/application.properties")
public class RestService {

    private static final Logger log = LoggerFactory.getLogger(RestService.class);
    @Value("${price.app.url}")
    private String priceAppUrl;

    public String getData(int numberOfQuotes) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(priceAppUrl + numberOfQuotes);

        try {
            log.info("sending get request");
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            JsonParser parser = new JsonParser();
            JsonObject o = parser.parse(content).getAsJsonObject();
            return o.get("average").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}