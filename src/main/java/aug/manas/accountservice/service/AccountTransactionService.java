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
public interface AccountTransactionService {

	AccountTransaction addTransaction(AccountTransaction transaction);

	boolean deleteTransaction(Long transaction);

	AccountTransaction updateTransaction(AccountTransaction transaction);

	AccountTransaction getTransactionById(Long transactionId);

	List<AccountTransaction> getTransactionsListByIds(List<Long> idList);

	boolean deleteTransactionsListByIds(List<Long> idList);
}
