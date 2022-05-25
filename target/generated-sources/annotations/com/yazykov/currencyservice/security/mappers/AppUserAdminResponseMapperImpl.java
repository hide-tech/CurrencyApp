package com.yazykov.currencyservice.security.mappers;

import com.yazykov.currencyservice.security.appuser.AppUser;
import com.yazykov.currencyservice.security.dto.AppUserAdminResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-25T10:34:11+0300",
    comments = "version: 1.5.0.RC1, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class AppUserAdminResponseMapperImpl implements AppUserAdminResponseMapper {

    @Override
    public AppUserAdminResponse AppUserToAppUserAdminResponse(AppUser user) {
        if ( user == null ) {
            return null;
        }

        AppUserAdminResponse appUserAdminResponse = new AppUserAdminResponse();

        appUserAdminResponse.setId( user.getId() );
        appUserAdminResponse.setUsername( user.getUsername() );
        appUserAdminResponse.setPassword( user.getPassword() );
        appUserAdminResponse.setEmail( user.getEmail() );
        if ( user.getRole() != null ) {
            appUserAdminResponse.setRole( user.getRole().name() );
        }
        appUserAdminResponse.setBanned( user.getBanned() );
        appUserAdminResponse.setEnabled( user.getEnabled() );
        appUserAdminResponse.setBaseCurrency( user.getBaseCurrency() );
        appUserAdminResponse.setAmount( user.getAmount() );

        return appUserAdminResponse;
    }
}
