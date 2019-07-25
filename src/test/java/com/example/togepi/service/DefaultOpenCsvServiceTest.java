package com.example.togepi.service;

import com.example.togepi.entity.User;
import com.example.togepi.mapper.MainMapper;
import com.example.togepi.repository.UserRepository;
import com.example.togepi.service.impl.DefaultOpenCsvService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DefaultOpenCsvServiceTest {

    @TestConfiguration
    static class OpenCsvServiceTestContextConfiguration {

        @Bean
        public OpenCsvService openCsvService() {
            return new DefaultOpenCsvService();
        }

        @Bean
        public MainMapper mainMapper() {
            return new MainMapper();
        }
    }

    @Autowired
    private OpenCsvService openCsvService;

    @MockBean
    private MainMapper mapper;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void givenValidUsers_afterWritingToCsv_thenReturnCorrectResult() throws Exception {
        final List<User> users = new ArrayList<>();
        users.add(new User("Yonatan", "yonatan@hotmail.com"));
        users.add(new User("Yesika", "yesika@hotmail.com"));

        when(userRepository.findAll()).thenReturn(users);
        openCsvService.writeFromObject();

        final List<User> resUsers = openCsvService.readToObject();
        assertEquals(resUsers.size(), users.size());
    }
}
