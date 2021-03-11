package com.mutantapi;


import com.mutantapi.database.AbstractDao;
import com.mutantapi.router.HealthRouter;
import com.mutantapi.router.MutantRouter;
import com.mutantapi.services.MutantServiceImpl;

import static spark.Spark.port;

public class App {
    public static void main(String[] args) {
        port(3000);

        //Initialize DB
        AbstractDao.initializeDataBase();

        new HealthRouter();
        new MutantRouter(new MutantServiceImpl());

    }
}
