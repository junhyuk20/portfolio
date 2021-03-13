package my.web.note;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import my.web.note.service.CommentService;
import my.web.note.vo.CommentVO;

@Controller
@RequestMapping(value="/comment")
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CommentService cs;
	
	//댓글 입력 
	@ResponseBody
	@RequestMapping(value="/commentWrite", method=RequestMethod.POST)
	public String commentWrite(int board_no, String comment_contents) {
		
		String member_id = (String)session.getAttribute("loginId");
		String member_nm = (String)session.getAttribute("loginName");
		CommentVO cmt = new CommentVO();
		
		cmt.setMember_id(member_id);
		cmt.setMember_nm(member_nm);
		cmt.setBoard_no(board_no);
		cmt.setComment_contents(comment_contents);
		
		int cnt = cs.commentInsert(cmt);
		if (cnt == 0) {
			System.out.println("댓글 등록 실패");
		}else {
			System.out.println("댓글 등록 성공");
		}
			
		return "redirect:/board/boardRead";
		
	}
	
	// 댓글 삭제
	@RequestMapping(value="/commentDelete", method=RequestMethod.GET)
	public String commentDelete(CommentVO cmt) {
		String member_id = (String)session.getAttribute("loginId");
		cmt.setMember_id(member_id);
		
		logger.info("페이지로부터 받은 board_no:{}"+cmt.getBoard_no());
		logger.info("페이지로부터 받은 comment_no:{}"+cmt.getComment_no());
		
		int cnt = cs.commentDelete(cmt);
		
		if (cnt == 0) {
			System.out.println("댓글 삭제 실패");
		} else {
			System.out.println("댓글 삭제 성공!");
		}
		return "redirect:/board/boardRead?board_no="+ cmt.getBoard_no();
	}
	//댓글 수정
	@RequestMapping(value="/commentUpdate", method=RequestMethod.POST)
	public String commentUpdate(CommentVO cmt) {
		String member_id = (String)session.getAttribute("loginId");
		cmt.setMember_id(member_id);
		
		logger.info("페이지로부터 넘어온 댓글번호:{}", cmt.getComment_no());
		logger.info("페이지로부터 넘어온 게시글 번호:{}", cmt.getBoard_no());
		logger.info("페이지로부터 넘어온 댓글 내용:{}", cmt.getComment_contents());
		
		int cnt = cs.commentUpdate(cmt);
		
		if (cnt == 0) {
				System.out.println("댓글 수정 실패");
		}else {
				System.out.println("댓글 수정 성공!");
		}
		
		
		return "redirect:/board/boardRead?board_no="+cmt.getBoard_no();
		
	}
}
