/**
 * 
 */
package aug.manas.accountservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import aug.manas.accountservice.model.UserTransaction;

/**
 * @author shweta
 *
 */
public interface UserTransactionRepository extends JpaRepository<UserTransaction, Long> {

	List<UserTransaction> findByTransId(long transId);
	
//	@Query(value = " SELECT  t.trans_Id, t.description, t.date,  t.transaction_type, t.amount FROM account_transaction t JOIN user_transaction usertransaction ON usertransaction.trans_Id = t.trans_Id WHERE usertransaction.user_Id = :userId", nativeQuery = true)
	@Query(value = " SELECT  ut.transId FROM UserTransaction AS ut WHERE ut.userId = :userId")
	List<Long> findByUserId(@Param("userId") long userId);

	
	@Query(value = "DELETE transaction, usertransaction FROM AccountTransaction transaction JOIN UserTransaction usertransaction WHERE usertransaction.userId = :userId AND usertransaction.transId = transaction.transId", nativeQuery = true)
	void deleteByUserId(@Param("userId") long userId);
}
