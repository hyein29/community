package co.kr.community.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.kr.community.entity.Board;
import co.kr.community.entity.Category;
import co.kr.community.entity.Comment;
import co.kr.community.service.BoardService;
import co.kr.community.service.CategoryService;
import co.kr.community.service.CommentService;

@Controller
public class BoardController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	BoardService boardService;
	
	@Autowired
	CommentService commentService;

	// 게시판 조회
//	@RequestMapping(value = "/board", method = RequestMethod.GET)
//	public ModelAndView list() {
//		ModelAndView mv = new ModelAndView("board/list");
//		List<Board> boards = boardService.getBoardList();
//		mv.addObject("boards", boards);
//		return mv;
//	}
	
	// 게시판 조회(페이징)
	@GetMapping(value = "/board")
	public ModelAndView list(@PageableDefault Pageable pageable) {
		ModelAndView mv = new ModelAndView("board/list");
		Page<Board> boards = boardService.getBoardList(pageable);
		mv.addObject("boards", boards);
		return mv;
	}

	// 게시물 작성 페이지
	@GetMapping(value = "/board/write")
	public ModelAndView writePage() {
		ModelAndView mv = new ModelAndView("board/write");
		List<Category> categories = categoryService.getCategoryList();
		mv.addObject("categories", categories);
		return mv;
	}
	
	// 게시물 작성
	@PostMapping(value = "/board/write")
	@ResponseBody
	public String write(Board board, Principal principal) {
		
		return boardService.insert(board, principal);
	}
	
	// 게시물 조회
	@GetMapping(value = "/board/{bNo}")
	public ModelAndView content(@PathVariable("bNo") Long bNo) {
		boardService.updateViewCnt(bNo);
		ModelAndView mv = new ModelAndView("board/content");
		Optional<Board> content = boardService.getBoardContent(bNo);
		List<Comment> comments = commentService.getCommentList(bNo);
		mv.addObject("content", content.get()); // Optional에서 값을 빼오려면 get()을 써줘야 함
		mv.addObject("comments", comments);
		return mv;
	}
	
	// 게시물 수정 페이지
	@GetMapping(value = "/board/modify/{bNo}")
	public ModelAndView modify(@PathVariable("bNo") Long bNo) {
		ModelAndView mv = new ModelAndView("board/modify");
		Optional<Board> content = boardService.getBoardContent(bNo);
		mv.addObject("content", content.get());
		return mv;
	}
	
	// 게시물 수정
	@PutMapping(value = "/board/{bNo}")
	@ResponseBody
	public String modify(Board board, RedirectAttributes redirectAttributes) {
		System.out.println("board ======================> " + board);
		redirectAttributes.addAttribute("bNo", board.getBNo());
		return boardService.update(board);
	}
	
	// 게시물 삭제
	@DeleteMapping(value = "/board/{bNo}")
	public String delete(@PathVariable("bNo") Long bNo) {
		System.out.println("bNo ======================> " + bNo);
		boardService.delete(bNo);
		return "redirect:/board";
	}
	
	// 게시물 좋아요 조회
	@GetMapping(value = "/board/likes/{bNo}")
	@ResponseBody
	public String likesCheck(@PathVariable("bNo") Long bNo, Principal principal) {
		System.out.println("bNo ======================> " + bNo);
		
		
		return boardService.likesCheck(bNo, principal);
	}
	
	// 게시물 좋아요 설정
	@PostMapping(value = "/board/likes")
	@ResponseBody
	public String insertLike(Long bNo, Principal principal) {
		System.out.println("insertlike ======================> " + bNo);
		
		
		return boardService.insertLike(bNo, principal);
	}
	
	// 게시물 좋아요 해제
	@PostMapping(value = "/board/likes")
	@ResponseBody
	public String deleteLike(Long bNo, Principal principal) {
		System.out.println("deletelike ======================> " + bNo);
		
		
		return boardService.deleteLike(bNo, principal);
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
