package com.tsystems.javaschool.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Component
@Log4j2
public class JMSProducer {
    private static final String JMS_CONNECTION_FACTORY_JNDI = "jms/RemoteConnectionFactory";
    private static final String JMS_TOPIC_JNDI = "jms/topic/products";
    private static final String WILDFLY_REMOTING_URL = "http-remoting://127.0.0.1:8081";
    private static final String JMS_USERNAME = "Bazil";
    private static final String JMS_PASSWORD = "123";

    public void sendMessage() {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        props.put(Context.PROVIDER_URL, WILDFLY_REMOTING_URL);
        props.put(Context.SECURITY_PRINCIPAL, JMS_USERNAME);
        props.put(Context.SECURITY_CREDENTIALS, JMS_PASSWORD);
        try {
            Context context = new InitialContext(props);
            TopicConnectionFactory connectionFactory = (TopicConnectionFactory) context.lookup(JMS_CONNECTION_FACTORY_JNDI);
            Topic topic = (Topic) context.lookup(JMS_TOPIC_JNDI);

            try (TopicConnection connection = connectionFactory.createTopicConnection(JMS_USERNAME, JMS_PASSWORD)) {
                connection.start();

                try (TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE)) {

                    try (TopicPublisher sender = session.createPublisher(topic)) {
                        TextMessage message = session.createTextMessage("Hello from main");

                        sender.send(message);
                    }
                }
            }

        } catch (JMSException | NamingException e) {
            log.error(e.getMessage());
        }
        log.info("Message sent!");
    }


}
