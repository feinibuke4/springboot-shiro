package com.service;

import com.doain.UserDomain;

public interface UserService {

	public UserDomain findName(String name);
	public UserDomain findById(Integer id);
}
