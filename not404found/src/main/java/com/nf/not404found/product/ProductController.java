package com.nf.not404found.product;

import com.nf.not404found.main.model.dto.MainPageProductDTO;
import com.nf.not404found.product.model.dto.ProductDTO;
import com.nf.not404found.product.model.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service){
        this.service = service;
    }
    //이 곳은 상품을 DB 에서 가져와 출력하는 페이지들이다.
    //신상품, 베스트, 카테고리 등 페이지는 모두 동일하지만
    //사용자가 버튼을 누를 때 마다 페이지 형식은 유지한채로 데이터만 다르게 가져와 뿌리는 형식으로 한다. (동기 비동기 둘 다 가능)
    //사용자가 상품을 클릭하면 @@상품의 정보를 가지고@@ Order 페이지로 이동한다.
    //상품의 정보를 어떻게 가져가냐? 버튼을 누르면 뿌릴 때 서버사이드에 저장을 해야겠지?
//    @GetMapping("productPage")
//    public ModelAndView openProductPage(ModelAndView mv, @RequestBody String value){
//        //value 는 무슨 상품 목록을 선택했는지를 받아올 것이다.
//
//        mv.setViewName("productpage/productPage");
//        return mv;
//    }
    @GetMapping("productPage")
    public String openProductPage(){
        return "productpage/productPage";
    }
    @GetMapping("products")//상품 뿌려주는 페이지에 연결할 거
    public String openProductsPage(@RequestParam String value, Model md){//받아오면 쿼리로 value 날리고 상품들 선택해서 뿌리자.
        md.addAttribute("chooseCategory",value);

        List<MainPageProductDTO> list;
        if(value.equals("가구")) {    //가구, 자재 선택시
            list = service.getFurniture();
            md.addAttribute("products", list);
        } else if(value.equals("자재")){
            list = service.getMaterial();
            md.addAttribute("products", list);
        } else if(value.equals("newProducts")){ //신상품 선택시
            list = service.getNewProduct();
            md.addAttribute("products", list);
        }else if(value.equals("best")){ //베스트 선택시
            list = service.getBest();
            md.addAttribute("products", list);
        } else {    //테마 선택 시
            list = service.getProduct(value);
            md.addAttribute("products", list);
        }


        return "productpage/productsPage";
    }
}
