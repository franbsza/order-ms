package com.digital.orderms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comments")
public class Comment {

    @Id
    private String id;
    private String message;
    private String userId;
    @ManyToOne
    private Order order;
}
