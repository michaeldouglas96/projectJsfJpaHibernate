package connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory factory = null;

	static {
		init();
	}

	public static void init() {

		try {

			if (factory == null) {
				factory = Persistence.createEntityManagerFactory("project");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Return connection to system
	public static EntityManager getEntityManager() {

		return factory.createEntityManager();
	}

	public static Object getPrimaryKey(Object entity) {

		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}

}
