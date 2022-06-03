package service;

import model.Request;
import model.Response;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.UUID;

public class AirportService {

    private static final Logger logger = Logger.getLogger(AirportService.class);

    public Request createRequest(int id) {
        Request request = new Request();
        request.setAirportID(id);
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setThreadName(Thread.currentThread().getName());
        request.setUuid(UUID.randomUUID());
        return request;
    }

    public void checkIdAndLog(Request request, Response response) {
        if (request.getAirportID() == response.getAirport().getId()) {
            logger.info("Request with UUID " + request.getUuid() + " responsed OK");
        }
        else
        {
            logger.info("Request with UUID " + request.getUuid() + " responsed not OK");
        }

    }
}
