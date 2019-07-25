package com.example.togepi.dto.csv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class UserCsvDto {

    @CsvBindByPosition(position = 0)
    private String username;

    @CsvBindByPosition(position = 1)
    private String email;
}
