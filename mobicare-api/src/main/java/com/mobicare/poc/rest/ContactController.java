package com.mobicare.poc.rest;

import com.mobicare.poc.model.Contact;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * Created by vinicius.martins on 06/06/2016.
 */
@RestController
//@Api(tags = {"Contacts API"},description = "API de contatos")
@RequestMapping("/rest/contact")
public class ContactController {


  //  @ApiOperation(value = "Detalhes do contato",notes = "Este método retorna os dados do contato requisitado",produces = MediaType.APPLICATION_JSON_VALUE)
 //   @ApiResponses(value = {
 //           @ApiResponse(code = 200, message = "Dados retornados com sucesso", response = Contact.class),
 //           @ApiResponse(code = 404, message = "Contato não encontrado"),
 //           @ApiResponse(code = 500, message = "Erro inesperado")})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Contact> contact(@RequestHeader(name="X-MIP-CHANNEL",required = false) String channel, @PathParam("id") Long id){

        return ResponseEntity.ok(new Contact("Vinicius","vinicius.martins@mobicare.com.br"));
    }
}
