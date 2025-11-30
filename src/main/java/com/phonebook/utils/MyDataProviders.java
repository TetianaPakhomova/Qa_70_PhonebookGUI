package com.phonebook.utils;

import com.phonebook.models.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProviders {

    @DataProvider
    public Iterator<Object[]> addNewContact(){

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Karl", "Adam", "123456789025", "Test@gm.com", "Berlin", "QA"});
        list.add(new Object[]{"Kris", "Adam", "123456789016", "Test@gm.com", "Berlin", "QA"});
        list.add(new Object[]{"Alex", "Adam", "123456789012375", "Test@gm.com", "Berlin", "QA"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addNewContactFromCsv() throws IOException {
        List<Object[]>list= new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new FileReader(new File("src/test/resources/ContactData.csv")));
        String line = reader.readLine();
        while (line!=null) {
            String[] split = line.split(",");
            list.add(new Object[]{new Contact().setName(split[0])
                    .setLastName(split[1])
                    .setPhone(split[2])
                    .setEmail(split[3])
                    .setAddress(split[4])
                    .setDescription(split[5])});
            line = reader.readLine();
        }

        return list.iterator();
    }
}
