package aug.manas.accountservice.repository;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aug.manas.accountservice.model.AccountTransaction;
import aug.manas.accountservice.model.TransactionType;

/**
 * @author shweta
 *
 */
@Profile("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountTransactionRepositoryTest {

	private static final Logger logger = LoggerFactory.getLogger(AccountTransactionRepositoryTest.class);

	@Autowired
	private AccountTransactionRepository repository;

	private static List<AccountTransaction> listExpTransactions;

	/**
	 * Setting up in memory db with 3 transactions.
	 */
	@BeforeClass
	public static void setup() {
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 0, 10);
		Date date1 = cal.getTime();

		cal.set(2018, 1, 11);
		Date date2 = cal.getTime();

		cal.set(2018, 1, 19);
		Date date3 = cal.getTime();

		AccountTransaction transaction1 = new AccountTransaction("Salary", date1, TransactionType.INCOME,
				new BigDecimal(5000));
		AccountTransaction transaction2 = new AccountTransaction("Rent", date2, TransactionType.EXPENSE,
				new BigDecimal(2000.00));
		AccountTransaction transaction3 = new AccountTransaction("Cable", date3, TransactionType.EXPENSE,
				new BigDecimal(150.00));
		AccountTransaction transaction4 = new AccountTransaction("Salary", date3, TransactionType.INCOME,
				new BigDecimal(5000.00));
		// creating a list of transactions
		listExpTransactions = new ArrayList<>();

		listExpTransactions.add(transaction1);
		listExpTransactions.add(transaction2);
		listExpTransactions.add(transaction3);
		listExpTransactions.add(transaction4);

	}

	/*
	 * Test to verify saving a new transaction works or not
	 */
	@Test
	public void should_save_exptransaction() {
		logger.debug("Testing shouldSaveExptransaction");

		//
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 0, 20);
		Date date = cal.getTime();

		// Creating a new transaction to ensure save occurs via repository.
		AccountTransaction exp = new AccountTransaction("Mobile", date, TransactionType.EXPENSE,
				new BigDecimal(100.00));
		AccountTransaction expTransactionCreated = repository.save(exp);

		assertThat(expTransactionCreated, hasProperty("description"));
		assertThat(expTransactionCreated, hasProperty("id"));
		assertThat(expTransactionCreated, hasProperty("date"));
		assertThat(expTransactionCreated, hasProperty("amount"));
		assertThat(expTransactionCreated, hasProperty("type"));

		assertEquals(expTransactionCreated.getDescription(), "Mobile");
		assertTrue(expTransactionCreated.getDate().compareTo(exp.getDate()) == 0);
		assertEquals(TransactionType.EXPENSE, expTransactionCreated.getType());
		assertTrue(expTransactionCreated.getId().equals(1L));
		assertEquals(expTransactionCreated.getAmount(), new BigDecimal(100.00));
	}

	/*
	 * Test to verify all transactions received
	 */
	@Test
	public void test_get_list_of_exptransactions() {
		logger.debug("Testing list of all transactions");
		// adding 4 transactions
		repository.save(listExpTransactions);

		List<AccountTransaction> listExpTransactions = repository.findAll();
		assertNotNull(listExpTransactions);
		assertEquals(5, listExpTransactions.size());
	}

	/*
	 * Test to get a transaction based on id
	 */
	@Test
	public void test_get_transaction_byId() {
		logger.debug("Getting a transaction based on tran id");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 0, 20);
		Date date1 = cal.getTime();

		cal.set(2018, 0, 10);
		Date date2 = cal.getTime();

		AccountTransaction transactionById1 = repository.findOne(1l);

		assertNotNull(transactionById1);
		assertTrue(transactionById1.getId().equals(1L));
		assertEquals(transactionById1.getDescription(), "Mobile");
		assertEquals(sdf.format(transactionById1.getDate()), sdf.format(date1));
		assertEquals(transactionById1.getType(), TransactionType.EXPENSE);
		assertTrue(transactionById1.getAmount().compareTo(new BigDecimal(100.00)) == 0);

		AccountTransaction transactionById2 = repository.findOne(2l);

		assertNotNull(transactionById2);
		assertTrue(transactionById2.getId().equals(2L));
		assertEquals(transactionById2.getDescription(), "Salary");
		assertEquals(sdf.format(transactionById2.getDate()), sdf.format(date2));
		assertEquals(transactionById2.getType(), TransactionType.INCOME);
		assertTrue(transactionById2.getAmount().compareTo(new BigDecimal(5000.00)) == 0);
	}

	/*
	 * Test to get all transactions by type TransactionType.INCOME
	 */
	@Test
	public void test_get_list_of_exptransactions_forType_Equals_Income() {
		logger.debug("Testing list of all transactions for Income");

		List<AccountTransaction> listIncomeExpTransactions = repository.findByType(TransactionType.INCOME);
		assertNotNull(listIncomeExpTransactions);
		assertEquals(2, listIncomeExpTransactions.size());
		listIncomeExpTransactions.forEach(
				expTransactionsIncome -> assertTrue(expTransactionsIncome.getType() == TransactionType.INCOME));
	}

	/*
	 * Test to get all transactions by type TransactionType.EXPENSE
	 */
	@Test
	public void test_get_list_of_exptransactions_forType_Equals_EXPENSE() {
		logger.debug("Testing list of all transactions for Expense");

		List<AccountTransaction> listExpenseExpTransactions = repository.findByType(TransactionType.EXPENSE);
		assertNotNull(listExpenseExpTransactions);
		assertEquals(3, listExpenseExpTransactions.size());
		listExpenseExpTransactions.forEach(
				expTransactionsExpense -> assertTrue(expTransactionsExpense.getType() == TransactionType.EXPENSE));
	}

	/*
	 * Test non existing transaction
	 */

	@Test
	public void test_no_transaction_for_given_id() {
		logger.debug("Testing for non existing transaction");

		AccountTransaction nonexistingtransactionById = repository.findOne(10l);
		assertNull(nonexistingtransactionById);

	}

	/*
	 * Test to get list of transactions for given date range
	 */
	@Test
	public void test_get_list_of_exptransactions_for_daterange() {
		logger.debug("Testing for get list of transactions withing given date range");

		Calendar cal = Calendar.getInstance();
		cal.set(2018, 0, 05);
		Date fromDate = cal.getTime();

		cal.set(2018, 0, 20);
		Date toDate = cal.getTime();

		List<AccountTransaction> listExpTransactions = repository.findByDate(fromDate, toDate);
		assertNotNull(listExpTransactions);
		assertEquals(2, listExpTransactions.size());
		assertThat(listExpTransactions, containsInAnyOrder(listExpTransactions.get(0), listExpTransactions.get(1)));

	}

	/*
	 * Test to get list of last 10 transactions
	 */
	// @Test
	// public void test_get_list_of_last10_exptransactions () {
	// logger.debug("Testing for get list of 10 latest transactions");
	//
	//
	// List<ExpTransaction> list10ExpTransactions =
	// repository.findFist10ByDateOrderByDateDesc();
	// assertNotNull(list10ExpTransactions);
	// assertEquals(5, listExpTransactions.size());
	// assertThat(listExpTransactions,
	// containsInAnyOrder(listExpTransactions.get(0),
	// listExpTransactions.get(1)));
	//
	// }
}
