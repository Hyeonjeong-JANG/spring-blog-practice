package shop.mtcoding.blog.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name = "user_tb")
@Data // getter,setter,toString 생성
@Entity // 유저 클래스가 엔티티임을 선언
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt;
}
