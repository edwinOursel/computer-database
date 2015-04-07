package com.excilys.cdb.cli;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.assertj.core.api.Assertions;

public class TestDashboard {

	private WebDriver driver;
	
	@Before
	public void init() {
		driver = new HtmlUnitDriver();
		driver.get("http://localhost:8080/computer-database/dashboard");
	}
	
	@Test
	public void testFirstElement() {
		WebElement query = driver.findElement(By.id("companyName_1"));
		Assertions.assertThat(query != null).isTrue();
		Assertions.assertThat(query.getText()).isEqualTo("Apple Inc.");

		query = driver.findElement(By.id("computerName_1"));
		Assertions.assertThat(query != null).isTrue();
		Assertions.assertThat(query.getText()).isEqualTo("MacBook Pro 15.4 inch");
	}
	
	@Test
	public void testGoToAddElementUrl() {
		driver.findElement(By.id("addComputer")).click();
		Assertions.assertThat(driver.getCurrentUrl()).isEqualTo("http://localhost:8080/computer-database/addComputer");
	}
	
	@Test
	public void testGoToEditElementUrl() {
		driver.findElement(By.id("computerName_1")).click();
		Assertions.assertThat(driver.getCurrentUrl()).isEqualTo("http://localhost:8080/computer-database/editComputer?id=1");
	}
	
	@After
	public void close() {
		driver.close();
	}

}
