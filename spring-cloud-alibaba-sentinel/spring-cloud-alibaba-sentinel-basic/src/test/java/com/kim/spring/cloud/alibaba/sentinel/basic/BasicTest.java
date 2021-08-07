package com.kim.spring.cloud.alibaba.sentinel.basic;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author kim
 * @Since 2021/8/7
 */
public class BasicTest {

    private Integer i=1;

    @Test
    public void sentinelBasic() throws BlockException {
        //加载定义好的规则
        initRules();
        for (;;i++){
            try(Entry entry= SphU.entry("hello sentinel")){
                bussinessResource();
                entry.exit();
            }catch (BlockException e){
                e.printStackTrace();
                System.out.println("每秒超过20次之后的操作");
                throw e;
            }
        }
    }

    //定义规则
    private void initRules(){
        List<FlowRule> flowRules=new ArrayList<>();
        FlowRule flowRule=new FlowRule();
        //定义要进行限流的业务资源名称
        flowRule.setResource("hello sentinel");
        //设定每秒最多20个请求，多出来的请求将会抛出异常
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(20);
        flowRules.add(flowRule);
        //加载规则
        FlowRuleManager.loadRules(flowRules);
    }

    //被限流的业务资源，可以是一个方法，也可以是一个接口，也可以是一段代码块
    private void bussinessResource(){

        System.out.println("handle bussiness success:"+i);

    }



}
