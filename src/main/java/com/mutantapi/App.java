package com.mutantapi;


import com.mutantapi.router.HealthRouter;

import static spark.Spark.port;

public class App {
    public static void main(String[] args) {
        port(3000);
        new HealthRouter();
    }
}
