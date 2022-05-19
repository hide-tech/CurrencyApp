package com.yazykov.currencyservice.security.service;

import com.yazykov.currencyservice.security.appuser.AppUser;
import com.yazykov.currencyservice.security.dto.AddValueRequest;
import com.yazykov.currencyservice.security.dto.AppUserAdminResponse;
import com.yazykov.currencyservice.security.dto.AppUserResponse;
import com.yazykov.currencyservice.security.dto.ChangeBaseRequest;
import com.yazykov.currencyservice.security.mappers.AppUserAdminResponseMapper;
import com.yazykov.currencyservice.security.mappers.AppUserResponseMapper;
import com.yazykov.currencyservice.security.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository repository;

    private final AppUserAdminResponseMapper adminMapper;

    private final AppUserResponseMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("USer with username %s not found", username))
        );
    }

    public List<AppUserAdminResponse> loadAllUsers() {
        List<AppUser> users = repository.findAll();
        return users.stream().map(adminMapper::AppUserToAppUserAdminResponse).collect(Collectors.toList());
    }

    public AppUserResponse loadUserById(Long id) {
        AppUser user = repository.getById(id);
        return mapper.appUserToAppUserResponse(user);
    }

    @Transactional
    public String banUser(Long id) {
        AppUser user = repository.getById(id);
        user.setBanned(true);
        repository.save(user);
        return String.format("User with id %s has been successfully banned", id);
    }

    @Transactional
    public String unbanUser(Long id) {
        AppUser user = repository.getById(id);
        user.setBanned(false);
        repository.save(user);
        return String.format("User with id %s has been successfully unbanned", id);
    }

    @Transactional
    public String changeBaseCurrency(ChangeBaseRequest change) {
        AppUser user = repository.getById(change.getId());
        user.setBaseCurrency(change.getBaseTo());
        user.setAmount(change.getNewAmount());
        repository.save(user);
        return "Your base currency has been successfully changed";
    }

    @Transactional
    public void changeAmountCurrency(AddValueRequest request) {
        AppUser user = repository.getById(request.getId());
        BigDecimal result = user.getAmount().add(request.getValue());
        user.setAmount(result);
        repository.save(user);
    }
}
