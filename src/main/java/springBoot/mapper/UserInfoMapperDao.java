package springBoot.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import springBoot.entity.User;
@Repository
public interface UserInfoMapperDao {

	
	public User getUser(User u) ;
	
	public List<User>  getUsers(User u);
}
