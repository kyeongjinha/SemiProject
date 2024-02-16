package com.nf.not404found.board.controller;

import com.nf.not404found.admin.common.exception.ThumbnailRegistException;
import com.nf.not404found.board.model.dto.AttachmentDTO;
import com.nf.not404found.board.model.dto.BoardDTO;
import com.nf.not404found.board.model.dto.InteriorChallengeDTO;
import com.nf.not404found.board.model.service.BoardService;
import com.nf.not404found.common.exception.board.NoticeModifyException;
import com.nf.not404found.common.exception.board.NoticeRemoveException;
import com.nf.not404found.common.exception.board.NoticeWriteException;
import com.nf.not404found.common.paging.Pagenation;
import com.nf.not404found.common.paging.SelectCriteria;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;


@Controller
@RequestMapping("/board/ic")
@Slf4j
public class InteriorChallengeController {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    @Value("${spring.servlet.multipart.location}")
    private String ROOT_LOCATION;

    private final BoardService boardService;

    public InteriorChallengeController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public ModelAndView selectIcList(@RequestParam(required = false, defaultValue = "") String searchCondition,
                                     @RequestParam(required = false, defaultValue = "") String searchValue,
                                     @RequestParam(value="currentPage", defaultValue = "1") int pageNo,
                                     ModelAndView mv) {

        log.info("");
        log.info("");
        log.info("[InteriorChallengeController] ========================================================= start");

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        log.info("[InteriorChallengeController] 컨트롤러에서 검색조건 확인하기 : " + searchMap);

        /*
         * 전체 게시물 수가 필요하다.
         * 데이터베이스에서 먼저 전체 게시물 수를 조회해올 것이다.
         * 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회한다.
         */

        int totalIcCount = boardService.selectTotalIcCount(searchMap);
        log.info("[InteriorChallengeController] totalIcCount : " + totalIcCount);

        /* 한 페이지에 보여줄 게시물 수 */
        int limit = 9;

        /* 한 번에 보여질 페이징 버튼의 갯수 */
        int buttonAmount = 5;

        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = null;

        if (searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalIcCount, limit, buttonAmount, searchCondition, searchValue);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalIcCount, limit, buttonAmount);
        }

        log.info("[InteriorChallengeController] selectCriteria : " + selectCriteria);


        List<InteriorChallengeDTO> icList = boardService.selectIcList(selectCriteria);

        log.info("[InteriorChallengeController] icList : " + icList);

        mv.addObject("selectCriteria", selectCriteria);
        mv.addObject("icList", icList);

        mv.setViewName("board/ic/list");

        log.info("[InteriorChallengeController] ========================================================= end");

        return mv;
    }


    @GetMapping("/write")
    public String goWriteIc() {
        return "board/ic/write";
    }

    @PostMapping("/write")
    public String registIc(@ModelAttribute InteriorChallengeDTO ic,
                           @RequestParam("thumbnailImg") MultipartFile thumbnailImg, RedirectAttributes rttr)
            throws UnsupportedEncodingException, ThumbnailRegistException {

        log.info("");
        log.info("");
        log.info("[InteriorChallengeController] ========================================================= start");

        //String rootLocation = request.getSession().getServletContext().getRealPath("/resources");
        String rootLocation = ROOT_LOCATION + IMAGE_DIR;


        String fileUploadDirectory = rootLocation + "/upload/original";
        String thumbnailDirectory = rootLocation + "/upload/thumbnail";


        File directory = new File(fileUploadDirectory);
        File directory2 = new File(thumbnailDirectory);

        log.info("[InteriorChallengeController] fileUploadDirectory : " + directory);
        log.info("[InteriorChallengeController] thumbnailDirectory : " + directory2);

        /* 파일 저장경로가 존재하지 않는 경우 디렉토리를 생성한다. */
        if (!directory.exists() || !directory2.exists()) {

            /* 폴더를 한 개만 생성할거면 mkdir, 상위 폴더도 존재하지 않으면 한 번에 생성하란 의미로 mkdirs를 이용한다. */
            log.info("[InteriorChallengeController] 폴더 생성 : " + directory.mkdirs());
            log.info("[InteriorChallengeController] 폴더 생성 : " + directory2.mkdirs());
        }


        try {
            if (thumbnailImg.getSize() > 0) {
                String original_attachment_name = thumbnailImg.getOriginalFilename();

                log.info("[InteriorChallengeController] originFileName : " + original_attachment_name);

                String ext = original_attachment_name.substring(original_attachment_name.lastIndexOf("."));
                String attachment_name = UUID.randomUUID().toString().replace("-", "") + ext;

                log.info("[InteriorChallengeController] 변경한 이름 : " + attachment_name);

                log.info("[InteriorChallengeController] paramFile : " + fileUploadDirectory + "/" + attachment_name);
                thumbnailImg.transferTo(new File(fileUploadDirectory + "/" + attachment_name));

                /* DB에 업로드한 파일의 정보를 저장하는 비지니스 로직 수행 */
                /* 필요한 정보를 Map에 담는다. */
                Map<String, String> fileMap = new HashMap<>();
                fileMap.put("original_attachment_name", original_attachment_name);
                fileMap.put("original_attachment_name", original_attachment_name);
                fileMap.put("saved_path", fileUploadDirectory);


                /* 썸네일로 변환 할 사이즈를 지정한다. */
                int width = 300;
                int height = 300;

                /* 썸네일로 변환 후 저장한다. */
                Thumbnails.of(fileUploadDirectory + "/" + attachment_name).size(width, height)
                        .toFile(thumbnailDirectory + "/thumbnail_" + attachment_name);

                /* 나중에 웹서버에서 접근 가능한 경로 형태로 썸네일의 저장 경로도 함께 저장한다. */
                String thumbnail_path = "/thumbnail_" + attachment_name;

                /* InteriorChallengeDTO에 썸네일 경로를 설정한다. */

//                List<AttachmentDTO> attachmentList = ic.getAttachmentList();
                AttachmentDTO thumbnailAttachment = new AttachmentDTO();
                thumbnailAttachment.setOriginal_attachment_name(original_attachment_name);
                thumbnailAttachment.setAttachment_name(attachment_name);
                thumbnailAttachment.setSaved_path(fileUploadDirectory);
                thumbnailAttachment.setThumbnail_path(thumbnail_path);

//                attachmentList.add(thumbnailAttachment);

                ic.setAttachmentList(new ArrayList<AttachmentDTO>());
                ic.getAttachmentList().add(thumbnailAttachment);
            }

            log.info("[InteriorChallengeController] ic : " + ic);

            boardService.registIc(ic);

            rttr.addFlashAttribute("message", "인테리어 챌린지 등록에 성공하셨습니다.");

        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            // thumbnailImg 예외 처리
            if (thumbnailImg.getSize() > 0) {
                String thumbnailImgSavedFileName = UUID.randomUUID().toString().replace("-", "") +
                        thumbnailImg.getOriginalFilename().substring(thumbnailImg.getOriginalFilename().lastIndexOf("."));

                File deleteFile = new File(fileUploadDirectory + "/" + thumbnailImgSavedFileName);
                boolean isDeleted1 = deleteFile.delete();

                File deleteThumbnail = new File(thumbnailDirectory + "/thumbnail_" + thumbnailImgSavedFileName);
                boolean isDeleted2 = deleteThumbnail.delete();

                if (isDeleted1 && isDeleted2) {
                    log.info("[InteriorChallengeController] thumbnailImg 업로드 실패 시 삭제 완료!");
                } else {
                    log.error("[InteriorChallengeController] thumbnailImg 업로드 실패 시 삭제 실패!");
                }
            }
        } catch (NoticeWriteException e) {
            e.printStackTrace();
        }

        log.info("[InteriorChallengeController] ========================================================= end");

        return "redirect:/board/ic/list";
    }


    @GetMapping("/view")
    public String selectIcView(@RequestParam int post_code, Model model) {

        log.info("");
        log.info("");
        log.info("[InteriorChallengeController] ========================================================= start");

        InteriorChallengeDTO icView = boardService.selectIcView(post_code);
        log.info("[InteriorChallengeController] icView : " + icView);

        model.addAttribute("icView", icView);

        log.info("[InteriorChallengeController] ========================================================= end");
        return "board/ic/view";
    }

    @GetMapping("/getIcView")
    @ResponseBody
    public InteriorChallengeDTO getIcView(@RequestParam int post_code) {
        return boardService.selectIcView(post_code);
    }

    @GetMapping("modify")
    public String goModifyIc(@RequestParam int post_code, Model model) {

        log.info("");
        log.info("");
        log.info("[InteriorChallengeController] goModifyIc ========================================================= start");

        InteriorChallengeDTO icView = boardService.selectIcView(post_code);
        log.info("[InteriorChallengeController] icView : " + icView);

        model.addAttribute("icView", icView);

        log.info("[InteriorChallengeController] ========================================================= end");

        return "board/ic/modify";
    }

    @PostMapping("/modify")
    @Transactional
    public String modifyIc(@ModelAttribute InteriorChallengeDTO ic, RedirectAttributes rttr) throws NoticeModifyException {

        log.info("");
        log.info("");
        log.info("[InteriorChallengeController] modifyIc ========================================================= start");
        log.info("[InteriorChallengeController] modifyIc =============================== board : " + ic);


        boardService.modifyIc(ic);

        rttr.addFlashAttribute("message", "게시글 수정에 성공하셨습니다!");

        log.info("[InteriorChallengeController] modifyIc ========================================================= end");

        return "redirect:/board/ic/list";
    }

    @GetMapping("/delete")
    @Transactional
    public String removeIc(@RequestParam int post_code, RedirectAttributes rttr) throws NoticeRemoveException {

        log.info("");
        log.info("");
        log.info("[InteriorChallengeController] removeIc ========================================================= start");
        log.info("[InteriorChallengeController] removeIc ========================================================= post_code : {} ", post_code);
        boardService.removeIc(post_code);

        rttr.addFlashAttribute("message", "게시글 삭제에 성공하셨습니다!");
        log.info("[InteriorChallengeController] removeIc ========================================================= end");
        return "redirect:/board/ic/list";
    }

}

