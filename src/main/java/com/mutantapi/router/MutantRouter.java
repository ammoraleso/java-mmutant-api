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
                boolean isMutant = mutantService.validateMutant(req.body());
                final ResponseMutant response = new ResponseMutant();
                response.setMutant(isMutant);
                return response;
            } catch (Exception ex) {
                res.status(400);
                final ResponseError responseError = new ResponseError(ex);
                return responseError;
            }
        }, json());
    }
}
