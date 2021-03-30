import common.model.Person;
import common.model.PersonDTO;
import common.service.PersonService;
import predicates.PersonPredicates;

import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class PredicateTest {
    public static void main(String[] args) {
        PersonService personService = new PersonService();
        List<Person> personList = personService.getDummyPersonList();
        Person person = personList.stream().findFirst().orElse(new Person("dunmmyUser","Dummy", 35, "", ""));

        System.out.println("/* IMPERATIVE APPROACH */");
        System.out.println(getPersonAgeIncremented(person));


        System.out.println("/* DECLARATIVE/FUNCTIONAL APPROACH */");
        System.out.println(getPersonAgeIncremented.apply(person));
        printPersonConsumer.accept(person);
        printAllPersonsConsumer.accept(personList);
        printNameByPredicateByConsumer.accept(personList, p -> p.getName().startsWith("J"));
        printNameByPredicateByConsumer.accept(personList, predicatePersonStartWithJ);

        /** Directly Passed Predicate */
        printAllPersonsConsumer.accept(
                filterPersonByPredicate.apply(personList, p -> p.getName().startsWith("J"))
        );

        /** Assigned Predicate in a variable */
        /*Predicate<Person> personNameStartWithJPredicate = p -> p.getName().startsWith("J");
        printAllPersonsConsumer.accept(
                filterPersonByPredicate.apply(personList, personNameStartWithJPredicate)
        );*/

        /** Outer Predicate, We can store these predicates in a Util function and get then like specification/requirement */
        /*printAllPersonsConsumer.accept(
                filterPersonByPredicate.apply(personList, predicatePersonStartWithJ)
        );*/

//        printAllPersonDtoConsumer.accept(getPersonToPersonDtoByPredicate.apply(personList, predicatePersonStartWithJ));


        /** Predicates from Util Function */
        /*printAllPersonsConsumer.accept(
                filterPersonByPredicate.apply(personList, PersonPredicates.personNameStartsWith("J"))
        );*/
//        printAllPersonDtoConsumer.accept(getPersonToPersonDtoByPredicate.apply(personList, PersonPredicates.personNameStartsWith("J")));
    }

    public static int getPersonAgeIncremented(Person person){
        return person.getAge() + 1;
    }

    /** A simple lambda function, takes person as input and returns age incremented by 1 */
    public static Function<Person, Integer> getPersonAgeIncremented = person -> person.getAge() + 1;

    public static Function<Person, PersonDTO> mapPersonToPersonDTO = person -> new PersonDTO(
                    person.getName(),
                    person.getAge(),
                    person.getEmail(),
                    person.getMobile()
            );


    /** Consumers: Takes only one input and do something.
     * A Consumer just consumes the input but doesn't return anything.
     * the name Consumer Makes absolutely sense of how it behaves
     * */

    /* Taking Person as input and printing it */
    public static Consumer<Person> printPersonConsumer = person -> System.out.println(person.toString());

    /* Input: Person List
    * Looping through the List of Person and calling the printPersonConsumer
    * */
    public static Consumer<List<Person>> printAllPersonsConsumer = personList -> personList.forEach(printPersonConsumer);
    public static Consumer<List<PersonDTO>> printAllPersonDtoConsumer = personList -> personList.forEach( p -> System.out.println(p.toString()));

    /**
     * Predicate: A logical condition basically, return true/false
     * Input: A List of Person
     * return: Filtering results start with letter
     */
    public static Predicate<Person> predicatePersonStartWithJ = person -> person.getName().startsWith("J");



    /** NB: Bi means 2 */
    /**
     * BiConsumers: Takes 2 Arguments and Do its works
     * Input 1: A List of Person
     * Input 2: A Predicate, A logical condition basically, which return s true/false
     * Action: Filtering the list by the Predicate
     * and Looping through the List of Person and calling the printPersonConsumer
     */
    public static BiConsumer<List<Person>, Predicate<Person>> printNameByPredicateByConsumer =
            (people, personPredicate) -> people
                    .stream()
                    .filter(personPredicate)
                    .forEach(printPersonConsumer);

    /**
     * BiFunction: Takes 2 Argument,
     * Explanation: I have assigned a list of person and a Predicate to filter and return the list
     *  */
    public static BiFunction<List<Person>, Predicate<Person>, List<Person>> filterPersonByPredicate =
            (people, personPredicate) -> people
                    .stream()
                    .filter(personPredicate)
                    .collect(Collectors.toList());

    public static BiFunction<List<Person>, Predicate<Person>, List<PersonDTO>> getPersonToPersonDtoByPredicate =
            (people, personPredicate) -> people
                    .stream()
                    .filter(personPredicate)
                    .map(mapPersonToPersonDTO)
                    .collect(Collectors.toList());

}
