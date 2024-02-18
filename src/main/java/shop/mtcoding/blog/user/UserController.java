package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserRepository userRepository;
    private final HttpSession session;

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO requestDTO, HttpServletRequest request) {
        if (requestDTO.getUsername().length() < 3) {
            request.setAttribute("msg", "잘못된 요청을 하셨습니다: ");
            request.setAttribute("status", 400);
            return "error/40x";
        }

        User user = userRepository.findByUsernameAndPassword(requestDTO);
        if (user == null) {
            request.setAttribute("msg", "가입된 유저가 아닙니다: ");
            request.setAttribute("status", 401);
            return "error/40x";
        } else {
            session.setAttribute("sessionUser", user);
        }

        return "redirect:/";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO, HttpServletRequest request) {
        if (requestDTO.getUsername().length() < 3) {
            request.setAttribute("msg", "잘못된 요청을 하셨습니다: ");
            request.setAttribute("status", 400);
            return "error/40x";
        }
        userRepository.save(requestDTO);
        return "redirect:/loginForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
