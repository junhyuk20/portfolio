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
	
	public int commentInsert(CommentVO cmt) {
		int cnt = dao.commentInsert(cmt);
		
		return cnt;
		
	}
	
	public ArrayList<CommentVO> commentList(int board_no){
		ArrayList<CommentVO> list = dao.commentList(board_no);
		
		return list;
	}

	public int commentDelete(CommentVO cmt) {
		int cnt = dao.commentDelete(cmt);
		
		return cnt;
	}
	public int commentUpdate(CommentVO cmt) {
		int cnt = dao.commentUpdate(cmt);
		
		return cnt;
	}
}
