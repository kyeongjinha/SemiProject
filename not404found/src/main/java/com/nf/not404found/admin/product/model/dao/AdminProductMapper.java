package com.nf.not404found.admin.product.model.dao;

import com.nf.not404found.admin.product.model.dto.AdminAttachmentDTO;
import com.nf.not404found.admin.product.model.dto.AdminProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminProductMapper {
    List<AdminProductDTO> selectConditionProduct(AdminProductDTO product);

    List<AdminProductDTO> selectAllProduct();

    int insertProduct(AdminProductDTO thumbnail);

    int insertAttachment(AdminAttachmentDTO adminAttachmentDTO);

    int insertOptions(AdminProductDTO thumbnail);

    List<AdminProductDTO> selectOneProduct(String productName);

    List<AdminProductDTO> selectOneProduct2(Long productCode);

    int modifyProduct(AdminProductDTO modifyProduct);

    int modifyProduct2(AdminProductDTO modifyProduct);

    int modifyProduct3(AdminAttachmentDTO adminAttachmentDTO);

    int modifyProduct4(AdminProductDTO modifyProduct);

    List<AdminAttachmentDTO> selectAttach(Long productCode);

    List<AdminProductDTO> selectOneProductMain(String productName);

}
