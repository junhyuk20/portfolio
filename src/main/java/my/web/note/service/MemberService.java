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
	// 회원가입 서비스 기능입니다.
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
	//로그인 처리를 해주는 서비스 입니다.
	public String memberLogin(MemberVO member) {
		MemberVO loginCheck = dao.memberSelectOne(member.getMember_id());
		String page = "";
		
		// 로그인 시 현재 입력한 정보로 db에 존재여부와 db에의비밀번호와 현재 입력한 비밀번호가 같은지를 비교한 결과로 login처리를 하는 기능입니다.
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
	// 로그아웃 처리를 하는 서비스 입니다.
	public void logOut() {
		session.removeAttribute("loginId");
	}
	// 한사람의 정보를 가져오는 서비스 입니다.
	public MemberVO memberSelectOne(String member_id) {
		return dao.memberSelectOne(member_id);
	}
	
	//회원 정보를 수정하는 서비스 입니다.
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
	 // 아이디 중복확인 기능 서비스 입니다.
	public boolean idCheck(String member_id) {
		boolean data;
		String idCheck = dao.idCheck(member_id);
		
		if(member_id.equals(idCheck) || member_id.length() == 0) {
			System.out.println("사용할 수 없는 아이디 입니다.");
			data = false;
			
		} else {
			System.out.println("사용가능한 아이디 입니다.");
			data = true;
		}
		return data;
	}
	 
}
