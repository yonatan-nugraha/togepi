package com.example.togepi.mapper;

import com.example.togepi.model.User;
import com.example.togepi.model.csv.CSVUser;
import ma.glasnost.orika.MapperFacade;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainMapperTest {

    @Test
    public void givenUser_thenReturnCorrectCSVUser() {
        final String username = "Yon";
        final String email = "yon@hotmail.com";
        final User user = new User(username, email);

        final MapperFacade mapper = new MainMapper();
        final CSVUser csvUser = mapper.map(user, CSVUser.class);

        assertEquals(csvUser.getUsername(), username);
    }

    @Test
    public void givenCSVUser_thenReturnCorrectUser() {
        final String username = "Yon";
        final String email = "yon@hotmail.com";
        final CSVUser csvUser = new CSVUser(username, email);

        final MapperFacade mapper = new MainMapper();
        final User user = mapper.map(csvUser, User.class);

        assertEquals(user.getUsername(), username);
    }
}
