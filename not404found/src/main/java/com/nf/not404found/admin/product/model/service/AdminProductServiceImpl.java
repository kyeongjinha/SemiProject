package com.nf.not404found.admin.product.model.service;


import com.nf.not404found.admin.product.model.dao.AdminProductMapper;
import com.nf.not404found.admin.product.model.dto.AdminAttachmentDTO;
import com.nf.not404found.admin.product.model.dto.AdminProductDTO;
import com.nf.not404found.admin.common.exception.ThumbnailRegistException;
import com.nf.not404found.admin.common.exception.modifyProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class AdminProductServiceImpl implements AdminProductService{

    private final AdminProductMapper mapper;

    public AdminProductServiceImpl(AdminProductMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<AdminProductDTO> selectConditionProduct(AdminProductDTO product) {
        log.info("서비스 왔냐 ======================================================>");

        List<AdminProductDTO> productList1 = mapper.selectConditionProduct(product);

        log.info("잘들어갔냐 ============================================>>");

        return productList1;
    }

    @Override
    public List<AdminProductDTO> selectAllProduct() {
        log.info("전체 상품 서비스 왔냐 ===================================>");

        List<AdminProductDTO> productList = mapper.selectAllProduct();

        log.info("전체 상품 잘 들어갔냐 ====================================================");

        return productList;
    }

    @Override
    @Transactional
    public void insertProduct(AdminProductDTO thumbnail) throws ThumbnailRegistException {
        
        log.info("상품등록 서비스 단으로 왔냐 ============================================>");

        int productResult = mapper.insertProduct(thumbnail);

        log.info("상품등록 매퍼 잘 갔다 왔냐 ===========================================>");

        Long productCode = thumbnail.getProductCode();

        thumbnail.getAdminOption().setProductCode(productCode);


        int optionsResult = mapper.insertOptions(thumbnail);

        log.info("옵션 경로 잘 타고 왔냐 =============================================");

        List<AdminAttachmentDTO> attachmentList = thumbnail.getAttachmentList();

        log.info("이미지 매퍼 시작 =================================================>");

        for (int i = 0; i < attachmentList.size(); i++) {
            attachmentList.get(i).setProductCode(thumbnail.getProductCode());
        }

        log.info("여기까진 오는거냐 ==========================================>");

        int attachmentResult = 0;
        for (int i = 0; i < attachmentList.size(); i++) {
            attachmentResult += mapper.insertAttachment(attachmentList.get(i));
        }

        log.info("===========================================================이미지 매퍼 끝 ===================>");

        if (!(productResult > 0 && attachmentResult == attachmentList.size() && optionsResult > 0 )){
            log.info("오지마 제발 ================================> 상품 등록이 실패해부렀어===========>");
            throw new ThumbnailRegistException("상품 등록에 실패하셨습니다.");
        }


    }

    @Override
    public List<AdminProductDTO> selectOneProduct(String productName) {

        log.info("========================================================> 서비스단 시작");
        log.info("========================================================> productName = " + productName);

        List<AdminProductDTO> productList = mapper.selectOneProduct(productName);

        log.info("==========================================================> 비동기 잘 들어왔나 = > productList = " + productList);


        return productList;
    }

    @Override
    public List<AdminProductDTO> selectOneProduct2(Long productCode) {

        List<AdminProductDTO> productList = mapper.selectOneProduct2(productCode);

        log.info("======================================================> 잘 되냐?");

        return productList;
    }

    @Override
    @Transactional
    public void modifyProduct(AdminProductDTO modifyProduct) throws modifyProductException{

        List<AdminAttachmentDTO> forif = mapper.selectAttach(modifyProduct.getProductCode());

        int result = mapper.modifyProduct(modifyProduct);
        log.info("카테고리 수정 잘 되었나===================================================>");
        int result2 = mapper.modifyProduct2(modifyProduct);
        log.info("상품 수정 서비스 다 끝났나 =-================================ : " + modifyProduct);
        int result3 = 0;
        log.info("썸네일 가냐 ==========================> modifyProduct = " + modifyProduct);
        // LIST 객체 통해서 값을 추가
        List<AdminAttachmentDTO> attachmentList = modifyProduct.getAttachmentList();

        for(int i = 0; i < attachmentList.size(); i++){
            AdminAttachmentDTO attachmentDTO = attachmentList.get(i);
            attachmentDTO.setProductCode(modifyProduct.getProductCode());
            attachmentDTO.setNo(modifyProduct.getAttachmentList().get(i).getNo());
            if (forif.size()>0 && attachmentList.size() > 0) {
                if (!forif.get(i).getModifyName().equals(attachmentDTO.getModifyName())) {
                    log.info("=======================================================> forif " + forif.get(i).getModifyName());
                    log.info("=======================================================> product " + attachmentDTO.getModifyName());
                    log.info("==========================================================> " + i);
                    log.info("============================================================ no : " + modifyProduct.getAttachmentList().get(i).getNo());
                    result3 += mapper.modifyProduct3(attachmentDTO); // attachmentDTO에 이름을 넣어야된다......../,.//.바꾸기 전의 이름
                }
            }
            log.info("==============================================> attachmentList " + attachmentList);
        }
        int result4 = mapper.modifyProduct4(modifyProduct);
        log.info("상품 수정 됬나 =====================================================>");
        System.out.println("result = " + result);
        System.out.println("result2 = " + result2);
        System.out.println("result3 = " + result3);
        System.out.println("result4 = " + result4);
        if(!(result >= 0 && result2 >= 0  && result3 >= 0 && result4 >= 0)) {
            throw new modifyProductException("상품 정보 수정에 실패하셨습니다.");
        }
    }

    @Override
    public List<AdminProductDTO> selectOneProduct3(String productName) {

        List<AdminProductDTO> productList = mapper.selectOneProductMain(productName);

        return productList;
    }

    @Override
    public List<AdminProductDTO> selectOneProductName(String productName) {

        List<AdminProductDTO> selectList = mapper.selectOneProductMain(productName);

        return selectList;
    }
}
