package com.jjmontenegrop.springbootjpa.repositories;

import com.jjmontenegrop.springbootjpa.entities.Person;
import com.jjmontenegrop.springbootjpa.entities.PersonDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Query("SELECT p, p.name FROM Person p WHERE p.name = ?1")
    List<Person> searchByProgrammingLanguage(String name);

    @Query("SELECT p FROM Person p WHERE p.programmingLanguage = ?1 AND p.name = ?2")
    List<Person> findAllMixPersonDataList(String java, String name);

    List<Person> findByProgrammingLanguage(String java);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p")
    List<Object[]> obtenerPersonData ();

    @Query("SELECT new com.jjmontenegrop.springbootjpa.entities.PersonDTO (p.name, p.lastname) FROM Person p")
    List<PersonDTO> obtenerPersonDataDTO();

}
