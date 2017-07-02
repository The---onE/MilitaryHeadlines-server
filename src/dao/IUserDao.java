package dao;

import entity.User;

public interface IUserDao {
	// 用户名密码登录
	User login(String username, String password);
	
	// 用户注册
	User register(String username, String password, String nickname);
	
	// 校验码自动登录
	User autoLogin(String username, String checksum);
	
	// 校验码校验登录
	User checkLogin(String username, String checksum);
}
