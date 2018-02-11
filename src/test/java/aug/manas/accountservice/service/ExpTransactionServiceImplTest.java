/**
 * 
 */
package aug.manas.accountservice.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aug.manas.accountservice.repository.ExpTransactionRepository;
import aug.manas.accountservice.service.impl.ExpTransactionServiceImpl;

/**
 * @author shweta
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ExpTransactionServiceImplTest {

	private static final Logger logger = LoggerFactory.getLogger(ExpTransactionServiceImplTest.class);
	@Mock
	private ExpTransactionRepository transactionRepository;

	@InjectMocks
	private ExpTransactionServiceImpl expTransactionServiceImpl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
/*	
	@Test
	public void should_add_transaction(){
		logger.debug("should_add_transaction");
		ExpTransaction exp = new ExpTransaction("Mobile",new Date(), TransactionType.EXPENSE, Currency.CAD, 100.00);
		expTransactionServiceImpl.addTransaction(exp);
	}
*/
}
