/**
 * 
 */
package aug.manas.accountservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import aug.manas.accountservice.model.AccountTransaction;
import aug.manas.accountservice.service.UserTransactionService;
import aug.manas.accountservice.util.ExpAccountServiceException;

/**
 * @author shweta
 *
 */
@RestController
@RequestMapping("/api/transactions")
public class AccountServiceController {
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceController.class);

	@Autowired
	private UserTransactionService userTransactionService;

	/**
	 * List All transactions for user. UserId is path parameter
	 * 
	 * @param userId
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public void listAllTransactionForUser(@PathVariable("userId") long userId) {
		logger.debug("Inside listAllTransactionForUser");
		List<AccountTransaction> accountTransactionlist = userTransactionService.getAllTransactionsforUser(userId);

		System.out.println("Bandya :::"+ accountTransactionlist);

	}

	/**
	 * addTransaction for User userId is path param and Transaction is query
	 * param in post.
	 * 
	 * @param userId
	 * @param transaction
	 * @param ucBuilder
	 */
	@RequestMapping(value = "/{userId}/transaction", method = RequestMethod.POST)
	public ResponseEntity<AccountTransaction> addTransactionForUser(@PathVariable("userId") long userId,
			@RequestBody AccountTransaction transaction, UriComponentsBuilder ucBuilder) {
		logger.debug("Inside add Transaction for User id " + userId);
		ResponseEntity<AccountTransaction> response = null;
		AccountTransaction accountTransactionCreated = userTransactionService.addTransaction(userId, transaction);
		if (accountTransactionCreated != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(ucBuilder.path("/api/transactions/{userId}/transaction/{id}")
					.buildAndExpand(userId, accountTransactionCreated.getId()).toUri());
			response = new ResponseEntity<AccountTransaction>(accountTransactionCreated, httpHeaders,
					HttpStatus.CREATED);
		}
		return response;
	}

	/**
	 * updateTransaction for User userId and transactionId are path params and
	 * Transaction is query param in put.
	 * 
	 * @param userId
	 * @param transaction
	 * @return
	 * @throws ExpAccountServiceException
	 */
	@RequestMapping(value = "/{userId}/transaction/{transactionId}", method = RequestMethod.PUT)
	public ResponseEntity<AccountTransaction> updateTransactionForUser(@PathVariable("userId") long userId,
			@PathVariable("transactionId") long transactionId, @RequestBody AccountTransaction transaction)
			throws ExpAccountServiceException {
		logger.debug("Updating Transaction " + transactionId + " for User id " + userId);
		ResponseEntity<AccountTransaction> response = null;
		AccountTransaction accountTransaction = userTransactionService.findTransactionByTransactionId(transactionId);

		if (accountTransaction == null) {
			logger.error("Unable to update. Transaction with id {} not found.", transactionId);
			throw new ExpAccountServiceException("Unable to upate. Transaction with id not found." + transactionId);

		}
		accountTransaction.setDescription(transaction.getDescription());
		accountTransaction.setDate(transaction.getDate());
		accountTransaction.setType(transaction.getType());
		accountTransaction.setAmount(transaction.getAmount());

		AccountTransaction accountTransactionUpdated = userTransactionService.updateTransaction(userId,
				accountTransaction);
		if (accountTransactionUpdated != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			// httpHeaders.setLocation(ucBuilder.path("/api/trans/{userId}/transaction/{id}")
			// .buildAndExpand(userId,
			// accountTransactionCreated.getId()).toUri());
			response = new ResponseEntity<AccountTransaction>(accountTransactionUpdated, httpHeaders, HttpStatus.OK);
		}
		return response;
	}

	/**
	 * * deleteTransaction for User . userId and transactionId are path params
	 * 
	 * @param userId
	 * @param transactionId
	 * @return
	 * @throws ExpAccountServiceException
	 */
	@RequestMapping(value = "/{userId}/transaction/{transactionId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTransactionForUser(@PathVariable("userId") long userId,
			@PathVariable("transactionId") long transactionId) throws ExpAccountServiceException {
		logger.debug("Deleting Transaction " + transactionId + " for User id " + userId);
		ResponseEntity<AccountTransaction> response = null;
		AccountTransaction accountTransaction = userTransactionService.findTransactionByTransactionId(transactionId);

		if (accountTransaction == null) {
			logger.error("Unable to update. Transaction with id {} not found.", transactionId);
			throw new ExpAccountServiceException("Unable to delete. Transaction with id not found." + transactionId);

		}
		boolean transactionDeleted = userTransactionService.deleteTransaction(transactionId);
		if (transactionDeleted) {
			response.status(HttpStatus.OK);
		}
		return response;
	}

}
