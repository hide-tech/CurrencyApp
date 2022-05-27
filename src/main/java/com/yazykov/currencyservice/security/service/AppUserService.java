package com.yazykov.currencyservice.security.service;

import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.security.appuser.AppUser;
import com.yazykov.currencyservice.security.dto.AddValueRequest;
import com.yazykov.currencyservice.security.dto.AppUserAdminResponse;
import com.yazykov.currencyservice.security.dto.AppUserResponse;
import com.yazykov.currencyservice.security.dto.ChangeBaseRequest;
import com.yazykov.currencyservice.security.mappers.AppUserAdminResponseMapper;
import com.yazykov.currencyservice.security.mappers.AppUserResponseMapper;
import com.yazykov.currencyservice.security.repository.AppUserRepository;
import com.yazykov.currencyservice.service.CurrencyService;
import com.yazykov.currencyservice.throwable.ExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserService implements UserDetailsService {

    private final AppUserRepository repository;

    private final AppUserAdminResponseMapper adminMapper;

    private final AppUserResponseMapper mapper;

    private final CurrencyService service;

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
        AppUser user;
        try {
            user = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        } catch (EntityNotFoundException ex){
            throw new ExceptionHandler(String.format("User with id %d not found", id), HttpStatus.NOT_FOUND);
        }
        return mapper.appUserToAppUserResponse(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public String banUser(Long id) {
        AppUser user = repository.getById(id);
        user.setBanned(true);
        repository.save(user);
        return String.format("User with id %s has been successfully banned", id);
    }

    @Transactional(rollbackFor = Exception.class)
    public String unbanUser(Long id) {
        AppUser user = repository.getById(id);
        user.setBanned(false);
        repository.save(user);
        return String.format("User with id %s has been successfully unbanned", id);
    }

    @Transactional(rollbackFor = Exception.class)
    public String changeBaseCurrency(ChangeBaseRequest change) {
        AppUser user = repository.getById(change.getId());

        CurrencyResponse currency = service.getLatestCurrency();
        BigDecimal currentAmount = user.getAmount();
        BigDecimal from = currency.getRates().get(user.getBaseCurrency());
        BigDecimal to = currency.getRates().get(change.getBaseTo());
        BigDecimal resultDev = currentAmount.divide(from, RoundingMode.HALF_UP);
        BigDecimal result = resultDev.multiply(to);

        user.setBaseCurrency(change.getBaseTo());
        user.setAmount(result);

        repository.save(user);
        return "Your base currency has been successfully changed";
    }

    @Transactional(rollbackFor = Exception.class)
    public void changeAmountCurrency(AddValueRequest request) {
        AppUser user = repository.getById(request.getId());

        CurrencyResponse currency = service.getLatestCurrency();
        BigDecimal currentAmount = user.getAmount();
        BigDecimal from = currency.getRates().get(user.getBaseCurrency());
        BigDecimal to = currency.getRates().get(request.getBase());
        BigDecimal resultPass = currentAmount.divide(from, RoundingMode.HALF_UP).multiply(to);
        BigDecimal result = resultPass.add(request.getValue());

        user.setBaseCurrency(request.getBase());
        user.setAmount(result);
        repository.save(user);
        log.info("Successfully amount added");
    }
}
