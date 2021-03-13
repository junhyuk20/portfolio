package my.web.note;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import my.web.note.service.BoardService;
import my.web.note.service.CommentService;
import my.web.note.util.FileService;
import my.web.note.util.PageNavigator;
import my.web.note.vo.BoardVO;
import my.web.note.vo.CommentVO;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//업로드경로 입니다.
	private String uploadPath = "/boardfile";
	
	//페이징 이용을 위한 변수 입니다.
		private final int COUNTPERPAGE = 10;
		private final int PAGEPERGROUP = 5;
	
	@Autowired
	private BoardService bs;
	@Autowired
	private CommentService cs;
	@Autowired
	private HttpSession session;
	
	
	//게시글 목록입니다.
	//처음 홈 페이지를 들어왔을 때 현재페이지를 1로 주었습니다.
	//검색을 하지 않을 수도 있으므로 기본값으로"" 을 주었습니다.
	@RequestMapping(value="/boardList", method=RequestMethod.GET)
	public String boardList(
			@RequestParam(value="page", defaultValue = "1") int page,
			@RequestParam(value="searchType", defaultValue = "") String searchType,
			@RequestParam(value="searchText", defaultValue = "") String searchText,
			Model model) {
		int count = bs.boardCount(searchType, searchText);

		PageNavigator navi = new PageNavigator(COUNTPERPAGE, PAGEPERGROUP, page, count);
		
		ArrayList<HashMap<String, Object>> list = bs.boardList(searchType, searchText, navi.getStartRecord(), navi.getCountPerPage()); 
		
		logger.info("list의 사이즈{}",list.size());
		
		model.addAttribute("list",list );
		model.addAttribute("navi", navi);
		return "board/boardList";
	}
	//게시글 양식 입니다.
	@RequestMapping(value="/writeForm", method=RequestMethod.GET)
	public String boardWriteForm() {
		return "board/boardWriteForm";
	}
	//게시글로 입력 입니다.
	@RequestMapping(value="/boardWrite", method=RequestMethod.POST)
	public String boardWrite(BoardVO board, MultipartFile upload) {
		
		if (!upload.isEmpty()) {
			
			//물리적인 저장 
			 String savedfile = FileService.saveFile(upload, uploadPath);
			
			 //논리적인 저장 db에 저장되는 형식 
			 board.setSavedfile(savedfile);
			 board.setOriginalfile(upload.getOriginalFilename());
		}
		return bs.boardInsert(board) ;
	}
	//게시글 보기 입니다.
	@RequestMapping(value="/boardRead", method=RequestMethod.GET)
	public String boardRead(int board_no, Model model) {
		
		model.addAttribute("read", bs.boardRead(board_no));
		model.addAttribute("list", cs.commentList(board_no));
		return "board/readForm";
	}
	//게시판에서 수정할 정보를 가져오는 컨트롤러 입니다.
		@RequestMapping(value="/updateForm", method=RequestMethod.GET)
		public String boardUpdateForm(int board_no, Model model) {
			
			model.addAttribute("list",bs.boardRead(board_no));
			return "board/updateForm";
		}
	// 수정을 진행하는 컨트롤러 입니다.
		@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
		public String boardUpdate(BoardVO board) {
			return bs.boardUpdate(board);
		}
		
	//게시글 삭제 입니다. 
	@RequestMapping(value="/boardDelete", method=RequestMethod.GET)
	public String boardDelete(BoardVO board) {
		int cnt = bs.boardDelete(board);
		
		if (cnt == 0) {
				System.out.println("삭제 실패");
		}else {
			System.out.println("삭제성공");
		}
		return "redirect:/board/boardList";
	}
	
	//첨부파일 다운로드하는 컨트롤러
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String fileDownload(int board_no, HttpServletResponse response) {
	
		BoardVO board =bs.boardOne(board_no);
		
		//원래의파일명
		String originalfile = new String(board.getOriginalfile());
		logger.info("오리지널 파일은: {}", originalfile);
		try {
		// 설정만 해놓은 상태
		// Content-Disposition : 내가 지금 응답으로 보내려는 컨텐츠가, attachment;filename : UTF-8형식으로인코딩한 첨부파일 이야
		// 그럼 브라우저가 이 코드를 받고선 내가 읽을 수 있는 파일이지만 읽지말고 다운로드 해야 겠다 라고 인식하고 다운로드 기능을 하게된다.
		// 인코딩을 하는 이유는 파일이름에 영어 이외의 국가 언어나 특수 문자가 있을 수 있으니 UTF-8 형식으로 인코딩을 하는 것 입니다.	
			response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(originalfile, "UTF-8"));
		
		// 이 인셉션은 사용자가 UTF-8의 철자를 틀렸을 때 띄우는 익셉션
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//저장된파일경로
		String fullPath = uploadPath + "/" + board.getSavedfile();
		
		//서버의파일을읽을입력스트림과클라이언트에게전달할출력스트림
		FileInputStream filein = null; // 물리적인 공간의 파일을 WebApplication으로 가져오기 위해서 
		ServletOutputStream fileout = null; //WebApplication에서 파일객체를 사용자의 브라우저로 전달하기 위해서
		
		try {
			filein = new FileInputStream(fullPath); //경로 연결
			fileout = response.getOutputStream(); // 경로 연결 
			
			//Spring의 파일관련유틸, 파일이 전달되는 코드
			//filein,fileout 의미는 inPut스트림으로부터 전달받은 파일을 복사해서 outPut스트림으로으로  복사하라는 뜻 입니다.
			FileCopyUtils.copy(filein, fileout);
			
			// 이제 다 사용 하였으니깐 close메서드를 통해서 연결을 종료 합니다.
			filein.close();
			fileout.close();
		// 경로가 맞지 않았을 때 익셉션
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
