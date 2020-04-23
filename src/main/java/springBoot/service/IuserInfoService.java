package springBoot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import springBoot.entity.User;

@Service
public interface IuserInfoService {

	public User getUser (User u);
	public List<User> getUsers(User u);
	
	public int saveUser(User u);
}
