/**
 * Code developed by Ashfaq (© [Year])
 * GitHub: https://github.com/DarkSharkAsh
 */

package com.ashfaq.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashfaq.dev.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
