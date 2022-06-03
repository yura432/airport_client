package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Airport {
    private int id;

    private String name;
    private String city;
    private String country;
    private String IATACode;
    private String ICAOCode;
    private Double latitude;
    private Double longitude;
    private Double num1; //I didn't understand what does it mean
    private Double num2;
    private Character letter;
    private String timeZone;
    private String airport;
    private String airportClass;

}
