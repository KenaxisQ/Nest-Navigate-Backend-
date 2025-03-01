package com.arista.nestnavigator.authorization.repository;

import com.arista.nestnavigator.authorization.Entity.Token;
import com.arista.nestnavigator.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {


    @Query("""
select t from Token t inner join User u on t.user.id = u.id
where t.user.id = :userId and t.loggedOut = false
""")
    List<Token> findAllAccessTokensByUser(String userId);

    Optional<Token> findByAccessToken(String token);

    Optional<Token > findByRefreshToken(String token);

    void deleteAllByUser(User user);
}
