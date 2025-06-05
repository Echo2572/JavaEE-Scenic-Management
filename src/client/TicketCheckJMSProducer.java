package client;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class TicketCheckJMSProducer {

    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "jms/queue/ticketCheckQueue";
    private static final String DEFAULT_USERNAME = "testJNDI";
    private static final String DEFAULT_PASSWORD = "syb22123012";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "remote://localhost:4447";

    public void sendMessage(String ticketCode) throws Exception {
        Context context = null;
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;

        try {
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
            env.put(Context.PROVIDER_URL, PROVIDER_URL);
            env.put(Context.SECURITY_PRINCIPAL, DEFAULT_USERNAME);
            env.put(Context.SECURITY_CREDENTIALS, DEFAULT_PASSWORD);
            context = new InitialContext(env);

            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(DEFAULT_CONNECTION_FACTORY);
            Destination destination = (Destination) context.lookup(DEFAULT_DESTINATION);

            connection = connectionFactory.createConnection(DEFAULT_USERNAME, DEFAULT_PASSWORD);
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(destination);
            connection.start();

            TextMessage message = session.createTextMessage(ticketCode);
            producer.send(message);
            Thread.sleep(1);
            System.out.println("Sent ticket check message: " + message.getText());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 按正确顺序关闭资源，并捕获所有异常
            try {
                if (producer != null) {
                    producer.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
            try {
                if (session != null) {
                    session.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
            try {
                if (context != null) {
                    context.close();
                }
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }
}
