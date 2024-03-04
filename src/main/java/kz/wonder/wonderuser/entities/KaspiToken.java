package kz.wonder.wonderuser.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kaspi_token", schema = "schema_wonder")
public class KaspiToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token_kaspi", unique = true, nullable = false)
    private String tokenKaspi;

    @Column(name = "kaspi_token_enabled", nullable = false)
    private boolean tokenEnabled;

    @Column(name = "name_of_seller", nullable = false)
    private String nameOfSeller;
}
