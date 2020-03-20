package com.ilysenko.stagelog.aspect;

import com.ilysenko.stagelog.annotation.Stage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class StageMethodAspect {
    @Pointcut("@annotation(stage) && execution(@com.ilysenko.stagelog.annotation.Stage * *.*(..))")
    public void methodPointcut(Stage stage) {
    }

    @Before(value = "methodPointcut(stage)", argNames = "jp,stage")
    public void before(JoinPoint jp, Stage stage) {
        String startMessage = getStartMessage(stage);
        if (isEmpty(startMessage)) {
            startMessage = jp.getSignature().toShortString() + "...";
        }
        System.out.println(startMessage);
    }

    @AfterReturning(pointcut = "methodPointcut(stage)", argNames = "jp,stage")
    public void after(JoinPoint jp, Stage stage) {
        String finishMessage = stage.finishMessage();
        if (isEmpty(finishMessage) && isEmpty(getStartMessage(stage))) {
            finishMessage = jp.getSignature().toShortString() + " finished";
        }
        System.out.println(finishMessage);
    }

    private boolean isEmpty(String value) {
        return "".equals(value);
    }

    private String getStartMessage(Stage stage) {
        return isEmpty(stage.value()) ? stage.value() : stage.startMessage();
    }

}