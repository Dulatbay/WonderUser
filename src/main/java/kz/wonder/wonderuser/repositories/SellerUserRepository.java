package kz.wonder.wonderuser.repositories;

import kz.wonder.wonderuser.entities.SellerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerUserRepository extends JpaRepository<SellerUser, Long> {

}
