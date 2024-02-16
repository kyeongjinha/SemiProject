package com.nf.not404found.product.model.dao;

import com.nf.not404found.main.model.dto.MainPageProductDTO;
import com.nf.not404found.product.model.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<MainPageProductDTO> getProduct(String value);

    List<MainPageProductDTO> getFurniture();

    List<MainPageProductDTO> getMaterial();

    List<MainPageProductDTO> getNewProduct();

    List<MainPageProductDTO> getBest();
}
