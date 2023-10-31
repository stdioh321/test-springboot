package com.example.securitytest.dtos.ticket;


import com.example.securitytest.models.Ticket;
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

    public TicketPatchForm(Ticket ticket) {
        this.setName(ticket.getName());
        this.setField01(ticket.getField01());
        this.setField02(ticket.getField02());
    }
}