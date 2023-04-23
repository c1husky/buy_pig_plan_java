package pers.telboom.api;

public class Api {
    public static enum ApiType{
        BAIDU_LXB,
        BAIDU_SHAN_QIAO;
    }
    private String id;
    private String name;
    private String url;
    private String taskType;
    private ApiType webType;

    public Api() {}

    public Api(String id, String name, String url, String taskType, String ApiType) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.taskType = taskType;
        this.webType = webType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public ApiType getWebType() {
        return webType;
    }

    public void setWebType(ApiType webType) {
        this.webType = webType;
    }
}
