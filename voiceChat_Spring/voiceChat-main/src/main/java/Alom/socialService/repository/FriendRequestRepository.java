package Alom.socialService.repository;

import Alom.login.domain.user.User;
import Alom.socialService.domain.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequest,Long> {
    Optional<FriendRequest> findByRequestFromUserAndRequestToUser(User fromUser,User toUser);
    List<FriendRequest> findAllByRequestToUserAndAcceptedByReceiver(User toUser,boolean acceptedByReceiver);
}
