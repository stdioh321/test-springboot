package com.devsuperior.userdept.controller.dto;

import com.devsuperior.userdept.entities.Departament;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartamentDto {
    @NotNull
    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public Departament toEntity() {
        return Departament
                .builder()
                .id(this.getId())
                .name(this.getName())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }

    public static DepartamentDto toInstance(Departament departament) {
        return new DepartamentDto(departament.getId(), departament.getName(), departament.getCreatedAt(), departament.getUpdatedAt(), departament.getDeletedAt());
    }
}
