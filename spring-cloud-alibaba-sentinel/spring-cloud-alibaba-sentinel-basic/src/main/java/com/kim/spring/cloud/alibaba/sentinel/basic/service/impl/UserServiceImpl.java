package com.kim.spring.cloud.alibaba.sentinel.basic.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.kim.spring.cloud.alibaba.sentinel.basic.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author kim
 * @Since 2021/8/7
 */
@Service
public class UserServiceImpl implements UserService {


    private static Integer count=0;

    @Override
     /**
     *定义资源：资源名是hello，限流降级处理方法是:blockExceptionHandler,要求该方法的返回值和参数和原方法
      * 一致，并且和业务方法在同一个类，public,只是参数多了一个BlockException，用于接收业务方法抛出的限流异常
      * 异常降级处理方法是fallbackHandler，要求该方法的返回值和参数和原方法一致，
      * 并且和业务方法在同一个类，public,只是参数多了一个Throwable，用于接受业务方法抛出的异常
     */
    @SentinelResource(value = "hello",blockHandler = "blockExceptionHandler",
            fallback = "fallbackHandler",exceptionsToIgnore = {IllegalArgumentException.class})
    public String hello(String name) {
        if(name.equals("exception")){
            throw new RuntimeException("抛出异常");
        }else if(name.equals("ignore")){
            throw new IllegalArgumentException("参数错误");
        }
        return name+": "+count++;
    }

    public String blockExceptionHandler(String name, BlockException blockException){
        blockException.printStackTrace();
        return name+": hello,sentinel block";
    }

    public String fallbackHandler(String name,Throwable throwable){
        throwable.printStackTrace();
        return name+": hello,sentinel fallback";
    }


    /**
     * 使用blockHandlerClass fallbackclass，如果blockHandler或者fallback的方法不在本类，则
     * 通过blockHandlerClass fallbackclass指定具体的方法所在的类，并且该方法在该类中一定要是静态方法
     * */
    @SentinelResource(value="hi",blockHandler = "blockExceptionHandler",blockHandlerClass = BlockFallbackHandler.class,
            fallback = "fallbackHandler",fallbackClass = BlockFallbackHandler.class)
    public String hi(){
        return "hi,sentinel";
    }


    public static class BlockFallbackHandler{

        public static String blockExceptionHandler(BlockException blockException){
            blockException.printStackTrace();
            return "hi,sentinel block";
        }

        public static String fallbackHandler(Throwable throwable){
            throwable.printStackTrace();
            return "hi,sentinel fallback";
        }
    }

    /**
     * defaultFallback 的使用，如果没有配置fallback，则defaultFallback生效，如果两个都配置了
     * 则fallback生效,默认配置的defaultFallback的方法在本类并且是public，若不在本类，
     * 可以配置fallbackClass指定，方法所在的类，并且该方法要是静态方法，方法返回值和原方法一致，
     * 参数为空，可以多加一个Throwable类型的参数
     * */
    @SentinelResource(value="how",defaultFallback = "defaultFallback")
    public void how(){
        System.out.println("how sentinel");
    }

    public void defaultFallback(Throwable throwable){
        throwable.printStackTrace();
        System.out.println("how fallback");
    }


}
