package com.example.tochka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsDTO {
    @JsonProperty("id")
    public String id;

    @JsonProperty("title")
    public String title;

    @JsonProperty("time")
    public String time;

    @JsonProperty("imgType")
    public String imgType;

    @JsonProperty("description")
    public String description;

    @JsonProperty("link")
    public String link;
}
