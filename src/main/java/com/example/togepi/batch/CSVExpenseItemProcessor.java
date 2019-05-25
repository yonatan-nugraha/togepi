package com.example.togepi.batch;

import com.example.togepi.model.csv.CSVUser;
import org.springframework.batch.item.ItemProcessor;

public class CSVExpenseItemProcessor implements ItemProcessor<CSVUser, CSVUser> {

    @Override
    public CSVUser process(final CSVUser csvUser) throws Exception {
        final CSVUser csvUser1 = new CSVUser();
        csvUser1.setUsername(csvUser.getUsername());
        csvUser1.setPassword(csvUser.getPassword());

        return csvUser1;
    }
}
