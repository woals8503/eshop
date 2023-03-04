package market.eshop.controller;

import lombok.RequiredArgsConstructor;
import market.eshop.domain.Address;
import market.eshop.domain.Member;
import market.eshop.domain.form.LoginForm;
import market.eshop.domain.form.MemberForm;
import market.eshop.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm form,
                        BindingResult bindingResult,
                        HttpServletResponse response) {

        List<Member> loginMember = memberService.login(form);

        // 쿠키 적용
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.get(0).getId()));
        response.addCookie(idCookie);
        
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

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expireCookie(response, "memberId");
        return "redirect:/catalog";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }


}
