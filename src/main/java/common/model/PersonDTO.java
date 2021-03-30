package common.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonDTO {
    private String name;
    private int age;
    private String email;
    private String phone;

}
