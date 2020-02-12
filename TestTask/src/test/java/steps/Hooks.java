package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import web.Chromer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Hooks {
    @Before(value = "@UI")
    public void runDriver() {
            Chromer.createDriver();
            Chromer.initElements();
    }

    @After(value = "@UI")
    public void backToHomepage(Scenario scenario) throws IOException {
        if(scenario.isFailed()) {
            SimpleDateFormat ft = new SimpleDateFormat("yy-MM-dd HH_mm_ss");
            String sDate = ft.format(new Date());
            File screenshot1 = new File("D:/SNAPSHOT/screenshot/"+scenario.getName()+sDate+".png");
            final byte[] screenshot2 = ((TakesScreenshot) Chromer.driver).getScreenshotAs(OutputType.BYTES);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(screenshot2));
            ImageIO.write(image, "png", screenshot1);
        }
        Chromer.closeDriver();
    }
}