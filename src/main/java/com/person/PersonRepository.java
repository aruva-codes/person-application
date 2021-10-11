package com.person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    /**
     * gets Person record by the name
     * @param name
     * @return person
     */
    Optional<Person> findByName(String name);

    /**
     * get list of person(s) younger than the given date
     * @param date
     * @return person(s)
     */
    List<Person> findPeopleByBirthDayBefore(LocalDate date);
}
