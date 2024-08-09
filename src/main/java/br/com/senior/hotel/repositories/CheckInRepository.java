package br.com.senior.hotel.repositories;

import br.com.senior.hotel.projections.CheckInSpendingView;
import br.com.senior.hotel.entities.CheckIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, Long> {

    @Query(value = "select h.nome, h.documento, h.telefone, vt.valorTotal, uv.valorUltimaHospedagem " +
            "from hospede h " +
            "inner join ( " +
            "   select hospede_id, sum(valor_cobranca) as valorTotal " +
            "   from check_in " +
            "   group by hospede_id " +
            "   ) vt on h.id = vt.hospede_id " +
            "inner join ( " +
            "   select ci.hospede_id, valor_cobranca as valorUltimaHospedagem, case when dt_saida is null then true else false end as emHospedagem " +
            "   from check_in ci inner join ( " +
            "   select hospede_id, max(id) as ultimo_checkin " +
            "   from check_in " +
            "   group by hospede_id) uc on ci.id = uc.ultimo_checkin " +
            "   ) uv on h.id = uv.hospede_id " +
            "where uv.emHospedagem = :emHospedagem", nativeQuery = true)
    Page<CheckInSpendingView> listBillingByCustomer(@Param("emHospedagem") Boolean emHospedagem, Pageable pageable);

}
