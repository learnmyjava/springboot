package springBoot.service;

import org.springframework.stereotype.Service;

import springBoot.entity.User;

@Service
public interface IuserInfoService {

	public User getUser (User u);
}
