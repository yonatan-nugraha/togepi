package com.example.togepi.batch;

import com.example.togepi.model.csv.CSVUser;
import org.springframework.batch.item.ItemProcessor;

public class CSVUserItemProcessor implements ItemProcessor<CSVUser, CSVUser> {

    @Override
    public CSVUser process(final CSVUser csvUser) throws Exception {
        return csvUser;
    }
}
