package br.com.senior.hotel.controllers;

import br.com.senior.hotel.dto.HospedeDto;
import br.com.senior.hotel.services.HospedesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/hospedes")
public class HospedesController {

    @Autowired
    private HospedesService hospedesService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Page<HospedeDto> listHospedes(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "100") int size,
                                         @RequestParam(value = "q", required = false) String queryStr) {
        try {
            return hospedesService.findAllByQueryString(queryStr, PageRequest.of(page, size)).map(HospedeDto::new);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HospedeDto getHospede(@PathVariable("id") Long hospedeId) {
        try {
            return new HospedeDto(hospedesService.getHospede(hospedeId));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createHospede(@RequestBody HospedeDto hospedeDto) {
        try {
            return hospedesService.createHospede(hospedeDto);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateHospede(@PathVariable("id") Long hospedeId,
                              @RequestBody HospedeDto hospedeDto) {
        try {
            hospedesService.updateHospede(hospedeId, hospedeDto);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHospede(@PathVariable("id") Long hospedeId) {
        try {
            hospedesService.deleteHospede(hospedeId);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
