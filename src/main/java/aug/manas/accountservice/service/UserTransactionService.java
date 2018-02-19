/**
 * 
 */
package aug.manas.accountservice.service;

import java.util.List;

import aug.manas.accountservice.model.AccountTransaction;

/**
 * @author shweta
 *
 */
public interface UserTransactionService {

	AccountTransaction addTransaction(long userId, AccountTransaction t);
	boolean deleteTransaction(long transId);
	boolean deleteAllTransactions(long userId);
	AccountTransaction updateTransaction(long userId, AccountTransaction t);
	List<AccountTransaction> getAllTransactionsforUser(long userId);
	AccountTransaction findTransactionByTransactionId(Long transactionId);
}