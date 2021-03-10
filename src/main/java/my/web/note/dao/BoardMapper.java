package my.web.note.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;

import my.web.note.vo.BoardVO;
import my.web.note.vo.CommentVO;

public interface BoardMapper {

		public int boardInsert(BoardVO board);
		
		public ArrayList<HashMap<String, Object>> boardList(HashMap<String, Object> map, RowBounds rb);
		
		public ArrayList<HashMap<String, Object>> selectAll();
		
		public HashMap<String, Object> boardRead(int board_no);
		
		public void updateHits(int board_no);
		
		public int boardDelete(BoardVO board);
		
		public HashMap<String, Object> selectOne(BoardVO board);
		
		public int boardUpdate(BoardVO board);
		
		public BoardVO boardOne(int board_no);
		
		public int boardCount(HashMap<String, Object> map);
}
