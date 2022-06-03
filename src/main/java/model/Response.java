package model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class Response {
    private UUID uuid;
    private UUID requestUUID;
    private Timestamp getTimestamp;
    private Timestamp sendTimestamp;
    private Airport airport;
}
