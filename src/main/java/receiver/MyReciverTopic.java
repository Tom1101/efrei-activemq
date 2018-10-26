package receiver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

public class MyReciverTopic {
    public static void main(String[] args) {
        try {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
            TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
            Topic topic = (Topic) applicationContext.getBean("topic");
            // Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
            TopicConnection connection = factory.createTopicConnection();
            // Open a session
            TopicSession session = connection.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
            // start the connection
            connection.start();
            // Create a receive
            TopicSubscriber receiver = session.createSubscriber(topic);
            // Receive the message
            Message message = receiver.receive();
            System.out.println("receivertopic");
            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
