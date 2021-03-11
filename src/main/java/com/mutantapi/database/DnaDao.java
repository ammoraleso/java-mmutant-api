package com.mutantapi.database;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mutantapi.dto.DnaDTO;
import org.bson.Document;
import org.bson.conversions.Bson;

public class DnaDao extends AbstractDao<DnaDTO> {

    @Override
    public DnaDTO add(final DnaDTO dnaDTO){
        try {
            final Bson filter = Filters.eq("dna", dnaDTO.getDna());
            final Bson update =  createDocument(dnaDTO);
            final UpdateOptions options = new UpdateOptions().upsert(true);
            database.getCollection("dna")
                    .updateOne(filter, update, options);
            return dnaDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Error saving DNA in database");
        }
    }

    @Override
    protected Bson createDocument(final DnaDTO dnaDTO) {
        return new Document("$set",
                new Document()
                        .append("dna", dnaDTO.getDna())
                        .append("isMutant", dnaDTO.isMutant()));
    }

}
