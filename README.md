# Notification Service - Time Capsule Project

Este microserviço é responsável por processar eventos do RabbitMQ e enviar notificações por e-mail aos usuários do sistema Time Capsule.

## 🚀 Funcionalidades

O serviço consome mensagens de diversas filas e dispara e-mails correspondentes:

- **Convite de Usuário:** Notifica quando um usuário é convidado para uma cápsula.
- **Promoção a Administrador:** Informa ao usuário que ele se tornou administrador de uma cápsula.
- **Saída de Usuário:** Notifica quando alguém deixa uma cápsula.
- **Abertura de Cápsula:** Avisa todos os participantes quando o tempo de espera acaba e a cápsula é aberta.
- **Criação de Cápsula:** Confirmação de que uma nova cápsula foi criada com sucesso.
- **Nova Mensagem:** Alerta sobre novas interações dentro de uma cápsula ativa.

## 🛠 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.x**
- **Spring AMQP (RabbitMQ):** Para mensageria distribuída.
- **Spring Mail:** Para envio de e-mails via SMTP.

