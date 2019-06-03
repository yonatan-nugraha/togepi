package com.example.togepi.batch;

import com.example.togepi.model.csv.CSVUser;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

public class CSVUserItemReader extends FlatFileItemReader<CSVUser> {

    private static final String CSV_INPUT_FILE_PATH = "./csvs/input.csvs";

    public CSVUserItemReader() {
        this.setResource(new FileSystemResource(CSV_INPUT_FILE_PATH));
        this.setLineMapper(new DefaultLineMapper<CSVUser>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "name", "description", "amount" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CSVUser>() {{
                setTargetType(CSVUser.class);
            }});
        }});
    }
}