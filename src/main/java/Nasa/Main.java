package Nasa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=TGIdKT56nCFNs0mY3xvbevR2B2Rd5ahlMNBsLI0g");
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();
             CloseableHttpResponse response = httpClient.execute(request)) {
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            List<NasaMedia> nasaMediaList = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
            });
            ;
            nasaMediaList.forEach(i -> {
                HttpGet get = new HttpGet(i.getUrl());
                File file = new File("nasa.jpg");


                try (CloseableHttpResponse respounseIMG = httpClient.execute(get);
                     BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                     ){
                    byte[] bytes = respounseIMG.getEntity().getContent().readAllBytes();
                    bos.write(bytes);
                    bos.flush();
                    System.out.println(i.getDate()+ "\n"+ i.getExplanation());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
