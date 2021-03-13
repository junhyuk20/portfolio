package my.web.note.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.web.note.dao.CommentDAO;
import my.web.note.dao.CommentMapper;
import my.web.note.vo.CommentVO;

@Service
public class CommentService {

	@Autowired
	CommentDAO dao;
	
	// 댓글 입력 
	public int commentInsert(CommentVO cmt) {
		int cnt = dao.commentInsert(cmt);
		
		return cnt;
		
	}
	// 댓글 리스트
	public ArrayList<CommentVO> commentList(int board_no){
		ArrayList<CommentVO> list = dao.commentList(board_no);
		
		return list;
	}
	// 댓글 삭제
	public int commentDelete(CommentVO cmt) {
		int cnt = dao.commentDelete(cmt);
		
		return cnt;
	}
	// 댓글 수정
	public int commentUpdate(CommentVO cmt) {
		int cnt = dao.commentUpdate(cmt);
		
		return cnt;
	}
}
