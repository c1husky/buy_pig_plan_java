package pers.telboom.impl;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pers.telboom.api.Api;
import pers.telboom.BoomConfig;
import pers.telboom.driver.DriverConfig;
import pers.telboom.driver.DriverLoader;
import pers.telboom.scheduler.BlockQueueScheduler;
import pers.telboom.scheduler.Scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Slf4j
public abstract class AbstractBoom extends Thread implements IBoom {
    protected DriverLoader driverLoader;
    protected Scheduler scheduler;
    protected BoomConfig boomConfig;
    private String firstHandle;
    private Collection<Api> startApis = null;

    public AbstractBoom(BoomConfig boomConfig) {
        this.boomConfig = boomConfig;
    }

    @Override
    public IBoom init(DriverConfig driverConfig) {
        driverLoader = new DriverLoader(driverConfig);
        driverLoader.load();
        return this;
    }

    @Override
    public void start(String initUrl) {
        checkComponet();
        checkUrl(initUrl);
        if (!scheduler.isEmpty()) {
            WebDriver driver = driverLoader.getWebDriver();
            driver.get(initUrl);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            firstHandle = driver.getWindowHandle();
            Api api;
            try {
                while ((api = scheduler.poll()) != null) {
                    try {
                        doBefore(api, driver);
                        execute(api, driver);
                        onSuccess(api, driver);
                        doAfter(api, driver);
                    } catch (Exception e) {
                        onFailure(api, driver, e);
                    }
                }
            } finally {
                driver.quit();
            }
        }

    }

    private void checkComponet() {
        if (scheduler == null) {
            scheduler = new BlockQueueScheduler();
        }
        if (startApis != null) {
            for (Api api : startApis) {
                scheduler.offer(api);
            }
        }
    }
    //获取新句柄的名称


    @Override
    public void doBefore(Api api, WebDriver driver) {
        checkUrl(api.getUrl());
        ((JavascriptExecutor) driver).executeScript(String.format("window.open('%s');", api.getUrl()));
        driver.switchTo().window(getNewHandle(driver.getWindowHandles(), firstHandle));
    }

    @Override
    public void doAfter(Api api, WebDriver driver) {
        try {
            Thread.sleep(500);
            driver.close();
            driver.switchTo().window(firstHandle);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBoom setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
        return this;
    }

    @Override
    public void addStartApi(Api api) {
        if (this.startApis == null) {
            this.startApis = new ArrayList<>();
        }
        this.startApis.add(api);
    }

    @Override
    public void onFailure(Api api, WebDriver driver, Exception e) {
        log.error("execute fail:{}", api.getUrl());
        driver.close();
        driver.switchTo().window(firstHandle);
    }

    @Override
    public void onSuccess(Api api, WebDriver driver) {
        log.info("execute success:{}", api.getUrl());
    }

    @Override
    public void setStartApis(Collection<Api> startApis) {
        this.startApis = startApis;
    }

    protected String getNewHandle(Set<String> handles, String oldHandle) {
        String newHandle = null;
        for (String handle : handles) {
            newHandle = Objects.equals(oldHandle, handle) ? newHandle : handle;
        }
        return newHandle;
    }

    private void checkUrl(String url) {
        if (!(url.startsWith("http://") || url.startsWith("https://"))) {
            throw new IllegalArgumentException("无效的路径");
        }
    }

}
