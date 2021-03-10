package my.web.note.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.web.note.vo.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSession session;
	
	public int memberInsert(MemberVO member) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		int cnt = 0;
		
		try {
			cnt = mapper.memberInsert(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	public MemberVO memberSelectOne(String member_id) {
		MemberVO list = null;
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		try {
			list = mapper.memberSelectOne(member_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public int memberUpdate(MemberVO member) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		int cnt = 0;
		
		try {
			cnt = mapper.memberUpdate(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
}
