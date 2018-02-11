/**
 * 
 */
package aug.manas.accountservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aug.manas.accountservice.model.ExpTransaction;
import aug.manas.accountservice.model.TransactionType;


/**
 * @author shweta
 *
 */
public interface ExpTransactionRepository extends JpaRepository<ExpTransaction, Long> {
	
	List<ExpTransaction> findByType(TransactionType type);  

}
