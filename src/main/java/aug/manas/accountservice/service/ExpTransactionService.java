/**
 * 
 */
package aug.manas.accountservice.service;


import aug.manas.accountservice.model.ExpTransaction;

/**
 * @author shweta
 *
 */
public interface ExpTransactionService {

	Long addTransaction(ExpTransaction t);
	boolean deleteTransaction(Long t);
	void updateTransaction(ExpTransaction t);
}
