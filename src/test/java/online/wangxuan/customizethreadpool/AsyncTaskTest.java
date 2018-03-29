package online.wangxuan.customizethreadpool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

/**
 * @author wangxuan
 * @date 2018/3/29 17:12
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncTaskTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void AsyncTaskTest() throws InterruptedException, ExecutionException {
        for (int i = 0; i < 10; i++) {
            asyncTask.doTask1(i);
        }
        logger.info("All tasks finished");
    }

    @Test
    public void AsyncTaskNativeTest() throws InterruptedException, ExecutionException {

        for (int i = 0; i < 10; i++) {
            asyncTask.doTask2(i);
        }

        logger.info("All tasks finished.");
    }
}
