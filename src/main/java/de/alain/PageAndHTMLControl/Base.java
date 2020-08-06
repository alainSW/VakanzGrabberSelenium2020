package de.alain.PageAndHTMLControl;

import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {

	public static WebDriver driver;

	public Base(WebDriver driver) {
		// Webdriver initialisieren
		Base.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void visit(String url) {
		// Seite öffnen
		try {
			driver.get(url);
		} catch (WebDriverException wde) {
			System.out.println(wde);
			fail();
		}
	}

	public void click(WebElement element, int timeout) {
		// klicken
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (WebDriverException wde) {
			System.out.println(wde);
			fail();
		}
	}

	public void type(WebElement element, int timeout, String inputText) {
		// Textfeld eingeben
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
			element.clear();
			element.sendKeys(inputText);
		} catch (WebDriverException wde) {
			System.out.println(wde);
			fail();
		}
	}

	public void waitForElementToBeVisible(WebElement element, int timeout) {
		// warten
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));

		} catch (WebDriverException wde) {
			System.out.println(wde);
			fail();
		}
	}

	public static String selectgetText(WebElement element, int timeout) {
		// Feldinhalt entnehmen
		String text = "";
		try {
			// text = new WebDriverWait(driver,
			// timeout).until(ExpectedConditions.elementToBeClickable(element)).getText();
			text = new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element)).getText();

		} catch (WebDriverException wde) {
			return "kein Text";
		}
		return text;
	}

	public static String selectgetAttributdataOriginalTitle(WebElement element, int timeout) {
		// Feldinhalt entnehmen
		String text = "";
		try {
			text = new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element))
					.getAttribute("data-original-title");

		} catch (WebDriverException wde) {
			return "kein Text";
		}
		// System.out.println(text);
		return text;
	}

	public static String selectgetAttributByValue(WebElement element, int timeout) {
		// Feldinhalt entnehmen
		String text = "";
		try {
			text = new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element))
					.getAttribute("value");

		} catch (WebDriverException wde) {
			return "kein Text";
		}
		// System.out.println(text);
		return text;
	}

	public static String selectgetAttributByPlaceholder(WebElement element, int timeout) {
		// Feldinhalt entnehmen
		String text = "";
		try {
			text = new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element))
					.getAttribute("placeholder");

		} catch (WebDriverException wde) {
			return "kein Text";
		}
		// System.out.println(text);
		return text;
	}

	public static String selectgetAttributById(WebElement element, int timeout) {
		// Feldinhalt entnehmen
		String text = "";
		try {
			text = new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element))
					.getAttribute("id");

		} catch (WebDriverException wde) {
			return "kein Text";
		}
		// System.out.println(text);
		return text;
	}

	public static String selectgetAttributByDataOriginalTitle(By projektListenMitAttribut, int timeout) {

		String attributwerte1 = "";
		String attributwerte2 = "";
		try {
			java.util.List<WebElement> text = driver.findElements(projektListenMitAttribut);
			int i = 0;

			for (WebElement Id : text) {
				attributwerte2 = attributwerte2 + "\n" + Id.getAttribute("data-original-title");
				i++;
			}
			attributwerte1 = attributwerte2;
			System.out.println(attributwerte1);

		} catch (WebDriverException wde) {
			return "kein Id";
		}
		return attributwerte1;
	}

	public void select(WebElement element, int timeout, String visibleText) {
		waitForElementToBeVisible(element, timeout);
		Select select = new Select(element);
		try {
			select.selectByVisibleText(visibleText);

		} catch (WebDriverException wde) {
			System.out.println(wde);
			fail();
		}
	}

	public boolean elementsIsVisible(WebElement element, int timeout) {
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));

		} catch (WebDriverException wde) {
			fail();

			return false;
		}
		return true;
	}
}
