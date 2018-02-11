/**
 * 
 */
package aug.manas.accountservice.service;

import java.util.List;

import aug.manas.accountservice.model.ExpTransaction;
import aug.manas.accountservice.model.User;

/**
 * @author shweta
 *
 */
public interface UserService {
	 
	User findById(Long id);

	User findByUsername(String username);
	
	 User findUserByEmail(String email);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(Long id);

	void deleteAllUsers();

	List<User> findAllUsers();

	boolean isUserExist(User user);
	
	List<ExpTransaction> getAllTransactions(long id);
	
	void addExpTransaction(long id , ExpTransaction t);
	
	//delete, update  
}
