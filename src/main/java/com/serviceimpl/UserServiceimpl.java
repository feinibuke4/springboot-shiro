package com.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.doain.UserDomain;
import com.service.UserService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserServiceimpl implements UserService {
	
	@Autowired
	private UserDao dao;

	@Override
	public UserDomain findName(String name) {
		// TODO Auto-generated method stub
		Example example=new Example(UserDomain.class);
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("name", name);
		return this.dao.selectOneByExample(example);
	}

	@Override
	public UserDomain findById(Integer id) {
		// TODO Auto-generated method stub
		return this.dao.selectByPrimaryKey(id);
	}

}
