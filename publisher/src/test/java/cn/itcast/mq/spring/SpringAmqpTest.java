package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue() {
        // 队列名称
        String queueName = "simple.queue";
        // 消息
        String message = "hello mq!";
        // 发送消息
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testWorkQueue() throws InterruptedException {
        // 队列名称
        String queueName = "simple.queue";
        // 消息
        String message = "hello, message-";
        for (int i = 0; i < 50; i++) {
            // 发送消息
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(30); // 每次
        }
    }

    @Test
    public void testFanoutExchange() {
        // 队列名称
        String exchangeName = "lpy.fanout";
        // 消息
        String message = "hello fanout!";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    @Test
    public void testSendDirectExchange() {
        // 交换机名称
        String exchangeName = "lpy.direct";
        // 消息
        String message = "hello direct red";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "red", message);

        // 消息
        String message2 = "hello direct blue";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "blue", message2);

        // 消息
        String message3 = "hello direct yellow";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "yellow", message3);
    }

    @Test
    public void testSendTopicExchange() {
        // 交换机名称
        String exchangeName = "lpy.topic";
        // 消息
        String message = "hello topic a.b";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "a.b", message);

        // 消息
        String message1 = "hello topic a.b.c";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "a.b.c", message1);
    }

    @Test
    public void testSendMap() throws InterruptedException {
        // 准备消息
        Map<String,Object> msg = new HashMap<>();
        msg.put("name", "小宇");
        msg.put("age", 18);
        // 发送消息
        rabbitTemplate.convertAndSend("simple.queue", msg);
    }
}