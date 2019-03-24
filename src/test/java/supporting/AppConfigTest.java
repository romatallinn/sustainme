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
    public void assertExistHost() {
        Assert.assertNotNull(AppConfig.host);
    }

    @Test
    public void assertExistAppKey() {
        Assert.assertTrue(StringUtils.isNotBlank(AppConfig.appKey));
    }

    @Test
    public void assertExistDebug() {
        Assert.assertNotNull(AppConfig.debug);
    }

}
