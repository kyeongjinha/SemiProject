package com.nf.not404found.board.model.service;

import com.nf.not404found.board.model.dao.BoardMapper;
import com.nf.not404found.board.model.dto.*;
import com.nf.not404found.common.exception.board.*;
import com.nf.not404found.common.functions.UserInformation;
import com.nf.not404found.common.paging.SelectCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService{

    private final BoardMapper mapper;
    private final UserInformation user;

    public BoardServiceImpl(BoardMapper mapper, UserInformation user) {
        this.mapper = mapper;
        this.user = user;
    }


    /* 공지사항 & QnA 게시글 전체 갯수 조회용 메소드 */
    @Override
    public int selectTotalCount(Map<String, Object> searchMap) {

        int result = mapper.selectTotalCount(searchMap);
        log.info("");
        log.info("");
        log.info("[BoardServiceImpl]  selectTotalCount ===================== {}", result);

        return result;
    }

    /* 공지사항 & QnA 게시글 전체 리스트 조회용 메소드 */
    @Override
    public List<BoardDTO> selectBoardList(Map<String, Object> selectCriteria2) {

        List<BoardDTO> boardList = mapper.selectBoardList(selectCriteria2);
        log.info("");
        log.info("");
        log.info("[BoardServiceImpl]  selectBoardList ===================== {}", boardList);

        return boardList;
    }

    @Override
    @Transactional
    public BoardDTO selectNoticeView(int post_code) {

        BoardDTO noticeView = null;

        /* 조회수 증가 */
        int result = mapper.incrementBoardCount(post_code);

        if (result > 0) {
            noticeView = mapper.selectNoticeView(post_code);
        }

        log.info("");
        log.info("");
        log.info("[BoardServiceImpl]  selectNoticeView ===================== {}", noticeView);

        return noticeView;
    }

    @Override
    public List<ReviewDTO> selectReviewList(Map<String, Object> selectCriteria2) {

        List<ReviewDTO> reviewList = mapper.selectReviewList(selectCriteria2);

        log.info("");
        log.info("");
        log.info("[BoardServiceImpl]  selectReviewList ===================== {}", reviewList);

        return reviewList;
    }

    @Override
    @Transactional
    public void writeNotice(BoardDTO board) throws NoticeWriteException {

        int result = mapper.insertNotice(board);

        if (!(result > 0)) {
            throw new NoticeWriteException("게시글 등록에 실패하셨습니다.");
        }
    }

    @Override
    public List<ReviewDTO> getTotalReviewCountByProduct(List<ReviewDTO> reviewList) {

        for(int i = 0; i < reviewList.size(); i++){
            int result = mapper.getTotalReviewCountByProduct(reviewList.get(i).getProduct().getProduct_code());

            reviewList.get(i).setReviewCount(result);
        }


        log.info("");
        log.info("");
        log.info("[BoardServiceImpl]  getTotalReviewCountByProduct ===================== {}", reviewList);

        return reviewList;
    }

    @Override
    @Transactional
    public BoardDTO selectQnaView(int post_code) {
        BoardDTO qnaView = null;

        /* 조회수 증가 */
        int result = mapper.incrementBoardCount(post_code);

        if (result > 0) {
            qnaView = mapper.selectQnaView(post_code);
        }

        log.info("");
        log.info("");
        log.info("[BoardServiceImpl]  selectNoticeView ===================== {}", qnaView);

        return qnaView;
    }

    /**
     * QnA 게시글 댓글 조회용 메소드
     */
    @Override
    public List<CommentDTO> selectQnaCommentList(int post_code) {

        List<CommentDTO> qnaCommentList = null;

        qnaCommentList = mapper.selectQnaCommentList(post_code);

        log.info("");
        log.info("");
        log.info("[BoardServiceImpl]  selectQnaCommentList ===================== {}", qnaCommentList);

        return qnaCommentList;
    }

    @Override
    @Transactional
    public List<CommentDTO> registQnaComment(CommentDTO registComment) throws CommentRegistException {
        int post_code = registComment.getPost_code();
        int categorycode_board = registComment.getCategorycode_board();
        String comment_body = registComment.getComment_body();
        String id = user.getId();

        int result = mapper.insertQnaComment(post_code, categorycode_board, comment_body, id);
        List<CommentDTO> commentList = selectQnaCommentList(registComment.getPost_code());
        if (result > 0) {
            return commentList;
        } else {
            throw new CommentRegistException("댓글 등록에 실패하셨습니다.");
        }

    }

    @Override
    @Transactional
    public List<CommentDTO> removeComment(CommentDTO removeComment) throws CommentRemoveException {

        int post_code = removeComment.getPost_code();
        int comment_code = removeComment.getComment_code();

        int result = mapper.deleteQnaComment(comment_code);
        List<CommentDTO> commentList = selectQnaCommentList(removeComment.getComment_code());

        if (result > 0) {
            return commentList;
        } else {
            throw new CommentRemoveException("댓글 삭제에 실패하셨습니다.");
        }

    }

    @Override
    @Transactional
    public void modifyNotice(BoardDTO board) throws NoticeModifyException {
        log.info("");
        log.info("");
        log.info("[BoardServiceImpl] modifyNotice =================================== start");
        int result = mapper.updateNotice(board);


        log.info("[BoardServiceImpl] modifyNotice =================================== result : {}", result);
        log.info("[BoardServiceImpl] modifyNotice =================================== end");

        if(!(result > 0)) {
            throw new NoticeModifyException("공지사항 수정에 실패하셨습니다.");
        }
    }

    @Override
    @Transactional
    public void removeNotice(int post_code) throws NoticeRemoveException {
        log.info("");
        log.info("");
        log.info("[BoardServiceImpl] removeNotice =================================== start");
        int result = mapper.deleteNotice(post_code);


        log.info("[BoardServiceImpl] removeNotice =================================== result : {}", result);
        log.info("[BoardServiceImpl] removeNotice =================================== end");

        if(!(result > 0)) {
            throw new NoticeRemoveException("공지사항 삭제에 실패하셨습니다.");
        }
    }

    @Override
    @Transactional
    public void writeQna(BoardDTO board) throws NoticeWriteException {
        int result = mapper.insertQna(board);

        if (!(result > 0)) {
            throw new NoticeWriteException("게시글 등록에 실패하셨습니다.");
        }
    }

    @Override
    @Transactional
    public void modifyQna(BoardDTO board) throws NoticeModifyException {
        log.info("");
        log.info("");
        log.info("[BoardServiceImpl] modifyQna =================================== start");
        int result = mapper.updateQna(board);


        log.info("[BoardServiceImpl] modifyQna =================================== result : {}", result);
        log.info("[BoardServiceImpl] modifyQna =================================== end");

        if(!(result > 0)) {
            throw new NoticeModifyException("QnA 수정에 실패하셨습니다.");
        }
    }

    @Override
    @Transactional
    public void removeQna(int post_code) throws NoticeRemoveException {
        log.info("");
        log.info("");
        log.info("[BoardServiceImpl] removeQna =================================== start");
        mapper.deleteQnaCommentByPostCode(post_code);
        int result = mapper.deleteQna(post_code);

        log.info("[BoardServiceImpl] removeQna =================================== result : {}", result);
        log.info("[BoardServiceImpl] removeQna =================================== end");

        if(!(result > 0)) {
            throw new NoticeRemoveException("QnA 삭제에 실패하셨습니다.");
        }
    }

    @Override
    @Transactional
    public void writeReview(BoardDTO board, ReviewDTO review) throws NoticeWriteException {

        log.info("board : " + board);
        log.info("review : " + review);

        int result1 = mapper.insertBoard(board);

        int post_code = board.getPost_code();
        String id = board.getAccount().getId();

        review.setPost_code(post_code);
        review.getBoard().getAccount().setId(id);

        log.info("post_code 확인!!!!!!!!!!!!" + post_code);

        int result = mapper.insertReview(review);

        log.info("[BoardServiceImpl] writeReview =================================== result : {}", result);
        log.info("[BoardServiceImpl] writeReview =================================== result1 : {}", result1);

        if (!(result1 > 0 && result > 0)) {
            throw new NoticeWriteException("게시글 등록에 실패하셨습니다.");
        }
    }

    @Override
    public int selectTotalIcCount(Map<String, Object> searchMap) {
        int result = mapper.selectTotalIcCount(searchMap);
        log.info("");
        log.info("");
        log.info("[BoardServiceImpl]  selectTotalIcCount ===================== {}", result);

        return result;
    }

    @Override
    public List<InteriorChallengeDTO> selectIcList(SelectCriteria selectCriteria) {
        List<InteriorChallengeDTO> icList = mapper.selectIcList(selectCriteria);
        log.info("");
        log.info("");
        log.info("[BoardServiceImpl]  selectIcList ===================== {}", icList);

        return icList;
    }

    @Override
    @Transactional
    public void registIc(InteriorChallengeDTO ic) throws NoticeWriteException {

        int result = mapper.insertIc(ic);

        int post_code = ic.getPost_code();

        // thumbnailAttachment를 attachment로 삽입
        AttachmentDTO thumbnailAttachment = ic.getAttachmentList().get(0); // thumbnailAttachment는 첫 번째로 추가된 파일이라 가정
        thumbnailAttachment.setPost_code(post_code);

        log.info("post_code 확인!!!!!!!!!!!!" + post_code);

        int resultThumbnail = mapper.insertAttachment(thumbnailAttachment);

        // 결과 확인 후 예외 처리
        if (resultThumbnail <= 0) {
            throw new NoticeWriteException("썸네일 파일 등록에 실패하셨습니다.");
        }

        if (result <= 0) {
            throw new NoticeWriteException("게시글 등록에 실패하셨습니다.");
        }
    }

    @Override
    public InteriorChallengeDTO selectIcView(int post_code) {

        InteriorChallengeDTO icView = null;

        int result = mapper.incrementIcCount(post_code);

        if (result > 0) {
            icView = mapper.selectIcView(post_code);
        }
        return icView;
    }

    @Override
    @Transactional
    public void modifyIc(InteriorChallengeDTO ic) throws NoticeModifyException {
        log.info("");
        log.info("");
        log.info("[BoardServiceImpl] modifyIc =================================== start");

        int result = mapper.updateIc(ic);

        log.info("[BoardServiceImpl] modifyIc =================================== result : {}", result);
        log.info("[BoardServiceImpl] modifyIc =================================== end");

        if(!(result > 0)) {
            throw new NoticeModifyException("인테리어 챌린지 수정에 실패하셨습니다.");
        }
    }

    @Override
    @Transactional
    public void removeIc(int post_code) throws NoticeRemoveException {
        log.info("");
        log.info("");
        log.info("[BoardServiceImpl] removeIc =================================== start");

        int result = mapper.deleteIc(post_code);

        log.info("[BoardServiceImpl] removeIc =================================== result : {}", result);
        log.info("[BoardServiceImpl] removeIc =================================== end");

        if(!(result > 0)) {
            throw new NoticeRemoveException("인테리어 챌린지 삭제에 실패하셨습니다.");
        }
    }
}
