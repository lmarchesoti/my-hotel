package br.com.my.hotel.repositories;

import br.com.my.hotel.entities.CheckInBillingParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInBillingParamsRepository extends JpaRepository<CheckInBillingParams, Long> {
}
