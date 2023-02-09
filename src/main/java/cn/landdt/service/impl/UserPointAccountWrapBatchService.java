package cn.landdt.service.impl;

import cn.landdt.entity.UserPointAccountEntity;
import cn.landdt.service.UserPointAccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

@Service
public class UserPointAccountWrapBatchService {

    @Resource
    private UserPointAccountService userPointAccountService;

    //最大合并任务数
    public static int MAX_TASK_NUM = 5;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class Request {
        //请求id（唯一）
        private String requestId;
        //用户积分账户id
        private Long id;

        private CompletableFuture<UserPointAccountEntity> completableFuture;
    }

    @PostConstruct
    public void init() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            int size = queue.size();
            if (size == 0) {
                return;
            }

            List<Request> requestList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                //最多合并X个请求
                if (i < MAX_TASK_NUM) {
                    requestList.add(queue.poll());
                }
            }

            Map<String, UserPointAccountEntity> userPointAccountEntityMap = userPointAccountService.findOneBatch(requestList);
            requestList.forEach(request -> {
                UserPointAccountEntity userPointAccountEntity = userPointAccountEntityMap.get(request.getRequestId());
                request.getCompletableFuture().complete(userPointAccountEntity);
            });
        }, 1000, 100, TimeUnit.MILLISECONDS);

    }

    private Queue<Request> queue = new LinkedBlockingQueue();

    public UserPointAccountEntity findAccountById(Long id) {
        Request request = new Request();
        request.setRequestId(UUID.randomUUID().toString().replace("-", ""));
        CompletableFuture<UserPointAccountEntity> completableFuture = new CompletableFuture<>();
        request.setCompletableFuture(completableFuture);
        queue.offer(request);
        try {
            return completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
