package com.mutantapi.router;


import com.mutantapi.enumns.MutantEnum;
import com.mutantapi.handlererror.ResponseError;

import static com.mutantapi.utils.JsonUtil.toJson;
import static spark.Spark.after;
import static spark.Spark.exception;

public class GeneralRouter {

    protected GeneralRouter(){
        after((req, res) -> {
            res.type(MutantEnum.RESPONSE_TYPE.getName());
        });

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(403);
            res.body(toJson(new ResponseError(e)));
        });
    }
}
