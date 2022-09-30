package repository;

import entitys.UserRegister;

public interface IDaoUser {
	
	UserRegister consultUser(String userName, String senha);
	
	
	
	
	
}
