package by.asckarugin.controller;

import by.asckarugin.dto.telegram.TelegramDto;
import by.asckarugin.service.ProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {
    private final ProducerService producerService;

    public SendController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/send-test-message")
    public void sendTestMessage(){
        TelegramDto telegramDto = TelegramDto.builder()
                .chat_id(1045002896L)
                .message("Тестовое сообщение из vacancy-notifier")
                .build();

        producerService.sendMessage(telegramDto);
    }
}
