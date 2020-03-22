/*
 * MIT License
 *
 * Copyright (c) 2020 Ilya Lysenko
 *
 * A short and simple permissive license with conditions only requiring preservation of copyright and license notices.
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */
package com.ilysenko.stagelog;

import com.ilysenko.stagelog.annotation.Stage;

public class Example {

    @Stage(startMessage = "Starting...", finishMessage = "Application started")
    public void start() {
        init();
        show();
    }

    @Stage(startMessage = "Initialization...", finishMessage = "Initialization finished", trackTime = true)
    private void init() {
        loadConfig();
        loadData();
        applyConfig();
    }

    @Stage("Load config")
    private void loadConfig() {
        // ...
        sleep(100);
    }

    @Stage(trackTime = true)
    private void loadData() {
        // ...
        sleep(200);
    }

    @Stage(startMessage = "Apply config")
    private void applyConfig() {
        // ...
        sleep(300);
    }

    @Stage(value = "Show user interface")
    private void show() {
        // ...
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
