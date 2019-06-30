package com.example.togepi.mapper;

import com.example.togepi.model.User;
import com.example.togepi.model.csv.CSVUser;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class CSVUserMapper extends CustomMapper<User, CSVUser> {

    @Override
    public void mapAtoB(User a, CSVUser b, MappingContext context) {
        b.setUsername(a.getUsername());
        b.setEmail(a.getEmail());
    }

    @Override
    public void mapBtoA(CSVUser a, User b, MappingContext context) {
        b.setUsername(a.getUsername());
        b.setEmail(a.getEmail());
    }
}
