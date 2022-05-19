package com.yazykov.currencyservice.security.mappers;

import com.yazykov.currencyservice.security.appuser.AppUser;
import com.yazykov.currencyservice.security.appuser.AppUserRole;
import com.yazykov.currencyservice.security.dto.AppUserAdminResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AppUserAdminResponseMapperTest {

    AppUserAdminResponseMapper mapper = Mappers.getMapper(AppUserAdminResponseMapper.class);

    @Test
    void appUserToAppUserAdminResponse_thenOk() {
        //given
        AppUser user = new AppUser("user", "12345", "user@user.com",
                AppUserRole.ROLE_USER, false, true, "USD", new BigDecimal("100"));
        //when
        AppUserAdminResponse response = mapper.AppUserToAppUserAdminResponse(user);
        //then
        assertEquals(response.getId(), user.getId());
        assertEquals(response.getUsername(), user.getUsername());
        assertEquals(response.getPassword(), user.getPassword());
        assertEquals(response.getEmail(), user.getEmail());
        assertEquals(response.getBanned(), user.getBanned());
        assertEquals(response.getEnabled(), user.getEnabled());
        assertEquals(response.getRole().toString(), user.getRole().toString());
        assertEquals(response.getBaseCurrency(), user.getBaseCurrency());
        assertEquals(response.getAmount(), user.getAmount());
    }
}