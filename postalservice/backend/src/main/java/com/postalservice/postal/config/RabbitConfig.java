package com.postalservice.postal.config;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitConfig {
  @Value("${app.queues.letter}") String letterQueueName;
  @Value("${app.queues.parcel}") String parcelQueueName;
  @Bean public Queue letterQueue(){ return new Queue(letterQueueName, true); }
  @Bean public Queue parcelQueue(){ return new Queue(parcelQueueName, true); }
}
