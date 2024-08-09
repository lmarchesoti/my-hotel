package br.com.senior.hotel.repositories;

import br.com.senior.hotel.dto.CheckInSpendingDto;
import br.com.senior.hotel.entities.CheckIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
//    @Query("select " +
//            "from ")
//    Page<CheckInSpendingDto> listBillingByCustomer(Boolean emHospedagem, Pageable pageable);
}
