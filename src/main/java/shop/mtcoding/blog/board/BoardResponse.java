package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardResponse {
    @Data
    public static class DetailDTO {
        private int id; // 보드아이디
        private String title;
        private String content;
        private int userId; // 유저아이디
        private String username;
    }
}
