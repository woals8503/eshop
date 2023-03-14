package market.eshop.controller;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.embadded.Address;
import market.eshop.domain.Member;
import market.eshop.domain.form.LoginForm;
import market.eshop.domain.form.MemberForm;
import market.eshop.service.MemberService;
import market.eshop.web.session.SessionConst;
import market.eshop.web.session.SessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final SessionManager sessionManager;


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

      /** 로그인 1 */
//    @PostMapping("/login")
//    public String login(@Validated @ModelAttribute LoginForm form,
//                        BindingResult bindingResult,
//                        HttpServletResponse response) {
//
//        List<Member> loginMember = memberService.login(form);
//
//        //session 관리자로 세션 생성 후 회원 데이터 보관
//        sessionManager.createSession(loginMember, response);
//
//        return "redirect:/catalog";
//    }


    /** 로그인 2 HttpSession방법 */
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm form,
                        BindingResult bindingResult,
                        HttpServletRequest request) {

        List<Member> loginMember = memberService.login(form);
        
        //세션이 있으면 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        // 기본은 true
        // false는 세션이 없다면 null을 반환한다.

        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/catalog";
    }

    @PostMapping("/regist")
    public String regist(@Valid MemberForm form, BindingResult result) {
        if(result.hasErrors()) {
            System.out.println(result);
            return "login";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());
        member.setUsername(form.getUsername());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/catalog";
    }

    /** 로그아웃 1 */
//    @PostMapping("/logout")
//    public String logout(HttpServletRequest request) {
//        sessionManager.expire(request);
//        return "redirect:/catalog";
//    }

    /** 로그아웃 2 HttpSession 사용 */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) session.invalidate();   // 세션 삭제

        return "redirect:/catalog";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
