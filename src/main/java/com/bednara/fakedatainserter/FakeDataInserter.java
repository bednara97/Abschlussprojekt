package com.bednara.fakedatainserter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bednara.application.DBConnectionHandler;
import com.bednara.enums.EPaymentType;
import com.bednara.enums.ERoomCategory;
import com.bednara.enums.ERoomStatus;
import com.github.javafaker.Faker;

public class FakeDataInserter {

    private static Connection connection = DBConnectionHandler.getConnection();
    private static Faker faker = new Faker();
    private static Random random = new Random();

    public static void insertEmployees() {

        List<String> availablePersonIDNumbers = getIDNumbersFromDatabase();
        List<String> jobTitles = new ArrayList<String>();
        jobTitles.add("Front Desk Agent");
        jobTitles.add("Front Desk Manager");

        try {
            for (int i = 3; i < 11; ++i) {
                String personIDNumber = availablePersonIDNumbers.get(random.nextInt(availablePersonIDNumbers.size()));
                availablePersonIDNumbers.remove(personIDNumber);

                PreparedStatement stmt = connection
                        .prepareStatement("INSERT INTO EMPLOYEE VALUES (default, ?, ?, ?, ?)");
                stmt.setString(1, jobTitles.get(random.nextInt(jobTitles.size())));
                stmt.setDouble(2, faker.number().randomDouble(2, 1500, 3000));
                stmt.setInt(3, i);
                stmt.setLong(4, Long.parseLong(personIDNumber));
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertGuests() {
        List<String> availablePersonIDNumbers = getIDNumbersFromDatabase();

        try {
            for (int i = 0; i < 50; ++i) {
                String personIDNumber = availablePersonIDNumbers.get(random.nextInt(availablePersonIDNumbers.size()));
                availablePersonIDNumbers.remove(personIDNumber);

                PreparedStatement stmt = connection
                        .prepareStatement("INSERT INTO GUEST VALUES (?, ?)");
                stmt.setLong(1, generateNumbers(8));
                stmt.setLong(2, Long.parseLong(personIDNumber));
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertPersons() {
        try {
            for (int i = 0; i < 50; ++i) {
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();

                PreparedStatement stmt = connection
                        .prepareStatement("INSERT INTO PERSON VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                stmt.setLong(1, generateNumbers(10));
                stmt.setString(2, firstName);
                stmt.setString(3, lastName);
                stmt.setTimestamp(4, new java.sql.Timestamp((faker.date().birthday().getTime())));
                stmt.setString(5, faker.phoneNumber().phoneNumber());
                stmt.setString(6, generateMailAddress(firstName, lastName));
                stmt.setString(7, faker.address().streetName());
                stmt.setInt(8, faker.number().numberBetween(1, 500));
                stmt.setInt(9, faker.number().numberBetween(1, 50));
                stmt.setString(10, faker.address().zipCode());
                stmt.setString(11, faker.address().city());
                stmt.setString(12, faker.address().country());
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertRooms() {
        List<String> guestRooms = generateRoomsNumbers();

        try {
            for (int i = 0; i < 100; ++i) {
                String randomRoomNumber = guestRooms.get(random.nextInt(guestRooms.size()));
                ERoomCategory randomRoomCategory = getRandomValueFromEnum(ERoomCategory.class);
                guestRooms.remove(randomRoomNumber);

                PreparedStatement stmt = connection.prepareStatement("INSERT INTO ROOM VALUES (default, ?, ?, ?, ?)");
                stmt.setInt(1, Integer.parseInt(randomRoomNumber));
                stmt.setString(2, randomRoomCategory.toString());
                stmt.setString(3, getRandomValueFromEnum(ERoomStatus.class).toString());
                stmt.setDouble(4, randomRoomCategory.getCategoryPrice());
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static void insertReservations() {
        List<String> confirmationNumbers = generateConfirmationNumbers();
        List<String> roomNumbers = getRoomNumber();
        List<String> guestIDs = getGuestIDsFromDatabase();

        String sqlInsertReservationStatement = "INSERT INTO RESERVATION VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sqlInsertReservationStatement);

            for (int i = 0; i < 50; i++) {
                Timestamp arrivalDate = generateRandomArrivalDate();
                Timestamp departureDate = generateRandomDepartureDate(arrivalDate);

                String confirmationNumber = confirmationNumbers.get(random.nextInt(0, confirmationNumbers.size()));
                stmt.setString(1, confirmationNumber);
                confirmationNumbers.remove(confirmationNumber);

                stmt.setTimestamp(2, arrivalDate);
                stmt.setTimestamp(3, departureDate);

                String roomNumber = roomNumbers.get(random.nextInt(0, roomNumbers.size()));
                stmt.setInt(4, Integer.parseInt(roomNumber));
                roomNumbers.remove(roomNumber);

                stmt.setDouble(5, 500.0);

                stmt.setString(6, getRandomValueFromEnum(EPaymentType.class).toString());
                stmt.setString(7, faker.finance().creditCard());

                String guestID = guestIDs.get(random.nextInt(0, guestIDs.size()));
                stmt.setString(8, guestID);
                guestIDs.remove(guestID);

                stmt.setString(9, getRoomID(roomNumber));

                stmt.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static List<String> getGuestIDsFromDatabase() {
        List<String> availableGuestIDs = new ArrayList<String>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT GUEST_ID FROM GUEST");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                availableGuestIDs.add(resultSet.getString("GUEST_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableGuestIDs;
    }

    public static List<String> getIDNumbersFromDatabase() {
        List<String> availablePersonIDNumbers = new ArrayList<String>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT p.ID_NUMBER FROM EMPLOYEE e " +
                    "RIGHT JOIN PERSON p ON p.ID_NUMBER = e.ID_NUMBER " +
                    "LEFT JOIN GUEST g ON p.ID_NUMBER = g.ID_NUMBER WHERE EMPLOYEE_ID IS NULL AND GUEST_ID IS NULL;");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                availablePersonIDNumbers.add(resultSet.getString("ID_NUMBER"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availablePersonIDNumbers;
    }

    public static List<String> generateConfirmationNumbers() {
        List<String> generatedConfirmationNumbers = new ArrayList<String>();
        String generatedConfirmationNumber = "";
        final char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890".toCharArray();

        for (int i = 0; i < 50; ++i) {
            for (int j = 0; j < 15; ++j) {
                generatedConfirmationNumber += chars[random.nextInt(chars.length)];
            }
            generatedConfirmationNumbers.add(generatedConfirmationNumber);
            generatedConfirmationNumber = "";
        }

        return generatedConfirmationNumbers;
    }

    public static String generateMailAddress(String firstName, String lastName) {
        String mailAddress = "";

        List<String> hyphenList = new ArrayList<String>();
        hyphenList.add("-");
        hyphenList.add(".");
        hyphenList.add("_");

        List<String> mailNames = new ArrayList<String>();
        mailNames.add("@hotmail.com");
        mailNames.add("@outlook.com");
        mailNames.add("@yahoo.com");
        mailNames.add("@gmx.at");
        mailNames.add("@gmail.com");

        mailAddress += firstName.toLowerCase() + hyphenList.get(random.nextInt(hyphenList.size()))
                + lastName.toLowerCase() + mailNames.get(random.nextInt(mailNames.size()));

        return mailAddress;
    }

    public static long generateNumbers(int number) {
        String generatedNumber = String.valueOf(random.nextInt(1, 10));

        for (int i = 0; i < number; i++) {
            generatedNumber += random.nextInt(0, 10);
        }

        return Long.parseLong(generatedNumber);
    }

    public static List<String> getConfirmationNumbersFromGuests() {
        List<String> confirmationNumbers = new ArrayList<String>();
        String sqlGetConfirmationNumberStatement = "SELECT CONFIRMATION_NUMBER FROM GUEST;";

        try {
            PreparedStatement stmt = connection.prepareStatement(sqlGetConfirmationNumberStatement);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                confirmationNumbers.add(resultSet.getString("CONFIRMATION_NUMBER"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return confirmationNumbers;

    }

    public static Timestamp generateRandomArrivalDate() {
        long beginDate = Timestamp.valueOf("2022-11-11 00:00:00").getTime();
        long endDate = Timestamp.valueOf("2022-12-11 00:00:00").getTime();
        long diff = endDate - beginDate + 1;

        return new Timestamp(beginDate + (long) (Math.random() * diff));
    }

    public static Timestamp generateRandomDepartureDate(Timestamp arrivalDate) {
        long beginDate = arrivalDate.getTime();
        long maxDate = beginDate + (60 * 60 * 24 * 10) * 1000;
        long diff = maxDate - beginDate + 1;

        return new Timestamp(beginDate + (long) (Math.random() * diff));
    }

    public static List<String> generateRoomsNumbers() {
        List<String> rooms = new ArrayList<String>();
        int counter = 0;
        for (int i = 100; i < 1000; ++i) {
            if (rooms.size() == 99) {
                rooms.add(String.valueOf(999));
            }
            if (counter == 11) {
                i += 98;
                counter = 0;
            } else {
                rooms.add(String.valueOf(i));
                ++counter;
            }
        }

        return rooms;
    }

    public static List<String> getRoomNumber() {
        List<String> guestRooms = new ArrayList<String>();

        String sqlGetRoomsStatement = "SELECT ROOM_NUMBER FROM ROOM;";

        try {
            PreparedStatement stmt = connection.prepareStatement(sqlGetRoomsStatement);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                guestRooms.add(resultSet.getString("ROOM_NUMBER"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return guestRooms;
    }

    public static String getRoomID(String roomNumber) {
        String roomID = "";

        String sqlGetRoomsStatement = "SELECT ROOM_ID FROM ROOM WHERE ROOM_NUMBER = ?;";

        try {
            PreparedStatement stmt = connection.prepareStatement(sqlGetRoomsStatement);
            stmt.setString(1, roomNumber);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                roomID = resultSet.getString("ROOM_ID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return roomID;
    }

    public static <T extends Enum<?>> T getRandomValueFromEnum(Class<T> enumClass) {
        int x = random.nextInt(enumClass.getEnumConstants().length);
        return enumClass.getEnumConstants()[x];
    }

}