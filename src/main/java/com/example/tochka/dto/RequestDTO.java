package com.example.tochka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestDTO {


    @JsonProperty("uri")
    public String uri;

    @JsonProperty("rss")
    public Boolean rss;

    @JsonProperty("type")
    public String type;

    @JsonProperty("imgType")
    public String imgType;

    @JsonProperty("href")
    public String href;

    @JsonProperty("time")
    public String time;
}
