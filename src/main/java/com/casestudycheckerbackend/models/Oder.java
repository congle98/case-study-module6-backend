package com.casestudycheckerbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Oder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @OneToOne
    private UserInformation user;

    @OneToOne
    private UserInformation  provider;

    private String address;
    private Long hour;
    private String startTime;
    private LocalDate day;

    @ManyToOne
    private StatusOder status = new StatusOder(1L);


}
