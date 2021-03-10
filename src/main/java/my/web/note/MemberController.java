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
	
	@RequestMapping(value="/joinForm", method=RequestMethod.GET)
	public String joinForm() {
		return "member/joinForm";
	}
	
	@RequestMapping(value="/loginForm", method=RequestMethod.GET)
	public String loginForm() {
		
		return "member/loginForm";
	}
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(MemberVO member) {
		
		return ms.memberInsert(member);
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(MemberVO member) {
	
		return ms.memberLogin(member);
	}
	@RequestMapping(value="/logOut", method=RequestMethod.GET)
	public String logOut() {
		
		ms.logOut();
		return "redirect:/";
	}
	@RequestMapping(value="/memberInfo", method=RequestMethod.GET)
	public String memberUpdate(String member_id, Model model) {
		model.addAttribute("info", ms.memberSelectOne(member_id));
		return "member/memberInfo";
	}
	@RequestMapping(value="/updateForm", method=RequestMethod.GET)
	public String updateForm(String member_id, Model model) {
		model.addAttribute("info", ms.memberSelectOne(member_id));
		return "member/updateForm";
	}
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String memberUpdate(MemberVO member) {
		return ms.memberUpdate(member);
	}
	// 개인정보 변경후 보여줄 페이지
	@RequestMapping(value="/selectOne", method=RequestMethod.GET)
	public String selectOne(Model model) {
		String member_id = (String)session.getAttribute("loginId");
		model.addAttribute("info", ms.memberSelectOne(member_id));
		return "member/selectOne";
	}
}
