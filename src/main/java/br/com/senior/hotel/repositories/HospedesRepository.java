package br.com.senior.hotel.repositories;

import br.com.senior.hotel.entities.Hospede;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedesRepository extends JpaRepository<Hospede, Long> {

    @Query("select h " +
            "from Hospede h " +
            "where :queryStr is null " +
            "or ( " +
            " (h.nome like :queryStr) " +
            " or (h.documento like :queryStr) " +
            " or (h.telefone like :queryStr) " +
            ")")
    Page<Hospede> findAllByQueryString(@Param("queryStr") String queryStr, Pageable pageable);

    @Query("select distinct ch.hospede " +
            "from CheckIn ch " +
            "where ch.dataSaida is null")
    Page<Hospede> findAllEmHospedagem(Pageable pageable);

    @Query("select distinct h " +
            "from Hospede h " +
            "inner join ( " +
            "   select ch.hospede.id as hospede_id, " +
            "   sum(case when ch.dataSaida is null then 1 else 0 end) as em_hospedagem " +
            "from CheckIn ch " +
            "group by ch.hospede.id) ch_hospedagem " +
            "on ch_hospedagem.hospede_id = h.id " +
            "where ch_hospedagem.em_hospedagem = 0")
    Page<Hospede> findAllNotEmHospedagem(Pageable pageable);
}
