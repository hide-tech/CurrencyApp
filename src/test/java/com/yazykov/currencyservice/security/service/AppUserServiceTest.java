package com.yazykov.currencyservice.security.service;

import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.security.appuser.AppUser;
import com.yazykov.currencyservice.security.appuser.AppUserRole;
import com.yazykov.currencyservice.security.dto.AddValueRequest;
import com.yazykov.currencyservice.security.dto.ChangeBaseRequest;
import com.yazykov.currencyservice.security.mappers.AppUserAdminResponseMapper;
import com.yazykov.currencyservice.security.mappers.AppUserResponseMapper;
import com.yazykov.currencyservice.security.repository.AppUserRepository;
import com.yazykov.currencyservice.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock
    private AppUserRepository repository;
    @Mock
    private CurrencyService currencyService;
    private AppUserAdminResponseMapper adminMapper = Mappers.getMapper(AppUserAdminResponseMapper.class);
    private AppUserResponseMapper mapper = Mappers.getMapper(AppUserResponseMapper.class);

    private AppUserService service;

    @BeforeEach
    void setUp() {
        service = new AppUserService(repository, adminMapper, mapper, currencyService);
    }

    @Test
    void loadUserByUsername() {
        //init
        Optional<AppUser> userOpt = Optional.of(new AppUser(18L, "user", "user", "user@user.com", AppUserRole.ROLE_USER,
                false, true, "USD", new BigDecimal("100")));
        Mockito.doReturn(userOpt)
                .when(repository)
                .findByUsername("user");
        //when
        service.loadUserByUsername("user");
        //then
        verify(repository, Mockito.times(1)).findByUsername("user");
    }

    @Test
    void loadAllUsers() {
        //when
        service.loadAllUsers();
        //then
        verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    void loadUserById() {
        //when
        service.loadUserById(13L);
        //then
        verify(repository).getById(13L);
    }

    @Test
    void banUser() {
        //init
        Mockito.doReturn(new AppUser())
                .when(repository)
                .getById(14L);
        //when
        service.banUser(14L);
        //then
        verify(repository, Mockito.times(1)).getById(14L);
        Mockito.verify(repository, Mockito.times(1)).save(repository.getById(14L));

        assertTrue(repository.getById(14L).getBanned());
    }

    @Test
    void unbanUser() {
        //init
        Mockito.doReturn(new AppUser())
                .when(repository)
                .getById(15L);
        //when
        service.unbanUser(15L);
        //then
        verify(repository, Mockito.times(1)).getById(15L);
        Mockito.verify(repository, Mockito.times(1)).save(repository.getById(15L));

        assertEquals(false, repository.getById(15L).getBanned());
    }

    @Test
    void changeBaseCurrency() {
        //given
        Mockito.doReturn(new AppUser(12L, "user", "user", "user@user.com", AppUserRole.ROLE_USER,
                        false, true, "EUR", new BigDecimal("100")))
                .when(repository)
                .getById(12L);
        Mockito.doReturn(new CurrencyResponse(LocalDateTime.now(), Map.of("USD", new BigDecimal("1.0"),
                        "EUR", new BigDecimal("0.8"))))
                .when(currencyService)
                .getLatestCurrency();
        //when
        service.changeBaseCurrency(new ChangeBaseRequest(12L, "USD"));
        //then
        Mockito.verify(repository, Mockito.times(1)).getById(12L);
        Mockito.verify(currencyService, Mockito.times(1)).getLatestCurrency();
        Mockito.verify(repository, Mockito.times(1)).save(
                Mockito.any(AppUser.class)
        );
        assertEquals("USD",repository.getById(12L).getBaseCurrency());
        assertEquals(new BigDecimal("125.0"), repository.getById(12L).getAmount());

    }

    @Test
    void changeAmountCurrency() {
        //given
        Mockito.doReturn(new AppUser(18L, "user", "user", "user@user.com", AppUserRole.ROLE_USER,
                        false, true, "EUR", new BigDecimal("100")))
                .when(repository)
                .getById(20L);
        Mockito.doReturn(new CurrencyResponse(LocalDateTime.now(), Map.of("USD", new BigDecimal("1.0"),
                        "EUR", new BigDecimal("0.8"))))
                .when(currencyService)
                .getLatestCurrency();
        //when
        service.changeAmountCurrency(new AddValueRequest(20L, "USD", new BigDecimal("150")));
        //then
        verify(repository, Mockito.times(1)).getById(20L);
        Mockito.verify(repository, Mockito.times(1)).save(repository.getById(20L));

        assertEquals("USD", repository.getById(20L).getBaseCurrency());
        assertEquals(new BigDecimal("275.0"), repository.getById(20L).getAmount());
    }
}