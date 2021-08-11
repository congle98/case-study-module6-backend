package com.casestudycheckerbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FeedbackOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    private UserInformation provider;

    private Integer starRating;

    private String description;

    private LocalDateTime createDate = LocalDateTime.now();

    private Boolean confirm=false;

    @OneToOne
    @JsonIgnore
    private Oder oder;
}
