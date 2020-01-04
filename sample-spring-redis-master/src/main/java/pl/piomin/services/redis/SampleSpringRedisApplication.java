package pl.piomin.services.redis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import pl.piomin.services.redis.model.Account;
import pl.piomin.services.redis.model.Customer;
import pl.piomin.services.redis.model.Transaction;
import pl.piomin.services.redis.repository.CustomerRepository;
import pl.piomin.services.redis.repository.TransactionRepository;

@SpringBootApplication
@EnableRedisRepositories
public class SampleSpringRedisApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SampleSpringRedisApplication.class, args);
    }
    
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionRepository transactionRepository;

	@Override
	public void run(String... args) throws Exception {
		Customer customer = new Customer(1L, "123456", "John Smith");
        customer.addAccount(new Account(1L, "1234567890", 2000));
        customer.addAccount(new Account(2L, "1234567891", 4000));
        customerRepository.save(customer);
        
        Transaction t = new Transaction(1L, 1000, new Date(), 20L, 40L);
        Transaction t2 = new Transaction(1L, 2000, new Date(), 30L, 10L);
        Transaction t3 = new Transaction(1L, 3000, new Date(), 40L, 20L);
        Transaction t4 = new Transaction(1L, 4000, new Date(), 50L, 30L);
        Transaction t5 = new Transaction(1L, 5000, new Date(), 60L, 50L);
        
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(t);
        transactions.add(t2);
        transactions.add(t3);
        transactions.add(t4);
        transactions.add(t5);
        
        transactionRepository.saveAll(transactions);
	}
}
