package com.example.togepi.service;

import com.example.togepi.model.User;
import com.example.togepi.repository.UserRepository;
import com.example.togepi.service.impl.DefaultOpenCSVService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class OpenCSVServiceTest {

    @InjectMocks
    private OpenCSVService openCSVService = new DefaultOpenCSVService();

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenUsers_afterWritingToCSV_thenReadCorrectResult() throws Exception {
        final List<User> users = new ArrayList<>();
        users.add(new User("Yonatan", "yonatan@hotmail.com"));
        users.add(new User("Yesika", "yesika@hotmail.com"));

        when(userRepository.findAll()).thenReturn(users);
        openCSVService.writeFromObject();

        final List<User> resUsers = openCSVService.readToObject();
        assertEquals(resUsers.size(), users.size());
    }
}
