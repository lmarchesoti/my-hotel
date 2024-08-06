package br.com.senior.hotel.services;

import br.com.senior.hotel.entities.CheckInBillingParams;
import br.com.senior.hotel.repositories.CheckInBillingParamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckInBillingParamsService {

    @Autowired
    private CheckInBillingParamsRepository checkInBillingParamsRepository;

    public CheckInBillingParams getBillingParams() {
        return checkInBillingParamsRepository.findById(1L).orElseThrow(
                () -> new RuntimeException("System configuration error"));
    }

}
