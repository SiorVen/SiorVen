package org.siorven.services;

import org.siorven.model.MachineResource;
import org.siorven.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 * Created by ander on 03/06/2017.
 */

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;


    public void notify(MachineResource mr) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mails());

        //Message with the default locale
        String txt = messageSource.getMessage("resource.mail.ranOut",
                new String[]{mr.getResource().getName(), mr.getMachineSlot().getMachine().getAlias()}, new Locale("es"));
        message.setText(txt);

        String subject = messageSource.getMessage("resource.mail.ranOut.subject", null, new Locale("es"));
        message.setSubject(subject);

        mailSender.send(message);
    }

    private String[] mails() {
        List<User> users = userService.findAll();

        String[] emails = new String[users.size()];

        for (int i = 0; i < users.size(); i++) {
            emails[i] = users.get(i).getEmail();
        }

        return emails;
    }
}
