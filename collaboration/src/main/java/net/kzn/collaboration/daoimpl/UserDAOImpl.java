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
	public boolean register(User user) {
		
		try {
			
			if(user == null) {
				throw new NullPointerException("user object is null!");
			}
			
			sessionFactory.getCurrentSession().persist(user);
			
			return true;
		}
		catch(NullPointerException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
		catch(Exception ex) {
			return false;
		}
	
	
	}

	@Override
	public boolean validate(String username, String password) {
		
		return false;
	}

}
