package br.com.my.hotel.services;

import br.com.my.hotel.dto.CheckInDto;
import br.com.my.hotel.projections.CheckInSpendingView;
import br.com.my.hotel.entities.CheckIn;
import br.com.my.hotel.entities.Hospede;
import br.com.my.hotel.repositories.CheckInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CheckInService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private CheckInBillingService checkInBillingService;

    @Autowired
    private HospedesService hospedesService;

    public Long createCheckin(CheckInDto checkinDto, Long hospedeId) {
        CheckIn checkIn = new CheckIn();
        Hospede hospede = hospedesService.getHospede(hospedeId);

        checkIn.setHospede(hospede);
        checkIn.setDataEntrada(checkinDto.getDataEntrada() == null ? null : LocalDateTime.parse(checkinDto.getDataEntrada(), DATE_TIME_FORMATTER));
        checkIn.setAdicionalVeiculo(checkinDto.isAdicionalVeiculo());

        if (checkinDto.getDataSaida() != null) {
            checkIn.setDataSaida(LocalDateTime.parse(checkinDto.getDataSaida(), DATE_TIME_FORMATTER));
            checkIn.setValorCobranca(checkInBillingService.calculateBilling(checkIn.getDataEntrada(), checkIn.getDataSaida(), checkIn.isAdicionalVeiculo()));
        }

        return checkInRepository.save(checkIn).getId();
    }

    public Page<CheckInSpendingView> listBillingByCustomer(Boolean emHospedagem, Pageable pageable) {
        return checkInRepository.listBillingByCustomer(emHospedagem, pageable);
    }

}
