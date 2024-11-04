package br.com.my.hotel.dto;

import br.com.my.hotel.entities.CheckIn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckInDto {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

    private HospedeDto hospede;
    private String dataEntrada;
    private String dataSaida;
    private boolean adicionalVeiculo;

    public CheckInDto(CheckIn checkIn) {
        this.hospede = new HospedeDto(checkIn.getHospede());
        this.dataEntrada = checkIn.getDataEntrada() != null ? checkIn.getDataEntrada().format(dateTimeFormatter) : null;
        this.dataSaida = checkIn.getDataSaida() != null ? checkIn.getDataSaida().format(dateTimeFormatter) : null;
        this.adicionalVeiculo = checkIn.isAdicionalVeiculo();
    }
}
