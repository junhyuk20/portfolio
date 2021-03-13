package my.web.note;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import my.web.note.service.MemberService;
import my.web.note.vo.MemberVO;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberService ms;
	
	// 회원가입 정보를 작성하러가는 컨트롤러 입니다.
	@RequestMapping(value="/joinForm", method=RequestMethod.GET)
	public String joinForm() {
		return "member/joinForm";
	}
	// 로그인 컨트롤러 입니다.
	@RequestMapping(value="/loginForm", method=RequestMethod.GET)
	public String loginForm() {
		
		return "member/loginForm";
	}
	// 입력된 회원 정보를 가지고 등록하는 컨트롤러 입니다.
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(MemberVO member) {
		
		return ms.memberInsert(member);
	}
	//로그인 컨트롤러 입니다.
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(MemberVO member) {
	
		return ms.memberLogin(member);
	}
	//로그아웃 컨트롤러 입니다.
	@RequestMapping(value="/logOut", method=RequestMethod.GET)
	public String logOut() {
		
		ms.logOut();
		return "redirect:/";
	}
	//개인정보를 보여주는 컨트롤러 입니다.
	@RequestMapping(value="/memberInfo", method=RequestMethod.GET)
	public String memberUpdate(String member_id, Model model) {
		model.addAttribute("info", ms.memberSelectOne(member_id));
		return "member/memberInfo";
	}
	//업데이트를 하기위한 컨트로러 입니다.
	@RequestMapping(value="/updateForm", method=RequestMethod.GET)
	public String updateForm(String member_id, Model model) {
		model.addAttribute("info", ms.memberSelectOne(member_id));
		return "member/updateForm";
	}
	//입력된 정보를 갖고 업데이트 하는 컨트롤러 입니다
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String memberUpdate(MemberVO member) {
		return ms.memberUpdate(member);
	}
	// 개인정보 변경후 수정된 정보 입니다.
	@RequestMapping(value="/selectOne", method=RequestMethod.GET)
	public String selectOne(Model model) {
		String member_id = (String)session.getAttribute("loginId");
		model.addAttribute("info", ms.memberSelectOne(member_id));
		return "member/selectOne";
	}
}
