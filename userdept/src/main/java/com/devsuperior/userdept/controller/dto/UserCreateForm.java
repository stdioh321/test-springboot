package com.devsuperior.userdept.controller.dto;

import com.devsuperior.userdept.entities.Departament;
import com.devsuperior.userdept.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateForm {

    @NotNull
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private DepartamentDto departament;

    public User toEntity() {
        return User
                .builder()
                .name(this.getName())
                .email(this.email)
                .departament(this.getDepartament().toEntity())
                .build();
    }
}
