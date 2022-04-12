package dao;

import dto.User;

public interface UserDao {
	
	int idCheck(String id);
	int insert(User user);
	int update(User user);
	int delete(String id, String pw);
	User select(String id);
	User login(String id, String pw);
}
