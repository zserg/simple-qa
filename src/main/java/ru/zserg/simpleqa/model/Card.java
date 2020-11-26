package ru.zserg.simpleqa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Card {
    private Integer id;
    private String front;
    private String back;
    private String fileName;
}
