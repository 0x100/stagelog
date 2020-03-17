package com.ilysenko.stagelog;

import com.ilysenko.stagelog.annotation.Stage;

public class StagesExample {

    @Stage(startMessage = "Starting...", finishMessage = "Started")
    public void start() {
        init();
        show();
    }

    @Stage(startMessage = "Initialization...", finishMessage = "Initialized")
    private void init() {
        loadConfig();
        loadData();
    }

    @Stage("Loading configuration...")
    private void loadConfig() {
        // ...
    }

    @Stage("Loading data...")
    private void loadData() {
        // ...
    }

    @Stage("Showing user interface...")
    private void show() {
        // ...
    }
}
