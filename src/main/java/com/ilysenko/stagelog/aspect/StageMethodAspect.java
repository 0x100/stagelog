/*
 * MIT License
 *
 * Copyright (c) 2020 Ilya Lysenko
 *
 * A short and simple permissive license with conditions only requiring preservation of copyright and license notices.
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */
package com.ilysenko.stagelog.aspect;

import com.ilysenko.stagelog.annotation.Stage;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class StageMethodAspect {
    @Pointcut("@annotation(stage) && execution(@com.ilysenko.stagelog.annotation.Stage * *.*(..))")
    public void methodPointcut(Stage stage) {
    }

    @Around(value = "methodPointcut(stage)", argNames = "joinPoint,stage")
    public void before(ProceedingJoinPoint joinPoint, Stage stage) throws Throwable {
        StopWatch stopWatch = beforeStart(joinPoint, stage);
        joinPoint.proceed();
        afterFinish(joinPoint, stage, stopWatch);
    }

    private StopWatch beforeStart(ProceedingJoinPoint joinPoint, Stage stage) {
        StopWatch stopWatch = null;
        if (stage.trackTime()) {
            stopWatch = StopWatch.createStarted();
        }
        String startMessage = getStartMessage(stage);
        if (isEmpty(startMessage)) {
            startMessage = joinPoint.getSignature().toShortString() + "...";
        }
        log(startMessage);
        return stopWatch;
    }

    private void afterFinish(ProceedingJoinPoint joinPoint, Stage stage, StopWatch stopWatch) {
        String finishMessage = stage.finishMessage();
        if (isEmpty(finishMessage) && isEmpty(getStartMessage(stage))) {
            finishMessage = joinPoint.getSignature().toShortString() + " finished";
        }
        if (stopWatch != null) {
            stopWatch.stop();
            finishMessage += " in " + stopWatch.toString();
        }
        log(finishMessage);
    }

    private boolean isEmpty(String value) {
        return "".equals(value);
    }

    private String getStartMessage(Stage stage) {
        return isEmpty(stage.value()) ? stage.startMessage() : stage.value();
    }

    private void log(String message) {
        if (!isEmpty(message)) {
            System.out.println(message);
        }
    }
}