package br.com.timecapsuleproject.notificationService.services;

import br.com.timecapsuleproject.timeCapsuleService.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender mailSender;

    public void sendUserInvitationEmail(UserInvitationNotification notification) {
        String subject = "Você foi convidado para uma Cápsula do Tempo!";
        String text = String.format("Olá! Você recebeu um convite para participar da cápsula: %s.", notification.getTimeCapsuleName());
        sendEmail(notification.getInvitedUserEmail(), subject, text);
    }

    public void sendUserTurnAdminEmail(UserTurnAdminNotification notification) {
        String subject = "Você agora é um administrador!";
        String text = String.format("Olá! Você agora é administrador da cápsula: %s.", notification.getTimeCapsuleName());
        sendEmail(notification.getUserEmail(), subject, text);
    }

    public void sendUserLeaveEmail(UserLeaveNotification notification) {
        String subject = "Saída da Cápsula";
        String text = String.format("Você saiu ou foi removido da cápsula: %s.", notification.getTimeCapsuleName());
        sendEmail(notification.getUserEmail(), subject, text);
    }

    public void sendCapsuleOpenedEmail(CapsuleOpenedNotification notification) {
        String subject = "Uma Cápsula do Tempo foi aberta!";
        String text = String.format("A cápsula %s está aberta! Venha conferir as memórias.", notification.getTimeCapsuleName());
        sendEmail(notification.getUserEmail(), subject, text);
    }

    public void sendCapsuleCreationEmail(CapsuleCreationNotification notification) {
        String subject = "Nova Cápsula Criada!";
        String text = String.format("A cápsula %s foi criada com sucesso.", notification.getTimeCapsuleName());
        sendEmail(notification.getOwnerEmail(), subject, text);
    }

    public void sendNewMessageEmail(NewMessageNotification notification) {
        String subject = "Nova mensagem na Cápsula!";
        String text = String.format("Uma nova mensagem foi adicionada à cápsula: %s.", notification.getTimeCapsuleName());
        sendEmail(notification.getSenderEmail(), subject, text);
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@timecapsule.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        
        log.info("Attempting to send email to: {}", to);
        mailSender.send(message);
        log.info("Email sent successfully to: {}", to);
    }
}
