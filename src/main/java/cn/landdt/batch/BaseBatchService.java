package cn.landdt.batch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.*;

@Slf4j
public abstract class BaseBatchService<R, P> {

    //最大合并任务数
    public static int MAX_TASK_NUM = 5;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class Request {
        //请求id（唯一）
        private String requestId;

        private P requestParam;

        private CompletableFuture<R> completableFuture;
    }

    protected Queue<Request> queue = new LinkedBlockingQueue<>();

    public void start() {
        String key = getKey();
        log.info("【{}】合并请求任务开始", key);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            int size = queue.size();
            if (size == 0) {
                return;
            }
            int batchSize = size;
            List<Request> requestList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                //最多合并X个请求
                if (i < MAX_TASK_NUM) {
                    requestList.add(queue.poll());
                } else {
                    batchSize = MAX_TASK_NUM;
                }
            }
            log.info("【{}】合并任务，本次合并请求：{}个", key, batchSize);

            Map<P, List<R>> batchResultMap = batchProcess(requestList);
            HashMap<String, R> result = new HashMap<>();
            requestList.forEach(request -> {
                List<R> batchResult = batchResultMap.get(request.getRequestParam());
                if (CollectionUtils.isEmpty(batchResult)) {
                    result.put(request.getRequestId(), null);
                } else {
                    result.put(request.getRequestId(), batchResult.get(0));
                }
            });

            requestList.forEach(request -> {
                R obj = result.get(request.getRequestId());
                request.getCompletableFuture().complete(obj);
            });
        }, 1000, 100, TimeUnit.MILLISECONDS);

    }

    public R singleRequest(P requestParam) {
        Request request = new Request();
        request.setRequestId(UUID.randomUUID().toString().replace("-", ""));
        request.setRequestParam(requestParam);
        CompletableFuture<R> completableFuture = new CompletableFuture<>();
        request.setCompletableFuture(completableFuture);
        queue.offer(request);
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract Map<P, List<R>> batchProcess(List<Request> requestList);

    public abstract String getKey();

    public static void setMaxTaskNum(int maxTaskNum) {
        MAX_TASK_NUM = maxTaskNum;
    }
}
