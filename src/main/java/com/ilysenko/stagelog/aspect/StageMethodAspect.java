package com.ilysenko.stagelog.aspect;

import com.ilysenko.stagelog.annotation.Stage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class StageMethodAspect {

    @Before("@annotation(stage)")
    public void before(JoinPoint jp, Stage stage) {
        String startMessage = getStartMessage(stage);
        if (isEmpty(startMessage)) {
            startMessage = "Method " + jp.getSignature().toShortString() + " started...";
        }
        System.out.println(startMessage);
    }

    @AfterReturning("@annotation(stage)")
    public void after(JoinPoint jp, Stage stage) {
        String finishMessage = stage.finishMessage();
        if (isEmpty(finishMessage) && isEmpty(getStartMessage(stage))) {
            finishMessage = "Method " + jp.getSignature().toShortString() + " finished";
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