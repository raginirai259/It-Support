package com.support.it.controller;

import com.support.it.entity.Stage;
import com.support.it.entity.Ticket;
import com.support.it.entity.User;
import com.support.it.exception.NoSuchTicketException;
import com.support.it.model.ErrorMessage;
import com.support.it.model.StatusDTO;
import com.support.it.model.TicketDTO;
import com.support.it.repository.TicketRepository;
import com.support.it.service.TicketService;
import com.support.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SupportAgentController {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketService ticketService;
    @Autowired
    UserService userService;

    @GetMapping(value = "/allTickets")
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    //Adding a ticket
    @PostMapping(value = "/createTicket", consumes = "application/json")
    public ResponseEntity<?> createTicket(@Valid @RequestBody TicketDTO newTicket, Errors errors) {

        String response;
        if (errors.hasErrors()) {
            //collecting the validation errors of all fields together in a String delimited by commas
            response = errors.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
            ErrorMessage error = new ErrorMessage();
            error.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
            error.setMessage(response);
            return ResponseEntity.ok(error);
        } else {
            response = ticketService.createNewTicket(newTicket);
            return ResponseEntity.ok(response);
        }

    }

    //Assigning ticket
    @PostMapping(value = "{id}/assign")
    public String assignTicket(@PathVariable("id") int id, @RequestParam String userEmail) {
        Ticket activeTicket = ticketRepository.findById(id);
        User user = userService.findUserByEmail(userEmail);
        activeTicket.setAssignedTo(user);
        activeTicket.setLastUpdated(LocalDate.now());
        ticketRepository.save(activeTicket);
        return "Ticket assigned successfully to user: " + user.getName();
    }

    @PostMapping(value = "{id}/addComments")
    public String addCommentsToExistingTicket(@PathVariable("id") int id, @RequestBody StatusDTO statusDTO) throws NoSuchTicketException {

       String response = ticketService.updateComments(id,statusDTO);
       return response;
    }


    //updating of specific information tied to the initial ticket when it was created, i.e. the
    //description or title
    @PutMapping(value = "{id}/update")
    public String updateTicketDetails(@PathVariable("id") int id, @Valid @RequestBody TicketDTO ticketDTO) {
        Ticket activeTicket = ticketRepository.findById(id);
        activeTicket.setDescription(ticketDTO.getDescription());
        activeTicket.setTitle(ticketDTO.getTitle());
        activeTicket.setLastUpdated(LocalDate.now());
        ticketRepository.save(activeTicket);
        return "Ticket updated successfully with id: " + id;
    }

    //processes ticket closure
    @PutMapping(value = "{id}/close")
    public String processTicketClosure(@PathVariable("id") int id) {
        Ticket activeTicket = ticketRepository.findById(id);
        activeTicket.setStage(Stage.Resolved);
        activeTicket.setDateClosed(LocalDate.now());
        ticketRepository.save(activeTicket);
        return "Ticket Resolved";
    }

    //enables the ability to reopen tickets when they have been closed
    @PutMapping(value = "{id}/reopen")
    public String processTicketReopen(@PathVariable("id") int id) {
        Ticket activeTicket = ticketRepository.findById(id);
        activeTicket.setStage(Stage.InQueue);
        activeTicket.setDateClosed(null);
        activeTicket.setAssignedTo(null);
        ticketRepository.save(activeTicket);
        return "Ticket Reopened successfully with Id: " + id;
    }

    // Deleting a Ticket
    @DeleteMapping(value = "{id}/delete")
    public String deleteTicket(@PathVariable("id") int id) throws NoSuchTicketException {
        return ticketService.deleteTicket(id);

    }
}
