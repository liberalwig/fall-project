package Controller;

import Service.UserService;
import Vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService service;

    // 로그인 페이지
//    @RequestMapping("")
//    public String userLoginPage() {
//        return "";
//    }

    // 로그인
    @RequestMapping("/login")
    public ModelAndView userLogin(@ModelAttribute UserVo vo, HttpSession session) throws Exception {
        boolean result = service.userLogin(vo, session);
        ModelAndView mav = new ModelAndView();
        if (result == true) {
            mav.setViewName(""); // 로그인 했을 시 이동할 곳
        } else {
            mav.setViewName("html/index"); // 로그인 세션이 없을 때 로그인 페이지로
        }
        return mav;
    }

    // 회원가입 페이지
//    @RequestMapping("")
//    public String userJoinPage() {
//        return "";
//    }

    // 회원가입
    @RequestMapping("/join")
    public String userJoin(@ModelAttribute UserVo vo) throws Exception {
        service.userJoin(vo);
        return "redirect:";
    }
}