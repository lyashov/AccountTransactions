package com.lyaev.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
Написать приложение(Rest API), которое позволяет одновременно (параллельно) проводить операции по снятию/пополнению одного и того же счета.
Операции должны осуществляться с помощью http, состояние счетов хранить в БД, вести журнал операций в БД.
Написать тесты, которые позволят проверить данное приложение.
Технологии Java, Spring, любая SQL база данных с поддержкой транзакций, maven */

@SpringBootApplication
public class AccountsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
