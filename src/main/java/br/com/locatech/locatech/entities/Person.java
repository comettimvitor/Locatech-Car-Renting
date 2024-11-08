package br.com.locatech.locatech.entities;

import lombok.*;

//Annotations from Lombok.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Person {
    private Long id;
    private String name;
    private String cpf;
    private String telephone;
    private String email;
}
