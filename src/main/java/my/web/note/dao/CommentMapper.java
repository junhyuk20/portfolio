package my.web.note.dao;

import java.awt.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import my.web.note.vo.CommentVO;

public interface CommentMapper {

	public int commentInsert(CommentVO cmt);
	
	public ArrayList<CommentVO> commentList(int board_no);

	public int commentDelete(CommentVO cmt);
	
}
