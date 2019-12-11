package empapp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Path("events")
public class EmployeeSseResource {

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void events(@Context SseEventSink sink, @Context Sse sse) {
        IntStream.range(0, 10)
                .mapToObj(i -> sse.newEvent("HEllo SSE"))
                .peek(e ->sink.send(e))
                .peek(e -> sleep())
                .forEach(e -> System.out.println("done"));
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
