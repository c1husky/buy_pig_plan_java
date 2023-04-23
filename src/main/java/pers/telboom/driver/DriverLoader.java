package pers.telboom.driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DriverLoader {

    private DriverConfig driverConfig;

    public DriverLoader(DriverConfig driverConfig) {
        this.driverConfig=driverConfig;
    }

    /**
     * @description:设置webdriver驱动环境变量
     **/
    public void load(){
        System.setProperty(driverConfig.getDriverType().getDriverName(),driverConfig.getDriverPath());
    }
    /**
     * @description:加载webdriver驱动
     **/
    public  WebDriver getWebDriver(){
        DriverType driverType = driverConfig.getDriverType();
        return driverType.getWebDriver();
    }
    public  WebDriver getWebDriver(Capabilities capabilities) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        DriverType driverType = driverConfig.getDriverType();
        return driverType.getWebDriver(capabilities);
    }

}
