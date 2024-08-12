package Alom.login.repository.user;
import Alom.login.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.userUuid = :userUuid")
    Optional<User> findByUser(UUID userUuid);
    User findByProviderId(String providerId);

}
