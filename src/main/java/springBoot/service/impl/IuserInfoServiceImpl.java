package springBoot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBoot.entity.User;
import springBoot.mapper.UserInfoMapperDao;
import springBoot.service.IuserInfoService;
@Service
public class IuserInfoServiceImpl implements IuserInfoService{

	@Autowired
	UserInfoMapperDao userMapper;

	@Override
	public User getUser(User u) {
		return userMapper.getUser(u);
		
		
	}

	@Override
	public List<User> getUsers(User u) {
		return userMapper.getUsers(u);
	}

	@Override
	public int saveUser(User u) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
