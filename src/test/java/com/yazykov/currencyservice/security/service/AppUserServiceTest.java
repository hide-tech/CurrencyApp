package com.yazykov.currencyservice.security.service;

import com.yazykov.currencyservice.security.mappers.AppUserAdminResponseMapper;
import com.yazykov.currencyservice.security.mappers.AppUserResponseMapper;
import com.yazykov.currencyservice.security.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock
    private AppUserRepository repository;
    private AppUserAdminResponseMapper adminMapper = Mappers.getMapper(AppUserAdminResponseMapper.class);
    private AppUserResponseMapper mapper = Mappers.getMapper(AppUserResponseMapper.class);

    private AppUserService service;

    @BeforeEach
    void setUp() {
        service = new AppUserService(repository, adminMapper, mapper);
    }

    @Test
    void loadUserByUsername() {
//        //when
//        service.loadUserByUsername("");
//        //then
//        verify(repository).findByUsername("");
    }

    @Test
    void loadAllUsers() {
        //when
        service.loadAllUsers();
        //then
        verify(repository).findAll();
    }

    @Test
    void loadUserById() {
        //when
        service.loadUserById(14L);
        //then
        verify(repository).getById(14L);
    }

    @Test
    void banUser() {
        //when
        service.loadUserById(14L);
        //then
        verify(repository).getById(14L);
    }

    @Test
    void unbanUser() {
        //when
        service.loadUserById(14L);
        //then
        verify(repository).getById(14L);
    }

    @Test
    void changeBaseCurrency() {
        //when
        service.loadUserById(14L);
        //then
        verify(repository).getById(14L);
    }

    @Test
    void changeAmountCurrency() {
        //when
        service.loadUserById(14L);
        //then
        verify(repository).getById(14L);
    }
}