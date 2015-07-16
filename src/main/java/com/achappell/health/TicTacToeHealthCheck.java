package com.achappell.health;

import com.codahale.metrics.health.HealthCheck;

public class TicTacToeHealthCheck extends HealthCheck{

    public TicTacToeHealthCheck() {
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}

