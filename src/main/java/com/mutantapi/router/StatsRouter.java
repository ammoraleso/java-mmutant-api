package com.mutantapi.router;

import com.mutantapi.dto.StatsDTO;
import com.mutantapi.enumns.MutantEnum;
import com.mutantapi.handlererror.ResponseError;
import com.mutantapi.response.ResponseMutant;
import com.mutantapi.services.StateServiceImpl;
import com.sun.org.glassfish.external.statistics.impl.StatsImpl;

import static com.mutantapi.utils.JsonUtil.json;
import static spark.Spark.get;

public class StatsRouter extends GeneralRouter {

    private StateServiceImpl statsService;

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
