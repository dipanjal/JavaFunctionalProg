package common.service;

import common.model.Person;

import java.util.Arrays;
import java.util.List;

public class PersonService {

    public List<Person> getDummyPersonList(){
        return Arrays.asList(
                new Person("jhonDoe","Jhon Doe", 30, "", ""),
                new Person("eric","Eric Clapton", 60, "", ""),
                new Person("jasonB","Jason Backer", 50, "", "")
        );
    }
}
