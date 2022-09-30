package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import connection.JPAUtil;
import entitys.UserRegister;

public class DaoGeneric <E> {
					
	private EntityManager entityManager = JPAUtil.getEntityManager();
	
	public void save(E entity) {
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entity);
		transaction.commit();
		entityManager.close();
		
	}
	
	public void delete(E entity) {
		
		Object id = JPAUtil.getPrimaryKey(entity);
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.createNativeQuery("delete from " + entity.getClass().getSimpleName().toLowerCase() 
																 + " where id =" + id).executeUpdate();
		transaction.commit();
		
	}
	
	public E update(E entity) {
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		E ret = entityManager.merge(entity);
		
		transaction.commit();
		
		return ret;
		
	}
	
	public List<E> listUser(Class<E> entity){
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		List<E> retorno = entityManager.createQuery("from " + entity.getName()).getResultList();
		
		transaction.commit();
		entityManager.close();
		
		return retorno;
	}

	
}