package com.phonebook.core;

import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser",Browser.CHROME.browserName()));

    Logger logger = LoggerFactory.getLogger(TestBase.class);


    @BeforeMethod(alwaysRun = true)
    public void startTest(Method method, Object[] p){
        logger.info("Start test {} with data: {}", method.getName(), Arrays.asList(p));
    }

    @BeforeSuite(alwaysRun = true)
    public void setUp(){
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void stopTest(ITestResult result){
        if (result.isSuccess()){
            logger.info("PASSED: {}", result.getMethod().getMethodName());

        }else {
            logger.error("FAILED: {} Screenshot: {}", result.getMethod().getMethodName(), app.getContact()
                    .takeScreenshot());
        }
        logger.info("Stop Test");
        logger.info("************************************************");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        app.stop();
    }
    @BeforeGroups("smoky")
    public void setUpSmokyGroup(){
        logger.info("Start smoky test");
    }
    @AfterGroups("smoky")
    public void stopSmokyTestGroup(){
        logger.info("Stop smoky test");
    }

}
