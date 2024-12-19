package com.bekassyl.ebooks.repository;

import com.bekassyl.ebooks.model.Ebook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EbookRepository extends JpaRepository<Ebook, Long> {
    Page<Ebook> findByTitleContaining(String title, Pageable pageable);
}
