package com.example.togepi.batch;

import com.example.togepi.dto.csv.UserCsvDto;
import org.springframework.batch.item.ItemProcessor;

public class UserCsvItemProcessor implements ItemProcessor<UserCsvDto, UserCsvDto> {

    @Override
    public UserCsvDto process(final UserCsvDto userCsvDto) throws Exception {
        return userCsvDto;
    }
}
