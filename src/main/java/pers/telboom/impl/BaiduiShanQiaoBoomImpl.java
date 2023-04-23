package pers.telboom.impl;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pers.telboom.api.Api;
import pers.telboom.BoomConfig;
@Slf4j
public class BaiduiShanQiaoBoomImpl extends AbstractBoom {

    public BaiduiShanQiaoBoomImpl(BoomConfig boomConfig) {
        super(boomConfig);
    }

    @Override
    public Api.ApiType getAccpetApiType() {
        return Api.ApiType.BAIDU_SHAN_QIAO;
    }

    @Override
    public void execute(Api api, WebDriver driver)throws Exception {
        WebElement element = driver.findElement(By.className("pc-icon-leave-tel"));
        element.click();
        Thread.sleep(500);
        //输入电话
        driver.findElement(By.className("leavetel-input"))
                .sendKeys(boomConfig.getTelephone());
        //点击发送
        driver.findElement(By.className("leavetel-callback")).click();
        Thread.sleep(500);
    }

}
