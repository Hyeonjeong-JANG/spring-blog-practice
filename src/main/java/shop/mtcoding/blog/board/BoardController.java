package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardRepository boardRepository;
    private final HttpSession session;

    //////////////////////// 메인, 게시글 목록
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList", boardList);
        return "index";
    }

    //////////////////////////// 글 저장
    @GetMapping("/board/saveForm")
    public String saveForm() {
        User user = (User) session.getAttribute("sessionUser");

        if (user == null) {
            return "redirect:/loginForm";
        }
        return "board/saveForm";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO requestDTO, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        if (requestDTO.getTitle().length() > 30) {
            request.setAttribute("msg", "제목은 30자까지만 가능합니다");
            request.setAttribute("status", 400);
            return "error/40x";
        }
        boardRepository.save(requestDTO, sessionUser.getId());
        return "redirect:/";
    }

    /////////////////////// 게시글 디테일
    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request) {
        BoardResponse.DetailDTO responseDTO = boardRepository.findById(id);
        request.setAttribute("board", responseDTO);

        User sessionUser = (User) session.getAttribute("sessionUser");
        boolean pageOwner;
        if (sessionUser == null) {
            pageOwner = false;
            System.out.println("글주인아님");
        } else {
            int sessionUserId = sessionUser.getId();
            int contentOwnerId = responseDTO.getUserId();
            pageOwner = sessionUserId == contentOwnerId;
            System.out.println("글주인임");
        }

        request.setAttribute("pageOwner", pageOwner);
        return "board/detail";
    }

    //////////////////////// 게시글 삭제
    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

        BoardResponse.DetailDTO responseDTO = boardRepository.findById(id);
        if (responseDTO.getUserId() != sessionUser.getId()) {
            request.setAttribute("msg", "게시글을 삭제할 권한이 없습니다: ");
            request.setAttribute("status", 403);
            return "error/40x";
        }

        boardRepository.delete(id);

        return "redirect:/";
    }
}
