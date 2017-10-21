package com.pm.onlinetest.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pm.onlinetest.domain.PasswordResetToken;
import com.pm.onlinetest.domain.User;

@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Integer> {
	PasswordResetToken findByToken(String token);
	
	@Query("SELECT pt.user FROM PasswordResetToken pt WHERE pt.token=:token)")
	User findUserByToken(@Param("token") String token);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE PasswordResetToken pt SET pt.expiryDate=:expiryDate WHERE pt.token=:token)")
	void updateTokenExpiredTime(@Param("token") String token, @Param("expiryDate") LocalDateTime expiryDate);	

}
