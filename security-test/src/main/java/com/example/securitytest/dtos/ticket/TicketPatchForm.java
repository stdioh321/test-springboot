package com.example.securitytest.dtos.ticket;


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
public class TicketPatchForm {
    @Size(min = 2)
    String name = null;
    @Size(min = 2)
    String field01 = null;
    @Size(min = 2)
    String field02 = null;
}