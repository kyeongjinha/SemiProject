package com.nf.not404found.board.model.service;

import com.nf.not404found.board.model.dto.BoardDTO;
import com.nf.not404found.board.model.dto.CommentDTO;
import com.nf.not404found.board.model.dto.InteriorChallengeDTO;
import com.nf.not404found.board.model.dto.ReviewDTO;
import com.nf.not404found.common.exception.board.*;
import com.nf.not404found.common.paging.SelectCriteria;

import java.util.List;
import java.util.Map;

public interface BoardService {

    public int selectTotalCount(Map<String, Object> searchMap);

    public List<BoardDTO> selectBoardList(Map<String, Object> selectCriteria2);

    BoardDTO selectNoticeView(int post_code);

    List<ReviewDTO> selectReviewList(Map<String, Object> selectCriteria2);

    void writeNotice(BoardDTO board) throws NoticeWriteException;

    List<ReviewDTO> getTotalReviewCountByProduct(List<ReviewDTO> reviewList);

    BoardDTO selectQnaView(int post_code);

    List<CommentDTO> selectQnaCommentList(int post_code);

    List<CommentDTO> registQnaComment(CommentDTO registComment) throws CommentRegistException;

    List<CommentDTO> removeComment(CommentDTO removeComment) throws CommentRemoveException;

    void modifyNotice(BoardDTO board) throws NoticeModifyException;

    void removeNotice(int post_code) throws NoticeRemoveException;

    void writeQna(BoardDTO board) throws NoticeWriteException;

    void modifyQna(BoardDTO board) throws NoticeModifyException;

    void removeQna(int post_code) throws NoticeRemoveException;

    void writeReview(BoardDTO board, ReviewDTO review) throws NoticeWriteException;

    int selectTotalIcCount(Map<String, Object> searchMap);

    List<InteriorChallengeDTO> selectIcList(SelectCriteria selectCriteria);

    void registIc(InteriorChallengeDTO ic) throws NoticeWriteException;

    InteriorChallengeDTO selectIcView(int post_code);

    void modifyIc(InteriorChallengeDTO ic) throws NoticeModifyException;

    void removeIc(int post_code) throws NoticeRemoveException;
}
