package com.sparta.schedules.entitydtotest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestTimer {
    //시간 힘수
    static long beforeTime_OverAll;
    static long beforeTime_EachTest;
    static long afterTime_OverAll;
    static long afterTime_EachTest;
    static int testnum = 1;
    //


    // before after 테스트 연습/실용
    @BeforeAll
    static void setBeforeTime_OverAll(){
        beforeTime_OverAll = System.currentTimeMillis();
    }
    @AfterAll
    static void setAfterTime_OverAll(){
        afterTime_OverAll = System.currentTimeMillis();
        System.out.println("모든 실험 걸린 시간 : "+(afterTime_OverAll-beforeTime_OverAll));
    }
    @BeforeEach
    void setBeforeTime_EachTest(){
        beforeTime_EachTest = System.currentTimeMillis();
    }
    @AfterEach
    void setAfterTime_EachTest(){
        afterTime_EachTest = System.currentTimeMillis();
        System.out.println("이번 실험 : "+testnum+" 번, 걸린 시간 : "+(afterTime_EachTest-beforeTime_EachTest));
        testnum++;
    }
}
