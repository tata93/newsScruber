package com.example.tochka.service;

import com.example.tochka.domain.NewLenta;
import com.example.tochka.dto.NewsDTO;

import java.io.IOException;
import java.util.List;


public interface News {

    List<NewLenta> agregateNews(String uri, String type, String imgType, String href,String time) throws IOException;

    void registrateNewsSource(String uri, String type, Boolean rssr, String imgType, String href,String time) throws IOException;

    List<NewLenta> agregateNewsFromRss(String uri) throws IOException;

    List<NewsDTO> getAllNews();

    List<NewsDTO> searchByTytle(String title);
}
