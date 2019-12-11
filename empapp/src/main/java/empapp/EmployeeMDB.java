package empapp;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destination",
                propertyValue = "java:/jms/queue/EmployeeQueue"
        ),
        @ActivationConfigProperty(
                propertyName = "messageSelector",
                propertyValue = "type = 'create-employee'")
})
public class EmployeeMDB implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("Try to get message");
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("Message has arrived: " +
                        textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
