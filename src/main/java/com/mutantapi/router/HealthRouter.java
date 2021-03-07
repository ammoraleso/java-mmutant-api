package com.mutantapi.router;

import com.mutantapi.enumns.MutantEnum;
import com.mutantapi.handlererror.ResponseError;

import static com.mutantapi.utils.JsonUtil.json;
import static com.mutantapi.utils.JsonUtil.toJson;
import static spark.Spark.*;

public class HealthRouter extends GeneralRouter {

    public HealthRouter() {
        get("/health", (req, res) -> MutantEnum.SERVER_UP.getName(),json());
    }
}
