package com.support.it.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "status")
public class Status implements Comparable<Status> {
    @Id
    @Column(name = "status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comments;

    @CreationTimestamp
    private LocalDate updateDate;

    private String userEmail;

    @Override
    public int compareTo(Status status) {
        if (getUpdateDate() == null || status.getUpdateDate() == null) {
            return 0;
        }
        return getUpdateDate().compareTo(status.getUpdateDate());
    }

}
