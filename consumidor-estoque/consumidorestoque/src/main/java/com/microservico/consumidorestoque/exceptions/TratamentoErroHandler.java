package com.microservico.consumidorestoque.exceptions;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

public class TratamentoErroHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        String nomeDaFila = ((ListenerExecutionFailedException) t).getFailedMessage().getMessageProperties().getConsumerQueue(); // Realizado cast, para mensager ser encapsulada dentro de um listener
        System.out.println(nomeDaFila);

        String mensagem = new String(((ListenerExecutionFailedException) t).getFailedMessage().getBody());
        System.out.println(mensagem);
        System.out.println(t.getCause().getMessage()); // Captura de mensagens de erro

        // Faz mensagem parar de retornar a fila, para não cair em loop
        throw new AmqpRejectAndDontRequeueException("Não deve retornar a fila!");
        // Também poderia -> Logar no ElasticSearch, Cloud Watch(AWS)...
    }
    
}
