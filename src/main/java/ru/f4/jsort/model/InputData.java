package ru.f4.jsort.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputData {
    String order;
    String type;
    String outname;
    String[] input;
}