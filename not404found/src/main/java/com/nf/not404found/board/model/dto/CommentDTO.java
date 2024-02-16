package com.nf.not404found.board.model.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDTO {

    private int comment_code;
    private int post_code;
    private int categorycode_board;
    BoardDTO board;
    private int postCode;
    private String comment_body;
    private Date comment_date;
    private String id;

    public CommentDTO(int post_code, int categorycode_board, String comment_body, String id) {
        this.post_code = post_code;
        this.categorycode_board = categorycode_board;
        this.comment_body = comment_body;
        this.id = id;
    }
}
