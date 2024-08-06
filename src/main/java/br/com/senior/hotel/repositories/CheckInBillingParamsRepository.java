package br.com.senior.hotel.repositories;

import br.com.senior.hotel.entities.CheckInBillingParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInBillingParamsRepository extends JpaRepository<CheckInBillingParams, Long> {
    @Query("select top 1 bp from CheckInBillingParams bp")
    CheckInBillingParams getBillingParams();
}
