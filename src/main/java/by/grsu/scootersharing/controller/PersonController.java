package by.grsu.scootersharing.controller;

import by.grsu.scootersharing.dto.PersonDto;
import by.grsu.scootersharing.model.Person;
import by.grsu.scootersharing.model.Role;
import by.grsu.scootersharing.service.PersonService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("scooter-sharing/api")
public class PersonController {

    private final PersonService personService;
    private final ObjectMapper mapper;
    
    public PersonController(PersonService personService, ObjectMapper mapper) {
        this.personService = personService;
        this.mapper = mapper;
    }

    @GetMapping("/user")
    public ResponseEntity<List<Person>> getPersons() {
        return ResponseEntity.ok().body(personService.getPersons());
    }

    @GetMapping("/user/{personId}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable String personId){
        long id = Long.parseLong(personId);
        return ResponseEntity.ok().body(personService.getPersonById(id));
    }

    @GetMapping("/user/username")
    public ResponseEntity<PersonDto> getPersonByEmail(@RequestParam String username){
        return ResponseEntity.ok().body(personService.getPersonByUsername(username));
    }

    @PostMapping("/user/create")
    public ResponseEntity<PersonDto> addPerson(@RequestBody String requestJson) {
        try {
            PersonDto dto = mapper.readValue(requestJson, PersonDto.class);
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("scooter-sharing/api/user/create").toUriString());
            return ResponseEntity.created(uri).body(personService.create(dto));
        } catch (JsonProcessingException e) {
            log.info("Exception json processing "+e.getMessage());
            throw new RuntimeException();
        }
    }

    @PutMapping("/user/update")
    public ResponseEntity<PersonDto> update(@RequestBody String requestJson) {
        try {
            PersonDto dto = mapper.readValue(requestJson, PersonDto.class);
            return ResponseEntity.ok().body(personService.update(dto));
        } catch (JsonProcessingException e) {
            log.info("Exception json processing "+e.getMessage());
            throw new RuntimeException();
        }
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                PersonDto user = personService.getPersonByUsername(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))//10 minutes
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception){
                response.setHeader("error",exception.getMessage());
                response.setStatus(FORBIDDEN.value());
//                    response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
        else{
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
