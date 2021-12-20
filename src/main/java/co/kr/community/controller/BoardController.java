package co.kr.community.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;

import org.apache.commons.io.FileUtils;

import co.kr.community.entity.Board;
import co.kr.community.entity.Category;
import co.kr.community.service.BoardService;
import co.kr.community.service.CategoryService;

@Controller
public class BoardController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	BoardService boardService;

	// 게시판 조회
//	@RequestMapping(value = "/board", method = RequestMethod.GET)
//	public ModelAndView list() {
//		ModelAndView mv = new ModelAndView("board/list");
//		List<Board> boards = boardService.getBoardList();
//		mv.addObject("boards", boards);
//		return mv;
//	}
	
	// 게시판 조회(페이징)
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public ModelAndView list(@PageableDefault Pageable pageable) {
		ModelAndView mv = new ModelAndView("board/list");
		Page<Board> boards = boardService.getBoardList(pageable);
		mv.addObject("boards", boards);
		return mv;
	}

	// 게시물 작성 페이지
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public ModelAndView writePage() {
		ModelAndView mv = new ModelAndView("board/write");
		List<Category> categories = categoryService.getCategoryList();
		mv.addObject("categories", categories);
		return mv;
	}
	
	// 게시물 작성
	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
//	@ResponseBody
	public String write(Board board) {
		boardService.insert(board);
//		String test = "Success";
//		return test;
		return "redirect:/board";
	}
	
	// 게시물 조회
	@RequestMapping(value = "/board/{bNo}", method = RequestMethod.GET)
	public ModelAndView content(@PathVariable("bNo") Long bNo) {
		boardService.updateViewCnt(bNo);
		ModelAndView mv = new ModelAndView("board/content");
		Optional<Board> content = boardService.getBoardContent(bNo);
		mv.addObject("content", content.get()); // Optional에서 값을 빼오려면 get()을 써줘야 함
		return mv;
	}
	
	// 게시물 수정 페이지
	@RequestMapping(value = "/board/modify/{bNo}", method = RequestMethod.GET)
	public ModelAndView modify(@PathVariable("bNo") Long bNo) {
		ModelAndView mv = new ModelAndView("board/modify");
		Optional<Board> content = boardService.getBoardContent(bNo);
		mv.addObject("content", content.get());
		return mv;
	}
	
	// 게시물 수정
	@RequestMapping(value = "/board/{bNo}", method = RequestMethod.PUT)
	public String modify(Board board, RedirectAttributes redirectAttributes) {
		System.out.println("board ======================> " + board);
		boardService.update(board);
		redirectAttributes.addAttribute("b_no", board.getBNo());
		return "redirect:/board/{b_no}";
	}
	
	// 게시물 삭제
	@RequestMapping(value = "/board/{bNo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("bNo") Long bNo) {
		System.out.println("bNo ======================> " + bNo);
		boardService.delete(bNo);
		return "redirect:/board";
	}
	
	
	
	// 썸머 노트 file 컨트롤러
//	@RequestMapping(value = "/board/uploadSummernoteImageFile", method = RequestMethod.POST, produces = "application/json")
//	@ResponseBody
//	public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
//
//		System.out.println("ssssssssssssssssssssssssssssssss");
//		JsonObject jsonObject = new JsonObject();
//
//		String fileRoot = "C:\\summernote_image\\"; // 저장될 외부 파일 경로
//		String originalFileName = multipartFile.getOriginalFilename(); // 오리지날 파일명
//		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자
//
//		String savedFileName = UUID.randomUUID() + extension; // 저장될 파일 명
//		
//		System.out.println(savedFileName);
//
//		File targetFile = new File(fileRoot + savedFileName);
//
//		try {
//			InputStream fileStream = multipartFile.getInputStream();
//			FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장
//			jsonObject.addProperty("url", "/summernoteImage/" + savedFileName);
//			System.out.println("2222222222222222222");
//			jsonObject.addProperty("responseCode", "success");
//
//		} catch (IOException e) {
//			FileUtils.deleteQuietly(targetFile); // 저장된 파일 삭제
//			jsonObject.addProperty("responseCode", "error");
//			e.printStackTrace();
//		}
//
//		return jsonObject;
//	}
	


}
