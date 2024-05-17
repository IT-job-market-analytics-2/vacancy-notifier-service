package by.asckarugin.service;

import by.asckarugin.dto.Vacancy;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @RabbitListener(queues = "${rabbitmq.consumer.new-vacancies-queue}")
    public void consumeVacancy(Vacancy vacancy){

    }
}
