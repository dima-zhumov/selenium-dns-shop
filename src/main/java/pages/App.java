package pages;

public class App {
    public static void main(String[] args) {
        String s = "(+4 000";
        int i = Integer.parseInt(s.replaceAll("[^\\d]",""));
        System.out.println(i);
    }
}
