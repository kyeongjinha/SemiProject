package com.nf.not404found.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.Serializable;

@Configuration
public class WebConfig implements WebMvcConfigurer, Serializable {
    @Value("${summernote.image-dir}")
    private String summernotePath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/", "classpath:/static/");

        // 이미지 불러올 수 있도록 세팅

        // 사용자가 파일을 업로드할 때 연동되는 경로를 설정해주는 것
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///C:/dev/NF404/SemiProject404NotFound/not404found/src/main/resources/static/images/product/");

        registry.addResourceHandler("/thumbPath/**") //이미지경로
//                .addResourceLocations("file:///C:/not404foundImageFile/img/upload/thumbnail/");
                .addResourceLocations("file:///C:/dev/NF404/SemiProject404NotFound/not404found/src/main/resources/static/images/productimg/upload/thumbnail/");
        registry.addResourceHandler("/imagePath/**")
                .addResourceLocations("file:///C:/dev/NF404/SemiProject404NotFound/not404found/src/main/resources/static/images/productimg/upload/original/");

        /* 맥북 경로 */
//        registry.addResourceHandler("/thumbPath/**") //이미지경로
//                .addResourceLocations("file:////Users/sooyeun/Desktop/dev/404NOTFOUND/fileuploadimg/upload/thumbnail/");
//        registry.addResourceHandler("/imagePath/**")
//                .addResourceLocations("file:////Users/sooyeun/Desktop/dev/404NOTFOUND/fileuploadimg/upload/original/");
//
//        registry.addResourceHandler("/summernoteImage/**")
//                .addResourceLocations("file:///" + summernotePath);

    }
}
