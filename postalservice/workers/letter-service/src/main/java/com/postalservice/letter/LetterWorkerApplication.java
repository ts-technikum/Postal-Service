package com.postalservice.letter;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class LetterWorkerApplication {
  public static void main(String[] args){ SpringApplication.run(LetterWorkerApplication.class, args); }
  @Bean public MessageListener letterListener(LetterProcessor processor){
    return msg -> processor.process(new String(msg.getBody()));
  }
  @Bean public SimpleMessageListenerContainer container(ConnectionFactory cf, MessageListener listener, @Value("${app.queues.letter}") String queue){
    SimpleMessageListenerContainer c = new SimpleMessageListenerContainer(cf);
    c.setQueueNames(queue);
    c.setMessageListener(listener);
    return c;
  }
}
