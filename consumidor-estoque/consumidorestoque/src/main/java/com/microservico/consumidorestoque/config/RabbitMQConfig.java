package com.microservico.consumidorestoque.config;

import com.microservico.consumidorestoque.exceptions.TratamentoErroHandler;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Insire a classe no container do Spring, e informa que a mesma contem um um mais métodos bean
public class RabbitMQConfig {

    @Bean
    public RabbitListenerContainerFactory<DirectMessageListenerContainer> rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory) {

        DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory); // Seta conexão do rabbitMQ
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO); // O Ack da mensagem (a confirmação) é automatica

        factory.setPrefetchCount(4); // Define a quantidade de mensagens

        factory.setErrorHandler(new TratamentoErroHandler());

        // factory.setGlobalQos(true); // Habilita o prefetch count globalmente, por canal
        // factory.setConsumersPerQueue(20); // Seta quantidade de consumidores por fila

        return factory;
    }
}
