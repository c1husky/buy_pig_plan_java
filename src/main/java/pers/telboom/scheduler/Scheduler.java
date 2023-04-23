package pers.telboom.scheduler;

import pers.telboom.api.Api;

public interface Scheduler {

    Api poll();

    boolean isEmpty();

    boolean offer(Api api);
}
