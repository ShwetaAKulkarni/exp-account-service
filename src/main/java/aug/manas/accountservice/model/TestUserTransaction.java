/**
 * 
 */
package aug.manas.accountservice.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author shweta
 *
 */
@Entity
@Table(name = "test_user_transaction")
public class TestUserTransaction implements Serializable {

	private static final long serialVersionUID = -302209030368856060L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private long id;

	@Column(name = "user_id")
	private long userId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "trans_Id")
	private TestAccountTransaction accountTransaction;

	public TestAccountTransaction getAccountTransaction() {
		return accountTransaction;
	}

	public void setAccountTransaction(TestAccountTransaction accountTransaction) {
		this.accountTransaction = accountTransaction;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public TestUserTransaction() {

	}

	public TestUserTransaction(long userId, TestAccountTransaction accountTransaction) {
		this.userId = userId;
		this.accountTransaction = accountTransaction;
	}

}
