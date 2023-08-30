package com.dmdev.spring.database.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@RevisionEntity // should be only one on all application!!!
public class Revision {

    @RevisionNumber // required
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @RevisionTimestamp // required
    private Long timestamp;

}
