package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Task: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    private String generateNotificationBody(Long tasksCount) {
        if(tasksCount == 1) {
            return "Currently in database you got: " + tasksCount + " task";
        } else {
            return "Currently in database you got: " + tasksCount + " tasks";
        }
    }

    @Scheduled(fixedDelay = 1000000)
    public void sendInformationEmail() {
        long tasksCount = taskRepository.count();
            simpleEmailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT, generateNotificationBody(tasksCount)));
    }

}
