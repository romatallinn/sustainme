package supporting;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.*;

import java.util.Collections;
import java.util.List;

public class ApplicationEntryTest {

    @Test
    public void classTest() {
        Assert.assertNotNull(new ApplicationEntry());
    }

    @Test
    public void disableLoggingTest() {

        ApplicationEntry.disableLogging();

        List<Logger> loggers = Collections.<Logger>list(LogManager.getCurrentLoggers());
        loggers.add(LogManager.getRootLogger());
        for ( Logger logger : loggers ) {
            Assert.assertEquals(logger.getLevel(), Level.OFF);
        }

    }

}
