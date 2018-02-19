/**
 * 
 */
package aug.manas.accountservice.repository;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aug.manas.accountservice.model.TestAccountTransaction;

/**
 * @author shweta
 *
 */
@Profile("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestUserTransactionRepositoryTest {
	
	private static final Logger logger = LoggerFactory.getLogger(TestUserTransactionRepositoryTest.class);

	@Autowired
	TestUserTransactionRepository testUserTransactionRepository;
	
	@BeforeClass
	public static void setup(){
		
	}
	
	@Test
	public void should_return_listof_transactions_bbyUserID(){
		logger.debug("should_return_listof_transactions_bbyUserID");
		
		List<TestAccountTransaction> testAccountTransaction = testUserTransactionRepository.findByUserId(1L);
	}
}
