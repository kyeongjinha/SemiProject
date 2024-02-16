package com.nf.not404found.admin.product.model.service;

import com.nf.not404found.admin.common.exception.modifyProductException;
import com.nf.not404found.admin.product.model.dto.AdminProductDTO;
import com.nf.not404found.admin.common.exception.ThumbnailRegistException;

import java.util.List;

public interface AdminProductService {
    public List<AdminProductDTO> selectConditionProduct(AdminProductDTO product);
    public List<AdminProductDTO> selectAllProduct();
    public void insertProduct(AdminProductDTO thumbnail) throws ThumbnailRegistException;
    public List<AdminProductDTO> selectOneProduct(String productName);

    List<AdminProductDTO> selectOneProduct2(Long productCode);

    void modifyProduct(AdminProductDTO modifyProduct) throws modifyProductException;

    List<AdminProductDTO> selectOneProduct3(String productName);

    List<AdminProductDTO> selectOneProductName(String productName);
}
