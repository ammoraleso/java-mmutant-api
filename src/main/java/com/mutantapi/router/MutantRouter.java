package com.mutantapi.router;

import com.mutantapi.enumns.MutantEnum;
import com.mutantapi.utils.JsonUtil;

import static com.mutantapi.utils.JsonUtil.json;
import static spark.Spark.get;

public class MutantRouter extends GeneralRouter {

    public MutantRouter() {
        get("/mutant", (req, res) -> {
            try {
                final String dna = req.body();
            } catch (Exception ex) {

            }
            return null;
        }, json());
    }
}
