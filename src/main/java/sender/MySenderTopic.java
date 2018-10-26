package sender;

import javax.jms.*;
import javax.jms.QueueConnectionFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySenderTopic {

    public static void main(String[] args) {

        try {

            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
            TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");

            Topic topic = (Topic) applicationContext.getBean("topic");

            System.out.println(topic);
            // Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
            TopicConnection connection = factory.createTopicConnection();
            // Open a session
            TopicSession session = connection.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
            // Start the connection
            connection.start();
            // Create a sender
            TopicPublisher sender = session.createPublisher(topic);
            // Create a message
            Message message = session.createTextMessage("CouCouTopic");
            // Send the message
            sender.send(message);
            // Close the session
            session.close();
            // Close the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
