package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fanout交换机配置
 */
@Configuration
public class FanoutConfig {

    /**
     * 声明交换机，设置名称
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("lpy.fanout");
    }

    /**
     * 队列1
     * @return
     */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.queue1");
    }

    /**
     * 绑定交换机和队列1
     */
    @Bean
    public Binding bindingQueue1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {

        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }


    /**
     * 队列1
     * @return
     */
    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.queue2");
    }

    /**
     * 绑定交换机和队列2
     */
    @Bean
    public Binding bindingQueue2(Queue fanoutQueue2, FanoutExchange fanoutExchange) {

        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

}
