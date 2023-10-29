package com.devsuperior.userdept.controller.dto;

import com.devsuperior.userdept.entities.Departament;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartamentCreateForm {

    @NotNull
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    public Departament toEntity() {
        Departament d = new Departament();
        d.setName(this.getName());
        return d;
    }

    public static DepartamentCreateForm toInstance(Departament departament) {
        return new DepartamentCreateForm(departament.getName());
    }
}
