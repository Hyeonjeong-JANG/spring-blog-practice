package shop.mtcoding.blog.board;

import jakarta.persistence.Column;
import lombok.Data;

public class BoardRequest {
    @Data
    public static class SaveDTO {
        @Column(length = 30)
        private String title;
        private String content;
    }
}
