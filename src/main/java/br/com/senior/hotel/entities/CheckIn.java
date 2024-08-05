package br.com.senior.hotel.entities;

import jakarta.persistence.*;

@Entity
//@Table(name = "TB_CHECKIN", schema = "HOTEL")
public class CheckIn {

    @Id
    @Column(name = "ID")
    private Long id;
}
