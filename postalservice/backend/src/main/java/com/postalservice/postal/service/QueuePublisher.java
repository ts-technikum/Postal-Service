package com.postalservice.postal.service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class QueuePublisher {
  private final RabbitTemplate rabbit;
  @Value("${app.queues.letter}") String letterQueueName;
  @Value("${app.queues.parcel}") String parcelQueueName;
  public QueuePublisher(RabbitTemplate rabbit){ this.rabbit = rabbit; }
  public void sendLetter(String id){ rabbit.convertAndSend(letterQueueName, id); }
  public void sendParcel(String id){ rabbit.convertAndSend(parcelQueueName, id); }
}
