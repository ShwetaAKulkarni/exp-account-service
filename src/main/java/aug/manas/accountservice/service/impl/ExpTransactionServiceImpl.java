/**
 * 
 */
package aug.manas.accountservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aug.manas.accountservice.model.ExpTransaction;
import aug.manas.accountservice.repository.ExpTransactionRepository;
import aug.manas.accountservice.service.ExpTransactionService;

/**
 * @author shweta
 *
 */
@Service("expTransactionService")
public class ExpTransactionServiceImpl implements ExpTransactionService {


	@Autowired
	private ExpTransactionRepository transactionRepository;

	@Override
	public Long addTransaction(ExpTransaction t) {
		ExpTransaction trans = transactionRepository.save(t);
		if(trans == null){
			return null;
		}
		return trans.getId();

	}

	@Override
	public boolean deleteTransaction(Long t) {
		transactionRepository.delete(t);
		return true;

	}

	@Override
	public void updateTransaction(ExpTransaction t) {
		transactionRepository.save(t);

	}


}
