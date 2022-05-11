package com.microservico.estoquepreco.controllers;

import com.microservico.estoquepreco.services.RabbitMQService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import constants.RabbitMQConstants;
import dto.EstoqueDTO;

@RestController
@RequestMapping(value = "estoque")
public class EstoqueController {
    
    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity<EstoqueDTO> alteraEstoque(@RequestBody EstoqueDTO estoqueDTO) {

        this.rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_ESTOQUE, estoqueDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
