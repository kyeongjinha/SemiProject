package com.nf.not404found.main.mainservice;

import com.nf.not404found.main.model.dao.GlobalMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalService {
    private final GlobalMapper mapper;
    public GlobalService(GlobalMapper mapper){
        this.mapper = mapper;
    }

    public List<String> findAllCategories() {
        return mapper.findAllCategory();
    }

    public List<String> findAllThemes() {
        return mapper.findAllThemes();
    }
}
