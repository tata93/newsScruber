package com.example.tochka.controller;

import com.example.tochka.dto.NewsDTO;
import com.example.tochka.service.News;
import com.example.tochka.service.NewsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ApiController {

    @Autowired
    private News news;

    public ApiController(News news) {
        this.news = news;
    }

    @GetMapping(value = "/news/newslenta")
    public ResponseEntity<List<NewsDTO>> getNews() throws IOException {
        List<NewsDTO> newsDTOS = news.getAllNews();
        return ResponseEntity.ok().body(newsDTOS);

    }

    @GetMapping(value = "/news")
    public ResponseEntity<List<NewsDTO>> searjNews(@RequestParam String title) throws IOException {
        List<NewsDTO> newsDTOS = news.searchByTytle(title);
        return ResponseEntity.ok().body(newsDTOS);

    }

    @PostMapping(value = "/news")
    public ResponseEntity agregateNews(@RequestParam String uri,
                                       @RequestParam(defaultValue = "false") Boolean rss,
                                       @RequestParam String type,
                                       @RequestParam String imgType,
                                       @RequestParam String href,
                                       @RequestParam String time) throws IOException {
            news.registrateNewsSource(uri, type,rss,imgType,href,time);
            return ResponseEntity.ok().build();

    }


}
