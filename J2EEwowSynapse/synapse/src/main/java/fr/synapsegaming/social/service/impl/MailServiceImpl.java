package fr.synapsegaming.social.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.synapsegaming.raid.entity.Event;
import fr.synapsegaming.social.service.MailService;
import fr.synapsegaming.user.entity.User;

@Service("MailService")
@Transactional
public class MailServiceImpl implements MailService {

    @Autowired
    private MailSender mailSender;

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendSubscriptionMail(User user) {
        String subject = "[Synapsegaming] no-reply : account activation";
        String body = "Bonjour, \r\nPour finaliser votre inscription, merci de cliquer sur le lien suivant : http://synapsegaming.fr/user/activation/"
                + user.getId() + ".\r\nCordialement,\r\nSynapse Gaming.";
        this.sendMail(user.getMail(), subject, body);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendEventInvitation(User user, Event event) {
        String subject = "[Synapsegaming] no-reply : Raid à " + event.getName()
                + " " + event.getDifficulty() + " le " + event.getDate()
                + " à " + event.getStartTime();
        String body = "Bonjour, \r\nVous avez été invité à renseigner votre disponibilité pour le raid à "
                + event.getName()
                + " "
                + event.getDifficulty()
                + " le "
                + event.getDate()
                + " à "
                + event.getStartTime()
                + ".\r\nPour cela, connectez-vous sur http://synapsegaming.fr et rendez-vous dans la section Raid.\r\nCordialement,\r\nSynapse Gaming";
        this.sendMail(user.getMail(), subject, body);
    }
}
