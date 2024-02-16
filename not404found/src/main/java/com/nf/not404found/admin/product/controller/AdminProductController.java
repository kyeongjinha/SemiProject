package com.nf.not404found.admin.product.controller;


import com.nf.not404found.admin.common.exception.modifyProductException;
import com.nf.not404found.admin.product.model.dto.AdminAttachmentDTO;
import com.nf.not404found.admin.product.model.dto.AdminProductCategoryDTO;
import com.nf.not404found.admin.product.model.dto.AdminProductDTO;
import com.nf.not404found.admin.product.model.service.AdminProductServiceImpl;
import com.nf.not404found.admin.common.exception.ThumbnailRegistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/admin/*")
@Slf4j
public class AdminProductController {

    private final AdminProductServiceImpl productService;

    public AdminProductController(AdminProductServiceImpl productService) {
        this.productService = productService;
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(int.class, new CustomNumberEditor(Integer.class, true));
//    }

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    @Value("C:/dev/NF404/SemiProject404NotFound/not404found/src/main/resources/static/images/product")
    private String ROOT_LOCATION;

    @GetMapping("product")
    public ModelAndView productMainPage(@RequestParam(required = false) String deleteStatus,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date enterDates,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date enterDateb,
                                        @RequestParam(required = false) String name,
                                        @RequestParam(required = false, defaultValue = "0") int prices,
                                        @RequestParam(required = false, defaultValue = "0") int priceb,
                                        @RequestParam(required = false, defaultValue = "0") int amounts,
                                        @RequestParam(required = false, defaultValue = "0") int amountb,
                                        @RequestParam(required = false, defaultValue = "0") Integer refCategoryCode,
                                        @RequestParam(required = false) String categoryName,
                                        ModelAndView mv){

        log.info("");
        log.info("");
        log.info("들어왔나?-==========================================" );

        mv.addObject("boardType", "상품 관리");

        log.info("애드오브젝'=========================================");
        log.info("확인 ======================================================> name : {} ", name);
        log.info("확인 ======================================================> deleteStatus : {} ", deleteStatus);
        log.info("확인 ======================================================> enterDate : {} ", enterDates);
        log.info("확인 ======================================================> enterDate : {} ", enterDateb);
        log.info("확인 ======================================================> price : {} ", priceb);
        log.info("확인 ======================================================> price : {} ", prices);
        log.info("확인 ======================================================> amount : {} ", amounts);
        log.info("확인 ======================================================> amount : {} ", amountb);
        log.info("확인 ======================================================> refCategoryCode : {} ", refCategoryCode);
        log.info("확인 ======================================================> categoryName : {} ", categoryName);

            List<AdminProductDTO> condition = null;

            int count = 0;
            // 검색결과 없을 때 하는거 처리하고 , 다른거 오류나는거 처리하고
        if (deleteStatus != null || enterDates != null || enterDateb != null || name != null || priceb != 0 || prices != 0 || amounts != 0 || amountb != 0 || refCategoryCode != 0 || categoryName != null){
            log.info("조건 들어왔나================================================>");

           AdminProductDTO product = new AdminProductDTO();
           product.setName(name);
           product.setPrices(prices);
           product.setPriceb(priceb);
           product.setAmountb(amountb);
           product.setAmounts(amounts);
           product.setEnterDates(enterDates);
           product.setEnterDateb(enterDateb);
           product.setDeleteStatus(deleteStatus);
           AdminProductCategoryDTO category = new AdminProductCategoryDTO();
           category.setRefCategoryCode(refCategoryCode);
           category.setCategoryName(categoryName);
           product.setAdminProductCategory(category);



            condition = productService.selectConditionProduct(product);

            log.info("프로덕트 조건 넣어서 잘 들어왔어?+=====================================");

            mv.addObject("condition", condition);

            log.info("컨디션 상태 ===============================================> condition : " + condition);

            if (condition.isEmpty() ){
                log.info("00000000000000000000000000000000000000000000000000000000000000000000");
                count++;
            }

        }
        if (count == 0) {
            List<AdminProductDTO> productList = productService.selectAllProduct();

            log.info("전체 상품 목록 잘 들어왔냐 ================================================>");

            mv.addObject("productList", productList);

            log.info("전체 상품 목록======================================================== productList = " + productList);
        }

        mv.setViewName("/admin/product/select");

        return mv;
    }
    @GetMapping("product/insert")
    public String productInsertPage(Model model){

        model.addAttribute("boardType", "상품 관리");

        return "/admin/product/insert";
    }

    @PostMapping("product/insert")
    public String insertProduct(@ModelAttribute AdminProductDTO thumbnail,
                                @RequestParam("thumbnailImage1")MultipartFile thumbnailImg1,
                                @RequestParam("thumbnailImage2")MultipartFile thumbnailImg2,
                                @RequestParam("thumbnailImage3")MultipartFile thumbnailImg3,
                                @RequestParam("thumbnailImage4")MultipartFile thumbnailImg4,
                                @RequestParam("thumbnailImage5")MultipartFile thumbnailImg5,
                                RedirectAttributes rttr)
            throws UnsupportedEncodingException, ThumbnailRegistException {

        log.info(" ");
        log.info(" ");
        log.info("[상품등록 드가자~=====================================================> start");


        log.info("========================================>" + thumbnail);
        log.info("========================================>" + thumbnailImg1);
        log.info("========================================>" + thumbnailImg2);
        log.info("========================================>" + thumbnailImg3);
        log.info("========================================>" + thumbnailImg4);
        log.info("========================================>" + thumbnailImg5);
        String rootLocation = ROOT_LOCATION + IMAGE_DIR;

        String fileUploadDirectory = rootLocation + "/upload/original";
        String thumbnailDirectory = rootLocation + "/upload/thumbnail";

        File directory = new File(fileUploadDirectory);
        File directory2 = new File(thumbnailDirectory);

        log.info("============================================디렉토리 설정 잘 했냐 =============================== fileUploadDirectory :" + directory);
        log.info("============================================디렉토리 설정 잘 했냐 =============================== thumbnailDirectory :" + thumbnailDirectory);

        if (!directory.exists() || !directory2.exists()){

            log.info("없냐아아======================================================> fileUploadDirectory : " + directory.mkdirs());
            log.info("없냐아아======================================================> thumbnailDirectory : " + directory2.mkdirs());

        }

        List<Map<String, String>> fileList = new ArrayList<>();

        List<MultipartFile> paramFileList = new ArrayList<>();

        paramFileList.add(thumbnailImg1);
        log.info("들어왔냐 ===================================================================================== 1번");
        paramFileList.add(thumbnailImg2);
        log.info("들어왔냐 ===================================================================================== 2번");
        paramFileList.add(thumbnailImg3);
        log.info("들어왔냐 ===================================================================================== 3번");
        paramFileList.add(thumbnailImg4);
        log.info("들어왔냐 ===================================================================================== 4번");
        paramFileList.add(thumbnailImg5);
        log.info("들어왔냐 ===================================================================================== 5번");

        try {

            for (MultipartFile paramFile : paramFileList){

                if (paramFile.getSize() > 0){

                    String originalFileName = paramFile.getOriginalFilename();

                    log.info("=====================================> originalFileName : " + originalFileName);

                    String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
                    String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                    log.info("수정 잘 됬냐 ========================================== " + savedFileName);

                    log.info("파일 경로한번 보자 =================================== " + fileUploadDirectory + "/" + savedFileName);

                    paramFile.transferTo(new File(fileUploadDirectory + "/" + savedFileName));

                    Map<String, String> fileMap = new HashMap<>();

                    fileMap.put("originalFileName", originalFileName);
                    fileMap.put("savedFileName", savedFileName);
                    fileMap.put("savedPath", fileUploadDirectory);

                    int width = 0;
                    int height = 0;

                    String fieldName = paramFile.getName();

                    log.info("필드 네임 =============================================> 필드이ㅓ름 : " + fieldName);

                    if ("thumbnailImage1".equals(fieldName)){
                        fileMap.put("fileType", "TITLE");;

                        width = 300;
                        height = 150;
                    }else {

                        fileMap.put("fileType", "BODY");

                        width = 120;
                        height= 100;
                    }

                    Thumbnails.of(fileUploadDirectory + "/" + savedFileName).size(width, height)
                            .toFile(thumbnailDirectory + "/thumbnail_" + savedFileName);

                    fileMap.put("thumbnailPath", "/thumbnail_" + savedFileName);

                    fileList.add(fileMap);
                }
            }

            log.info("fileList ======================================================================================== fileList : " + fileList);


            thumbnail.setAttachmentList(new ArrayList<AdminAttachmentDTO>());
            List<AdminAttachmentDTO> list = thumbnail.getAttachmentList();

            for (int i = 0; i < fileList.size() ; i++) {
                Map<String, String> file = fileList.get(i);

                AdminAttachmentDTO tempFileInfo = new AdminAttachmentDTO();

                tempFileInfo.setOriginalName(file.get("originalFileName"));
                tempFileInfo.setModifyName((file.get("savedFileName")));
                tempFileInfo.setSavedPath(file.get("savedPath"));
                tempFileInfo.setFileType(file.get("fileType"));
                tempFileInfo.setThumbnailPath(file.get("thumbnailPath"));
                tempFileInfo.setStatus("Y");

                list.add(tempFileInfo);

            }

            log.info(" thumbnail 잘 나왔냐 ================================================ 썸네일 : " + thumbnail);

            productService.insertProduct(thumbnail);

            log.info("=-==============================================================> 등록정보 : ");
            rttr.addFlashAttribute("message", "상품 등록에 성공하셨습니다.");


        } catch ( IllegalStateException | IOException e) {
            e.printStackTrace();

            int cnt = 0;

            for (int i = 0; i < fileList.size(); i++ ){
                Map<String, String> file = fileList.get(i);

                File deleteFile = new File(fileUploadDirectory + "/" + file.get("savedFileName"));

                boolean isDelete = deleteFile.delete();

                File deleteThumbnail = new File(thumbnailDirectory + "/thumbnail_" + file.get("savedFileName"));

                boolean isDelete2 = deleteThumbnail.delete();

                if (isDelete && isDelete2){
                    cnt++;
                }
            }

            if (cnt == fileList.size()){

                log.info("다 지워따아아=-======================================");

                e.printStackTrace();
            }else {
                e.printStackTrace();
            }
        }

        log.info("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ 상품등록 끝까지 왔다 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ=====");


        return "redirect:/admin/product/insert";
    }



    @GetMapping(value="product/modify")
    public String productModifyPage(Model model){

        model.addAttribute("boardType", "상품 관리");
        List<AdminProductDTO> productList = productService.selectAllProduct();

        model.addAttribute("productList", productList);

        return "/admin/product/modify";
    }

    @GetMapping(value="product/searchData", produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<AdminProductDTO> productModifyPage(@RequestParam String productName){

        log.info("=================================================> 비동기 시작 ");

        List<AdminProductDTO> productList = productService.selectOneProduct(productName);

        log.info("===========================================================> 비동기 끝 productList = " + productList);

        return productList;
    }

    @GetMapping(value="product/data", produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<AdminProductDTO> productModifyPage2(@RequestParam Long productCode){

        log.info("=================================================> 비동기 시작 ");

        List<AdminProductDTO> productList = productService.selectOneProduct2(productCode);

        log.info("===========================================================> 비동기 끝 productList = " + productList);

        return productList;
    }

    @PostMapping(value="product/modify")
    public String productModifyPage(@ModelAttribute AdminProductDTO modifyProduct,
                                    @RequestParam("thumbnailImage1")MultipartFile thumbnailImg1,
                                    @RequestParam("thumbnailImage2")MultipartFile thumbnailImg2,
                                    @RequestParam("thumbnailImage3")MultipartFile thumbnailImg3,
                                    @RequestParam("thumbnailImage4")MultipartFile thumbnailImg4,
                                    @RequestParam("thumbnailImage5")MultipartFile thumbnailImg5,
                                    RedirectAttributes rttr) throws modifyProductException {

        log.info("=====================================no확인 =================== + modifyProduct" + modifyProduct.getAttachmentList().get(2).getNo() );

        log.info("============================================================================== {} ", modifyProduct);
        log.info("===========================================상품 수정 ===================================");
        String rootLocation = ROOT_LOCATION + IMAGE_DIR;

        String fileUploadDirectory = rootLocation + "/upload/original";
        String thumbnailDirectory = rootLocation + "/upload/thumbnail";

        File directory = new File(fileUploadDirectory);
        File directory2 = new File(thumbnailDirectory);


        if (!directory.exists() || !directory2.exists()){

            log.info("없냐아아======================================================> fileUploadDirectory : " + directory.mkdirs());
            log.info("없냐아아======================================================> thumbnailDirectory : " + directory2.mkdirs());

        }

        List<Map<String, String>> fileList = new ArrayList<>();

        List<MultipartFile> paramFileList = new ArrayList<>();

        paramFileList.add(thumbnailImg1);
        paramFileList.add(thumbnailImg2);
        paramFileList.add(thumbnailImg3);
        paramFileList.add(thumbnailImg4);
        paramFileList.add(thumbnailImg5);
//        modifyProduct.getAttachmentList().get(0).setNo(image0);
//        modifyProduct.getAttachmentList().get(1).setNo(image1);
//        modifyProduct.getAttachmentList().get(2).setNo(image2);
//        modifyProduct.getAttachmentList().get(3).setNo(image3);
//        modifyProduct.getAttachmentList().get(4).setNo(image4);




        try {
            for (MultipartFile paramFile : paramFileList){

                if (paramFile.getSize() > 0){

                    String originalFileName = paramFile.getOriginalFilename();

                    log.info("=====================================> originalFileName : " + originalFileName);

                    String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
                    String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                    log.info("수정 잘 됬냐 ========================================== " + savedFileName);

                    log.info("파일 경로한번 보자 =================================== " + fileUploadDirectory + "/" + savedFileName);

                    paramFile.transferTo(new File(fileUploadDirectory + "/" + savedFileName));

                    Map<String, String> fileMap = new HashMap<>();

                    fileMap.put("originalFileName", originalFileName);
                    fileMap.put("savedFileName", savedFileName);
                    fileMap.put("savedPath", fileUploadDirectory);

                    int width = 0;
                    int height = 0;

                    String fieldName = paramFile.getName();

                    log.info("필드 네임 =============================================> 필드이ㅓ름 : " + fieldName);

                    if ("thumbnailImage1".equals(fieldName)){
                        fileMap.put("fileType", "TITLE");

                        width = 300;
                        height = 150;
                    }else {

                        fileMap.put("fileType", "BODY");

                        width = 120;
                        height= 100;
                    }

                    Thumbnails.of(fileUploadDirectory + "/" + savedFileName).size(width, height)
                            .toFile(thumbnailDirectory + "/thumbnail_" + savedFileName);

                    fileMap.put("thumbnailPath", "/thumbnail_" + savedFileName);

                    fileList.add(fileMap);
                }
            }

            List<AdminAttachmentDTO> cast = modifyProduct.getAttachmentList();

            log.info("fileList ======================================================================================== fileList : " + fileList);

            log.info("==============================================================================)))) {} ", modifyProduct);

            modifyProduct.setAttachmentList(new ArrayList<AdminAttachmentDTO>());
            List<AdminAttachmentDTO> list = modifyProduct.getAttachmentList();

            log.info("==============================================================================)))) {} ", modifyProduct);

            for (int i = 0; i < fileList.size() ; i++) {
                Map<String, String> file = fileList.get(i);

                AdminAttachmentDTO tempFileInfo = new AdminAttachmentDTO();

                tempFileInfo.setOriginalName(file.get("originalFileName"));
                tempFileInfo.setModifyName((file.get("savedFileName")));
                tempFileInfo.setSavedPath(file.get("savedPath"));
                tempFileInfo.setFileType(file.get("fileType"));
                tempFileInfo.setThumbnailPath(file.get("thumbnailPath"));
                tempFileInfo.setStatus("Y");

                list.add(tempFileInfo);


            }

            log.info(" thumbnail 잘 나왔냐 ================================================ 썸네일 : " + modifyProduct);
            log.info("list check ============================================================ {} ", list);
            modifyProduct.setAttachmentList(list);
//            modifyProduct.getAttachmentList().get(0).setNo(image0);
//            modifyProduct.getAttachmentList().get(1).setNo(image1);
//            modifyProduct.getAttachmentList().get(2).setNo(image2);
//            modifyProduct.getAttachmentList().get(3).setNo(image3);
//            modifyProduct.getAttachmentList().get(4).setNo(image4);

            log.info("=========================================== modify = " +modifyProduct);

            for (int i = 0; i < modifyProduct.getAttachmentList().size(); i++) {
                modifyProduct.getAttachmentList().get(i).setNo(
                        cast.get(i).getNo()
                );

            }

            log.info("==============================================================================)))) {} ", modifyProduct);

            productService.modifyProduct(modifyProduct);

            rttr.addFlashAttribute("message", "상품 수정에 성공하셨습니다.");


        } catch ( IllegalStateException | IOException e) {
            e.printStackTrace();

            int cnt = 0;

            for (int i = 0; i < fileList.size(); i++ ){
                Map<String, String> file = fileList.get(i);

                File deleteFile = new File(fileUploadDirectory + "/" + file.get("savedFileName"));

                boolean isDelete = deleteFile.delete();

                File deleteThumbnail = new File(thumbnailDirectory + "/thumbnail_" + file.get("savedFileName"));

                boolean isDelete2 = deleteThumbnail.delete();

                if (isDelete && isDelete2){
                    cnt++;
                }
            }

            if (cnt == fileList.size()){

                log.info("다 지워따아아=-======================================");

                e.printStackTrace();
            }else {
                e.printStackTrace();
            }
        }


        log.info("상품 수정 이걸 해 ? ========================================================> ");


        return "redirect:/admin/product/modify";
    }

    @GetMapping("product/insertCategory")
    public String insertCategory(Model model){

        model.addAttribute("boardType", "상품 관리");

        return "/admin/product/insertCategory";
    }


}
