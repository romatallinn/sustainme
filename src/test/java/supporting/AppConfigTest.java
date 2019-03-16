package supporting;

import org.junit.Assert;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;

public class AppConfigTest {

    @Test
    public void assertClass() {
        Assert.assertNotNull(new AppConfig());
    }

    @Test
    public void assertExistDbUrl() {
        Assert.assertTrue(StringUtils.isNotBlank(AppConfig.dbUrl));
    }

    @Test
    public void assertExistAppKey() {
        Assert.assertTrue(StringUtils.isNotBlank(AppConfig.appKey));
    }

    @Test
    public void assertExistAuthUrl() {
        Assert.assertTrue(StringUtils.isNotBlank(AppConfig.authUrl));
    }

    @Test
    public void assertExistDebug() {
        Assert.assertNotNull(AppConfig.debug);
    }

}
