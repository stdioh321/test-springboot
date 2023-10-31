package com.example.securitytest.dtos.ticket;


import com.example.securitytest.models.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDto {
    private Long id;
    private String name;
    private String field01;
    private String field02;

    public TicketDto(Ticket ticket) {
        this.setId(ticket.getId());
        this.setName(ticket.getName());
        this.setField01(ticket.getField01());
        this.setField02(ticket.getField02());
    }
}
