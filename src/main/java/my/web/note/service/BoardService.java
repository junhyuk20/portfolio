package my.web.note.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.web.note.dao.BoardDAO;
import my.web.note.dao.BoardMapper;
import my.web.note.vo.BoardVO;
import my.web.note.vo.CommentVO;

@Service
public class BoardService {
	@Autowired
	private BoardDAO dao;
	@Autowired
	private HttpSession session;
	
	public String boardInsert(BoardVO board) {
		String member_id = (String)session.getAttribute("loginId");
		board.setMember_id(member_id);
		
		int cnt = dao.boardInsert(board);
		String page = "";
		
		if (cnt == 0) {
			System.out.println("게시글 등록 실패");
			page = "redirect:/board/writeForm";
		}else {
			System.out.println("게시글 등록 성공!");
			page = "redirect:/board/boardList";
		}
		return page;
	}
	public ArrayList<HashMap<String, Object>> boardList(String searchType, String searchText,
		int startRecord, int countPerPage){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("searchType", searchType);
		map.put("searchText", searchText);
		ArrayList<HashMap<String, Object>> list = dao.boardList(map,startRecord,countPerPage);
		
		return list;
				
	}
	
	public  ArrayList<HashMap<String, Object>> selectAll() {
		return dao.selectAll();
	}
	
	public HashMap<String, Object> boardRead(int board_no){
		dao.updateHits(board_no);
		return dao.boardRead(board_no);
	}
	public int boardDelete(BoardVO board) {
		String member_id = (String)session.getAttribute("loginId");
		board.setMember_id(member_id);
		return dao.boardDelete(board);
	}
	public HashMap<String, Object> selectOne(BoardVO board) {
		String member_id = (String)session.getAttribute("loginId");
		board.setMember_id(member_id);
		return dao.selectOne(board);
	}
	public String boardUpdate(BoardVO board) {	
		String member_id = (String)session.getAttribute("loginId");
		board.setMember_id(member_id);
		
		int cnt = dao.boardUpdate(board);
		String page = "";
		
		if(cnt == 0) {
			System.out.println("수정 실패");
			page = "redirect:/board/updateForm";
		}else {
			System.out.println("수정 성공");
			page = "redirect:/board/boardList";
		}
		return page;
	}
	
	public BoardVO boardOne(int board_no) {
		return dao.boardOne(board_no);
	}
	public int boardCount(String searchType, String searchText) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("searchType", searchType);
		map.put("searchText", searchText);
		
		int count = dao.boardCount(map);
		
		return count;
	}
	
	

}
