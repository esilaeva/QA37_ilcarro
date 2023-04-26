package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    @DataProvider
    public static Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> registerUserFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/reg4.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] all = line.split(",");
            list.add(new Object[]{new User().setFirstName(all[0]).setLastName(all[1]).setEmail(all[2]).setPassword(all[3])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> loginDataFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/login.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] all = line.split(",");
            list.add(new Object[]{new User().setEmail(all[2]).setPassword(all[3])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> loginData() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"b.snyder@gmail.com", "Tt12345$"});
        list.add(new Object[]{"b.snyder@gmail.com", "Tt12345$"});

        return list.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> loginModels() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().setFirstName("").setLastName("Snyder").setEmail("b.snyder@gmail.com").setPassword("Tt12345$")});
        list.add(new Object[]{new User().setEmail("b.snyder@gmail.com").setPassword("Tt12345$")});

        return list.iterator();
    }

}
