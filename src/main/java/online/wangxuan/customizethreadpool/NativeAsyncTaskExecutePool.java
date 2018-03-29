package online.wangxuan.customizethreadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * TaskExecutePool使用的时候总是要加注解 @Async("myTaskAsyncPool")
 * <p>
 * 而下面这种重写spring默认线程池的方式使用的时候，只需要加 @Async 注解
 * 就可以，不用去声明线程池类
 *
 * @author wangxuan
 * @date 2018/3/29 17:19
 */
@Configuration
public class NativeAsyncTaskExecutePool implements AsyncConfigurer {


    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    private TaskThreadPoolConfig config;

    @Override
    public Executor getAsyncExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程池大小
        executor.setCorePoolSize(config.getCorePoolSize());
        // 最大线程数
        executor.setMaxPoolSize(config.getMaxPoolSize());
        // 队列容量
        executor.setQueueCapacity(config.getQueueCapacity());
        // 活跃时间
        executor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        // 线程名字前缀
        executor.setThreadNamePrefix("MyExecutor2-");

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        return executor;
    }

    /**
     * 异步任务中的异常处理
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
                logger.error("==========================" + throwable.getMessage() + "=======================", throwable);
                logger.error("exception method:" + method.getName());
            }
        };
    }
}
