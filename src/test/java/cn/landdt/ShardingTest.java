package cn.landdt;

import cn.hutool.core.util.IdUtil;
import cn.landdt.batch.UserPointAccountBatchService;
import cn.landdt.batch.UserPointAccountInsertBatchService;
import cn.landdt.entity.UserPointAccountEntity;
import cn.landdt.entity.UserPointGetEntity;
import cn.landdt.service.UserPointAccountService;
import cn.landdt.service.UserPointGetService;
import cn.landdt.service.impl.UserPointAccountWrapBatchService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
public class ShardingTest {

    @Resource
    private UserPointAccountService userPointAccountService;

    @Test
    public void abc() {
//
//        List<UserPointAccountEntity> userPointAccountEntityList = new ArrayList<>() {{
//            add(new UserPointAccountEntity(null, "15555555555", 112323L, 1, new BigDecimal("111"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "354354", 112323L, 1, new BigDecimal("123"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "123213", 17823L, 1, new BigDecimal("122"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "3574", 78112323L, 1, new BigDecimal("35"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "134378", 1989L, 1, new BigDecimal("3445"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "9898", 454523L, 1, new BigDecimal("5454"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "354345", 1454323L, 1, new BigDecimal("567"), LocalDateTime.now(), LocalDateTime.now()));
//        }};


//        userPointAccountService.saveBatch(userPointAccountEntityList);
//
//        UserPointAccountEntity userPointAccountEntity = new UserPointAccountEntity(null,"15555555555",112323L,1,new BigDecimal("111"), LocalDateTime.now(),LocalDateTime.now());
//        userPointAccountService.save(userPointAccountEntity);
//        List<UserPointAccountEntity> list = userPointAccountService.list(new LambdaQueryWrapper<UserPointAccountEntity>().last("order by id DESC limit 5"));
//        List<UserPointAccountEntity> list = userPointAccountService.list(new LambdaQueryWrapper<UserPointAccountEntity>().eq(UserPointAccountEntity::getId,1621042725627396098L));
//        System.out.println(JSONObject.toJSONString(list));
//        System.out.println(list.size());
        List<Long> abc = new ArrayList<>() {
            {
                add(1622800280697118721L);
                add(1622800280705507329L);
                add(1622800280709701633L);
                add(1622800280701313027L);
            }
        };
        List<UserPointAccountEntity> list = userPointAccountService.list(new LambdaQueryWrapper<UserPointAccountEntity>().in(UserPointAccountEntity::getId, abc)
                .eq(UserPointAccountEntity::getIntegral, 35));
        System.out.println(JSONObject.toJSONString(list));
    }

    public static void main2(String[] args) {
        List<Long> abc = new ArrayList<>() {{
            add(1622800280743256067L);
            add(1622800280743256066L);
            add(1622800280743256065L);
            add(1622800280739061761L);
            add(1622800280734867459L);
            add(1622800280734867458L);
            add(1622800280734867457L);
            add(1622800280730673155L);
            add(1622800280730673154L);
            add(1622800280726478850L);
        }};
        for (int i = 0; i < abc.size(); i++) {
            System.out.println(abc.get(i) % 32);
        }
    }

    @Resource
    private UserPointGetService userPointGetService;

    @Test
    public void bcd() {
        List<UserPointGetEntity> userPointGetEntities = new ArrayList<>() {{
            add(new UserPointGetEntity(null, BigDecimal.ONE, 1L, Long.valueOf(IdUtil.getSnowflake().nextIdStr()), BigDecimal.ONE, BigDecimal.ZERO, 2, Long.valueOf(IdUtil.getSnowflake().nextIdStr()), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()));
            add(new UserPointGetEntity(null, BigDecimal.ONE, 1L, Long.valueOf(IdUtil.getSnowflake().nextIdStr()), BigDecimal.ONE, BigDecimal.ZERO, 2, Long.valueOf(IdUtil.getSnowflake().nextIdStr()), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()));
            add(new UserPointGetEntity(null, BigDecimal.ONE, 1L, Long.valueOf(IdUtil.getSnowflake().nextIdStr()), BigDecimal.ONE, BigDecimal.ZERO, 2, Long.valueOf(IdUtil.getSnowflake().nextIdStr()), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()));
            add(new UserPointGetEntity(null, BigDecimal.ONE, 1L, Long.valueOf(IdUtil.getSnowflake().nextIdStr()), BigDecimal.ONE, BigDecimal.ZERO, 2, Long.valueOf(IdUtil.getSnowflake().nextIdStr()), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()));
            add(new UserPointGetEntity(null, BigDecimal.ONE, 1L, Long.valueOf(IdUtil.getSnowflake().nextIdStr()), BigDecimal.ONE, BigDecimal.ZERO, 2, Long.valueOf(IdUtil.getSnowflake().nextIdStr()), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()));
            add(new UserPointGetEntity(null, BigDecimal.ONE, 1L, Long.valueOf(IdUtil.getSnowflake().nextIdStr()), BigDecimal.ONE, BigDecimal.ZERO, 2, Long.valueOf(IdUtil.getSnowflake().nextIdStr()), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()));
            add(new UserPointGetEntity(null, BigDecimal.ONE, 1L, Long.valueOf(IdUtil.getSnowflake().nextIdStr()), BigDecimal.ONE, BigDecimal.ZERO, 2, Long.valueOf(IdUtil.getSnowflake().nextIdStr()), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()));
            add(new UserPointGetEntity(null, BigDecimal.ONE, 1L, Long.valueOf(IdUtil.getSnowflake().nextIdStr()), BigDecimal.ONE, BigDecimal.ZERO, 2, Long.valueOf(IdUtil.getSnowflake().nextIdStr()), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()));
        }};

        userPointGetService.saveBatch(userPointGetEntities);
        System.out.println(userPointGetService.list());
    }


    public static void main(String[] args) {
        //总额
        BigDecimal total = new BigDecimal("600000");
        BigDecimal balance = total;
        //月数
        Integer month = 30 * 12;
        //利率
        BigDecimal ratio = new BigDecimal("0.041");

        BigDecimal sum = new BigDecimal("0");
        for (int i = 1; i <= month; i++) {
            //本
            BigDecimal principal = total.divide(new BigDecimal(month + ""), 0, RoundingMode.DOWN);
            //利
            BigDecimal interest = balance.multiply(ratio).divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP);
            balance = balance.subtract(principal);
            BigDecimal sumTmp = principal.add(interest);
            sum = sum.add(sumTmp);
            System.out.println("第" + (((i - 1) / 12) + 1) + "年" + (((i - 1) % 12) + 1) + "月，应支付本：" + principal + "，利：" + interest + "，本次合计：" + sumTmp + "，累计合计：" + sum + "，剩余本：" + balance);
        }
    }

    @Resource
    private UserPointAccountWrapBatchService userPointAccountWrapBatchService;

    @Test
    public void testBatch() {
        List<Long> abc = new ArrayList<>() {{
            add(1622800280697118721L);
            add(1622800280705507329L);
            add(1622800280709701633L);
            add(1622800280722284545L);
            add(1622800280726478849L);
            add(1622800280734867457L);
            add(1622800280739061761L);
            add(1622800280743256065L);
            add(1622800280751644673L);
            add(1622800280755838977L);
            add(1622800280760033281L);
            add(1622800280697118721L);
            add(1622800280705507329L);
            add(1622800280709701633L);
            add(1622800280722284545L);
            add(1622800280726478849L);
            add(1622800280734867457L);
            add(1622800280739061761L);
            add(1622800280743256065L);
            add(1622800280751644673L);
            add(1622800280755838977L);
            add(1622800280760033281L);
            add(1622800280697118721L);
            add(1622800280705507329L);
            add(1622800280709701633L);
            add(1622800280722284545L);
            add(1622800280726478849L);
            add(1622800280734867457L);
            add(1622800280739061761L);
            add(1622800280743256065L);
            add(1622800280751644673L);
            add(1622800280755838977L);
            add(1622800280760033281L);
        }};
        for (Long aLong : abc) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CompletableFuture.runAsync(() -> {

                System.out.println(JSONObject.toJSONString(userPointAccountWrapBatchService.findAccountById(aLong)));
            }).exceptionally((e) -> {
                e.printStackTrace();
                return null;
            });
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Resource
    private UserPointAccountBatchService userPointAccountBatchService;

    @Test
    public void testBatch1() {

        List<Long> abc = new ArrayList<>() {{
            add(1622800280697118721L);
            add(1622800280705507329L);
            add(1622800280709701633L);
            add(1622800280722284545L);
            add(1622800280726478849L);
            add(1622800280734867457L);
            add(1622800280739061761L);
            add(1622800280743256065L);
            add(1622800280751644673L);
            add(1622800280755838977L);
            add(1622800280760033281L);
            add(1622800280697118721L);
            add(1622800280705507329L);
            add(1622800280709701633L);
            add(1622800280722284545L);
            add(1622800280726478849L);
            add(1622800280734867457L);
            add(1622800280739061761L);
            add(1622800280743256065L);
            add(1622800280751644673L);
            add(1622800280755838977L);
            add(1622800280760033281L);
            add(1622800280697118721L);
            add(1622800280705507329L);
            add(1622800280709701633L);
            add(1622800280722284545L);
            add(1622800280726478849L);
            add(1622800280734867457L);
            add(1622800280739061761L);
            add(1622800280743256065L);
            add(1622800280751644673L);
            add(1622800280755838977L);
            add(1622800280760033281L);
        }};
        for (Long aLong : abc) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CompletableFuture.runAsync(() -> {
                System.out.println(JSONObject.toJSONString(userPointAccountBatchService.singleRequest(aLong)));
            }).exceptionally((e) -> {
                e.printStackTrace();
                return null;
            });
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Resource
    private UserPointAccountInsertBatchService userPointAccountInsertBatchService;

//    @Test
//    public void ddd() {
//        List<UserPointAccountEntity> userPointAccountEntityList = new ArrayList<>() {{
//            add(new UserPointAccountEntity(null, "15555555555", 112323L, 1, new BigDecimal("111"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "354354", 112323L, 1, new BigDecimal("123"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "123213", 17823L, 1, new BigDecimal("122"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "3574", 78112323L, 1, new BigDecimal("35"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "134378", 1989L, 1, new BigDecimal("3445"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "9898", 454523L, 1, new BigDecimal("5454"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "354345", 1454323L, 1, new BigDecimal("567"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "15555555555", 112323L, 1, new BigDecimal("111"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "354354", 112323L, 1, new BigDecimal("123"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "123213", 17823L, 1, new BigDecimal("122"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "3574", 78112323L, 1, new BigDecimal("35"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "134378", 1989L, 1, new BigDecimal("3445"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "9898", 454523L, 1, new BigDecimal("5454"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "354345", 1454323L, 1, new BigDecimal("567"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "15555555555", 112323L, 1, new BigDecimal("111"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "354354", 112323L, 1, new BigDecimal("123"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "123213", 17823L, 1, new BigDecimal("122"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "3574", 78112323L, 1, new BigDecimal("35"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "134378", 1989L, 1, new BigDecimal("3445"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "9898", 454523L, 1, new BigDecimal("5454"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "354345", 1454323L, 1, new BigDecimal("567"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "15555555555", 112323L, 1, new BigDecimal("111"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "354354", 112323L, 1, new BigDecimal("123"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "123213", 17823L, 1, new BigDecimal("122"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "3574", 78112323L, 1, new BigDecimal("35"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "134378", 1989L, 1, new BigDecimal("3445"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "9898", 454523L, 1, new BigDecimal("5454"), LocalDateTime.now(), LocalDateTime.now()));
//            add(new UserPointAccountEntity(null, "354345", 1454323L, 1, new BigDecimal("567"), LocalDateTime.now(), LocalDateTime.now()));
//        }};
//        for (UserPointAccountEntity aLong : userPointAccountEntityList) {
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            CompletableFuture.runAsync(() -> {
//                System.out.println(JSONObject.toJSONString(userPointAccountInsertBatchService.singleRequest(aLong)));
//            }).exceptionally((e) -> {
//                e.printStackTrace();
//                return null;
//            });
//        }
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
