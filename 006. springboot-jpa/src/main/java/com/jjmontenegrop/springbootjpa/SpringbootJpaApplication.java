package com.jjmontenegrop.springbootjpa;

import com.jjmontenegrop.springbootjpa.entities.Person;
import com.jjmontenegrop.springbootjpa.entities.PersonDTO;
import com.jjmontenegrop.springbootjpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    private PersonRepository personRepository;

    @Autowired
    public SpringbootJpaApplication(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("All persons: ");
        List<Person> persons = (List<Person>) personRepository.findAll();
        persons.forEach(System.out::println);

        System.out.println("\bJava programmers: ");
        List<Person> findWhereProgrammingLanguage = personRepository.findByProgrammingLanguage("Java");
        findWhereProgrammingLanguage.forEach(System.out::println);

        System.out.println("\nJava programmers using @Query: ");
        List<Person> searchByProgrammingLanguage = personRepository.searchByProgrammingLanguage("Java");
        searchByProgrammingLanguage.forEach(System.out::println);

        List<Object[]> personsValues = personRepository.obtenerPersonData();

        personsValues.stream().forEach((record) -> {
            System.out.println("Name: " + record[0] + " - Programming Language: " + record[1]);
        });

        System.out.println("\nFind by id: ");
        System.out.println(personRepository.findById(1).get());

        System.out.println("\n Find all person DTO");
        List<PersonDTO> personDTOList = personRepository.obtenerPersonDataDTO();
        personDTOList.forEach(System.out::println);

//        create();

        update();

//        delete();
    }

    @Transactional
    public void create() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the person: ");
        String name = scanner.nextLine();
        System.out.println("Enter the last name of the person: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter the programming language of the person: ");
        String programmingLanguage = scanner.nextLine();

        scanner.close();

        Person person = new Person(0, name, lastName, programmingLanguage);
        personRepository.save(person);

        personRepository.findById(person.getId()).ifPresent(System.out::println);
    }

    @Transactional
    public void update() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the id of the person to update: ");
        int id = scanner.nextInt();

        Optional<Person> person = personRepository.findById(id);
        person.ifPresent(System.out::println);

        if (person.isPresent()) {
            System.out.println("Enter the name of the person: ");
            String name = scanner.next();
            System.out.println("Enter the last name of the person: ");
            String lastName = scanner.next();
            System.out.println("Enter the programming language of the person: ");
            String programmingLanguage = scanner.next();

            person.get().setName(name);
            person.get().setLastname(lastName);
            person.get().setProgrammingLanguage(programmingLanguage);

            personRepository.save(person.get());

            personRepository.findById(person.get().getId()).ifPresent(System.out::println);
        }

        System.out.println("Person not found");
    }

    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the id of the person to delete: ");
        int id = scanner.nextInt();

        Optional<Person> person = personRepository.findById(id);
        person.ifPresent(System.out::println);

        if (person.isEmpty()) {
            System.out.println("Person not found");
        } else {
            personRepository.delete(person.get());
            System.out.println("Person deleted");
        }
    }
}
