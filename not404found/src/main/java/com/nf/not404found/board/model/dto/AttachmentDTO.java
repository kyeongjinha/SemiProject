package com.nf.not404found.board.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AttachmentDTO {

    private int attachment_code;
//    private BoardDTO boardInfo;
    private int post_code;
    private String original_attachment_name; // 원래 이름
    private String attachment_name; // 수정된 이
    private String saved_path;   // 수정된 경로
    private String file_type;    // 파일 타입
    private String thumbnail_path;   // 섬네일 경로
    private String status;
}
