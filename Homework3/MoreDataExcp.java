package Homework3;

public class MoreDataExcp extends RuntimeException  {
    public MoreDataExcp() {
        super("Введено больше данных, чем нужно");
    }  
}