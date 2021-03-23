package com.mutantapi.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mutantapi.dto.StatsDTO;
import com.mutantapi.enumns.MutantEnum;

import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;

public class StatsDao extends AbstractDao<StatsDTO> {

    final static MongoCollection<StatsDTO> statsCollection = database.getCollection("stats",StatsDTO.class);

    @Override
    public StatsDTO add(final StatsDTO statsDTO){
        try {
            final Bson filter = Filters.eq(MutantEnum.ID_STATS.getName(), statsDTO.getId_stats());
            final Bson update =  createDocument(statsDTO);
            final UpdateOptions options = new UpdateOptions().upsert(true);
            database.getCollection("stats")
                    .updateOne(filter, update, options);
            return statsDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Error saving DNA in database");
        }
    }

    @Override
    protected Bson createDocument(final StatsDTO statsDTO) {
        return new Document("$set",
                new Document()
                        .append("id_stats", statsDTO.getId_stats())
                        .append("count_mutants_dna", statsDTO.getCount_mutants_dna())
                        .append("count_humans_dna", statsDTO.getCount_humans_dna())
                        .append("ratio", statsDTO.getRatio()));
    }

    public StatsDTO findAll() {
        return statsCollection.find(eq("id_stats",1)).first();
    }
}
