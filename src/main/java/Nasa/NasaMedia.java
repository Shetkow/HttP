package Nasa;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.http.client.methods.HttpUriRequest;

public class NasaMedia {
    private final String date;
    private final String explanation;
    private final String hdurl;
    private final String mediaType;
    private final String title;
    private final String url;
    private final String service_version;

    public NasaMedia(@JsonProperty("date") String date,
                     @JsonProperty("explanation") String explanation,
                     @JsonProperty("hdurl") String hdurl,
                     @JsonProperty("media_type") String mediaType,
                     @JsonProperty("title") String title,
                     @JsonProperty("url") String url,
    @JsonProperty("service_version") String service_version){
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.title = title;
        this.url = url;
        this.service_version = service_version;
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getMedia_type() {
        return mediaType;
    }

    public String getTitle() {
        return title;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getService_version() {
        return service_version;
    }

    public String getUrl() {
        return url;
    }
    @Override
    public String toString(){
        return "Это " + mediaType + "\n Краткое описание: " + title + "было запечетлено " + date;
    }
}
