package com.yazykov.currencyservice.security.controller;

import com.yazykov.currencyservice.security.dto.RegistrationResponse;
import com.yazykov.currencyservice.security.service.AppUserService;
import com.yazykov.currencyservice.security.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewUser(@RequestBody RegistrationResponse response){
        registrationService.register(response);
    }
}
