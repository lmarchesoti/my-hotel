package br.com.my.hotel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckInBillingParams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DIARIA_SEG_A_SEX")
    private Double diariaSegASex;

    @Column(name = "DIARIA_FIM_DE_SEMANA")
    private Double diariaFimDeSemana;

    @Column(name = "GARAGEM_SEG_A_SEX")
    private Double garagemSegASex;

    @Column(name = "GARAGEM_FIM_DE_SEMANA")
    private Double garagemFimDeSemana;

    @Column(name = "HORARIO_LIMITE_CHECKOUT")
    private LocalTime horarioLimiteCheckout;
}
