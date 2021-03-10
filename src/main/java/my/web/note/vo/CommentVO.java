package my.web.note.vo;

import lombok.Data;

@Data
public class CommentVO {

	private int comment_no;
	private int board_no;
	private String member_nm;
	private String member_id;
	private String comment_contents;
	private String comment_indate;
	private int comment_parent;
	
}
