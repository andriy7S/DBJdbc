package andriy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Jdbcconnector {
	public static void main(String[] args) throws SQLException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://login.salesforce.com/?locale=ca");
		
		
		String host = "localhost";
		String port = "3306";
		Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/demo", "root",
				"again1987");

		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from credentials where scenario = 'zerobalancecard'");

		while (rs.next()) {
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys(rs.getString("username"));
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys(rs.getString("password"));
		}
	}

}
