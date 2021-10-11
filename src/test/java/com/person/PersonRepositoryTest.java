package com.person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository repository;

    @Test
    public void testFindByName() {

        entityManager.persist(new Person(4l, "Ram", LocalDate.of(2020, 10, 9)));

        Optional<Person> personOptional = repository.findByName("Steve");

        assertEquals("Steve", personOptional.get().getName());
    }

    @Test
    public void testFindPeopleByBirthDayBefore() {

        entityManager.persist(new Person(4l, "Sam", LocalDate.of(2020, 10, 9)));
        entityManager.persist(new Person(5l, "Jam", LocalDate.of(2020, 10, 5)));
        entityManager.persist(new Person(6l, "Sami", LocalDate.of(2020, 10, 1)));

        List<Person> persons = repository.findPeopleByBirthDayBefore(LocalDate.of(2020, 10, 9));

        assertEquals(2, persons.size());

        assertThat(persons).extracting(Person::getName).contains("Jam");
    }
}
