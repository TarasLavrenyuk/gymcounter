package com.lavreniuk.gymcounter.repository;

import com.lavreniuk.gymcounter.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author taras
 * @date 15.04.18.
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE username = :username LIMIT 1;",
            nativeQuery = true)
    User getByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM users WHERE user_id = :userId ;",
            nativeQuery = true)
    User getOneById(@Param("userId") Long id);

    @Query(value = "SELECT * FROM users WHERE username = :username OR email = :email ;",
            nativeQuery = true)
    List<User> getByUsernameOrEmail(@Param("username") String username,
                                    @Param("email") String email);

    @Query(value = "INSERT INTO users (username, password, email, user_role, phone) VALUES ( :username , :password , :email , 'ROLE_USER', :phone ) RETURNING user_id;",
            nativeQuery = true)
    Long createUser(@Param("username") String username,
                    @Param("password") String password,
                    @Param("email") String email,
                    @Param("phone") String phone);
}
