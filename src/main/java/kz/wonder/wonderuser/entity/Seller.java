package kz.wonder.wonderuser.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "sellers")
@AllArgsConstructor
@NoArgsConstructor
public class Seller extends User {

    private String apiToken;

    private String telegramChatId;

}


