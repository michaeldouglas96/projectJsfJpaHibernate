package repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import connection.JPAUtil;
import entitys.UserRegister;

public class IDaoImpl implements IDaoUser {

	@Override
	public UserRegister consultUser(String userName, String senha) {
		
		UserRegister userRegister = null;
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		userRegister = (UserRegister) entityManager
		.createNativeQuery("select u from UserRegister u where u.username = '" + userName + "' and u.senha = '" + senha + "'").getSingleResult();
		
		transaction.commit();
		entityManager.close();
		
		return userRegister;
	}

}
