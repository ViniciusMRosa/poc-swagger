package com.mobicare.poc.controller;

import com.mobicare.poc.model.MobicareResource;
import com.mobicare.poc.repository.MobicareResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.swagger.web.SwaggerResource;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Endpoint abaixo serve para retornar as documentações, conforme for cadastrado no banco.
 * Para isso dependemos de um filter @see MobicareSwaggerFilter
 * Esse filtro recebe as requisições que batem em /swagger-resources/* e redirecionam para /mobicare-swagger-resources
 */
@RestController
@ApiIgnore
public class MobicareSwaggerController {


    private RestTemplate restTemplate;

    @Autowired
    private MobicareResourceRepository mobicareResourceRepository;


    @PostConstruct
    public void init(){
        restTemplate = new RestTemplate();
    }

    @RequestMapping(value = "/mobicare-swagger-resources", method = RequestMethod.GET)
    public ResponseEntity<List<SwaggerResource>> getResources(){

        Iterable<MobicareResource> resources =  mobicareResourceRepository.findAll();
        List<SwaggerResource> list = new ArrayList<SwaggerResource>();

        for(MobicareResource resource : resources){
            StringBuilder location = new StringBuilder();
            location.append("/docs/api/")
                    .append(resource.getId());
            SwaggerResource r = new SwaggerResource();
            r.setLocation(location.toString());
            r.setName(resource.getGroupName());
            r.setSwaggerVersion(resource.getSwaggerVersion());
            list.add(r);
        }
        return ResponseEntity.ok(list);
    }

    @ResponseBody
    @RequestMapping(value = "/docs/api/{api}",method = RequestMethod.GET)
    public ResponseEntity<?> getAPIDocs(@PathVariable("api") Long apiId){

        MobicareResource resource = mobicareResourceRepository.findOne(apiId);

        if(resource==null){
            return ResponseEntity.badRequest().body("Resource unavailable");
        }
        try{

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(resource.getFullUrl(),String.class, new Object[]{});
            return ResponseEntity.ok(responseEntity.getBody());
        }catch (HttpClientErrorException e){
            e.printStackTrace();
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        }

    }

}
