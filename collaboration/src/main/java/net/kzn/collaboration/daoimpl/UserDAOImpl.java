package net.kzn.collaboration.daoimpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.collaboration.dao.UserDAO;
import net.kzn.collaboration.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	/* for registering a new user */
	@Override
	public void add(User user) {
		
		// persist the transient object
		sessionFactory.getCurrentSession().persist(user);

	}

	@Override
	public User getByParam(String param, String value) {
		
		String query = "FROM User WHERE " + param + "= :param";
		
		try {	
			return sessionFactory.getCurrentSession()
						.createQuery(query,User.class)
						.setParameter("param", value)
						.getSingleResult();
									
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		
	}

}
