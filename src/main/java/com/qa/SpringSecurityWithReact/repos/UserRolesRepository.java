package com.qa.SpringSecurityWithReact.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.SpringSecurityWithReact.entities.UserRole;
@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Long> {
	@Query("select a.role from UserRole a, User b where b.username=?1 and a.userid=b.userId")
	public List<String> findRoleByUserName(String username);
}