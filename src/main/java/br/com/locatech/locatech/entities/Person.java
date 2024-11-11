package br.com.locatech.locatech.entities;

import br.com.locatech.locatech.dto.PersonRequestDTO;
import lombok.*;

/**
 * Represents the basic info about a person.
 * <p>This class uses Lombok annotations to automatically generate getters and setters, along with equals,
 * hashCode and toString methods.</p>
 */

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

    public Person(PersonRequestDTO personRequestDTO) {
        this.name = personRequestDTO.name();
        this.cpf = personRequestDTO.cpf();
        this.telephone = personRequestDTO.telephone();
        this.email = personRequestDTO.email();
    }
}
