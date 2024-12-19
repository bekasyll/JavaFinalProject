package com.bekassyl.ebooks.service;

import com.bekassyl.ebooks.model.Ebook;
import com.bekassyl.ebooks.repository.EbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EbookService {

    @Autowired
    private EbookRepository ebookRepository;

    public Page<Ebook> findByTitleContaining(String title, Pageable pageable) {
        return ebookRepository.findByTitleContaining(title, pageable);
    }

    public Optional<Ebook> findById(Long id) {
        return ebookRepository.findById(id);
    }

    public Ebook save(Ebook ebook) {
        return ebookRepository.save(ebook);
    }

    public void deleteById(Long id) {
        ebookRepository.deleteById(id);
    }
}
