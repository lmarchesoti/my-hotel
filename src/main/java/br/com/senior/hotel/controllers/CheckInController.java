package br.com.senior.hotel.controllers;

import br.com.senior.hotel.dto.CheckInDto;
import br.com.senior.hotel.services.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
}
