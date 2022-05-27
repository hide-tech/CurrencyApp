package com.yazykov.currencyservice.security.mappers;

import com.yazykov.currencyservice.security.appuser.AppUser;
import com.yazykov.currencyservice.security.dto.AppUserResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-27T10:56:50+0300",
    comments = "version: 1.5.0.RC1, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class AppUserResponseMapperImpl implements AppUserResponseMapper {

    @Override
    public AppUserResponse appUserToAppUserResponse(AppUser user) {
        if ( user == null ) {
            return null;
        }

        AppUserResponse appUserResponse = new AppUserResponse();

        appUserResponse.setUsername( user.getUsername() );
        appUserResponse.setEmail( user.getEmail() );
        appUserResponse.setBaseCurrency( user.getBaseCurrency() );
        appUserResponse.setAmount( user.getAmount() );

        return appUserResponse;
    }
}
