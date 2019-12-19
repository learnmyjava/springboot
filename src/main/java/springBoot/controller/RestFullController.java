package springBoot.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springBoot.entity.User;
import springBoot.service.IuserInfoService;

/**
 * @author li_hhui
 * @date:2019年12月13日
 * @version:
 */
@RestController
@RequestMapping("/users")
public class RestFullController {

	
	@Autowired
	IuserInfoService userImpl;
	
	@ApiOperation(value="获取用户列表",notes="当前用")
	@RequestMapping(method=RequestMethod.GET)
	public List<User> getUsers(){
		List<User> users = userImpl.getUsers(null);
		return users;
	}
	
/*	@ApiOperation(value="新增用户",notes="根据user对象创建用户")
	@RequestMapping(value="",method=RequestMethod.POST)
	public String addUser(@RequestBody  User u){
		System.out.println("保存用户成功");
		return "success";
	}*/
}
