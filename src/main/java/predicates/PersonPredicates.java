package predicates;

import common.model.Person;

import java.util.function.Predicate;

public final class PersonPredicates {

    public static Predicate<Person> personNameStartsWith(String prefix){
        return p -> p.getName().startsWith(prefix);
    }
}
