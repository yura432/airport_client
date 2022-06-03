package model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class Request {
    private UUID uuid;
    private String threadName;
    private int airportID;
    private Timestamp timestamp;
}
