/**
 * 
 */
package aug.manas.accountservice.repository;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shweta
 *
 */
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = AccountServiceApplication.class)
//// @ActiveProfiles(profiles= "test")
@RunWith(SpringRunner.class)
@SpringBootTest
// @ContextConfiguration(classes = AccountServiceApplication.class)
@ActiveProfiles(profiles = "test")
// @DataJpaTest
public class ExpTransactionRepositoryTest {

	private static final Logger logger = LoggerFactory.getLogger(ExpTransactionRepositoryTest.class);

	@Autowired
	private ExpTransactionRepository repository;

	@Autowired
	private TestEntityManager entityManager;
/*
	@Before
	public void setup() {
		
		ExpTransaction t1 = new ExpTransaction("Rent", new Date(), TransactionType.EXPENSE, Currency.CAD, 2000.00);
		ExpTransaction t2 = new ExpTransaction("Salary", new Date(), TransactionType.INCOME, Currency.CAD, 800.00);
		ExpTransaction t3 = new ExpTransaction("Mobile", new Date(), TransactionType.EXPENSE, Currency.CAD, 100.00);
	}

	@Test
	public void shouldSaveExptransaction() {
		logger.debug("shouldSaveExptransaction");
		ExpTransaction exp = new ExpTransaction("Mobile", new Date(), TransactionType.EXPENSE, Currency.CAD, 100.00);
		// entityManager.persist(exp);
		// entityManager.flush();

		ExpTransaction expTransaction = repository
				.save(new ExpTransaction("Mobile", new Date(), TransactionType.EXPENSE, Currency.CAD, 100.00));
		// ExpTransaction expTransaction = repository.findOne((long) 1);
		assertThat(expTransaction).hasFieldOrPropertyWithValue("Title", "Mobile");
	}
	*/

	/*
	 * @Test public void shouldFindTransactionListByTypeEqualsExpense() {
	 * logger.debug("shouldFindTransactionListByType");
	 * 
	 * // Current Date // LocalDate today = LocalDate.now(); Date today = new
	 * Date();
	 * 
	 * ExpTransaction t1 = new ExpTransaction(); t1.setTitle("Rent");
	 * t1.setType(TransactionType.EXPENSE); t1.setCurrency(Currency.CAD);
	 * t1.setDate(today); t1.setAmount(2000.00); repository.save(t1);
	 * 
	 * ExpTransaction t2 = new ExpTransaction(); t2.setTitle("Salary");
	 * t2.setType(TransactionType.INCOME); t2.setCurrency(Currency.CAD);
	 * t2.setDate(today); t2.setAmount(8000.00); repository.save(t2);
	 * 
	 * ExpTransaction t3 = new ExpTransaction(); t3.setTitle("Mobile");
	 * t3.setType(TransactionType.EXPENSE); t3.setCurrency(Currency.CAD);
	 * t3.setDate(today); t3.setAmount(100.00); repository.save(t3);
	 * 
	 * List<ExpTransaction> t = repository.findByType(TransactionType.EXPENSE);
	 * assertEquals(t.size(), 2);
	 * 
	 * }
	 */

}
