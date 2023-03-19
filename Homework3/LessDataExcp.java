package Homework3;

public class LessDataExcp extends RuntimeException {
    public LessDataExcp() {
        super("Введено меньше данных, чем нужно");
    }   
}