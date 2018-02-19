/**
 * 
 */
package aug.manas.accountservice.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aug.manas.accountservice.model.AccountTransaction;
import aug.manas.accountservice.model.UserTransaction;
import aug.manas.accountservice.repository.UserTransactionRepository;
import aug.manas.accountservice.service.AccountTransactionService;
import aug.manas.accountservice.service.UserTransactionService;

/**
 * @author shweta This is a service class used to create, update , delete
 *         transactions and maintains the integrity of the transaction data as
 *         well.
 */
@Service("userTransactionService")
@Transactional
public class UserTransactionServiceImpl implements UserTransactionService {

	private static final Logger logger = LoggerFactory.getLogger(UserTransactionServiceImpl.class);

	private UserTransactionRepository userTransactionRepository;
	private AccountTransactionService accountTransactionService;

	/**
	 * @param expUserTransactionRepository
	 * @param accountTransactionService
	 */
	@Autowired
	public UserTransactionServiceImpl(UserTransactionRepository userTransactionRepository,
			AccountTransactionService accountTransactionService) {
		super();
		this.userTransactionRepository = userTransactionRepository;
		this.accountTransactionService = accountTransactionService;
	}

	/**
	 * Method to add the transaction to db
	 * 
	 * @param UserId,
	 *            the UserId
	 * @param AccountTransaction
	 *            Object
	 * @return boolean value of result of successful addition/save of
	 *         transaction.
	 */
	@Override
	public AccountTransaction addTransaction(long userId, AccountTransaction expTransaction) {
		logger.debug("Adding transaction for userId " + userId);
		AccountTransaction savedTransaction = null;

		// check if userID greater than or equal 1
		if (userId >= 1) {
			savedTransaction = accountTransactionService.addTransaction(expTransaction);
			if (savedTransaction != null) {
				UserTransaction saveUserTransaction = userTransactionRepository
						.save(new UserTransaction(userId, savedTransaction.getId()));
				if (saveUserTransaction == null) {
					logger.error("User-transaction not added for userId " + userId);
				}

			} else {
				logger.error("Transaction not added for userId " + userId);
			}

		} else {
			logger.error("Cannot add transaction for userId " + userId);
		}

		return savedTransaction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see aug.manas.accountservice.service.ExpUserTransactionService#
	 * deleteTransaction(long)
	 * 
	 * @param, transactionid for which transaction needs to be deleted.
	 */

	@Override
	public boolean deleteTransaction(long transactionId) {
		logger.debug("Deleting transactionId " + transactionId);
		boolean result = false;
		if (transactionId <= 0) {
			logger.error("Transaction cannot deleted for transactionId " + transactionId);
		} else {
			if (accountTransactionService.deleteTransaction(transactionId)) {

				// if transaction is deleted, then delete entry from
				// user_transaction table
				userTransactionRepository.delete(transactionId);
				if (userTransactionRepository.findOne(transactionId) == null) {
					result = true;
				}
			}
		}

		return result;
	}

	/**
	 * deleteAllTransactions for a User
	 * 
	 * @param userId
	 *            for which all transactions need to be deleted. Used when User
	 *            is deleted.
	 * @return boolean result for successfully deleted or not.
	 */

	@Override
	public boolean deleteAllTransactions(long userId) {
		boolean result = false;
		logger.debug("Deleting all transactions for user id " + userId);
		if (userId > 0) {
			userTransactionRepository.deleteByUserId(userId);
			if (getAllTransactionsforUser(userId) == null) {
				result = true;
			}
		} else {
			logger.error("Cannot delete all transactions for user id " + userId);
		}

		return result;
	}

	/**
	 * updateTransaction for a given userId.
	 */
	@Override
	public AccountTransaction updateTransaction(long userId, AccountTransaction expTransaction) {
		logger.debug("Updating transactions for user id" + userId);
		if (userId <= 0) {
			logger.error("Cannot uodate transaction for userId " + userId);
		}
		return accountTransactionService.updateTransaction(expTransaction);
	}

	@Override
	public AccountTransaction findTransactionByTransactionId(Long transactionId) {
		logger.debug("Getting transaction by transaction id" + transactionId);
		AccountTransaction accountTransactionByTransactionId = null;
		if (transactionId != null && transactionId >= 1) {
			accountTransactionByTransactionId = accountTransactionService.getTransactionById(transactionId);
		}

		return accountTransactionByTransactionId;
	}

	/**
	 * Get all transactions for User
	 */

	@Override
	public List<AccountTransaction> getAllTransactionsforUser(long userId) {
		logger.debug("Getting all transactions for userID " + userId);
	
		List<Long> userTransactionIds = userTransactionRepository.findByUserId(userId);
		
		List<AccountTransaction> accountTransactions = accountTransactionService.getTransactionsListByIds(userTransactionIds);
		
		return accountTransactions;

	}
	


}
