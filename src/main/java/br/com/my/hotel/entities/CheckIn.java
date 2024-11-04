package br.com.my.hotel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "HOSPEDE_ID")
    private Hospede hospede;

    @Column(name = "DT_ENTRADA")
    private LocalDateTime dataEntrada;

    @Column(name = "DT_SAIDA")
    private LocalDateTime dataSaida;

    @Column(name = "FL_VEICULO")
    private boolean adicionalVeiculo;

    @Column(name = "VALOR_COBRANCA")
    private Double valorCobranca;
}
