package com.mobicare.poc.rest;

import com.mobicare.poc.dto.UserDTO;
import com.mobicare.poc.model.Config;
import com.mobicare.poc.model.User;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by vinicius.martins on 01/06/2016.
 */

@RestController
@Api(tags = {"Home"}, basePath = "/minhaapi", description = "Home API",produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/rest/home")
public class HomeController {

    @ApiOperation(value = "Return Home", notes = "Return Home Object",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Home de usuário retornada com sucesso", response = User.class),
            @ApiResponse(code = 404, message = "Usuário inexistente"),
            @ApiResponse(code = 500, message = "Erro inesperado")})
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> home(@ApiParam(value = "Updated user object", required = true,name = "UserDTO") @RequestBody UserDTO userDTO,
                                     @ApiParam(name = "X-MIP-CHANNEL",required = false,example = "WEB") @RequestHeader(value = "X-MIP-CHANNEL",required = false) String channel){


        return ResponseEntity.ok(new User("Usuário","user@email.com"));
    }


    @ApiOperation(value = "Return config ",notes = "This method returns the configuration needed to initiate the system",produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/config",method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Configuração retornada com sucesso", response = Config.class),
            @ApiResponse(code = 404, message = "Config não encontrada"),
            @ApiResponse(code = 500, message = "Erro inesperado")})
    public ResponseEntity<?> config(@ApiParam(name = "X-MIP-CHANNEL",required = true, example = "WEB") @RequestHeader("X-MIP-CHANNEL") String channel){

        return ResponseEntity.ok(new Config());
    }

}
