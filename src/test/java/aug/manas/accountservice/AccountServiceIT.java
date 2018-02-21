package aug.manas.accountservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import aug.manas.accountservice.model.AccountTransaction;
import aug.manas.accountservice.model.TransactionType;
import aug.manas.accountservice.repository.AccountTransactionRepository;
import aug.manas.accountservice.repository.UserTransactionRepository;
import aug.manas.accountservice.service.UserTransactionService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceIT {
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceIT.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Mock
	UserTransactionService userTransactionService;

	@Mock
	AccountTransactionRepository accountTransactionRepository;

	@Mock
	UserTransactionRepository userTransactionRepository;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	private MockMvc mockMvc;
	private static List<AccountTransaction> listExpTransactions;
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		Calendar cal = Calendar.getInstance();
		cal.set(2018, 0, 10);
		Date date1 = cal.getTime();

		cal.set(2018, 1, 11);
		Date date2 = cal.getTime();

		cal.set(2018, 1, 19);
		Date date3 = cal.getTime();

		AccountTransaction transaction1 = new AccountTransaction("Salary", date1, TransactionType.INCOME,
				new BigDecimal(5000.00));
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

	@Test
	public void contextLoads() {
	}

	/*
	 * Test to add transaction for user
	 */
	@Test
	public void should_1_add_transaction_for_user() throws Exception {
		logger.debug("Testing should_add_transaction_for_user");
		this.mockMvc
				.perform(post("/api/acct-service/1/transaction/").content(this.json(listExpTransactions.get(0)))
						.contentType(contentType))
				.andExpect(status().isCreated()).andDo(print()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.description", is(listExpTransactions.get(0).getDescription())))
				.andExpect(jsonPath("$.amount").value(listExpTransactions.get(0).getAmount()))
				.andExpect(jsonPath("$.date",
						is(new SimpleDateFormat("yyyy-MM-dd").format(listExpTransactions.get(0).getDate()))))
				.andExpect(jsonPath("$.type").value(listExpTransactions.get(0).getType().toString()));

	}

	/*
	 * Test to update the transaction
	 */
	@Test
	public void should_2_update_transaction_for_user() throws Exception {
		logger.debug("Testing should_update_transaction_for_user");
		this.mockMvc
				.perform(put("/api/acct-service/1/transaction/1").content(this.json(listExpTransactions.get(0)))
						.contentType(contentType))
				.andExpect(status().isOk()).andDo(print()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.description", is(listExpTransactions.get(0).getDescription())))
				.andExpect(jsonPath("$.amount").value(listExpTransactions.get(0).getAmount()))
				.andExpect(jsonPath("$.date",
						is(new SimpleDateFormat("yyyy-MM-dd").format(listExpTransactions.get(0).getDate()))))
				.andExpect(jsonPath("$.type").value(listExpTransactions.get(0).getType().toString()));

	}

	/*
	 * Test to get all transactions for user
	 */
	@Test
	public void should_3_return_alltransactions_for_user_success() throws Exception {
		logger.debug("Testing should_return_alltransaction_for_user_success");
		this.mockMvc.perform(get("/api/acct-service/1/transaction/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].description", is(listExpTransactions.get(0).getDescription())))
				.andExpect(jsonPath("$[0].amount").value("5000.0"))
				.andExpect(jsonPath("$[0].date",
						is(new SimpleDateFormat("yyyy-MM-dd").format(listExpTransactions.get(0).getDate()))))
				.andExpect(jsonPath("$[0].type").value(listExpTransactions.get(0).getType().toString()));

	}

	/*
	 * Test fail to get all transactions for user
	 */
	@Test
	public void should_4_return_alltransactions_for_user_fail() throws Exception {
		logger.debug("Testing should_return_alltransaction_for_user_fail");
		this.mockMvc.perform(get("/api/acct-service/10/transaction/")).andDo(print()).andExpect(status().isNoContent());

	}

	/*
	 * Test to get transaction for user id and transaction id in path
	 */
	@Test
	public void should_4_return_transaction_for_user_for_transactionId_success() throws Exception {
		logger.debug("Testing should_return_transaction_for_user_for_transactionId_success");
		this.mockMvc
				.perform(get("/api/acct-service/1/transaction/1").content(this.json(listExpTransactions.get(0)))
						.contentType(contentType))
				.andExpect(status().isOk()).andDo(print()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.description", is(listExpTransactions.get(0).getDescription())))
				.andExpect(jsonPath("$.amount").value("5000.0"))
				.andExpect(jsonPath("$.date",
						is(new SimpleDateFormat("yyyy-MM-dd").format(listExpTransactions.get(0).getDate()))))
				.andExpect(jsonPath("$.type").value(listExpTransactions.get(0).getType().toString()));

	}

	/*
	 * Test fail to transaction for user id and transaction id in path
	 */
	@Test
	public void should_4_return_transaction_for_user_for_transactionId_fail() throws Exception {
		logger.debug("Testing should_return_transaction_for_user_for_transactionId_fail");
		this.mockMvc.perform(get("/api/acct-service/1/transaction/10")).andDo(print()).andExpect(status().isNoContent());

	}

	/*
	 * Test to delete the transaction
	 */
	@Test
	public void should_5_delete_transaction_for_user() throws Exception {
		logger.debug("Testing should_delete_transaction_for_user");
		this.mockMvc.perform(delete("/api/acct-service/1/transaction/{1}", 1L)).andExpect(status().isOk())
				.andDo(print());

	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}
