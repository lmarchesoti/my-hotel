package br.com.my.hotel.controllers;

import br.com.my.hotel.dto.CheckInDto;
import br.com.my.hotel.projections.CheckInSpendingView;
import br.com.my.hotel.services.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/checkin")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Long doCheckin(@RequestBody CheckInDto checkinDto,
                          @RequestParam("hospedeId") Long hospedeId) {
        try {
            return checkInService.createCheckin(checkinDto, hospedeId);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/billing-by-customer")
    @ResponseStatus(HttpStatus.OK)
    public Page<CheckInSpendingView> listCheckInSpending(@RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "size", defaultValue = "100") int size,
                                                         @RequestParam("emHospedagem") Boolean emHospedagem) {
        try {
            return checkInService.listBillingByCustomer(emHospedagem, PageRequest.of(page, size));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
