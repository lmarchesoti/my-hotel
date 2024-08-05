package br.com.senior.hotel.repositories;

import br.com.senior.hotel.entities.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedesRepository extends JpaRepository<Hospede, Long> {
}
