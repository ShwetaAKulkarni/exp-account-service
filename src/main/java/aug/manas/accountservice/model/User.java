/**
 * 
 */
package aug.manas.accountservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author shweta
 *
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -6616322893048101376L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;
	private String username;
	
	//@JsonIgnore
	private String password;
	private String firstName;
	private String lastName;
	private String email;

/*	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_transaction1", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "trans_id"))
	private List<ExpTransaction> transactions;  */

	protected User() {

	}

	public User(String firstName, String lastName, String username, String password, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;

	}
/*
	public User(String firstName, String lastName, String username, String password, String email,
			List<ExpTransaction> transactions) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.transactions = transactions;

	} */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

/*	public List<ExpTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<ExpTransaction> transactions) {
		this.transactions = transactions;
	}  */

}
