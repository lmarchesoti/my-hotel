package br.com.my.hotel.services;

import br.com.my.hotel.entities.CheckInBillingParams;
import br.com.my.hotel.repositories.CheckInBillingParamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class CheckInBillingService {

    @Autowired
    private CheckInBillingParamsRepository checkInBillingParamsRepository;

    public CheckInBillingParams getBillingParams() {
        return checkInBillingParamsRepository.findById(1L).orElseThrow(
                () -> new RuntimeException("System configuration error"));
    }

    public Double calculateBilling(LocalDateTime dataEntrada, LocalDateTime dataSaida, boolean adicionalVeiculo) {
        CheckInBillingParams billingParams = getBillingParams();
        return doBillingCalculation(dataEntrada, dataSaida, adicionalVeiculo, billingParams);
    }

    protected static double doBillingCalculation(LocalDateTime dataEntrada, LocalDateTime dataSaida, boolean adicionalVeiculo, CheckInBillingParams billingParams) {
        double valorTotal = 0D;
        double valorSegASex = billingParams.getDiariaSegASex() + (adicionalVeiculo ? billingParams.getGaragemSegASex() : 0D);
        double valorFimDeSemana = billingParams.getDiariaFimDeSemana() + (adicionalVeiculo ? billingParams.getGaragemFimDeSemana() : 0D);
        Set<DayOfWeek> weekend = Set.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

        for (LocalDate dataAtual = LocalDate.from(dataEntrada); dataAtual.isBefore(LocalDate.from(dataSaida)); dataAtual = dataAtual.plusDays(1)) {
            valorTotal += weekend.contains(dataAtual.getDayOfWeek()) ? valorFimDeSemana : valorSegASex;
        }

        if (dataSaida.toLocalTime().isAfter(billingParams.getHorarioLimiteCheckout())) {
            valorTotal += weekend.contains(dataSaida.getDayOfWeek()) ? valorFimDeSemana : valorSegASex;
        }

        return valorTotal;
    }
}
