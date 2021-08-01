package com.casestudycheckerbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private String fullName;

    private LocalDate dateOfBirth;

    private Boolean gender;

    @ManyToOne
    private City city;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "userInformation")
    private List<Image> image;

    private Double height;

    private Double weight;

    private String hobbies;

    private String description;

    private String facebookLink;

    private Double priceByHour;

    private Double money = 100000.0;

    private Boolean isProvider = false;


    private int numberOfViews;

    private int numberOfRentals;


}
