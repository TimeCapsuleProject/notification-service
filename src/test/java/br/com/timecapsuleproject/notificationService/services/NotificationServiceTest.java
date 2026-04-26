package br.com.timecapsuleproject.notificationService.services;

import br.com.timecapsuleproject.timeCapsuleService.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private NotificationService notificationService;

    private ArgumentCaptor<SimpleMailMessage> messageCaptor;

    @BeforeEach
    void setUp() {
        messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
    }

    @Test
    void sendUserInvitationEmail_ShouldSendEmailWithCorrectContent() {
        // Arrange
        UserInvitationNotification notification = mock(UserInvitationNotification.class);
        when(notification.getTimeCapsuleName()).thenReturn("Cápsula de Teste");
        when(notification.getInvitedUserEmail()).thenReturn("test@example.com");

        // Act
        notificationService.sendUserInvitationEmail(notification);

        // Assert
        verify(mailSender).send(messageCaptor.capture());
        SimpleMailMessage capturedMessage = messageCaptor.getValue();

        assertEquals("test@example.com", Objects.requireNonNull(capturedMessage.getTo())[0]);
        assertEquals("Você foi convidado para uma Cápsula do Tempo!", capturedMessage.getSubject());
        assertEquals("Olá! Você recebeu um convite para participar da cápsula: Cápsula de Teste.", capturedMessage.getText());
    }

    @Test
    void sendUserTurnAdminEmail_ShouldSendEmailWithCorrectContent() {
        // Arrange
        UserTurnAdminNotification notification = mock(UserTurnAdminNotification.class);
        when(notification.getTimeCapsuleName()).thenReturn("Cápsula de Teste");
        when(notification.getUserEmail()).thenReturn("admin@example.com");

        // Act
        notificationService.sendUserTurnAdminEmail(notification);

        // Assert
        verify(mailSender).send(messageCaptor.capture());
        SimpleMailMessage capturedMessage = messageCaptor.getValue();

        assertEquals("admin@example.com", Objects.requireNonNull(capturedMessage.getTo())[0]);
        assertEquals("Você agora é um administrador!", capturedMessage.getSubject());
        assertEquals("Olá! Você agora é administrador da cápsula: Cápsula de Teste.", capturedMessage.getText());
    }

    @Test
    void sendUserLeaveEmail_ShouldSendEmailWithCorrectContent() {
        // Arrange
        UserLeaveNotification notification = mock(UserLeaveNotification.class);
        when(notification.getTimeCapsuleName()).thenReturn("Cápsula de Teste");
        when(notification.getUserEmail()).thenReturn("user@example.com");

        // Act
        notificationService.sendUserLeaveEmail(notification);

        // Assert
        verify(mailSender).send(messageCaptor.capture());
        SimpleMailMessage capturedMessage = messageCaptor.getValue();

        assertEquals("user@example.com", Objects.requireNonNull(capturedMessage.getTo())[0]);
        assertEquals("Saída da Cápsula", capturedMessage.getSubject());
        assertEquals("Você saiu ou foi removido da cápsula: Cápsula de Teste.", capturedMessage.getText());
    }

    @Test
    void sendCapsuleOpenedEmail_ShouldSendEmailWithCorrectContent() {
        // Arrange
        CapsuleOpenedNotification notification = mock(CapsuleOpenedNotification.class);
        when(notification.getTimeCapsuleName()).thenReturn("Cápsula de Teste");
        when(notification.getUserEmail()).thenReturn("user@example.com");

        // Act
        notificationService.sendCapsuleOpenedEmail(notification);

        // Assert
        verify(mailSender).send(messageCaptor.capture());
        SimpleMailMessage capturedMessage = messageCaptor.getValue();

        assertEquals("user@example.com", Objects.requireNonNull(capturedMessage.getTo())[0]);
        assertEquals("Uma Cápsula do Tempo foi aberta!", capturedMessage.getSubject());
        assertEquals("A cápsula Cápsula de Teste está aberta! Venha conferir as memórias.", capturedMessage.getText());
    }

    @Test
    void sendCapsuleCreationEmail_ShouldSendEmailWithCorrectContent() {
        // Arrange
        CapsuleCreationNotification notification = mock(CapsuleCreationNotification.class);
        when(notification.getTimeCapsuleName()).thenReturn("Cápsula de Teste");
        when(notification.getOwnerEmail()).thenReturn("owner@example.com");

        // Act
        notificationService.sendCapsuleCreationEmail(notification);

        // Assert
        verify(mailSender).send(messageCaptor.capture());
        SimpleMailMessage capturedMessage = messageCaptor.getValue();

        assertEquals("owner@example.com", Objects.requireNonNull(capturedMessage.getTo())[0]);
        assertEquals("Nova Cápsula Criada!", capturedMessage.getSubject());
        assertEquals("A cápsula Cápsula de Teste foi criada com sucesso.", capturedMessage.getText());
    }

    @Test
    void sendNewMessageEmail_ShouldSendEmailWithCorrectContent() {
        // Arrange
        NewMessageNotification notification = mock(NewMessageNotification.class);
        when(notification.getTimeCapsuleName()).thenReturn("Cápsula de Teste");
        when(notification.getSenderEmail()).thenReturn("sender@example.com");

        // Act
        notificationService.sendNewMessageEmail(notification);

        // Assert
        verify(mailSender).send(messageCaptor.capture());
        SimpleMailMessage capturedMessage = messageCaptor.getValue();

        assertEquals("sender@example.com", Objects.requireNonNull(capturedMessage.getTo())[0]);
        assertEquals("Nova mensagem na Cápsula!", capturedMessage.getSubject());
        assertEquals("Uma nova mensagem foi adicionada à cápsula: Cápsula de Teste.", capturedMessage.getText());
    }
}
