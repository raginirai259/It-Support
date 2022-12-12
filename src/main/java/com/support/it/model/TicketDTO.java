package com.support.it.model;

import com.support.it.entity.Stage;
import com.support.it.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private String title;
    private String description;
    private String userEmail;

    // Converts Entity into DTO
    public static TicketDTO valueOf(Ticket ticket) {
        TicketDTO ticketDTO=new TicketDTO();
        ticketDTO.setDescription(ticket.getDescription());
        ticketDTO.setTitle(ticket.getTitle());
        ticketDTO.setUserEmail(ticket.getCreatedBy().getEmail());
        return ticketDTO;
    }


}
