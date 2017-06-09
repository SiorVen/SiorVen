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
 * Service of the mail
 */

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;


    /**
     * notifies the users that a machine resource's stock is gone
     *
     * @param mr The machine resource
     */
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

    /**
     * Gets a array containing the email addresses of all the users
     *
     * @return The emails
     */
    private String[] mails() {
        List<User> users = userService.findAll();

        String[] emails = new String[users.size()];

        for (int i = 0; i < users.size(); i++) {
            emails[i] = users.get(i).getEmail();
        }

        return emails;
    }
}
