package br.com.senior.hotel.services;

import br.com.senior.hotel.dto.HospedeDto;
import br.com.senior.hotel.entities.Hospede;
import br.com.senior.hotel.repositories.HospedesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HospedesService {

    @Autowired
    private HospedesRepository hospedesRepository;

    public Page<Hospede> findAllByQueryString(String queryStr, Pageable pageable) {
        return hospedesRepository.findAllByQueryString(queryStr == null ? null : "%" + queryStr + "%", pageable);
    }

    public Hospede getHospede(Long hospedeId) {
        return hospedesRepository.findById(hospedeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long createHospede(HospedeDto hospedeDto) {
        Hospede hospede = new Hospede();
        hospede.setNome(hospedeDto.getNome());
        hospede.setDocumento(hospedeDto.getDocumento());
        hospede.setTelefone(hospedeDto.getTelefone());
        return hospedesRepository.save(hospede).getId();
    }

    public void updateHospede(Long hospedeId, HospedeDto hospedeDto) {
        Hospede hospede = hospedesRepository.findById(hospedeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        hospede.setNome(hospedeDto.getNome());
        hospede.setDocumento(hospedeDto.getDocumento());
        hospede.setTelefone(hospedeDto.getTelefone());

        hospedesRepository.save(hospede);
    }

    public void deleteHospede(Long hospedeId) {
        if (!hospedesRepository.existsById(hospedeId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        hospedesRepository.deleteById(hospedeId);
    }

    public Page<Hospede> getAllByEmHospedagem(Boolean emHospedagem, Pageable pageable) {
        if (emHospedagem) {
            return hospedesRepository.findAllEmHospedagem(pageable);
        } else {
            return hospedesRepository.findAllNotEmHospedagem(pageable);
        }
    }
}
