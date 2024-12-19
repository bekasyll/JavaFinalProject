package com.bekassyl.ebooks.controller;

import com.bekassyl.ebooks.model.Ebook;
import com.bekassyl.ebooks.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/ebooks")
public class EbookController {

    @Autowired
    private EbookService ebookService;

    // Отображение списка книг с пагинацией и поиском
    @GetMapping
    public String listEbooks(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(defaultValue = "") String search,
            Model model) {
        Page<Ebook> ebookPage = ebookService.findByTitleContaining(search, PageRequest.of(page, size));

        model.addAttribute("ebooks", ebookPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", ebookPage.getTotalPages());
        model.addAttribute("totalItems", ebookPage.getTotalElements());
        model.addAttribute("size", size);
        model.addAttribute("search", search);

        return "ebooks/list";
    }

    // Создание новой книги
    @GetMapping("/new")
    public String newEbookForm(Model model) {
        model.addAttribute("ebook", new Ebook());
        return "ebooks/form";
    }

    // Сохранение книги
    @PostMapping
    public String saveEbook(@ModelAttribute Ebook ebook) {
        ebookService.save(ebook);
        return "redirect:/ebooks";
    }

    // Редактирования книги
    @GetMapping("/{id}/edit")
    public String editEbookForm(@PathVariable Long id, Model model) {
        Optional<Ebook> ebook = ebookService.findById(id);
        if (ebook.isPresent()) {
            model.addAttribute("ebook", ebook.get());
            return "ebooks/form";
        } else {
            return "redirect:/ebooks";
        }
    }

    // Обновление книги
    @PostMapping("/{id}")
    public String updateEbook(@PathVariable Long id, @ModelAttribute Ebook ebook) {
        ebook.setId(id);
        ebookService.save(ebook);
        return "redirect:/ebooks";
    }

    // Удаление книги
    @GetMapping("/{id}/delete")
    public String deleteEbook(@PathVariable Long id) {
        ebookService.deleteById(id);
        return "redirect:/ebooks";
    }
}