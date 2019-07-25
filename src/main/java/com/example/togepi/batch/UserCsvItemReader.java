package com.example.togepi.batch;

import com.example.togepi.dto.csv.UserCsvDto;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

public class UserCsvItemReader extends FlatFileItemReader<UserCsvDto> {

    private static final String CSV_INPUT_FILE_PATH = "./csvs/input.csvs";

    public UserCsvItemReader() {
        this.setResource(new FileSystemResource(CSV_INPUT_FILE_PATH));
        this.setLineMapper(new DefaultLineMapper<UserCsvDto>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "name", "description", "amount" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<UserCsvDto>() {{
                setTargetType(UserCsvDto.class);
            }});
        }});
    }
}