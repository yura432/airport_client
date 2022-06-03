import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import service.AirportService;

import java.io.IOException;

public class Main {

    private static final int LAST_AIRPORT_NUM = 12057;
    private static final int THREAD_COUNTS = 7;
    private static final String RESPONSE_RECEIVED = "Received response by thread ";
    private static final Logger logger = Logger.getLogger(Main.class);
    private static final AirportService airportService = new AirportService();

    public static void main(String[] args){
        int step = (int)Math.ceil((double) LAST_AIRPORT_NUM / THREAD_COUNTS);
        for (int i = 0; i < THREAD_COUNTS; i++){
            RequestSender requestSender = new RequestSender(step * i + 1, Math.min(LAST_AIRPORT_NUM, step * (i + 1)));
            Thread thread = new Thread(requestSender);
            thread.start();
        }



    }
    @AllArgsConstructor
    public static class RequestSender implements Runnable{

        private int startIndex;
        private int stopIndex;

        @Override
        public void run() {
            HttpResponse postResult;
            for (int i = startIndex; i <= stopIndex; i++) {
                model.Request request = airportService.createRequest(i);

                ObjectMapper mapper = new ObjectMapper();
                try {
                    postResult = Request.Post("http://localhost:8080/airport")
                            .bodyString(mapper.writeValueAsString(request), ContentType.APPLICATION_JSON)
                            .execute().returnResponse();
                    if (postResult.getStatusLine().getStatusCode() == 200) {
                        logger.info(
                                new StringBuilder(RESPONSE_RECEIVED)
                                        .append(Thread.currentThread().getName())
                                        .append(": ")
                                        .append(EntityUtils.toString(postResult.getEntity()))
                        );
                        model.Response response = mapper.readValue(EntityUtils.toString(postResult.getEntity()), model.Response.class);
                        airportService.checkIdAndLog(request, response);
                    } else {
                        logger.info(postResult.getStatusLine() + " for request " + mapper.writeValueAsString(request));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
