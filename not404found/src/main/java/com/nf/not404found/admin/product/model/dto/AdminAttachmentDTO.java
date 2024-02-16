package com.nf.not404found.admin.product.model.dto;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminAttachmentDTO {
    private Long no;    // 이미지 번호 오토인크리먼트
    private Long productCode;
    private String originalName;   // 원래 이름
    private String ModifyName;   // 수정된 이름
    private String savedPath;   // 수정된 경로
    private String fileType;    // 파일 타입
    private String thumbnailPath;   // 섬네일 경로
    private String status;      // 등록여부
}
