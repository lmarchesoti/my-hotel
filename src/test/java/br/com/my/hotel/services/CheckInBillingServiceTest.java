package br.com.my.hotel.services;

import br.com.my.hotel.entities.CheckInBillingParams;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckInBillingServiceTest {

    @Test
    void Given1DiariaSemanaAnd1DiariaFimDeSemanaSemGaragemAposLimiteSaida_whenDoBillingCalculation_thenReturn420() {
        CheckInBillingParams billingParams = new CheckInBillingParams(1L, 120.0, 150.00, 15.00, 20.00, LocalTime.of(16, 30));

        LocalDateTime dataEntrada = LocalDateTime.of(2024, 6, 14, 8, 0, 0);
        LocalDateTime dataSaida = LocalDateTime.of(2024, 6, 16, 17, 30, 0);
        boolean adicionalVeiculo = false;

        double valorEsperado = 420.0;
        double valorCheckIn = CheckInBillingService.doBillingCalculation(dataEntrada, dataSaida, adicionalVeiculo, billingParams);

        assertEquals(valorEsperado, valorCheckIn);
    }

    @Test
    void Given1DiariaSemanaAnd1DiariaFimDeSemanaComGaragemAntesLimiteSaida_whenDoBillingCalculation_thenReturn305() {
        CheckInBillingParams billingParams = new CheckInBillingParams(1L, 120.0, 150.00, 15.00, 20.00, LocalTime.of(16, 30));

        LocalDateTime dataEntrada = LocalDateTime.of(2024, 6, 14, 8, 0, 0);
        LocalDateTime dataSaida = LocalDateTime.of(2024, 6, 16, 13, 30, 0);
        boolean adicionalVeiculo = true;

        double valorEsperado = 305.0;
        double valorCheckIn = CheckInBillingService.doBillingCalculation(dataEntrada, dataSaida, adicionalVeiculo, billingParams);

        assertEquals(valorEsperado, valorCheckIn);
    }
}