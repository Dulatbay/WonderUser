package kz.wonder.wonderuser.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "storekeeper")
@AllArgsConstructor
public class Storekeeper extends User {
}
