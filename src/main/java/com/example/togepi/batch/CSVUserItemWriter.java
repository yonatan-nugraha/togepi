package com.example.togepi.batch;

import com.example.togepi.model.csv.CSVUser;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

public class CSVUserItemWriter implements ItemWriter<CSVUser> {

    private static final String CSV_FILE_PATH = "./csvs/output.csvs";

    @Override
    public void write(final List<? extends CSVUser> items) throws Exception {
        final FlatFileItemWriter<CSVUser> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource(CSV_FILE_PATH));
        writer.setAppendAllowed(true);
        writer.setLineAggregator(new DelimitedLineAggregator<CSVUser>() {{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<CSVUser>() {{
                setNames(new String[]{"name", "description", "amount"});
            }});
        }});
        writer.open(new ExecutionContext());
        writer.write(items);
        writer.close();
    }
}
