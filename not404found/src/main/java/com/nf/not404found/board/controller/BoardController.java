package com.nf.not404found.board.controller;


import com.nf.not404found.account.model.dto.AccountDTO;
import com.nf.not404found.board.model.dto.BoardDTO;
import com.nf.not404found.board.model.dto.CommentDTO;
import com.nf.not404found.board.model.dto.ReviewDTO;
import com.nf.not404found.board.model.service.BoardService;
import com.nf.not404found.common.exception.board.*;
import com.nf.not404found.common.paging.Pagenation;
import com.nf.not404found.common.paging.SelectCriteria;
import com.nf.not404found.member.security.auth.model.dto.AuthDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    /**
     * ======================================================================== 공지사항 ========================================================================
     */
    @GetMapping(value = "notice/list")
    public ModelAndView noticeList(@RequestParam(required = false, defaultValue = "") String searchCondition,
                                  @RequestParam(required = false, defaultValue = "") String searchValue,
                                  @RequestParam(required = true, defaultValue = "1") int categorycode_board,
                                  @RequestParam(value="currentPage", defaultValue = "1") int pageNo,
                                  ModelAndView mv) {

        log.info("");
        log.info("");
        log.info("[BoardController] ========================================================= start");
        /*
         * 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다.
         * 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다.
         */

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("categorycode_board", categorycode_board); // categorycode_board 추가

        log.info("[BoardController] 컨트롤러에서 검색조건 확인하기 : " + searchMap);

        /*
         * 전체 게시물 수가 필요하다.
         * 데이터베이스에서 먼저 전체 게시물 수를 조회해올 것이다.
         * 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회한다.
         */

        int totalCount = boardService.selectTotalCount(searchMap);
        log.info("[BoardController] totalBoardCount : " + totalCount);

        /* 한 페이지에 보여줄 게시물 수 */
        int limit = 10;

        /* 한 번에 보여질 페이징 버튼의 갯수 */
        int buttonAmount = 5;

        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = null;

        if (searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }

        log.info("[BoardController] selectCriteria : " + selectCriteria);

        Map<String, Object> selectCriteria2 = new HashMap<>();
        selectCriteria2.put("selectCriteria", selectCriteria);
        selectCriteria2.put("categorycode_board", categorycode_board);

        /* 조회 */
        List<BoardDTO> boardList = boardService.selectBoardList(selectCriteria2);

        log.info("[BoardController] boardList : " + boardList);

        mv.addObject("boardList", boardList);
        mv.addObject("selectCriteria", selectCriteria);
        log.info("[BoardController] SelectCriteria : " + selectCriteria);
        mv.setViewName("board/notice/list");

        log.info("[BoardController] ========================================================= end");
        return mv;
    }


    @GetMapping("/notice/view")
    public String selectNoticeView(@RequestParam int post_code, Model model) {

        log.info("");
        log.info("");
        log.info("[BoardController] ========================================================= start");

        BoardDTO noticeView = boardService.selectNoticeView(post_code);
        log.info("[BoardController] noticeView : " + noticeView);

        model.addAttribute("noticeView", noticeView);

        log.info("[BoardController] ========================================================= end");
        return "board/notice/view";
    }

    @GetMapping("notice/write")
    public String goWriteNotice() {
        return "board/notice/write";
    }

    @PostMapping("notice/write")
    public String writeNotice(@ModelAttribute BoardDTO board, RedirectAttributes rttr) throws NoticeWriteException {

        log.info("");
        log.info("");
        log.info("[BoardController] writeNotice ========================================================= start");
        log.info("[BoardController] writeNotice =============================== board : " + board);


        boardService.writeNotice(board);

        rttr.addFlashAttribute("message", "게시글 등록에 성공하셨습니다!");

        log.info("[BoardController] writeNotice ========================================================= end");

        return "redirect:/board/notice/list";
    }

    @PostMapping(value="*/uploadSummernoteImageFile", produces = "application/json")
    @ResponseBody
    public Map<String, String> uploadSummernoteImgFile(@RequestParam("file") MultipartFile multipartFile){

        log.info("");
        log.info("");
        log.info("[BoardController] uploadSummernoteImgFile ========================================================= start");
        log.info("[BoardController] multipartFile ========================================================= {}", multipartFile);
        Map<String, String> returnMap = new HashMap<>();
        String fileRoot = "/Users/sooyeun/Desktop/dev/404NOTFOUND/fileupload/";
        String originFileName = multipartFile.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf("."));

        String savedFileName = UUID.randomUUID().toString().replace("-","") + ext;
        System.out.println("savedFileName = " + savedFileName);
        File targetFile = new File(fileRoot + savedFileName);

//        try {
//          InputStream fileStream = multipartFile.getInputStream();
//            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
//            returnMap.put("url", "/summernoteImage/"+savedFileName);
//            returnMap.put("responseCode", "success");
//        } catch (IOException e) {
//            FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
//          e.printStackTrace();
//        }

        log.info("[BoardController] uploadSummernoteImgFile ===================================================== return \n {}", returnMap);
        log.info("[BoardController] uploadSummernoteImgFile ========================================================= end");
        return returnMap;
    }

    @GetMapping("notice/modify")
    public String goModifyNotice(@RequestParam int post_code, Model model) {

        log.info("");
        log.info("");
        log.info("[NoticeController] modifyNotice ========================================================= start");

        BoardDTO noticeView = boardService.selectNoticeView(post_code);
        log.info("[BoardController] noticeView : " + noticeView);

        model.addAttribute("noticeView", noticeView);

        log.info("[BoardController] ========================================================= end");

        return "board/notice/modify";
    }

    @PostMapping("notice/modify")
    public String modifyNotice(@ModelAttribute BoardDTO board, RedirectAttributes rttr) throws NoticeModifyException {

        log.info("");
        log.info("");
        log.info("[BoardController] modifyNotice ========================================================= start");
        log.info("[BoardController] modifyNotice =============================== board : " + board);


        boardService.modifyNotice(board);

        rttr.addFlashAttribute("message", "게시글 수정에 성공하셨습니다!");

        log.info("[BoardController] modifyNotice ========================================================= end");

        return "redirect:/board/notice/list";
    }

    @GetMapping("notice/delete")
    public String removeNotice(@RequestParam int post_code, RedirectAttributes rttr) throws NoticeRemoveException {

        log.info("");
        log.info("");
        log.info("[BoardController] removeNotice ========================================================= start");
        log.info("[BoardController] removeNotice ========================================================= post_code : {} ", post_code);
        boardService.removeNotice(post_code);

        rttr.addFlashAttribute("message", "게시글 삭제에 성공하셨습니다!");
        log.info("[BoardController] removeNotice ========================================================= end");
        return "redirect:/board/notice/list";
    }



    /**
     * ======================================================================== Review ========================================================================
     */
    @GetMapping(value = "review/review")
    public ModelAndView reviewList(@RequestParam(required = false, defaultValue = "") String searchCondition,
                                   @RequestParam(required = false, defaultValue = "") String searchValue,
                                   @RequestParam(required = true, defaultValue = "2") int categorycode_board,
                                   @RequestParam(value="currentPage", defaultValue = "1") int pageNo,
                                   ModelAndView mv) {

        log.info("");
        log.info("");
        log.info("[BoardController / review] ========================================================= start");
        /*
         * 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다.
         * 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다.
         */

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("categorycode_board", categorycode_board); // categorycode_board 추가


        log.info("[BoardController / review] 컨트롤러에서 검색조건 확인하기 : " + searchMap);

        /*
         * 전체 게시물 수 조회
         * 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회한다.
         */

        int totalCount = boardService.selectTotalCount(searchMap);
        log.info("[BoardController / review] totalBoardCount : " + totalCount);

        /* 한 페이지에 보여줄 게시물 수 */
        int limit = 5;

        /* 한 번에 보여질 페이징 버튼의 갯수 */
        int buttonAmount = 5;

        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = null;

        if (searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }

        log.info("[BoardController / review] selectCriteria : " + selectCriteria);

        Map<String, Object> selectCriteria2 = new HashMap<>();
        selectCriteria2.put("selectCriteria", selectCriteria);
        selectCriteria2.put("categorycode_board", categorycode_board);

        /* 조회 */
        List<ReviewDTO> reviewList = boardService.selectReviewList(selectCriteria2);

        log.info("[BoardController] reviewList : " + reviewList);

        /* 상품코드 별 리뷰 누적 갯수 */
        List<ReviewDTO> totalReviewCountByProduct = boardService.getTotalReviewCountByProduct(reviewList);

        log.info("===================================> totalReviewCountByProduct : " + totalReviewCountByProduct);


        mv.addObject("totalReviewCountByProduct", totalReviewCountByProduct);

        mv.addObject("reviewList", reviewList);
        mv.addObject("selectCriteria", selectCriteria);
        log.info("[BoardController / review] SelectCriteria2 : " + selectCriteria2);
        mv.setViewName("board/review/review");

        log.info("[BoardController / review] ========================================================= end");
        return mv;
    }

    @GetMapping("review/write")
    public String goWriteReview() {
        return "board/review/write";
    }



    @PostMapping("review/write")
    public String writeReview(@ModelAttribute BoardDTO board,
                              @ModelAttribute ReviewDTO review,
                              @AuthenticationPrincipal AuthDetails principal,
                              RedirectAttributes rttr) throws NoticeWriteException {

        log.info("");
        log.info("");
        log.info("[BoardController] writeReview ========================================================= start");
        log.info("[BoardController] writeReview =============================== board : " + board);
        log.info("[BoardController] writeReview =============================== review : " + review);
        log.info("[BoardController] writeReview =============================== principal.getUsername() : " + principal.getUsername());


        // 사용자 이름 가져오기
        String username = principal.getUsername();
        log.info("username/id 확인!!!!!!!!!!!!!! : " + username);

        if (board == null) {
            board = new BoardDTO();
        }
        if (review.getBoard() == null) {
            review.setBoard(new BoardDTO());
        }

        if (board.getAccount() == null) {
            board.setAccount(new AccountDTO());
        }
        if (review.getBoard().getAccount() == null) {
            review.getBoard().setAccount(new AccountDTO());
        }

        // AccountDTO에 사용자 이름 설정
        board.getAccount().setId(username);
        review.getBoard().getAccount().setId(username);

        float star_rating = review.getStar_rating();
        review.setStar_rating(star_rating);

        boardService.writeReview(board, review);

        rttr.addFlashAttribute("message", "게시글 등록에 성공하셨습니다!");
        rttr.addAttribute("star_rating", star_rating);

        log.info("[BoardController] writeReview ========================================================= end");

        return "redirect:/board/review/review";
    }





    /**
     * ======================================================================== QnA ========================================================================
     */
    @GetMapping("/qna/*")
    public String QnABoardLocation() {

        return "/board/qna/list";
    }

    @GetMapping(value = "qna/list")
    public ModelAndView qnaList(@RequestParam(required = false, defaultValue = "") String searchCondition,
                                   @RequestParam(required = false, defaultValue = "") String searchValue,
                                   @RequestParam(required = true, defaultValue = "3") int categorycode_board,
                                   @RequestParam(value="currentPage", defaultValue = "1") int pageNo,
                                   ModelAndView mv) {

        log.info("");
        log.info("");
        log.info("[BoardController] ========================================================= start");
        /*
         * 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다.
         * 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다.
         */

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("categorycode_board", categorycode_board); // categorycode_board 추가

        log.info("[BoardController] 컨트롤러에서 검색조건 확인하기 : " + searchMap);

        /*
         * 전체 게시물 수가 필요하다.
         * 데이터베이스에서 먼저 전체 게시물 수를 조회해올 것이다.
         * 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회한다.
         */

        int totalCount = boardService.selectTotalCount(searchMap);
        log.info("[BoardController] totalBoardCount : " + totalCount);

        /* 한 페이지에 보여줄 게시물 수 */
        int limit = 10;

        /* 한 번에 보여질 페이징 버튼의 갯수 */
        int buttonAmount = 5;

        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = null;

        if (searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }

        log.info("[BoardController] selectCriteria : " + selectCriteria);

        Map<String, Object> selectCriteria2 = new HashMap<>();
        selectCriteria2.put("selectCriteria", selectCriteria);
        selectCriteria2.put("categorycode_board", categorycode_board);

        /* 조회 */
        List<BoardDTO> boardList = boardService.selectBoardList(selectCriteria2);

        log.info("[BoardController] boardList : " + boardList);

        mv.addObject("boardList", boardList);
        mv.addObject("selectCriteria", selectCriteria);
        log.info("[BoardController] SelectCriteria : " + selectCriteria);
        mv.setViewName("board/qna/list");

        log.info("[BoardController] ========================================================= end");
        return mv;
    }

    @GetMapping("/qna/view")
    public String selectQnaView(@RequestParam int post_code, Model model) {

        log.info("");
        log.info("");
        log.info("[BoardController] ========================================================= start");

        BoardDTO qnaView = boardService.selectQnaView(post_code);
        log.info("[BoardController] QnaView : " + qnaView);

        model.addAttribute("qnaView", qnaView);

        /* 댓글 */
        List<CommentDTO> qnaCommentList = boardService.selectQnaCommentList(post_code);
        model.addAttribute("qnaCommentList", qnaCommentList);
        log.info("[BoardController] qnaCommentList : " + qnaCommentList);

        log.info("[BoardController] ========================================================= end");
        return "board/qna/view";
    }

    @PostMapping(value = "qna/registComment", produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<CommentDTO> registComment(@RequestBody CommentDTO registComment) throws CommentRegistException {


        return boardService.registQnaComment(registComment);
    }

    @PostMapping(value = "qna/removeComment", produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<CommentDTO> removeComment(@RequestBody CommentDTO removeComment) throws CommentRemoveException {

//
        return boardService.removeComment(removeComment);
    }

    @GetMapping("qna/write")
    public String goWriteQna() {
        return "board/qna/write";
    }

    @PostMapping("qna/write")
    @Transactional
    public String writeQna(@ModelAttribute BoardDTO board, RedirectAttributes rttr) throws NoticeWriteException {

        log.info("");
        log.info("");
        log.info("[BoardController] writeQna ========================================================= start");
        log.info("[BoardController] writeQna =============================== board : " + board);


        boardService.writeQna(board);

        rttr.addFlashAttribute("message", "게시글 등록에 성공하셨습니다!");

        log.info("[BoardController] writeQna ========================================================= end");

        return "redirect:/board/qna/list";
    }

    @GetMapping("qna/modify")
    public String goModifyQna(@RequestParam int post_code, Model model) {

        log.info("");
        log.info("");
        log.info("[BoardController] goModifyQna ========================================================= start");

        BoardDTO qnaView = boardService.selectQnaView(post_code);
        log.info("[BoardController] qnaView : " + qnaView);

        model.addAttribute("qnaView", qnaView);

        log.info("[BoardController] ========================================================= end");

        return "board/qna/modify";
    }

    @PostMapping("qna/modify")
    @Transactional
    public String modifyQna(@ModelAttribute BoardDTO board, RedirectAttributes rttr) throws NoticeModifyException {

        log.info("");
        log.info("");
        log.info("[BoardController] modifyQna ========================================================= start");
        log.info("[BoardController] modifyQna =============================== board : " + board);


        boardService.modifyQna(board);

        rttr.addFlashAttribute("message", "게시글 수정에 성공하셨습니다!");

        log.info("[BoardController] modifyQna ========================================================= end");

        return "redirect:/board/qna/list";
    }

    @GetMapping("qna/delete")
    @Transactional
    public String removeQna(@RequestParam int post_code, RedirectAttributes rttr) throws NoticeRemoveException {

        log.info("");
        log.info("");
        log.info("[BoardController] removeQna ========================================================= start");
        log.info("[BoardController] removeQna ========================================================= post_code : {} ", post_code);
        boardService.removeQna(post_code);

        rttr.addFlashAttribute("message", "게시글 삭제에 성공하셨습니다!");
        log.info("[BoardController] removeQna ========================================================= end");
        return "redirect:/board/qna/list";
    }

    /**
     * ======================================================================== 인테리어 챌린지 ========================================================================
     */
//
//    @GetMapping(value = "ic/list")
//    public ModelAndView icList(@RequestParam(required = false, defaultValue = "") String searchCondition,
//                                @RequestParam(required = false, defaultValue = "") String searchValue,
//                                @RequestParam(required = true, defaultValue = "4") int categorycode_board,
//                                @RequestParam(value="currentPage", defaultValue = "1") int pageNo,
//                                ModelAndView mv) {
//
//        log.info("");
//        log.info("");
//        log.info("[BoardController] ========================================================= start");
//        /*
//         * 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다.
//         * 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다.
//         */
//
//        Map<String, Object> searchMap = new HashMap<>();
//        searchMap.put("searchCondition", searchCondition);
//        searchMap.put("searchValue", searchValue);
//        searchMap.put("categorycode_board", categorycode_board); // categorycode_board 추가
//
//        log.info("[BoardController] 컨트롤러에서 검색조건 확인하기 : " + searchMap);
//
//        /*
//         * 전체 게시물 수가 필요하다.
//         * 데이터베이스에서 먼저 전체 게시물 수를 조회해올 것이다.
//         * 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회한다.
//         */
//
//        int totalCount = boardService.selectTotalCount(searchMap);
//        log.info("[BoardController] totalBoardCount : " + totalCount);
//
//        /* 한 페이지에 보여줄 게시물 수 */
//        int limit = 9;
//
//        /* 한 번에 보여질 페이징 버튼의 갯수 */
//        int buttonAmount = 5;
//
//        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
//        SelectCriteria selectCriteria = null;
//
//        if (searchCondition != null && !"".equals(searchCondition)) {
//            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
//        } else {
//            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
//        }
//
//        log.info("[BoardController] selectCriteria : " + selectCriteria);
//
//        Map<String, Object> selectCriteria2 = new HashMap<>();
//        selectCriteria2.put("selectCriteria", selectCriteria);
//        selectCriteria2.put("categorycode_board", categorycode_board);
//
//        /* 조회 */
//        List<BoardDTO> boardList = boardService.selectBoardList(selectCriteria2);
//
//        log.info("[BoardController] boardList : " + boardList);
//
//        mv.addObject("boardList", boardList);
//        mv.addObject("selectCriteria", selectCriteria);
//        log.info("[BoardController] SelectCriteria : " + selectCriteria);
//        mv.setViewName("board/ic/list");
//
//        log.info("[BoardController] ========================================================= end");
//        return mv;
//    }


}


