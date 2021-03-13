package my.web.note.dao;

import java.awt.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import my.web.note.vo.CommentVO;

public interface CommentMapper {
	// 댓글 입력 기능 입니다.
	public int commentInsert(CommentVO cmt);
	// 댓글 리스트 입니다.
	public ArrayList<CommentVO> commentList(int board_no);
	// 댓글 삭제 기능 입니다.
	public int commentDelete(CommentVO cmt);
	// 댓글 수정 기능 입니다.
	public int commentUpdate(CommentVO cmt);
	
}
