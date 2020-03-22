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

    @Stage(value = "Load config")
    private void loadConfig() {
        // ...
        sleep(123);
    }

    @Stage(value = "Load data")
    private void loadData() {
        // ...
        sleep(456);
    }

    @Stage(value = "Apply config")
    private void applyConfig() {
        // ...
        sleep(789);
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
