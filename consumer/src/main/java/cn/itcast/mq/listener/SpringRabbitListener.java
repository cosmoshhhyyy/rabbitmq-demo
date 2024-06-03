package cn.itcast.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueueMessage1(String msg) throws InterruptedException {
        System.out.println("消费者1接收到消息：" + msg);
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueueMessage2(String msg) throws InterruptedException {
        System.out.println("消费者2接收到消息：" + msg);
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) {
        System.out.println("消费者1接收到Fanout消息：" + msg);
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg) {
        System.out.println("消费者2接收到Fanout消息：" + msg);
    }
}
