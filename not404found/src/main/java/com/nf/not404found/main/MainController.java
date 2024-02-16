package com.nf.not404found.main;


import com.nf.not404found.admin.product.model.dto.AdminProductDTO;
import com.nf.not404found.admin.product.model.service.AdminProductService;
import com.nf.not404found.admin.product.model.service.AdminProductServiceImpl;
import com.nf.not404found.common.functions.UserInformation;
import com.nf.not404found.main.mainservice.MainService;
import com.nf.not404found.main.model.dto.MainPageProductDTO;
import com.nf.not404found.product.model.dto.ProductPageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
public class MainController {
    private final MainService service;

    private final AdminProductServiceImpl pService;
    private final UserInformation user;
    public MainController(MainService service, @Qualifier("adminProductServiceImpl") AdminProductServiceImpl pService, UserInformation user){
        this.service = service;
        this.pService = pService;
        this.user = user;
    }
//    @GetMapping("/")
//    public String defaultLocation(){
//
//        return "index";
//    }
    @GetMapping("/")
    public ModelAndView showMainPage(ModelAndView mv){
        List<MainPageProductDTO> list = service.showMainPageProduct();
        List<AdminProductDTO> pList = pService.selectAllProduct();
        List<MainPageProductDTO> sp = service.getSpecialProduct();

        mv.addObject("sp",sp);
        mv.addObject("pList", pList);
        mv.addObject("products",list);
        mv.setViewName("index");
        return mv;
    }
    @GetMapping("member/login") 
    public String logIn(){
        return "member/login";
    }
    @GetMapping("member/signup")
    public String signUp(){
        return "member/signup";
    }
    @GetMapping("productPage")
    public ModelAndView showProduct(@RequestParam String  product_name, ModelAndView mv){
        List<ProductPageDTO> list = service.getProduct(product_name);
        List<AdminProductDTO> pList = pService.selectOneProduct3(product_name);

        log.info("=============================================== pList : " + pList);

        mv.addObject("pList", pList);
        mv.addObject("products",list);
        mv.addObject("grade",user.getStatus());
        mv.setViewName("productpage/productPage");
        return mv;
    }
}
