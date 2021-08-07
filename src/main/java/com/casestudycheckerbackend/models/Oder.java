package com.casestudycheckerbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Double totalPrice;

    @ManyToOne
    private StatusOder status;

    @OneToOne FeedbackOrder feedback;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserInformation getUser() {
        return user;
    }

    public void setUser(UserInformation user) {
        this.user = user;
    }

    public UserInformation getProvider() {
        return provider;
    }

    public void setProvider(UserInformation provider) {
        this.provider = provider;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getHour() {
        return hour;
    }

    public void setHour(Long hour) {
        this.hour = hour;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public StatusOder getStatus() {
        return status;
    }

    public void setStatus(StatusOder status) {
        this.status = status;
    }

    public FeedbackOrder getFeedback() {
        return feedback;
    }

    public void setFeedback(FeedbackOrder feedback) {
        this.feedback = feedback;
    }
}
