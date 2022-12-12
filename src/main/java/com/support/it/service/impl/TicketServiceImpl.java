package com.support.it.service.impl;

import com.support.it.entity.Stage;
import com.support.it.entity.Status;
import com.support.it.entity.Ticket;
import com.support.it.entity.User;
import com.support.it.exception.NoSuchTicketException;
import com.support.it.model.StatusDTO;
import com.support.it.model.TicketDTO;
import com.support.it.repository.TicketRepository;
import com.support.it.service.TicketService;
import com.support.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserService userService;

    public String createNewTicket(TicketDTO newTicket) {
        User loggedInUser = userService.findUserByEmail(newTicket.getUserEmail());
        Ticket ticket=new Ticket();
        LocalDate currentDate=LocalDate.now();
        ticket.setDateOpened(currentDate);
        ticket.setCreatedBy(loggedInUser);
        ticket.setStage(Stage.InQueue);
        ticket.setTitle(newTicket.getTitle());
        ticket.setDescription(newTicket.getDescription());
        ticketRepository.saveAndFlush(ticket);
        return ticket.getTitle()+" got created successfully";
    }

    @Override
    public String deleteTicket(int id) throws NoSuchTicketException {
        String response;
        Ticket ticket = ticketRepository.findById(id);
        if(ticket!=null)
        {
            ticketRepository.delete(ticket);
            response =  ticket.getTitle()+"\tof\t"+id+"\t got deleted successfully";
        }
        else
        {
            throw new NoSuchTicketException("Ticket does not exist :"+id);
        }
        return response;
    }

    @Override
    public String updateComments(int id, StatusDTO statusDTO) throws NoSuchTicketException {
        String response;
        Ticket activeTicket = ticketRepository.findById(id);
        if(activeTicket!=null) {
            Status newStatus = new Status();
            newStatus.setUpdateDate(LocalDate.now());
            newStatus.setComments(statusDTO.getComments());
            newStatus.setUserEmail(activeTicket.getCreatedBy().getEmail());
            activeTicket.addUpdates(newStatus);
            ticketRepository.save(activeTicket);
            response =  activeTicket.getTitle()+"\tof\t"+id+"\t updated successfully";
        }
        else
        {
            throw new NoSuchTicketException("Ticket does not exist :"+id);
        }
        return response;

    }


}
