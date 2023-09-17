package com.example.demo;

import com.example.demo.authorDb.Author;
import com.example.demo.authorDb.AuthorRepository;
import com.example.demo.personDb.Person;
import com.example.demo.personDb.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestDbService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Person addPerson(Person person){
        return personRepository.save(person);
    }

    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }
}
