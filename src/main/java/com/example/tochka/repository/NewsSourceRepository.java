package com.example.tochka.repository;

import com.example.tochka.domain.NewsSource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsSourceRepository extends JpaRepository<NewsSource, String> {
}
