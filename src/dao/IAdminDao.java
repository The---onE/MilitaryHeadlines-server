package dao;

import entity.Admin;

public interface IAdminDao {
	// 用户名密码登录
	Admin login(String username, String password);
	
	// 用户注册
	Admin register(String username, String password, String nickname);
}
