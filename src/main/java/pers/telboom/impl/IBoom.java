package pers.telboom.impl;

import org.openqa.selenium.WebDriver;
import pers.telboom.api.Api;
import pers.telboom.driver.DriverConfig;
import pers.telboom.scheduler.Scheduler;

import java.util.Collection;

public interface IBoom {
    Api.ApiType getAccpetApiType();

    IBoom setScheduler(Scheduler scheduler);

    IBoom init(DriverConfig driverConfig);

    void start(String initUrl);

    void doBefore(Api api, WebDriver driver);

    void execute(Api api, WebDriver driver) throws Exception;

    void doAfter(Api api, WebDriver driver);

    void setStartApis(Collection<Api>apis);

    void addStartApi(Api api);

    void onFailure(Api api, WebDriver driver, Exception e);

    void onSuccess(Api api, WebDriver driver);
}
