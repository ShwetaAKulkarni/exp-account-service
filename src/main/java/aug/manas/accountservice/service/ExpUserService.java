/**
 * 
 */
package aug.manas.accountservice.service;

import java.util.List;

import aug.manas.accountservice.model.ExpTransaction;

/**
 * @author shweta
 *
 */
public interface ExpUserService {

	boolean addTransaction(long userId, ExpTransaction t);
	boolean deleteTransaction(long trans_id);
	boolean deleteAllTransactions(long userId);
	boolean updateTransaction(long userId, ExpTransaction t);
	List<ExpTransaction> getAllTransactionsforUser(long userId);
}