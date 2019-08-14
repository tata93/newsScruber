package com.example.tochka.controller;

import com.example.tochka.dto.NewsDTO;
import com.example.tochka.dto.RequestDTO;
import com.example.tochka.service.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private News news;

    public ApiController(News news) {
        this.news = news;
    }

    @GetMapping(value = "/news/newslenta")
    public ResponseEntity<List<NewsDTO>> getNews()  {
        List<NewsDTO> newsDTOS = news.getAllNews();
        return ResponseEntity.ok().body(newsDTOS);

    }

    @GetMapping(value = "/news")
    public ResponseEntity<List<NewsDTO>> searchNews(@RequestParam String title)  {
        List<NewsDTO> newsDTOS = news.searchByTytle(title);
        return ResponseEntity.ok().body(newsDTOS);

    }

    @PostMapping(value = "/news")
    public ResponseEntity agregateNews(@RequestBody RequestDTO requestDTO) throws IOException {
            news.registrateNewsSource(requestDTO.getUri(), requestDTO.getType(),requestDTO.getRss(),requestDTO.getImgType(),requestDTO.getHref(),requestDTO.getTime());
            return ResponseEntity.ok().build();

    }


}
