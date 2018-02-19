/**
 * 
 */
package aug.manas.accountservice.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aug.manas.accountservice.model.AccountTransaction;
import aug.manas.accountservice.repository.AccountTransactionRepository;
import aug.manas.accountservice.service.AccountTransactionService;

/**
 * @author shweta
 *
 */
@Service("accountTransactionService")
public class AccountTransactionServiceImpl implements AccountTransactionService {
	private static final Logger logger = LoggerFactory.getLogger(AccountTransactionServiceImpl.class);

	/**
	 * @param accountTransactionRepository
	 */
	@Autowired
	public AccountTransactionServiceImpl(AccountTransactionRepository accountTransactionRepository) {
		super();
		this.accountTransactionRepository = accountTransactionRepository;
	}

	private AccountTransactionRepository accountTransactionRepository;

	@Override
	public AccountTransaction addTransaction(AccountTransaction expTransaction) {
		logger.debug("Adding transaction");
		AccountTransaction savedTransaction = accountTransactionRepository.save(expTransaction);
		if (savedTransaction == null) {
			return null;
		}
		logger.debug("Transaction added for transactionId " + savedTransaction.getId());
		return savedTransaction;

	}

	@Override
	public boolean deleteTransaction(Long transactionId) {
		boolean result = false;
		logger.debug("Deleting transaction for transaction Id" + transactionId);
		if (transactionId <= 0) {
			logger.error("Transaction cannot deleted for transactionId " + transactionId);

		} else {
			accountTransactionRepository.delete(transactionId);
			if (accountTransactionRepository.findOne(transactionId) == null) {
				logger.debug("Transaction with transactionId " + transactionId + " was deleted successfully");
				result = true;
			}

		}

		return result;

	}

	@Override
	public AccountTransaction updateTransaction(AccountTransaction expTransaction) {
		logger.debug("Updating transactions");
		if (expTransaction != null && expTransaction.getId() >= 1) {
			logger.debug("Updating transaction for transaction Id " + expTransaction.getId());
			expTransaction = accountTransactionRepository.save(expTransaction);
		}
		return expTransaction;

	}

	@Override
	public AccountTransaction getTransactionById(Long transactionId) {
		logger.debug("Getting transaction for Id " + transactionId);
		AccountTransaction accountTransactionById = null;
		if (transactionId != null && transactionId >= 1) {

			accountTransactionById = accountTransactionRepository.findOne(transactionId);
		}
		return accountTransactionById;

	}
	//
	// /* (non-Javadoc)
	// * @see
	// aug.manas.accountservice.service.AccountTransactionService#getTransactionsListByIds(long[])
	// */
	// @Override
	// public List<AccountTransaction> getTransactionsListByIds(long... id) {
	// accountTransactionRepository.findById(id);
	//
	// return null;
	// }

	/* (non-Javadoc)
	 * @see aug.manas.accountservice.service.AccountTransactionService#getTransactionsListByIds(java.util.List)
	 */
	@Override
	public List<AccountTransaction> getTransactionsListByIds(List<Long> idList) {
		// TODO Auto-generated method stub
		return accountTransactionRepository.findByIdIn(idList);
	}

}
