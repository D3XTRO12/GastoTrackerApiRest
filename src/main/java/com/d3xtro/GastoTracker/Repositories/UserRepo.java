package com.d3xtro.GastoTracker.Repositories;

import com.d3xtro.GastoTracker.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    <Optional> User findByName(String name);
}
