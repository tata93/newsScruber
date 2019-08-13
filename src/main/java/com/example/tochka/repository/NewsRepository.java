package com.example.tochka.repository;

import com.example.tochka.domain.NewLenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewLenta, String> {

    @Query("SELECT m FROM NewLenta m WHERE m.title LIKE %:title%")
    List<NewLenta> findByTitle(@Param("title") String title);

}
