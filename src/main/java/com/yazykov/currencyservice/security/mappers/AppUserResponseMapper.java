package com.yazykov.currencyservice.security.mappers;

import com.yazykov.currencyservice.security.appuser.AppUser;
import com.yazykov.currencyservice.security.dto.AppUserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserResponseMapper {

    AppUserResponse appUserToAppUserResponse(AppUser user);
}
