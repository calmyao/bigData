package com.yao.mq.queue;

import javax.jms.JMSException;

/**
 * Created by Calm on 2018/11/12
 */
public class ProducerTest {

    /**
     *
     * @param args
     * @throws JMSException
     * @throws Exception
     */
    public static void main(String[] args) throws JMSException,Exception{
        ProducerTool producer = new ProducerTool();
        producer.produceMessage("Hello,world");
        producer.close();
    }
}
