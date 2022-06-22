package com.bates.diningreview.repositories;

import com.bates.diningreview.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String name);
}
