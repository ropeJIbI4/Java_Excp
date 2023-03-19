package Homework3;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {

            try {
                String[] result = makeData(addUserInput());
                checkFIO(result);
                checkBirthdate(result);
                checkNumber(result);
                checkGender(result);
                writeData(result);
                break;

            } catch (NumberFormatException e) {
                System.out.println("Некорректно введен номер телефона.");
                continue;

            } catch (DateTimeParseException e) {
                System.out.println("Вы ввели некорректный формат даты /Format: dd.mm.yyyy/");
                continue;
            } catch (MoreDataExcp e) {
                System.out.println(
                        "Вы ввели больше данных чем нужно.");
                continue;
            } catch (LessDataExcp e) {
                System.out.println(
                        "Вы ввели меньше данных чем нужно.");
                continue;
            } catch (IncorrectFIOExcp e) {
                System.out.println("Некорректно заполнено поле ФИО /Format: Фамилия, Имя, Отчество/");
                continue;
            } catch (IncorrectGenderExcp e) {
                System.out.println("Некорректно введен пол. f - женский, m - мужской");
                continue;
            }
        }

    }

    public static String addUserInput() {
        System.out.println(
                "\nВас приветствует БД!");
        System.out.println(
                "Введите данные через ПРОБЕЛ в следующем формате:\n(Фамилия,Имя,Отчество)(Дата рождения-dd.mm.yyyy)(Номер телефона)(Пол: f - жен / m - муж)");
        String input = "";
        Scanner scanner = new Scanner(System.in);
        try{
            input = scanner.nextLine();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            scanner.close();
        }
        

        return input;
    }

    public static String[] makeData(String input) {
        int dataLenght = 4;
        String[] result = input.split(" ");
        if (result.length > dataLenght) {
            throw new MoreDataExcp();
        }
        if (result.length < dataLenght) {
            throw new LessDataExcp();
        }
        return result;
    }

    public static String[] checkFIO(String[] result) {

        int dataLenght = 3;
        String[] fio = result[0].strip().split(",");
        if (fio.length != dataLenght) {
            throw new IncorrectFIOExcp();
        }
        return fio;
    }

    public static void checkBirthdate(String[] result) throws DateTimeParseException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        LocalDate ld = LocalDate.parse(result[1], formatter);

    }

    public static void checkNumber(String[] result) throws NumberFormatException {

        Integer.parseInt(result[2]);
    }

    public static void checkGender(String[] result) {
        if (!result[3].equals("f") && !result[3].equals("m")) {
            throw new IncorrectGenderExcp();
        }
    }

    public static String fromArrayToString(String[] result) {
        StringBuilder sb = new StringBuilder();
        for (String s : result) {
            sb.append(s + " ");
        }
        return sb.toString();
    }

    public static void writeData(String[] result) {
        String fileName = checkFIO(result)[0];
        String forWrite = fromArrayToString(result);
        try (FileWriter writer = new FileWriter(String.format("Homework3/%s.txt", fileName), true)) {
            writer.append(forWrite);
            writer.append("\n");
            System.out.println("\nВведенные данные корректны и записаны в файл:)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}