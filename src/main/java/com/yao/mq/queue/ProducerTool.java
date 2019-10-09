package com.yao.mq.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * Created by Calm on 2018/11/12
 */
public class ProducerTool {
    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private String subject = "myqueue";
    private Destination destination = null;
    private Connection connection = null;
    private Session session = null;
    private MessageProducer producer = null;

    private void initialize() throws JMSException,Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user,password,url);
        connection = connectionFactory.createConnection();
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        destination = session.createQueue(subject);
        producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    }

    /**
     * 发送消息
     * @param message
     * @throws JMSException
     * @throws Exception
     */
    public void produceMessage(String message) throws JMSException,Exception{
        initialize();
        TextMessage msg = session.createTextMessage(message);
        connection.start();
        System.out.println("Producer:->Sending message: " + message);
        producer.send(msg);
        System.out.println("Producer:->Message sent complete!");
    }

    public void close() throws JMSException{
        System.out.println("Producer:->Closing connection");
        if(producer != null)
            producer.close();
        if(session != null)
            session.close();
        if(connection != null)
            session.close();

    }
}


