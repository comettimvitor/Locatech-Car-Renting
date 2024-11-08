package br.com.locatech.locatech.entities;

import br.com.locatech.locatech.dto.RentRequestDTO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

//Annotations from Lombok.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Rent {
    private Long id;
    private Long personId;
    private Long vehicleId;
    private String vehicleModel;
    private String personCpf;
    private String personName;
    private LocalDate beginDate;
    private LocalDate endDate;
    private BigDecimal totalPrice;

    public Rent(RentRequestDTO rentRequestDTO, BigDecimal price) {
        this.personId = rentRequestDTO.personId();
        this.vehicleId = rentRequestDTO.vehicleId();
        this.beginDate = rentRequestDTO.beginDate();
        this.endDate = rentRequestDTO.endDate();
        this.totalPrice = price;
    }
}
