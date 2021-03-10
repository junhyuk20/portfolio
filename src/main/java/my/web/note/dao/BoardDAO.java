package my.web.note.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.web.note.vo.BoardVO;
import my.web.note.vo.CommentVO;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSession session;
	
	public int boardInsert(BoardVO board) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		int cnt = 0;
		
		try {
			cnt = mapper.boardInsert(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	public ArrayList<HashMap<String, Object>> boardList(HashMap<String, Object> map,
			int startRecord, int countPerPage){
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		ArrayList<HashMap<String, Object>> boardList = null;
		
		//페이징 처리에 사용할 객체
		RowBounds rb = new RowBounds(startRecord, countPerPage);
		try {
			boardList = mapper.boardList(map,rb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}
	public  ArrayList<HashMap<String, Object>> selectAll() {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		 ArrayList<HashMap<String, Object>> list = null;
		
		try {
			list = mapper.selectAll();
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return list;
	}
	public HashMap<String, Object> boardRead(int board_no){
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		HashMap<String, Object> read = null;
		
		try {
			 read = mapper.boardRead(board_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return read;
	}
	
	public void updateHits(int board_no) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		
		try {
			mapper.updateHits(board_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int boardDelete(BoardVO board) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		int cnt = 0;
		
		try {
			cnt = mapper.boardDelete(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	public HashMap<String, Object> selectOne(BoardVO board){
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		HashMap<String, Object> list = null;
		
		try {
			list = mapper.selectOne(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int boardUpdate(BoardVO board) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		int cnt = 0;
		
		try {
			cnt = mapper.boardUpdate(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public BoardVO boardOne(int board_no) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		BoardVO board = null;
		
		try {
			board = mapper.boardOne(board_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	public int boardCount(HashMap<String, Object> map) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		int cnt  = 0;
		
		try {
			cnt = mapper.boardCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	
}
