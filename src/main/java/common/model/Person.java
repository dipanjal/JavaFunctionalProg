package common.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    private String userName;
    private String name;
    private int age;
    private String email;
    private String mobile;
}
