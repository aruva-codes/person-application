package com.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class StartPersonApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(StartPersonApplication.class);

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(StartPersonApplication.class, args);
    }

    @Override
    public void run(String... args) {

        log.info("PersonApplication...");

        repository.save(new Person(1l, "Steve", LocalDate.of(2021, 10, 9)));
        repository.save(new Person(2l, "Tim", LocalDate.of(2021, 10, 8)));
        repository.save(new Person(3l, "Mark", LocalDate.of(2021, 10, 7)));

        System.out.println("\nfindAll()");
        repository.findAll().forEach(x -> System.out.println(x));

        System.out.println("\nfindById(1L)");
        repository.findById(1l).ifPresent(x -> System.out.println(x));

        System.out.println("\nfindByName('Tim')");
        repository.findByName("Tim").ifPresent(x -> System.out.println(x));

        System.out.println("\nfindPeopleByBirthDayBefore('2021/10/09)");
        repository.findPeopleByBirthDayBefore(LocalDate.of(2021, 10, 9))
                .forEach(x -> System.out.println(x));
    }

}

