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
	public boolean add(User user) {
		
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
	public User validate(User user) {
		// first fetch the user by username
		User temp = this.getByParam("username", user.getUsername());;
		if(temp!=null) {
			// if the user is found check for password
			if(temp.getPassword().equals(user.getPassword())) {
				return temp;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}		
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
