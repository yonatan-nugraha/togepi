package com.example.togepi.service.impl;

import com.example.togepi.exception.ResourceNotFoundException;
import com.example.togepi.model.csv.CSVUser;
import com.example.togepi.model.User;
import com.example.togepi.repository.UserRepository;
import com.example.togepi.service.OpenCSVService;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultOpenCSVService implements OpenCSVService {

    private static final String CSV_FILE_PATH = "./csvs/user.csvs";

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<String[]> read() throws ResourceNotFoundException {
        try {
            final Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
            final CSVReader csvReader = new CSVReader(reader);
            final List<String[]> list = new ArrayList<>();

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                list.add(line);
            }

            // read all lines at once
            // final List<String[]> list = csvReader.readAll();

            reader.close();
            csvReader.close();
            return list;
        } catch(Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("File " + CSV_FILE_PATH + " not found");
        }
    }

    @Override
    public List<User> readToObject() throws ResourceNotFoundException {
        try {
            final Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
            final CsvToBean<CSVUser> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVUser.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            final List<CSVUser> csvUsers = csvToBean.parse();
            final List<User> users = csvUsers.stream().map(csvUser -> mapCSVUserToUser(csvUser)).collect(Collectors.toList());

            reader.close();
            return users;
        } catch(Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("File " + CSV_FILE_PATH + " not found");
        }
    }

    @Override
    public List<String[]> write() throws ResourceNotFoundException {
        try {
            final Writer writer = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH));
            final CSVWriter csvWriter = new CSVWriter(writer);

            final List<String[]> list = new ArrayList<>();
            list.add(new String[]{"Name", "Email", "Phone", "Country"});
            list.add(new String[]{"Yonatan", "yonatan@hotmail.com", "081932058111", "Indonesia"});
            list.add(new String[]{"Yesika", "yesika@hotmail.com", "081932058222", "Indonesia"});

            list.forEach((String[] line) -> {
                csvWriter.writeNext(line);
            });

            // write all lines at once
            // csvWriter.writeAll(list);

            writer.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("File " + CSV_FILE_PATH + " not found");
        }
    }

    @Override
    public List<User> writeFromObject() throws ResourceNotFoundException {
        try {
            final Writer writer = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH));
            final StatefulBeanToCsv<CSVUser> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            final List<User> users = userRepository.findAll();
            final List<CSVUser> csvUsers = users.stream().map(user -> mapUserToCSVUser(user)).collect(Collectors.toList());

            beanToCsv.write(csvUsers);

            writer.close();
            return users;
        } catch(Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("File " + CSV_FILE_PATH + " not found");
        }
    }

    private CSVUser mapUserToCSVUser(User user) {
        final CSVUser csvUser = new CSVUser();
        csvUser.setUsername(user.getUsername());
        csvUser.setPassword(user.getPassword());

        return csvUser;
    }

    private User mapCSVUserToUser(CSVUser csvUser) {
        final User user = new User();
        user.setUsername(csvUser.getUsername());
        user.setPassword(csvUser.getPassword());

        return user;
    }
}
