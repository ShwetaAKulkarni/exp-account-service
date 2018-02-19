/**
 * 
 */
package aug.manas.accountservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aug.manas.accountservice.model.TestAccountTransaction;
import aug.manas.accountservice.model.TestUserTransaction;

/**
 * @author shweta
 *
 */
public interface TestUserTransactionRepository extends JpaRepository<TestUserTransaction, Integer> {
	List<TestAccountTransaction> findByUserId(long userId);
}
