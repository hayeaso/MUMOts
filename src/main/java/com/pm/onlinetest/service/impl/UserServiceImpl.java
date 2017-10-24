package com.pm.onlinetest.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pm.onlinetest.domain.Authority;
import com.pm.onlinetest.domain.PasswordResetToken;
import com.pm.onlinetest.domain.User;
import com.pm.onlinetest.repository.UserRepository;
import com.pm.onlinetest.repository.AuthorityRepository;
import com.pm.onlinetest.repository.PasswordResetTokenRepository;
import com.pm.onlinetest.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	public static final String TOKEN_INVALID = "invalidToken";
	public static final String TOKEN_EXPIRED = "expired";
	public static final String TOKEN_VALID = "valid";
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void save(User user) {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		String role = user.getAuthorities().get(0).getAuthority();
		user.setAuthorities(null);
		user.setPassword(encodedPassword);		
		userRepository.save(user);
		Authority authority = new Authority();
		authority.setUserId(user.getUserId());
		authority.setAuthority(role);
		authorityRepository.save(authority);
		
	}

	
	public void saveProfile(User user) {
		
		System.out.println(user.getPassword());

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);		
		userRepository.save(user);
	}
	
	@Override
	public List<User> findAllEnabled() {
		return (List<User>) userRepository.findAllEnabled();
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User findByUserId(Integer userId) {
		return userRepository.findByUserId(userId);
	}
	@Override
	public void delete(User user) {
		userRepository.delete( user);
	}

	@Override
	public List<User> findByAuthority(String authority) {
		return userRepository.findByAuthority(authority);
	}

	@Override
	public void softDelete(Integer userId) {
		userRepository.softDelete(userId);
	}

	@Override
	public User findByUsernameExceptThis(String username, Integer userId) {
		return userRepository.findByUsernameExceptThis(username, userId);
	}

	@Override
	public boolean emailExists(String email) {
		if(userRepository.isEmailExists(email)!=null) {
			return true;
		}
		return false;
	}

	@Override
	public int findByUseremail(String email) {
		return userRepository.findByUseremailid(email);		
	}
		
	@Override
	public void setAccessCodeInPasswordField(String email, String accessCode) {
		userRepository.passwordUpdate(email, accessCode);		
	}
	
	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}


	@Override
	public void updateStatus(Integer userId, boolean enabled) {
		userRepository.updateStatus(userId, enabled);		
	}


	@Override
	public User findByEmail(String email) {
		return userRepository.findFirstByEmail(email);
	}


	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		PasswordResetToken myToken = new PasswordResetToken(token, user);
	    passwordTokenRepository.save(myToken);
	}
	
	@Override
	public String validatePasswordResetToken(Integer id, String token) {
		PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
		if ((passToken == null) || (passToken.getUser().getUserId() != id)) {
			return TOKEN_INVALID;
		}

		LocalDateTime cal = LocalDateTime.now();
		if (passToken.getExpiryDate().isBefore(cal)) {
			return TOKEN_EXPIRED;
		}

		User user = passToken.getUser();
//		Authentication auth = new UsernamePasswordAuthenticationToken(user, null,
//				Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
//		SecurityContextHolder.getContext().setAuthentication(auth);

		return TOKEN_VALID;
	}
	
	@Override
	public User getUserFromToken(String token) {
		return passwordTokenRepository.findUserByToken(token);
	}

	@Override
	public void setTokenExipredTime(String token, LocalDateTime expiryDate) {		
		passwordTokenRepository.updateTokenExpiredTime(token, expiryDate);
	}
	
}
