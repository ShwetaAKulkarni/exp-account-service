/**
 * 
 */
package aug.manas.accountservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aug.manas.accountservice.model.ExpUserTransaction;

/**
 * @author shweta
 *
 */
public interface ExpUserTransactionRepository extends JpaRepository<ExpUserTransaction, Long> {
	
	List<ExpUserTransaction> findByUserId(long userId);  
	List<ExpUserTransaction> findByTransId(long transId);  

}
