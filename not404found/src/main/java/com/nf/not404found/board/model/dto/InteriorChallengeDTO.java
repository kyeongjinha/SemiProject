package com.nf.not404found.board.model.dto;

import com.nf.not404found.account.model.dto.AccountDTO;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InteriorChallengeDTO {

    private int post_code;
    private AccountDTO account;
    private String post_title;
    private String post_content;
    private Date post_created_date;
    private Date post_modified_date;
    private int post_views;
    private int recommend_count;
    private List<AttachmentDTO> attachmentList = new ArrayList<>();

    public InteriorChallengeDTO(List<AttachmentDTO> attachmentList) {
        this.attachmentList = attachmentList != null ? attachmentList : new ArrayList<>();
    }

}
