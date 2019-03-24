package supporting;

import org.junit.Assert;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;

public class ServerAPITest {

    @Test
    public void assertClass() {
        Assert.assertNotNull(new ServerAPI());
    }


    @Test
    public void assertExistHost() {
        Assert.assertTrue(StringUtils.isNotBlank(ServerAPI.HOST));
    }

    @Test
    public void assertExistAppKey() {
        Assert.assertTrue(StringUtils.isNotBlank(ServerAPI.appKey));
    }

}
