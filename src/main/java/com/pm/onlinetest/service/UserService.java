package com.pm.onlinetest.service;

import java.util.List;

import com.pm.onlinetest.domain.User;

 
public interface UserService {

	public void save(User user);
	public void saveProfile(User user);
	public void delete(User user);
	public void softDelete(Integer userId);
	public List<User> findAllEnabled();
	public User findByUsername(String username);
	public User findByUsernameExceptThis(String username, Integer userId);
	public User findByUserId(Integer userId);
	public List<User> findByAuthority(String authority);
	public boolean emailExists(String email);
	public int findByUseremail(String email);
	public void setAccessCodeInPasswordField(String email, String accessCode);
	public User findUserByAccessCode(String accessCode);
 }
