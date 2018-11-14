package com.example.demo.quartz;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Log4j2
public class TestTask {

    private static final Logger logger = LoggerFactory.getLogger(TestTask.class);

    public void run(){
        logger.info("（普通）定时器执行了！！！");
    }

}
