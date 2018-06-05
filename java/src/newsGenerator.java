import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;


public class newsGenerator {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "./chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.skysports.com/football");

        List<WebElement> news = driver.findElements(By.className("news-list__item"));
        for (WebElement each_news : news){
            try {
                System.out.println(each_news.getText());
                insertData(each_news.getText());
            } catch (Exception e) {
                System.out.println("Database Connection Error!");
                e.printStackTrace();
            }
        }
    }

    public static Connection insertData(String data) throws Exception{

        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:8889/news";
            String user = "root";
            String pass = "root";

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            PreparedStatement statement = (PreparedStatement) conn.prepareStatement(
                    "INSERT INTO `div` (`id`, `seen`, `content`) VALUES (NULL, ?, ?)");
            statement.setString(1, "0");
            statement.setString(2, data);
            System.out.println(statement);
            statement.executeUpdate();


            return conn;
        }
        catch (Exception e){
            throw e;
        }

    }
}