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
	
	//게시글 등록 서비스 입니다.
	public String boardInsert(BoardVO board) {
		String member_id = (String)session.getAttribute("loginId");
		board.setMember_id(member_id);
		
		int cnt = dao.boardInsert(board);
		String page = "";
	//게시글이 잘 등록이 되었는지 확인하는 구간 및 그에 맞는 페이지를 반환해주는 기능입니다.
		if (cnt == 0) {
			System.out.println("게시글 등록 실패");
			page = "redirect:/board/writeForm";
		}else {
			System.out.println("게시글 등록 성공!");
			page = "redirect:/board/boardList";
		}
		return page;
	}
	//검색 기능을 사용하기 위해서 vo클래스로 등로 되지않은 변수들을 hash map을 이용해서 값을 넣어주는 서비스 입니다.
	public ArrayList<HashMap<String, Object>> boardList(String searchType, String searchText,
		int startRecord, int countPerPage){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("searchType", searchType);
		map.put("searchText", searchText);
		ArrayList<HashMap<String, Object>> list = dao.boardList(map,startRecord,countPerPage);
		
		return list;
				
	}
	//b_table에 member_nm의 컬럼이 없으므로 hashmap을 통해서 가져옵니다.
	public  ArrayList<HashMap<String, Object>> selectAll() {
		return dao.selectAll();
	}
	
	public HashMap<String, Object> boardRead(int board_no){
		dao.updateHits(board_no);
		return dao.boardRead(board_no);
	}
	//게시물을 삭제하는 서비스 입니다.
	public int boardDelete(BoardVO board) {
		//삭제 주소창을 복사해서 login하지 않고 악의적으로 삭제할 수 있는 사고를 방지 하기위해 member_id를 추가해 주었습니다. 
		String member_id = (String)session.getAttribute("loginId");
		board.setMember_id(member_id);
		return dao.boardDelete(board);
	}
	
	public HashMap<String, Object> selectOne(BoardVO board) {
		String member_id = (String)session.getAttribute("loginId");
		board.setMember_id(member_id);
		return dao.selectOne(board);
	}
	//게시물을 수정하는 서비스 입니다.
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
