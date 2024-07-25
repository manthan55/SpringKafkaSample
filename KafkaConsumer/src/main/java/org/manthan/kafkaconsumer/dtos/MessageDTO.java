package org.manthan.kafkaconsumer.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageDTO {
    private int id;
    private String name;
    private double price;
}