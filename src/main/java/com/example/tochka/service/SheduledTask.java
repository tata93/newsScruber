package com.example.tochka.service;

import com.example.tochka.domain.NewLenta;
import com.example.tochka.domain.NewsSource;
import com.example.tochka.repository.NewsRepository;
import com.example.tochka.repository.NewsSourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SheduledTask {

    private final NewsSourceRepository newsSourceRepository;
    private final NewsRepository newsRepository;
    private final News news;

    public SheduledTask(NewsSourceRepository newsSourceRepository, NewsRepository newsRepository, News news) {
        this.newsSourceRepository = newsSourceRepository;
        this.newsRepository = newsRepository;
        this.news = news;
    }

    @Scheduled(fixedRate = 50000)
    public void reportCurrentTime() throws IOException {
        List<NewsSource> newsSourceList = newsSourceRepository.findAll();
        for (NewsSource newsSource : newsSourceList){
            List<NewLenta> newLentas = new ArrayList<>();
            List<NewLenta> newLentasFromData = newsSource.getNewLentas().stream().collect(Collectors.toList());
            if(newsSource.getRss()){
                newLentas =news.agregateNews(newsSource.getUri(), newsSource.getType(),newsSource.getImgType(),newsSource.getHref(),newsSource.getTime());
            } else {
                newLentas =news.agregateNewsFromRss(newsSource.getUri());
            }
            for (NewLenta element : newLentas){
                Optional<NewLenta> b =newLentasFromData.stream().filter(s->s.getLink().equals(element.getLink())).findAny();
                if(!b.isPresent()){
                    newsRepository.save(b.get());
                    log.info("add new News");

                }
            }
        }
        log.info("SheduledTask is working");
    }
}
