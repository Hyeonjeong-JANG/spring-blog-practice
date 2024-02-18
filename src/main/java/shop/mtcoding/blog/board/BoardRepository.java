package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAll() {
        String q = "select * from board_tb order by id desc";
        Query query = em.createNativeQuery(q, Board.class);
        return query.getResultList();
    }

    public BoardResponse.DetailDTO findById(int idx) {
        String q = """
                select b.id, b.title, b.content, b.user_id, u.username 
                from board_tb b
                inner join user_tb u
                on b.user_id = u.id
                where b.id=?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, idx);

        Object[] row = (Object[]) query.getSingleResult();
        Integer id = (Integer) row[0];
        String title = (String) row[1];
        String content = (String) row[2];
        Integer userId = (Integer) row[3];
        String username = (String) row[4];

        BoardResponse.DetailDTO responseDTO = new BoardResponse.DetailDTO();
        responseDTO.setId(id);
        responseDTO.setTitle(title);
        responseDTO.setContent(content);
        responseDTO.setUserId(userId);
        responseDTO.setUsername(username);

        return responseDTO;
    }

    @Transactional
    public void save(BoardRequest.SaveDTO requestDTO, int userId) {
        String q = "insert into board_tb(title, content, user_id, created_at) values(?,?,?,now())";
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getContent());
        query.setParameter(3, userId);

        query.executeUpdate();
    }
}
