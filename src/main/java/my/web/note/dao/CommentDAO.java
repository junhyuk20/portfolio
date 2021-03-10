package my.web.note.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.web.note.vo.CommentVO;

@Repository
public class CommentDAO {

	@Autowired
	SqlSession session;
	
	public int commentInsert(CommentVO cmt) {
		CommentMapper mapper = session.getMapper(CommentMapper.class);
		int cnt = 0;
		
		try {
			cnt = mapper.commentInsert(cmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public ArrayList<CommentVO> commentList(int board_no){
		CommentMapper mapper = session.getMapper(CommentMapper.class);
		ArrayList<CommentVO> list = null ;
	
		try {
			list = mapper.commentList(board_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int commentDelete(CommentVO cmt) {
		CommentMapper mapper = session.getMapper(CommentMapper.class);
		int cnt = 0;
		
		try {
			cnt = mapper.commentDelete(cmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
}
