package com.example.togepi.model.csv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class CSVUser {

    @CsvBindByPosition(position = 0)
    private String username;

    @CsvBindByPosition(position = 1)
    private String email;

    public CSVUser() {}

    public CSVUser(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
