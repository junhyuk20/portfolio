package my.web.note.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.web.note.dao.MemberDAO;
import my.web.note.dao.MemberMapper;
import my.web.note.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	@Autowired
	private HttpSession session;
	
	public String memberInsert(MemberVO member) {
		
		int cnt = dao.memberInsert(member);
		String page = "";
		
		if(cnt == 0) {
				System.out.println("회원가입 실패");
				page = "redirect:/member/joinForm";
		}else {
				System.out.println("회원가입 성공");
				page = "redirect:/";
		}
		
		return page;
	}
	
	public String memberLogin(MemberVO member) {
		MemberVO loginCheck = dao.memberSelectOne(member.getMember_id());
		String page = "";
		
		if (loginCheck != null && loginCheck.getMember_pw().equals(member.getMember_pw())) {
			System.out.println("로그인 성공");
			session.setAttribute("loginId", loginCheck.getMember_id());
			session.setAttribute("loginName", loginCheck.getMember_nm());
			page = "redirect:/";
		}else {
			System.out.println("로그인 실패");
			page ="redirect:/member/loginForm";
		}
		
		return page;
	}
	
	public void logOut() {
		session.removeAttribute("loginId");
	}
	
	public MemberVO memberSelectOne(String member_id) {
		return dao.memberSelectOne(member_id);
	}
	

	 public String memberUpdate(MemberVO member) { 
		 int cnt = dao.memberUpdate(member); 
		 String page = "";
		 
		 if (cnt == 0) {
			 System.out.println("업데이트 실패"); 
			 page ="redirect:/"; 
		 }else {
			 System.out.println("업데이트 성공"); 
			 page = "redirect:/member/selectOne"; 
		 } 
		 return page; 
	 }
	 
}
