package com.nf.not404found.main.model.dao;

import com.nf.not404found.main.model.dto.MainPageProductDTO;
import com.nf.not404found.product.model.dto.ProductPageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainPageMapper {

    List<MainPageProductDTO> showMainPageProduct();

    List<ProductPageDTO> getProduct(String name, String userName);

    List<String> getProductColor(String productCode);

    List<MainPageProductDTO> getSpecialProduct();
}
