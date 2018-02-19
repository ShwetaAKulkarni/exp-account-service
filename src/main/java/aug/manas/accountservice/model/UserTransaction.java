/**
 * 
 */
package aug.manas.accountservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author shweta
 *
 */
@Entity
@Table(name = "user_transaction")
public class UserTransaction implements Serializable {

	private static final long serialVersionUID = -302209030368856060L;

	@Column(name = "user_id")
	private long userId;
	
	/*@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_transaction", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "trans_id"))
	private List<ExpTransaction> transactions;*/

/*	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trans_id")
	private ExpTransaction transaction; */
	
	@Id
	@Column(name = "trans_id")
	private long transId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getTransId() {
		return transId;
	}

	public void setTransId(long transId) {
		this.transId = transId;
	}

	protected UserTransaction(){
		
	}
	
	public UserTransaction(long userId, long transId){
		this.userId = userId;
		this.transId = transId;
	}
	
}
