package com.example.togepi.mapper;

import com.example.togepi.entity.User;
import com.example.togepi.dto.csv.UserCsvDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class UserCsvMapper extends CustomMapper<User, UserCsvDto> {

    @Override
    public void mapAtoB(User a, UserCsvDto b, MappingContext context) {
        b.setUsername(a.getUsername());
        b.setEmail(a.getEmail());
    }

    @Override
    public void mapBtoA(UserCsvDto a, User b, MappingContext context) {
        b.setUsername(a.getUsername());
        b.setEmail(a.getEmail());
    }
}
