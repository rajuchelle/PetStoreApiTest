package test;

import com.relevantcodes.extentreports.*;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pojos.FinByStatusPojo;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class PetStoreTest1 extends  Utilities{
    static ExtentReports extentReport ;
    static ExtentTest test;

    @BeforeSuite
    public static void setUp() throws IOException {
        getTestProperties();
        getConfig(environment);
        extentReport = new ExtentReports("./src/test/ExtentReports/APITestReport.html");
        extentReport.loadConfig(new File("./src/test/Resources/Config/extent-config.xml"));
    }

    @AfterSuite
    public static void generateExtentReport() throws IOException {
        extentReport.endTest(test);
        extentReport.flush();
    }

    @BeforeMethod
    public void getEahMethod(){
        test = extentReport.startTest("PetStoreTest1");
    }



    @Test
    public void getPetsByStatus() throws IOException {
        getTestDetails("ps_test1");
        String apiPath = baseUrl+path+pathParam+queryParam;
        System.out.println("API URL: "+ apiPath);
        response = get(apiPath);
        FinByStatusPojo[] allPets = response.body().as(FinByStatusPojo[].class);
        System.out.println("Count of all Pets: "+allPets.length);
        Arrays.stream(allPets).forEach(eachPet -> {
            System.out.println(eachPet.getId());
        });
        Assert.assertEquals(response.statusCode(),Integer.parseInt(statusCode));
        test.log(LogStatus.PASS,"Test is passed");
    }

    @Test
    public void getPetsByStatus1() throws IOException {
        getTestDetails("ps_test1");
        String apiPath = baseUrl+path+pathParam+queryParam;
        System.out.println("API URL: "+ apiPath);
        response = get(apiPath);
        FinByStatusPojo[] allPets = response.body().as(FinByStatusPojo[].class);
        System.out.println("Count of all Pets: "+allPets.length);

        Arrays.stream(allPets).forEach(eachPet -> {
            System.out.println(eachPet.getId());
        });
        Assert.assertEquals(response.statusCode(),200);
        test.log(LogStatus.PASS,"Test is passed");

    }

}
