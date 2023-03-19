package Homework3;

public class IncorrectGenderExcp extends RuntimeException {
    public IncorrectGenderExcp() {
        super("Некорректно введен пол");
    } 
}