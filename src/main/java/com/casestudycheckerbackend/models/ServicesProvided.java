package com.casestudycheckerbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class ServicesProvided {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_service_id")
    private CategoryService categoryService;

    private String name;

    @ManyToMany(mappedBy = "services")
    @JsonIgnore
    private List<UserInformation> userInformations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserInformation> getUserInformations() {
        return userInformations;
    }

    public void setUserInformations(List<UserInformation> userInformations) {
        this.userInformations = userInformations;
    }
}
