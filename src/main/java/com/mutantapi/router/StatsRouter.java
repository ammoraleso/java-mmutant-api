package com.mutantapi.router;

import com.mutantapi.dto.StatsDTO;
import com.mutantapi.handlererror.ResponseError;
import com.mutantapi.services.StateServiceImpl;

import static com.mutantapi.utils.JsonUtil.json;
import static spark.Spark.get;

public class StatsRouter extends GeneralRouter {

    public StatsRouter(StateServiceImpl statsService) {
        get("/stats", (req, res) -> {
            try {
                final StatsDTO responseStats = statsService.getStats();
                if(responseStats==null){
                    res.status(401);
                }
                return responseStats;
            } catch (Exception ex) {
                res.status(400);
                final ResponseError responseError = new ResponseError(ex);
                return responseError;
            }
        },json());
    }
}
