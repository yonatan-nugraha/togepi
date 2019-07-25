package com.example.togepi.batch;

import com.example.togepi.dto.csv.UserCsvDto;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

public class UserCsvItemWriter implements ItemWriter<UserCsvDto> {

    private static final String CSV_FILE_PATH = "./csvs/output.csvs";

    @Override
    public void write(final List<? extends UserCsvDto> items) throws Exception {
        final FlatFileItemWriter<UserCsvDto> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource(CSV_FILE_PATH));
        writer.setAppendAllowed(true);
        writer.setLineAggregator(new DelimitedLineAggregator<UserCsvDto>() {{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<UserCsvDto>() {{
                setNames(new String[]{"name", "description", "amount"});
            }});
        }});
        writer.open(new ExecutionContext());
        writer.write(items);
        writer.close();
    }
}
