package ru.zserg.simpleqa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Card {
    private Integer id;
    private String front;
    private String back;
}
