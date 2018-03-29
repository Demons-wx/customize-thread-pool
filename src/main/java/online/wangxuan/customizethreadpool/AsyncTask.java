package online.wangxuan.customizethreadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


/**
 * @author wangxuan
 * @date 2018/3/29 17:08
 */
@Component
public class AsyncTask {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async("myTaskAsyncPool")
    public void doTask1(int i) {
        logger.info("Task" + i + " started.");
    }

    @Async
    public void doTask2(int i) throws InterruptedException {
        logger.info("Task2-Native" + i + " started.");
    }
}
