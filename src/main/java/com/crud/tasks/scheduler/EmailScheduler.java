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

    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    private String getTaskOrTasksString() {
        if (taskRepository.count() == 1) {
            return " task.";
        } else {
            return " tasks.";
        }
    }

    //@Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        simpleEmailService.sendMailMessage(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Currently in database You got: " + size + getTaskOrTasksString())
        );
    }

    @Scheduled(cron = "* * 6 * * *")
    public void sendDailyMail() {
        long size = taskRepository.count();
        String message = "Currently in database You got: " + size + getTaskOrTasksString();
        simpleEmailService.sendMimeMessage(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                message));
    }
}
