package pers.telboom.scheduler;

import pers.telboom.api.Api;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockQueueScheduler implements Scheduler{
    private BlockingQueue<Api>apiBlockingQueue=new LinkedBlockingQueue<>();


    @Override
    public Api poll() {
        return apiBlockingQueue.poll();
    }

    @Override
    public boolean isEmpty() {
        return apiBlockingQueue.isEmpty();
    }

    @Override
    public boolean offer(Api api) {
        return apiBlockingQueue.offer(api);
    }
}
