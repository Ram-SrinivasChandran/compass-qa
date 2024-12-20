package net.breezeware.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;

import net.breezeware.Components.BaseTest;

public class TestDataProvider extends BaseTest {
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(
                System.getProperty("user.dir") + "//src//test//java//net//breezeware//data//testData.json");
        return new Object[][] { { data.get(0) } };
    }

}
