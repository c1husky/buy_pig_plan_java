package pers.telboom.driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public enum DriverType {
    /**/
    CHROME("webdriver.chrome.driver",ChromeDriver.class){
        @Override
        public Capabilities getCapabilities() {
            ChromeOptions chromeOptions = new ChromeOptions();
            /**
             * chrome_options.add_experimental_option('excludeSwitches', ['enable-automation']) # 设置开发者提示
             * chrome_options.add_experimental_option('useAutomationExtension', False) #屏蔽自动化受控提示
             * options.add_argument('--disable-infobars') # 禁止策略化
             * options.add_argument('--no-sandbox') # 解决DevToolsActivePort文件不存在的报错
             * options.add_argument('window-size=1920x3000') # 指定浏览器分辨率
             * options.add_argument('--disable-gpu') # 谷歌文档提到需要加上这个属性来规避bug
             * options.add_argument('--incognito') # 隐身模式（无痕模式）
             * options.add_argument('--disable-javascript') # 禁用javascript
             * options.add_argument('--start-maximized') # 最大化运行（全屏窗口）,不设置，取元素会报错
             * options.add_argument('--disable-infobars') # 禁用浏览器正在被自动化程序控制的提示
             * options.add_argument('--hide-scrollbars') # 隐藏滚动条, 应对一些特殊页面
             * options.add_argument('blink-settings=imagesEnabled=false') # 不加载图片, 提升速度
             * options.add_argument('--headless') # 浏览器不提供可视化页面. linux下如果系统不支持可视化不加这条会启动失败
             * options.binary_location = r"C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" # 手动指定使用的浏览器位置
             **/
            chromeOptions.addArguments("--disable-gpu", "--disable-blink-features=AutomationControlled"
                            , "--incognito", "--disable-infobars", "--no-sandbox"
                            , "user-agent=Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.41 Safari/535.1 QQBrowser/6.9.11079.201")
                    .setExperimentalOption("useAutomationExtension", "False")
                    .setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            return chromeOptions;
        }

        @Override
        public WebDriver getWebDriver(Capabilities capabilities) {
            WebDriver webDriver=new ChromeDriver(capabilities);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
            jsExecutor.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => False})", "");
            return webDriver;
        }
    },
    FIREFOX("webdriver.gecko.driver",FirefoxDriver.class),
    EDGE("webdriver.edge.driver",EdgeDriver.class),
    SAFARI("",SafariDriver.class),
    OPERA("webdriver.opera.driver",OperaDriver.class),
    IE("webdriver.ie.driver",InternetExplorerDriver.class);



    Class<? extends WebDriver> cls;

    String driverName;

    DriverType(String driverName,Class<? extends WebDriver> cls){
        this.driverName=driverName;
        this.cls=cls;
    }
    public Class<? extends WebDriver> getCls() {
        return cls;
    }
    public String getDriverName() {
        return driverName;
    }

    /**
     * @description: 设置反扒参数
     **/
    public Capabilities getCapabilities(){
        throw new AbstractMethodError();
    }

    public WebDriver getWebDriver(Capabilities capabilities){
        throw new AbstractMethodError();
    }
    public WebDriver getWebDriver(){
        return this.getWebDriver(getCapabilities());
    }
}
