package com.yazykov.currencyservice.security.mappers;

import com.yazykov.currencyservice.security.appuser.AppUser;
import com.yazykov.currencyservice.security.dto.AppUserAdminResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserAdminResponseMapper {

    AppUserAdminResponse AppUserToAppUserAdminResponse(AppUser user);
}
