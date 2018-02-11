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
import aug.manas.accountservice.model.ExpTransaction;
import aug.manas.accountservice.model.TransactionType;
import aug.manas.accountservice.model.User;
import aug.manas.accountservice.repository.ExpUserTransactionRepository;
import aug.manas.accountservice.repository.UserRepository;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages = { "aug.manas.accountservice" })
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(UserRepository userRepository, ExpUserTransactionRepository expUserRepository) {
		return (args) -> {
			// Add user 1
			ExpTransaction exp1 = new ExpTransaction("Mobile", new Date(), TransactionType.EXPENSE, 
					new BigDecimal(100.00));
			ExpTransaction exp2 = new ExpTransaction("Rent", new Date(), TransactionType.EXPENSE, 
					new BigDecimal(1000.00));
			ExpTransaction exp3 = new ExpTransaction("Salary", new Date(), TransactionType.INCOME, 
					new BigDecimal(6000.00)); 
			List<ExpTransaction> expt1 = new ArrayList<>();
			expt1.add(exp1);
			expt1.add(exp2);
			expt1.add(exp3);
			userRepository.save(new User("Shweta", "Kulkarni", "Shwetak", "12342354", "shwrewk@rmff.com"));

			// Add user 2
			ExpTransaction exp4 = new ExpTransaction("InsuranceClaim", new Date(), TransactionType.INCOME, 
					new BigDecimal(5000.00));
			ExpTransaction exp5 = new ExpTransaction("Auto", new Date(), TransactionType.EXPENSE,  new BigDecimal(200.00));
			ExpTransaction exp6 = new ExpTransaction("Salary", new Date(), TransactionType.INCOME, 
					new BigDecimal(8000.00));
			List<ExpTransaction> expt2 = new ArrayList<>();
			expt2.add(exp4);
			expt2.add(exp5);
			expt2.add(exp6);
			userRepository.save(new User("ABC", "DEF", "sasf33", "34346", "q34235@qeqr.com"));

		//ExpUser user_trans = new ExpUser(1, );
	//		expUserRepository.save(user_trans);

		};

	}
}
