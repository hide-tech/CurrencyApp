package com.yazykov.currencyservice.security.mappers;

import com.yazykov.currencyservice.security.appuser.AppUser;
import com.yazykov.currencyservice.security.appuser.AppUserRole;
import com.yazykov.currencyservice.security.dto.AppUserResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AppUserResponseMapperTest {

    AppUserResponseMapper mapper = Mappers.getMapper(AppUserResponseMapper.class);

    @Test
    void appUserToAppUserResponse_thenOk() {
        //given
        AppUser user = new AppUser("user", "12345", "user@user.com",
                AppUserRole.ROLE_USER, false, true, "USD", new BigDecimal("100"));
        //when
        AppUserResponse response = mapper.appUserToAppUserResponse(user);
        //then
        assertEquals(response.getUsername(), user.getUsername());
        assertEquals(response.getEmail(), user.getEmail());
        assertEquals(response.getBaseCurrency(), user.getBaseCurrency());
        assertEquals(response.getAmount(), user.getAmount());
    }
}