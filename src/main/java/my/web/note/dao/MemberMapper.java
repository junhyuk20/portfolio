package my.web.note.dao;

import my.web.note.vo.MemberVO;

public interface MemberMapper {

	public int memberInsert(MemberVO member);
	
	public MemberVO memberSelectOne(String member_id);
	
	public int memberUpdate(MemberVO member);

	public String idCheck(String member_id);
	
	
}
