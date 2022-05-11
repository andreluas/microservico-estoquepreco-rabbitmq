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
import dto.PrecoDTO;

@RestController
@RequestMapping(value = "preco")
public class PrecoController {
    
    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity<PrecoDTO> alteraPreco(@RequestBody PrecoDTO precoDTO) {

        this.rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_PRECO, precoDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
