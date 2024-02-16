package com.nf.not404found.main.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GlobalMapper {

    @Select("SELECT category_name FROM product_category WHERE ref_category_code IS NULL")
    List<String> findAllCategory();

    @Select("SELECT category_name FROM product_category WHERE ref_category_code IS NOT NULL ")
    List<String> findAllThemes();
}
