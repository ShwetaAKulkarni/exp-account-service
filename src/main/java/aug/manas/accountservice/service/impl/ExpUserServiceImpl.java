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

import aug.manas.accountservice.model.ExpTransaction;
import aug.manas.accountservice.model.ExpUserTransaction;
import aug.manas.accountservice.repository.ExpUserTransactionRepository;
import aug.manas.accountservice.service.ExpTransactionService;
import aug.manas.accountservice.service.ExpUserService;

/**
 * @author shweta
 *
 */
@Service("expUserService")
@Transactional
public class ExpUserServiceImpl implements ExpUserService {

	private static final Logger logger = LoggerFactory.getLogger(ExpUserServiceImpl.class);
	
	@Autowired
	ExpUserTransactionRepository expUserTransactionRepository;
	
	@Autowired 
	ExpTransactionService expTransactionService;

	@Override
	public boolean addTransaction(long userId, ExpTransaction t) {
		logger.debug("Inside addTransaction" );
		boolean result = false;
		Long trans_id = expTransactionService.addTransaction(t);
		if(trans_id != null){
			expUserTransactionRepository.save(new ExpUserTransaction(userId, trans_id));
		}
	/*	if(userId >= 1){
			logger.info("Adding transaction for userId" +userId);
			List<ExpTransaction> transList = new ArrayList<>();
			transList.add(t);
			ExpUser user = expUserTransactionRepository.save(new ExpUser(userId, transList));
			if(user != null){
				result = true;
			}
		}*/
		
		return result;
	}

	@Override
	public boolean deleteTransaction(long t) {
		 if(expTransactionService.deleteTransaction(t)){
			 expUserTransactionRepository.delete(expUserTransactionRepository.findOne(t));
		 }
		
		return false;
	}

	@Override
	public boolean deleteAllTransactions(long userId) {
		expUserTransactionRepository.findByUserId(userId);
		return false;
	}

	@Override
	public boolean updateTransaction(long userId, ExpTransaction t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ExpTransaction> getAllTransactionsforUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
