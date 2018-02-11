/**
 * 
 */
package aug.manas.accountservice.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aug.manas.accountservice.AccountServiceApplication;
import aug.manas.accountservice.model.User;

/**
 * @author shweta
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AccountServiceApplication.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;

	@Test
	public void shoulFindUserByEmail() {

		User user = new User("Firstname","Lastname","Username", "password","username@email.com");
		repository.save(user);

		User found = repository.findByEmail(user.getEmail());
		assertEquals(user.getFirstName(), found.getFirstName());
		assertEquals(user.getLastName(), found.getLastName());
		assertEquals(user.getUsername(), found.getUsername());
		assertEquals(user.getPassword(), found.getPassword());
	}

}
