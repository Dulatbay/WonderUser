package kz.wonder.wonderuser.repository;

import kz.wonder.wonderuser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByKeycloakUserId(String keycloakUserId);
}
