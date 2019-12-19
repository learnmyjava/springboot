package springBoot.mapper;

import java.util.List;

import springBoot.entity.User;

public interface UserInfoMapperDao {

	
	public User getUser(User u) ;
	
	public List<User>  getUsers(User u);
}
