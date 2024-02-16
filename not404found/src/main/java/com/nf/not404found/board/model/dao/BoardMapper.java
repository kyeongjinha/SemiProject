package com.nf.not404found.board.model.dao;

import com.nf.not404found.board.model.dto.*;
import com.nf.not404found.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    int selectTotalCount(Map<String, Object> searchMap);

    List<BoardDTO> selectBoardList(Map<String, Object> selectCriteria2);

    int incrementBoardCount(int post_code);

    BoardDTO selectNoticeView(int post_code);

    List<ReviewDTO> selectReviewList(Map<String, Object> selectCriteria2);

    int insertNotice(BoardDTO board);

    int getTotalReviewCountByProduct(int product_code);

    BoardDTO selectQnaView(int post_code);

    List<CommentDTO> selectQnaCommentList(int post_code);

    int insertQnaComment(int post_code, int categorycode_board, String comment_body, String id);

    int deleteQnaComment(int comment_code);

    int updateNotice(BoardDTO board);


    int deleteNotice(int postCode);

    int insertQna(BoardDTO board);

    int updateQna(BoardDTO board);

    int deleteQna(int postCode);

    void deleteQnaCommentByPostCode(int post_code);

    int insertReview(ReviewDTO review);

    int insertBoard(BoardDTO board);

    int selectTotalIcCount(Map<String, Object> searchMap);

    List<InteriorChallengeDTO> selectIcList(SelectCriteria selectCriteria);

    int insertIc(InteriorChallengeDTO ic);

    int insertAttachment(AttachmentDTO ic);

    int incrementIcCount(int post_code);

    InteriorChallengeDTO selectIcView(int post_code);

    int updateIc(InteriorChallengeDTO ic);

    int deleteIc(int post_code);
}
