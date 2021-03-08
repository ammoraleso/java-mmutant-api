package com.mutantapi.router;

import com.mutantapi.enumns.MutantEnum;
import com.mutantapi.handlererror.ResponseError;
import com.mutantapi.response.ResponseMutant;
import com.mutantapi.services.MutantServiceImpl;

import static com.mutantapi.utils.JsonUtil.json;
import static com.mutantapi.utils.JsonUtil.toJson;
import static spark.Spark.*;

public class MutantRouter extends GeneralRouter {

    public MutantRouter(MutantServiceImpl mutantService) {
        post("/mutant", (req, res) -> {
            try {
                final ResponseMutant responseMutant = mutantService.validateMutant(req.body());
                if(!responseMutant.isMutant()){
                    res.status(401);
                }
                return responseMutant;
            } catch (Exception ex) {
                res.status(400);
                final ResponseError responseError = new ResponseError(ex);
                return responseError;
            }
        }, json());
    }
}
