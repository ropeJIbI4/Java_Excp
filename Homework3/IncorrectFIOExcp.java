package Homework3;

public class IncorrectFIOExcp extends RuntimeException  {
    public IncorrectFIOExcp() {
        super("Некорректно заполнено поле ФИО");
    } 
}