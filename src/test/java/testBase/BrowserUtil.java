package testBase;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import Enums.Enums.SelectBy;
import Enums.Enums.TimeOutInSeconds;
import Hooks.Log;


public class BrowserUtil extends BaseClass{

	/**
	 * Navigates to the given URL.
	 * @param url The URL to be loaded.
	 */
	public void NavigateToUrl(String url)
	{
		try
		{
			driver.get(url);
			Log.Debug("NavigateToUrl: Launched the URL : " + url);
		}
		catch(Exception e)
		{
			Log.Error("NavigateToUrl: Failed to launch the URL.");
			throw e;
		}		
	}
	
	
	/**
	 * Maximizes the screen.
	 */
	public void MaximizeScreen()
	{
		try 
		{
			driver.manage().window().maximize();
			Log.Debug("MaximizeScreen: Successfully maximized the screen.");
		} 
		catch (Exception e) 
		{
			Log.Error("MaximizeScreen: Failed to maximize the screen.");
			throw e;
		}		
	}
	
	/**
	 * Finds the element in the page.
	 * @param locator The XPath of the element to be given to find that element.
	 * @return webElement It returns the found web element that is present in the page.
	 */
	public WebElement FindElement(By locator)
	{
		WebElement webElement;
		try 
		{
			webElement = driver.findElement(locator);
			Log.Debug("FindElement: Finding the element : " + locator.toString());
		} 
		catch (Exception e) 
		{
			Log.Error("FindElement: Failed to find the element." + locator.toString());
			throw e;
		}

		return webElement;
	}
	
	/**
	 * Finds the list of elements in the page.
	 * @param locator The XPath of the element to be given to find that element.
	 * @return webElements It returns the found list of web elements that is present in the page.
	 */
	public List<WebElement> FindElements(By locator)
	{
		List<WebElement> webElements;
		try 
		{
			webElements = driver.findElements(locator);
			Log.Debug("FindElements: Finding the element : " + locator.toString());
		} 
		catch (Exception e) 
		{
			Log.Error("FindElements: Failed to find the element." + locator.toString());
			throw e;
		}

		return webElements;
	}
	
	/**
	 * Waits the element until it exits in the page.
	 * @param locator The XPath of the element to be given to find that element.
	 * @param timeOutInSeconds The wait time for the element to exist. The unit of time is of seconds.
	 * @return webElement It returns the found web element that is present in the page.
	 */
	public WebElement WaitForElement(By locator, TimeOutInSeconds timeOutInSeconds)
	{
		WebElement webElement;
		try 
		{
			long timeOut = Long.parseLong(String.valueOf(timeOutInSeconds.getNumVal()));
			webElement = new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOfElementLocated(locator));
			
			Log.Debug("WaitForElement: Waited for the element to exist : " + locator.toString());
		}
		catch (Exception e) 
		{
			Log.Error("WaitForElement: The element doesn't exist : " + locator.toString());
			throw e;
		}
		
		return webElement;
	}
	
	/**
	 * Waits the element until it exits in the page.
	 * @param locator The XPath of the element to be given to find that element.
	 * @param timeOutInSeconds The wait time for the element to exist. The unit of time is of seconds.
	 * @param pollingInterval The polling time to check the existence of the element. The unit of time is of seconds.
	 */
	public void WaitForElement(By locator, TimeOutInSeconds timeOutInSeconds, TimeOutInSeconds pollingInterval)
	{
		try 
		{
			long timeOut = Long.parseLong(String.valueOf(timeOutInSeconds.getNumVal()));
			long interval = Long.parseLong(String.valueOf(pollingInterval.getNumVal()));

			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);

			fluentWait.withTimeout(Duration.ofSeconds(timeOut))
			.pollingEvery(Duration.ofSeconds(interval))
			.ignoring(NoSuchElementException.class)
			.until(x -> this.FindElement(locator));
			
			Log.Debug("WaitForElement: Waited for the element to exist : " + locator.toString());
		}
		catch (Exception e) 
		{
			Log.Error("WaitForElement: The element doesn't exist : " + locator.toString());
			throw e;
		}
	}
	
	/**
	 * Clicks the element that is found.
	 * @param locator The XPath of the element to be given to find that element.
	 */
	public void ClickElement(By locator)
	{
		try 
		{
			this.FindElement(locator).click();
			Log.Debug("ClickElement: Successfully clicked the element.");
		} 
		catch (Exception e) 
		{
			Log.Error("ClickElement: Failed to click the element.");
			throw e;
		}
	}

	/**
	 * Types the text in the element that is found.
	 * @param locator The XPath of the element to be given to find that element.
	 * @param text The string to entered in that element.
	 */
	public void TypeInElement(By locator, String text) 
	{
		try 
		{
			this.FindElement(locator).sendKeys(text);
			Log.Debug("TypeInElement: Successfully entered the text - " + text);
		} 
		catch (Exception e) 
		{
			Log.Error("TypeInElement: Failed to enter the text in the element.");
			throw e;
		}
	}
	
	/**
	 * Types the text in the element that is found and Clears the exiting text present in it.
	 * @param locator The XPath of the element to be given to find that element.
	 * @param text The string to entered in that element.
	 * @param clearContents if true clear the contents in that element, otherwise enters text directly.
	 */
	public void TypeInElement(By locator, String text, boolean clearContents) 
	{
		try 
		{
			if (clearContents)
			{
				this.FindElement(locator).clear();
				this.FindElement(locator).sendKeys(text);
				Log.Debug("TypeInElement: Successfully entered the text - " + text);
			}
			else
			{
				this.TypeInElement(locator, text);
			}
		} 
		catch (Exception e) 
		{
			Log.Error("TypeInElement: Failed to enter the text in the element.");
			throw e;
		}
	}
	
	/**
	 * Types the text in the element that is found and Clears the exiting text present in it.
	 * @param locator The XPath of the element to be given to find that element.
	 * @return elementFound It returns true if the given element is found, otherwise false.  
	 */
	public boolean IsPageLoaded(By locator)
	{
		boolean elementFound;
		try
		{
			WebElement element = this.FindElement(locator);
			elementFound = element.equals(null) ? false : true;
		}
		catch(Exception e)
		{
			elementFound = false;
		}
		
		return elementFound;
	}
	
	/**
	 * Types the text in the element that is found and Clears the exiting text present in it.
	 * @param mouseHoverlocator The XPath of the element to be given to move to that element.
	 * @param locatorToClick The XPath of the element to be given to click that element. 
	 */
	public void MouseHoverAndClickElement(By mouseHoverlocator, By locatorToClick)
	{
		try 
		{
			Actions actions = new Actions(driver);
			this.WaitForElement(locatorToClick, TimeOutInSeconds.TWENTY, TimeOutInSeconds.FIVE);
			WebElement element = this.FindElement(mouseHoverlocator);
			actions.moveToElement(element).moveToElement(this.FindElement(locatorToClick)).click().build().perform();
			Log.Debug("MouseHoverAndClickElement: Successfully mouse hovered and clicked the element.");
		} 
		catch (Exception e) 
		{
			Log.Error("MouseHoverAndClickElement: Failed to mouse hover and click the element.");
			throw e;
		}	
	}
	
	/**
	 * Gets the text from the element that is found.
	 * @param locator The XPath of the element to be given to find that element.
	 * @return text It returns the text that is stored in the element.
	 */
	public String GetTextFromElement(By locator)
	{
		String text;
		try 
		{
			text = this.FindElement(locator).getText();
			Log.Debug("GetTextFromElement: Successfully fetched the text the element.");
		} 
		catch (Exception e) 
		{
			Log.Error("GetTextFromElement: Failed to fetch the text the element.");
			throw e;
		}	
		return text;
	}
	
	/**
	 * Gets the list of texts from the elements that are found.
	 * @param locator The XPath of the element to be given to find the elements.
	 * @return text It returns the list of texts that are stored in the elements.
	 */
	public List<String> GetTextFromElements(By locator)
	{
		List<String> ListOfTexts;
		try 
		{
			ListOfTexts = this.FindElements(locator).stream().map(x -> x.getText()).collect(Collectors.toList());
			Log.Debug("GetTextFromElements: Successfully fetched the text the elements.");
		} 
		catch (Exception e) 
		{
			Log.Error("GetTextFromElements: Failed to fetch the text the elements.");
			throw e;
		}	
		return ListOfTexts;
	}
	
	/**
	 * Gets the attribute value from the element that is found.
	 * @param locator The XPath of the element to be given to find that element.
	 * @return text It returns the attribute value that is present in the element.
	 */
	public String GetAttributeValueFromElement(By locator, String attribute)
	{
		String value;
		try 
		{
			value = this.FindElement(locator).getAttribute(attribute);
			Log.Debug("GetAttributeValueFromElement: Successfully fetched the value the element attribute.");
		} 
		catch (Exception e) 
		{
			Log.Error("GetAttributeValueFromElement: Failed to fetch the value the element attribute.");
			throw e;
		}	
		return value;
	}
	
	/**
	 * Gets the list of attribute values from the elements that are found.
	 * @param locator The XPath of the element to be given to find that element.
	 * @return text It returns the list of attribute values that are present in the elements.
	 */
	public List<String> GetAttributeValueFromElements(By locator, String attribute)
	{
		List<String> ListOfValues; 
		try 
		{
			ListOfValues = this.FindElements(locator).stream().map(x -> x.getAttribute(attribute)).collect(Collectors.toList());
			Log.Debug("GetAttributeValueFromElements: Successfully fetched the values the element attributes.");
		} 
		catch (Exception e) 
		{
			Log.Error("GetAttributeValueFromElements: Failed to fetch the values the element attributes.");
			throw e;
		}	
		return ListOfValues;
	}
	
	/**
	 * Moves to the element that is present in the page.
	 * @param locator The XPath of the element to be given to find that element.
	 */
	public void MoveToElement(By locator)
	{
		try
		{
			Actions actions = new Actions(driver);
			WebElement element = this.FindElement(locator);
			actions.moveToElement(element).sendKeys(Keys.DOWN).build().perform();
			Log.Debug("MoveToElement: Successfully moved to the element :  " + locator.toString());
		}
		catch (Exception e) 
		{
			Log.Debug("MoveToElement: Failed to move to the element :  " + locator.toString());
			throw e;
		}
	}

	/**
	 * Selects the value from the drop down.
	 * @param option The type of selection - produced from enums class SelectBy.
	 * @param locator The XPath of the element to be given to find that element.
	 * @param textOrIndex The text or index to given for the selection.
	 * @throws Exception 
	 */
	public void SelectFromDropdown(SelectBy option, By locator, String textOrIndex) throws Exception 
	{
		try 
		{
			Select select = new Select(this.FindElement(locator));
			switch (option) 
			{
			case VISIBLETEXT: select.selectByVisibleText(textOrIndex.trim());
			break;
			case INDEX: select.selectByIndex(Integer.parseInt(textOrIndex.trim()));
			break;
			case VALUE: select.selectByValue(textOrIndex.trim());
			break;
			default:
				throw new Exception("Invalid Input given");
			}		
			Log.Debug("SelectFromDropdown: Successfully selected " + textOrIndex + " from the dropdown.");
		}
		catch (Exception e) 
		{
			Log.Error("SelectFromDropdown: Failed to select " + textOrIndex + " from the dropdown.");
			throw e;
		}
	}

	/**
	 * Selects the value from the drop down after moving to the element if needed.
	 * @param option The type of selection - produced from enums class SelectBy.
	 * @param locator The XPath of the element to be given to find that element.
	 * @param textOrIndex The XPath of the element to be given to find that element.
	 * @param moveToElement If true moves to the element before selecting from dropdown, otherwise directly selects from dropdown.
	 * @throws Exception
	 */
	public void SelectFromDropdown(SelectBy option, By locator, String textOrIndex, boolean moveToElement) throws Exception 
	{
		try 
		{
			if (moveToElement)  
			{
				this.MoveToElement(locator);
			}

			this.SelectFromDropdown(option, locator, textOrIndex);
			Log.Debug("SelectFromDropdown: Successfully selected " + textOrIndex + " from the dropdown.");
		}
		catch (Exception e) 
		{
			Log.Error("SelectFromDropdown: Failed to select " + textOrIndex + " from the dropdown.");
			throw e;
		}
	}
	
	public void MouseHover(By locator)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(this.FindElement(locator)).build().perform();
	}
	
	public void MouseHover(WebElement element)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}
	
	public static String TakeScreenshot()
	{
		String filePath = null;
		final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
		String projectPath = System.getProperty("user.dir");
		Object timeStamp = sdf1.format(new Timestamp(System.currentTimeMillis()));
		File reportDirectory = new File( projectPath + "/Reports/TestReport/" + timeStamp );
		try
		{
			
			TakesScreenshot screenShot =((TakesScreenshot)driver);
			byte[] screenShotFile = screenShot.getScreenshotAs(OutputType.BYTES);

			File screenshotsFolder = new File( reportDirectory + "/Screenshots");
			if (!screenshotsFolder.exists()){
				screenshotsFolder.mkdirs();
			}

			filePath = screenshotsFolder + "\\SS_" + timeStamp + ".png";

			File tempFile = new File(filePath);
			tempFile.createNewFile();

			InputStream inputStream = new ByteArrayInputStream(screenShotFile);
			BufferedImage bufferedImage = ImageIO.read(inputStream);
			ImageIO.write(bufferedImage, "png", tempFile);
			System.out.println(filePath);
		}
		catch (Exception e) 
		{
			Log.Error("Failed to take screenshot. Exception Recieved: " + e.toString());
		}

		return filePath;
	}

}
