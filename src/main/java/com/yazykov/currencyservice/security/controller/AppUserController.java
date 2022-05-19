package com.yazykov.currencyservice.security.controller;

import com.yazykov.currencyservice.security.dto.*;
import com.yazykov.currencyservice.security.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AppUserAdminResponse> getAllUsers(){
        log.info("into appUserController method getAllUsers");
        return appUserService.loadAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public AppUserResponse getUserById(@PathVariable("id") Long id){
        log.info("into appUserController method getUserById");
        return appUserService.loadUserById(id);
    }

    @PostMapping("/{id}/change")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String changeBase(@RequestBody ChangeBaseRequest change){
        log.info("into appUserController method changeBase");
        return appUserService.changeBaseCurrency(change);
    }

    @PostMapping("/ban")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String banUserById(@RequestBody BanRequest request){
        log.info("into appUserController method banUserById");
        return appUserService.banUser(request.getId());
    }

    @PostMapping("/unban")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String unbanUserById(@RequestBody BanRequest request){
        log.info("into appUserController method unbanUserById");
        return appUserService.unbanUser(request.getId());
    }

    @PostMapping("/amount")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changeAmount(@RequestBody AddValueRequest request){
        log.info("into appUserController method changeAmount");
        appUserService.changeAmountCurrency(request);
    }
}
