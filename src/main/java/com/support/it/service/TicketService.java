package com.support.it.service;

import com.support.it.exception.NoSuchTicketException;
import com.support.it.model.StatusDTO;
import com.support.it.model.TicketDTO;

public interface TicketService {
    public String createNewTicket(TicketDTO newTicket) ;
    String deleteTicket(int id) throws NoSuchTicketException;

    String updateComments(int id, StatusDTO statusDTO) throws NoSuchTicketException;
}
