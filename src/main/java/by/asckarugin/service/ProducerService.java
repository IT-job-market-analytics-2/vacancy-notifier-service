package by.asckarugin.service;

import by.asckarugin.dto.telegram.TelegramDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Value("${rabbitmq.producer.telegram-notifications-queue}")
    private String telegramNotificationQueue;

    private final RabbitTemplate rabbitTemplate;

    public ProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(TelegramDto telegramDto){
        rabbitTemplate.convertAndSend(telegramNotificationQueue, telegramDto);
    }
}
