/**
 * 
 */
package aug.manas.accountservice.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aug.manas.accountservice.model.ExpTransaction;
import aug.manas.accountservice.model.User;
import aug.manas.accountservice.repository.UserRepository;
import aug.manas.accountservice.service.UserService;

/**
 * @author shweta
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);

	}

	@Override
	public void updateUser(User user) {
		saveUser(user);

	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.delete(id);

	}

	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public boolean isUserExist(User user) {
		return findByUsername(user.getUsername()) != null;
	}

	/* (non-Javadoc)
	 * @see aug.manas.accountservice.service.UserService#getAllTransactions(long)
	 */
	@Override
	public List<ExpTransaction> getAllTransactions(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see aug.manas.accountservice.service.UserService#addExpTransaction(long, aug.manas.accountservice.model.ExpTransaction)
	 */
	@Override
	public void addExpTransaction(long id, ExpTransaction t) {
		// TODO Auto-generated method stub
		
	}

}
