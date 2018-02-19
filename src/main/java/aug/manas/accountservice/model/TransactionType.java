/**
 * 
 */
package aug.manas.accountservice.model;

/**
 * @author shweta
 *
 */
public enum TransactionType {
	INCOME, EXPENSE;

	public static TransactionType getDefault() {
		return EXPENSE;
	}

}