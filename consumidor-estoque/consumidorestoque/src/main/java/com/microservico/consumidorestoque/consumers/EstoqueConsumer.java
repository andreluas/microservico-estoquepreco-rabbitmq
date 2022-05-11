package com.microservico.consumidorestoque.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import constants.RabbitMQConstants;
import dto.EstoqueDTO;

@Component // Adiciona classe no container do Spring
public class EstoqueConsumer {

    @RabbitListener(queues = RabbitMQConstants.FILA_ESTOQUE) // Marca um método para ser um ouvinte de mensagens de uma
                                                             // fila do RabbitMQ
    private void consumidor(String mensagem) throws JsonProcessingException, InterruptedException {
        EstoqueDTO estoqueDTO = new ObjectMapper().readValue(mensagem, EstoqueDTO.class);

        System.out.println(estoqueDTO.codigoproduto);
        System.out.println(estoqueDTO.quantidade);
        System.out.println("------");

        // throw new IllegalArgumentException("Arguemento inválido!"); -> Tratamento de erros da fila, para não entrar em looping

        // Thread.sleep(120000); Para a thread, para ler apenas 2 mensagens, como
        // configurado o prefetch no properties, para ser visto o funcionamento da
        // leitura de mensagens

        // Formula do prefetch: (Tempo de Envio da mensagem + Tempo da volta da
        // mensagem) / Tempo de processamento = Prefecth Cound (aprox)
    }
}