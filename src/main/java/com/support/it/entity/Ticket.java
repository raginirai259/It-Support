package com.support.it.entity;

import com.sun.istack.NotNull;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 1, max = 50, message = "Title too long (max 50 characters)")
    private String title;

    @NotNull
    @Size(min = 1, max = 500, message = "Description too long (max 500 characters)")
    private String description;

    @CreatedDate
    private LocalDate dateOpened;

    private LocalDate dateClosed;

    @UpdateTimestamp
    private LocalDate lastUpdated;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ticket_status", joinColumns = @JoinColumn(name = "ticket_id"), inverseJoinColumns = @JoinColumn(name = "status_id"))
    private List<Status> updates;

    @ManyToOne
    @CreatedBy
    private User createdBy;

    @ManyToOne
    private User assignedTo;
    private Stage stage;


    public Ticket() {
        this.stage = Stage.InQueue;
    }


    public Ticket(String title, String description, User createdBy) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(LocalDate dateOpened) {
        this.dateOpened = dateOpened;
    }

    public LocalDate getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(LocalDate dateClosed) {
        this.dateClosed = dateClosed;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<com.support.it.entity.Status> getUpdates() {
        return updates;
    }

    public void setUpdates(List<com.support.it.entity.Status> updates) {
        this.updates = updates;
    }

    public com.support.it.entity.User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(com.support.it.entity.User createdBy) {
        this.createdBy = createdBy;
    }

    public com.support.it.entity.User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(com.support.it.entity.User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public com.support.it.entity.Stage getStage() {
        return stage;
    }

    public void setStage(com.support.it.entity.Stage stage) {
        this.stage = stage;
    }

    public void addUpdates(Status updatedStatus) {
        this.updates.add(updatedStatus);
    }
}

