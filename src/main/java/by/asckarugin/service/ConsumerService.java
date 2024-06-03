package by.asckarugin.service;

import by.asckarugin.dto.hh.Vacancy;
import by.asckarugin.dto.telegram.TelegramDto;
import by.asckarugin.dto.telegram.UserDto;
import by.asckarugin.repository.impl.UserRepositoryImpl;
import org.jsoup.Jsoup;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {

    private final UserRepositoryImpl userRepository;

    private final ProducerService producerService;


    public ConsumerService(UserRepositoryImpl userRepository, ProducerService producerService) {
        this.userRepository = userRepository;
        this.producerService = producerService;
    }

    @RabbitListener(queues = "${rabbitmq.consumer.new-vacancies-queue}")
    public void consumeVacancy(Vacancy vacancy){
        List<UserDto> users = userRepository.findUsersByQuery(vacancy.getQuery());

        String message = formVacancyMessageText(vacancy);

        sendMessageToTelegram(users, message);
    }

    private void sendMessageToTelegram(List<UserDto> users, String message){
        for(UserDto user: users){
            if (user.getTelegramChatId() != null)
                sendMessage(user.getTelegramChatId(), message);
        }
    }

    private void sendMessage(Long chatId, String message){
        TelegramDto telegramDto = TelegramDto.builder()
                .chat_id(chatId)
                .message(message)
                .build();
        producerService.sendMessage(telegramDto);
    }

    private String formVacancyMessageText(Vacancy vacancy){
        StringBuilder stringBuilder = new StringBuilder("Новая вакансия для вас: ");
        stringBuilder.append("<b>#")
                .append(vacancy.getQuery())
                .append("<b>#");
        stringBuilder.append("\n");
        stringBuilder.append(vacancy.getName());

        if(vacancy.getEmployer() != null)
            stringBuilder
                    .append("\n")
                    .append(vacancy.getArea().getName())
                    .append("</b>");

        if(vacancy.getArea() != null)
            stringBuilder
                    .append("\n")
                    .append(vacancy.getArea().getName())
                    .append("</b>");

        if(vacancy.getSalary() != null){
            String salaryFrom = String.valueOf(vacancy.getSalary().getFrom());
            String salaryTo = String.valueOf(vacancy.getSalary().getTo());
            if(!salaryTo.equals("0") || !salaryFrom.equals("0")){
                stringBuilder.append("\n").append("Оплата: ");
                if (!salaryFrom.equals("0"))
                    stringBuilder.append("от ").append(salaryFrom).append(" ");

                if(!salaryTo.equals("0"))
                    stringBuilder.append("до ").append(salaryTo).append(" ");
            }

            stringBuilder.append(vacancy.getSalary().getCurrency());
        }

        if(vacancy.getSnippet() != null){
            String requirement = vacancy.getSnippet().getRequirement();
            String responsibility = vacancy.getSnippet().getResponsibility();
            if(requirement != null)
                stringBuilder.append("\n\n").append("Требования: ").append(deleteHtmlTags(requirement));

            if(responsibility != null)
                stringBuilder.append("\n\n").append("Обязанности: ").append(deleteHtmlTags(responsibility));
        }

        stringBuilder.append("\n");
        stringBuilder.append("\n").append(vacancy.getAlternateUrl());

        return stringBuilder.toString();
    }

    static String deleteHtmlTags(String html) {
        return Jsoup.parse(html).text();
    }

}
