package com.example.togepi.mapper;

import com.example.togepi.entity.User;
import com.example.togepi.dto.csv.UserCsvDto;
import com.example.togepi.service.OpenCsvService;
import com.example.togepi.service.impl.DefaultOpenCsvService;
import ma.glasnost.orika.MapperFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MainMapperTest {

    @TestConfiguration
    static class MainMapperTestContextConfiguration {

        @Bean
        public MainMapper mainMapper() {
            return new MainMapper();
        }
    }

    @Autowired
    private MainMapper mapper;

    @Test
    public void givenUser_thenReturnCorrectUserCsvDto() {
        final String username = "Yon";
        final String email = "yon@hotmail.com";
        final User user = new User(username, email);

        final UserCsvDto userCsvDto = mapper.map(user, UserCsvDto.class);

        assertEquals(userCsvDto.getUsername(), username);
    }

    @Test
    public void givenUserCsvDto_thenReturnCorrectUser() {
        final String username = "Yon";
        final String email = "yon@hotmail.com";
        final UserCsvDto userCsvDto = new UserCsvDto(username, email);

        final MapperFacade mapper = new MainMapper();
        final User user = mapper.map(userCsvDto, User.class);

        assertEquals(user.getUsername(), username);
    }
}
