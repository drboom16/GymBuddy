package com.gymbuddy.repository;

import com.gymbuddy.model.User;
import com.gymbuddy.model.UserGymBuddy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGymBuddyRepository extends JpaRepository<UserGymBuddy, Long> {
    List<UserGymBuddy> findByUser(User user);
    Optional<UserGymBuddy> findByUserAndGymBuddyId(User user, Long gymBuddyId);
    Optional<UserGymBuddy> findByUserAndIsActiveTrue(User user);
}
