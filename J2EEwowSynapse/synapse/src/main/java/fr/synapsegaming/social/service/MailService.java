package fr.synapsegaming.social.service;

import fr.synapsegaming.raid.entity.Event;
import fr.synapsegaming.user.entity.User;

/**
 * <b>MailService</b> is the public interface for Mail API
 * 
 * @author Meidi
 * 
 */
public interface MailService {

    /**
     * This will compose and send the message
     * 
     * @param to
     *            : user who will receive the email
     * @param subject
     *            : the mail subject
     * @param body
     *            : the message to deliver
     */
    public void sendMail(String to, String subject, String body);

    /***
     * Send a subscription mail with a link to confirm
     * 
     * @param user
     *            : user to contact
     */
    public void sendSubscriptionMail(User user);

    /**
     * Send a mail to a user to prevent of an event creation and give the
     * possibility to refuse/accept the invitation
     * 
     * @param user
     *            : user to contact
     */
    public void sendEventInvitation(User user, Event event);

}
