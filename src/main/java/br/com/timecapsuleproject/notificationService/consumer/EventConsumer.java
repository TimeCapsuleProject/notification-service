package br.com.timecapsuleproject.notificationService.consumer;

import br.com.timecapsuleproject.notificationService.services.NotificationService;
import br.com.timecapsuleproject.timeCapsuleService.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static br.com.timecapsuleproject.timeCapsuleService.constants.RabbitMqConstants.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = QUEUE_USER_INVITATION)
    public void consumeUserInvitationNotification(UserInvitationNotification userInvitationNotification) {
        log.info("Consuming invitation notification: {}", userInvitationNotification);
        notificationService.sendUserInvitationEmail(userInvitationNotification);
    }

    @RabbitListener(queues = QUEUE_USER_TURN_ADMIN)
    public void consumeUserTurnAdminNotification(UserTurnAdminNotification userTurnAdminNotification) {
        log.info("Consuming admin promotion notification: {}", userTurnAdminNotification);
        notificationService.sendUserTurnAdminEmail(userTurnAdminNotification);
    }

    @RabbitListener(queues = QUEUE_USER_LEAVE_NOTIFICATION)
    public void consumeUserLeaveNotification(UserLeaveNotification userLeaveNotification) {
        log.info("Consuming user leave notification: {}", userLeaveNotification);
        notificationService.sendUserLeaveEmail(userLeaveNotification);
    }

    @RabbitListener(queues = QUEUE_CAPSULE_OPENED)
    public void consumeCapsuleOpenedNotification(CapsuleOpenedNotification capsuleOpenedNotification) {
        log.info("Consuming capsule opened notification: {}", capsuleOpenedNotification);
        notificationService.sendCapsuleOpenedEmail(capsuleOpenedNotification);
    }

    @RabbitListener(queues = QUEUE_CAPSULE_CREATION_NOTIFICATION)
    public void consumeCapsuleCreationNotification(CapsuleCreationNotification capsuleCreationNotification) {
        log.info("Consuming capsule creation notification: {}", capsuleCreationNotification);
        notificationService.sendCapsuleCreationEmail(capsuleCreationNotification);
    }

    @RabbitListener(queues = QUEUE_NEW_MESSAGE_NOTIFICATION)
    public void consumeNewMessageNotification(NewMessageNotification newMessageNotification) {
        log.info("Consuming new message notification: {}", newMessageNotification);
        notificationService.sendNewMessageEmail(newMessageNotification);
    }

}
