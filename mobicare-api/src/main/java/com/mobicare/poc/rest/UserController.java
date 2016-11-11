package com.mobicare.poc.rest;

import com.fasterxml.jackson.core.sym.Name;
import com.mobicare.poc.dto.UserDTO;
import com.mobicare.poc.model.User;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by vinicius.martins on 02/06/2016.
 */
@RestController
@Api(tags = {"User API"},description = "API de usuários")
@RequestMapping("/rest/user")
public class UserController {



    @ApiOperation(value = "Save User", notes = "Save User Details",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário salvo com sucesso", response = User.class),
            @ApiResponse(code = 400, message = "Dados informados não batem com o esperado"),
            @ApiResponse(code = 500, message = "Erro inesperado")})
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> user(@ApiParam(value = "User object", required = true,name = "UserDTO") @RequestBody UserDTO userDTO){

        return ResponseEntity.ok(new User("Usuário","user@email.com"));
    }

    @ApiOperation(value = "Get User", notes = "Get User Details",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Detalhes de usuários retornados com sucesso", response = User.class)
    })
    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@ApiParam(name = "userId",required = true) @PathVariable("userId") Long userId,
                                     @ApiParam(name = "Channel") @RequestHeader(name = "X-MIP-CHANNEL",required = false) String channel){

        if(userId==null || userId.equals(0l)){
            return ResponseEntity.badRequest().body("{\"msg\":\"Id de usuário deve ser informado\"}");
        }

        return ResponseEntity.ok(new User("Usuário","user@email.com"));
    }



    @ApiOperation(nickname = "NickN",position = 1, value = "Update User", notes = "Update User Details",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário salvo com sucesso", response = User.class),
            @ApiResponse(code = 400, message = "Dados informados não batem com o esperado"),
            @ApiResponse(code = 500, message = "Erro inesperado")})
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@ApiParam(value = "Update user object", required = true,name = "UserDTO") @RequestBody UserDTO userDTO){

        return ResponseEntity.ok(new User("Usuário","user@email.com"));
    }

    @ApiOperation(value = "Delete User", notes = "Delete User",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário deletado com sucesso", response = Void.class),
            @ApiResponse(code = 400, message = "Dados informados não batem com o esperado"),
            @ApiResponse(code = 500, message = "Erro inesperado")})
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@ApiParam(value = "Update user object", required = true,name = "UserDTO") @RequestBody UserDTO userDTO){

        return ResponseEntity.ok(null);
    }
}
