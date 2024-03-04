package kz.wonder.wonderuser.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_tg", schema = "schema_wonder")
public class UserTg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tg_username", nullable = false, unique = true)
    private String tgUsername;

    @Column(name = "tg_chat_id", nullable = false, unique = true)
    private String tgChatId;
}
