package cn.landdt.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BatchServiceLauncher {

    public BatchServiceLauncher(List<BaseBatchService> baseBatchServices){
        for (BaseBatchService baseBatchService : baseBatchServices) {
            baseBatchService.start();
        }
    }
}
