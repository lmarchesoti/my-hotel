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
}
