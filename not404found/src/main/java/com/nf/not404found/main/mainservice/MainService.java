package com.nf.not404found.main.mainservice;

import com.nf.not404found.common.functions.UserInformation;
import com.nf.not404found.main.model.dao.MainPageMapper;
import com.nf.not404found.main.model.dto.MainPageProductDTO;
import com.nf.not404found.product.model.dto.ProductPageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
    private final MainPageMapper mapper;
    private final UserInformation user;
    public MainService(MainPageMapper mapper, UserInformation user){
        this.mapper = mapper;
        this.user = user;
    }

    public List<MainPageProductDTO> showMainPageProduct() {
        List<MainPageProductDTO> list = mapper.showMainPageProduct();
        for(MainPageProductDTO product : list){
            System.out.println("======"+product.getName()+"======");
            System.out.println(product.getPrice());
            System.out.println(product.getPriority());
            System.out.println(product.getDiscountRate());
            System.out.println(product.getReviewCount());
            System.out.println(product.getDiscountPrice());
            System.out.println("=========================");
        }
        return list;
    }

    public List<ProductPageDTO> getProduct(String name) {

        List<ProductPageDTO> list = mapper.getProduct(name,user.getName());
        for(ProductPageDTO p : list){
            System.out.println("===="+p.getName()+"====");
            System.out.println("마일리지 = "+p.getMileage());
            System.out.println("배송비 = "+p.getDeliveryCost());
            System.out.println("리뷰 수 = "+p.getReviewCount());
            System.out.println("가격 = "+p.getPrice());
            System.out.println("색상 = "+p.getColor());
            System.out.println("코드 = "+p.getProductCode());
            System.out.println("할인된 가격 = "+p.getDiscountPrice());
        }
        List<String> list2 = mapper.getProductColor(list.get(0).getProductCode());
        list.get(0).setColor(list2);
        return list;

    }

    public List<MainPageProductDTO> getSpecialProduct() {
        return mapper.getSpecialProduct();
    }
}
