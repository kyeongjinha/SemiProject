package com.nf.not404found.main;

import com.nf.not404found.main.mainservice.GlobalService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final GlobalService service; // DB에서 카테고리를 가져오는 서비스
    public GlobalControllerAdvice(GlobalService service){
        this.service = service;
    }

    @ModelAttribute("categories")
    public List<String> getCategories() {
        return service.findAllCategories();
    }
    @ModelAttribute("themes")
    public List<String> getThemes() {
        return service.findAllThemes();
    }
}
