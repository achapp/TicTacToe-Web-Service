package com.achappell;

import com.achappell.resources.TicTacToeResource;
import com.achappell.health.TicTacToeHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TicTacToeApplication extends Application<TicTacToeConfiguration> {
    public static void main(String[] args) throws Exception {
        new TicTacToeApplication().run(args);
    }

    @Override
    public String getName() {
        return "tictactoe";
    }

    @Override
    public void initialize(Bootstrap<TicTacToeConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(TicTacToeConfiguration configuration,
                    Environment environment) {

        final TicTacToeResource tttresource = new TicTacToeResource();
        environment.jersey().register(tttresource);

        final TicTacToeHealthCheck healthCheck = new TicTacToeHealthCheck();
        environment.healthChecks().register("running", healthCheck);
    }
}