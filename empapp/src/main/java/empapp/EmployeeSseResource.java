package empapp;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Path("events")
@Singleton
public class EmployeeSseResource {

    private Sse sse;

    private SseBroadcaster broadcaster;

    public void handleEvent(@Observes String event) {
        broadcaster.broadcast(
                sse.newEvent(event)
        );
    }

    @Context
    public void setSse(Sse sse) {
        this.sse = sse;
        broadcaster = sse.newBroadcaster();
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void events(@Context SseEventSink sink, @Context Sse sse) {
//        IntStream.range(0, 10)
//                .mapToObj(i -> sse.newEvent("HEllo SSE"))
//                .peek(e ->sink.send(e))
//                .peek(e -> sleep())
//                .forEach(e -> System.out.println("done"));
        broadcaster.register(sink);
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
