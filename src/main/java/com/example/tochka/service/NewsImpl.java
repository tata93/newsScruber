package com.example.tochka.service;

import com.example.tochka.domain.NewLenta;
import com.example.tochka.domain.NewsSource;
import com.example.tochka.dto.NewsDTO;
import com.example.tochka.repository.NewsRepository;
import com.example.tochka.repository.NewsSourceRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsImpl implements  News{
    private final NewsRepository newsRepository;
    private final NewsSourceRepository newsSourceRepository;

    public NewsImpl(NewsRepository newsRepository, NewsSourceRepository newsSourceRepository) {
        this.newsRepository = newsRepository;
        this.newsSourceRepository = newsSourceRepository;
    }

    @Override
    @Transactional
    public List<NewLenta> agregateNews(String uri, String type, String imgType, String href,String time ) throws IOException {
        Document document = Jsoup.connect(uri).get();
        Document d = Jsoup.parse(document.html());
        List<Element> elementList =d.select(type).stream().collect(Collectors.toList());
        List<NewLenta> newLentas = new ArrayList<>();
        for (Element element : elementList){
            d = Jsoup.parse(element.html());
            String imageElement="No Img";
            if (!d.select(imgType).toString().equals("")){
                imageElement = d.select(imgType).stream().findAny().get().attr("src").toString();
            }
            Element elementLink =d.select("a[href~="+href+"]").stream().findAny().filter(s->s.hasParent()).get();
            String elementTime =d.select(time).stream().findAny().filter(s->s.hasParent()).get().text();
            NewLenta newLenta = new NewLenta(elementLink.text(),elementLink.attr("href").toString(),imageElement,elementTime);
            newLentas.add(newLenta);
        }
        return newLentas;
    }

    @Override
    public void registrateNewsSource(String uri, String type, Boolean rss, String imgType, String href,String time) throws IOException {
        List<NewLenta> newLentas;
        if (!rss){
            newLentas= agregateNews(uri, type,  imgType,  href, time);
            newLentas.stream().forEach(s->newsRepository.save(s));
        } else {
            newLentas=agregateNewsFromRss(uri);
        }
        NewsSource newsSource = new NewsSource(uri,rss,type,imgType,href,time,newLentas);
        newsSourceRepository.save(newsSource);
    }

    @Override
    @Transactional
    public List<NewLenta> agregateNewsFromRss(String uri) throws IOException {
        Document document = Jsoup.connect(uri).get();
        Document d = Jsoup.parse(document.html());
        List<Element> e =document.getElementsByTag("item").stream().collect(Collectors.toList());
        List<NewLenta> newLentas = new ArrayList<>();
        for (Element element : e){
            NewLenta newLenta = new NewLenta(element.getElementsByTag("title").text(),element.getElementsByTag("link").text(),element.getElementsByTag("description").text());
            newLentas.add(newLenta);

        }
        return newLentas;

    }

    @Override
    public List<NewsDTO> getAllNews() {
        return newsRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<NewsDTO> searchByTytle(String title) {
        return  newsRepository.findByTitle(title).stream().map(this::toDto).collect(Collectors.toList());

    }

    private NewsDTO toDto(NewLenta newLenta) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setTitle(newLenta.getTitle());
        newsDTO.setDescription(newLenta.getDescription());
        newsDTO.setId(newLenta.getId());
        newsDTO.setImgType(newLenta.getImgType());
        newsDTO.setTime(newLenta.getTime());
        newsDTO.setLink(newLenta.getLink());
        return newsDTO;
    }
}
