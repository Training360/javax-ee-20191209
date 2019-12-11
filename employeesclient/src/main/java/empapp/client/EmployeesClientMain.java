package empapp.client;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class EmployeesClientMain {

    public static void main(String[] args) throws Exception {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProperties.put(Context.URL_PKG_PREFIXES,
                "org.jboss.ejb.client.naming");
        jndiProperties.put(Context.PROVIDER_URL,
                "http-remoting://localhost:8080");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        Context ctx = new InitialContext(jndiProperties);
        ConnectionFactory connectionFactory = (ConnectionFactory) ctx
                .lookup("jms/RemoteConnectionFactory");
        Destination destination = (Destination) ctx.lookup("/queue/EmployeeQueue");
//        try (JMSContext jmsContext = connectionFactory.createContext("guest1", "guest1")) {
//            jmsContext.createProducer().send(destination, "Message from client");
//        }

//        CountDownLatch latch = new CountDownLatch(5);

        try (JMSContext jmsContext = connectionFactory.createContext("guest1", "guest1")) {
            jmsContext.createProducer()
                    .setProperty("type", "create-employee")
                    .send(destination, "Hello world!");

//            jmsContext.createConsumer(destination).setMessageListener(
//                    m -> {System.out.println(m);latch.countDown();}
//            );
//            System.out.println("Listener has been registered");
//            latch.await();
        }



    }
}
