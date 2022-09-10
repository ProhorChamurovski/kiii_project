package org.daypilot.demo.html5eventcalendarspring.repository;


import org.daypilot.demo.html5eventcalendarspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

}
