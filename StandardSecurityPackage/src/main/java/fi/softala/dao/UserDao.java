package fi.softala.dao;

import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import fi.softala.bean.User;

@Component
public class UserDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void saveUser(User user) {
		String sql1 = "INSERT INTO users(username, password) VALUES (?, ?)";
	    String sql2 = "INSERT INTO user_roles(username, role) VALUES (?, ?)";
		Object[] parameters1 = new Object[] { user.getUsername(), user.getPassword() };
		Object[] parameters2 = new Object[] { user.getUsername(), user.getRole() }; 
		getJdbcTemplate().update(sql1, parameters1);
		getJdbcTemplate().update(sql2, parameters2);
	}
}
