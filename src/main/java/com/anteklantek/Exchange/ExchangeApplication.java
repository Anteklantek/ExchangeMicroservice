package com.anteklantek.Exchange;

import com.anteklantek.Exchange.repository.CurrencyExchangeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ExchangeApplication implements CommandLineRunner {

	@Autowired
	CurrencyExchangeTableRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ExchangeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}

