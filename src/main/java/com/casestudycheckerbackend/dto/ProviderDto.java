package com.casestudycheckerbackend.dto;

import com.casestudycheckerbackend.models.ServicesProvided;
import com.casestudycheckerbackend.models.UserServices;

import java.util.ArrayList;
import java.util.List;

public class ProviderDto {
    private long id;
    private String name;
    private String description;
    private Iterable<ServicesProvided> services;
    private double priceperh;
    private String avatar;
    private int views;

    public ProviderDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Iterable<ServicesProvided> getServices() {
        return services;
    }

    public void setServices(Iterable<ServicesProvided> services) {
        this.services = services;
    }

    public double getPriceperh() {
        return priceperh;
    }

    public void setPriceperh(double priceperh) {
        this.priceperh = priceperh;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "ProviderDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", services=" + services +
                ", priceperh=" + priceperh +
                '}';
    }
}
