package aug.manas.accountservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import aug.manas.accountservice.configuration.JpaConfiguration;
import aug.manas.accountservice.model.AccountTransaction;
import aug.manas.accountservice.model.TransactionType;
import aug.manas.accountservice.model.User;
import aug.manas.accountservice.repository.UserRepository;
import aug.manas.accountservice.service.UserTransactionService;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages = { "aug.manas.accountservice" })
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(UserRepository userRepository, UserTransactionService userTransactionService) {
		return (args) -> {
			// Add user 1
			AccountTransaction exp1 = new AccountTransaction("Mobile", new Date(), TransactionType.EXPENSE,
					new BigDecimal(100.00));
			AccountTransaction exp2 = new AccountTransaction("Rent", new Date(), TransactionType.EXPENSE,
					new BigDecimal(1000.00));
			AccountTransaction exp3 = new AccountTransaction("Salary", new Date(), TransactionType.INCOME,
					new BigDecimal(6000.00));
			List<AccountTransaction> expt1 = new ArrayList<>();
			expt1.add(exp1);
			expt1.add(exp2);
			expt1.add(exp3);
			userRepository.save(new User("Shweta", "Kulkarni", "Shwetak", "12342354", "shwrewk@rmff.com"));
//			userTransactionService.addTransaction(1L, exp1);
//			userTransactionService.addTransaction(1L, exp2);
//			userTransactionService.addTransaction(1L, exp3);

			// Add user 2
			AccountTransaction exp4 = new AccountTransaction("InsuranceClaim", new Date(), TransactionType.INCOME,
					new BigDecimal(5000.00));
			AccountTransaction exp5 = new AccountTransaction("Auto", new Date(), TransactionType.EXPENSE,
					new BigDecimal(200.00));
			AccountTransaction exp6 = new AccountTransaction("Salary", new Date(), TransactionType.INCOME,
					new BigDecimal(8000.00));
			List<AccountTransaction> expt2 = new ArrayList<>();
			expt2.add(exp4);
			expt2.add(exp5);
			expt2.add(exp6);
			userRepository.save(new User("ABC", "DEF", "sasf33", "34346", "q34235@qeqr.com"));
//			userTransactionService.addTransaction(2L, exp4);
//			userTransactionService.addTransaction(2L, exp5);
//			userTransactionService.addTransaction(2L, exp6);

		};

	}
}
