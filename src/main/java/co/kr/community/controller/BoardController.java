package co.kr.community.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

import org.apache.commons.io.FileUtils;

import co.kr.community.entity.Board;
import co.kr.community.entity.Category;
import co.kr.community.service.BoardService;
import co.kr.community.service.CategoryService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	BoardService boardService;

	// 게시판 조회
	@GetMapping("/list")
	public String list(Model model) {
		List<Board> boards = boardService.getBoardList();
		model.addAttribute("boards", boards);
		return "board/list";
	}

	// 게시물 작성 페이지
	@GetMapping("/write")
	public String write(Model model) {
		List<Category> categories = categoryService.getCategoryList();
		model.addAttribute("categories", categories);
		return "board/write";
	}
	
	// 썸머 노트 file 컨트롤러
	@PostMapping(value = "/uploadSummernoteImageFile", produces = "application/json")
	@ResponseBody
	public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

		JsonObject jsonObject = new JsonObject();

		String fileRoot = "C:\\summernote_image\\"; // 저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename(); // 오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자

		String savedFileName = UUID.randomUUID() + extension; // 저장될 파일 명

		File targetFile = new File(fileRoot + savedFileName);

		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장
			jsonObject.addProperty("url", "/summernoteImage/" + savedFileName);
			jsonObject.addProperty("responseCode", "success");

		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile); // 저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}

		return jsonObject;
	}

	// 게시물 작성
	@PostMapping("/write")
	@ResponseBody
	public String write(Board board) {
		boardService.insert(board);
		String test = "Success";
		return test;
	}
	
	@GetMapping("/content")
	public String content(@RequestParam("b_no") Long b_no, Model model) {
		Optional<Board> content = boardService.getBoardContent(b_no);
		model.addAttribute("content", content.get()); // Optional에서 값을 빼오려면 get()을 써줘야 함
		return "board/content";
	}



}
