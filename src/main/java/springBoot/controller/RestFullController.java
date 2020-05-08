package springBoot.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
 * rest风格接口
 */
@Slf4j
@RestController
@RequestMapping("/loginusers")
public class RestFullController {

	
	@Autowired
	IuserInfoService userImpl;
	
	@ApiOperation(value="获取用户列表",notes="当前用")
//	@RequestMapping(method=RequestMethod.GET)
	@GetMapping
	public List<User> getUsers(){
		List<User> users = userImpl.getUsers(null);
		log.info("获取用户成功");
		return users;
	}
	
	@ApiOperation(value="新增用户",notes="根据user对象创建用户")
	//@RequestMapping(value="",method=RequestMethod.POST)
	@PostMapping//新建 更新
	public String addUser(){
		log.info("新增用户成功");
		return "success";
	}
	
	@PutMapping
	public String updateUser(){
		log.info("更新用户成功");
		return "success";
	}
	
	@DeleteMapping
	public String deleteUser(){
		log.info("删除用户成功");
		return "success";
	}
	
	@PatchMapping//put 的补充，对已知资源的部分更新
	public String PatchUser(){
		log.info("部分更新用户成功");
		return "success";
	}
}
