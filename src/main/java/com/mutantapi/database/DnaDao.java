package com.mutantapi.database;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import com.mutantapi.dto.DnaDTO;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;

public class DnaDao extends AbstractDao<DnaDTO> {

    final static MongoCollection<DnaDTO> dnaCollection = database.getCollection("dna",DnaDTO.class);

    @Override
    public DnaDTO add(final DnaDTO dnaDTO){
        try {
            final Bson filter = eq("dna", dnaDTO.getDna());
            final Bson update =  createDocument(dnaDTO);
            final UpdateOptions options = new UpdateOptions().upsert(true);
            dnaCollection.updateOne(filter, update, options);
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
                        .append("isMutant", dnaDTO.getIsMutant()));
    }

    public DnaDTO findDna(final String dna) {

        final DnaDTO dnaDTO = dnaCollection.find(eq("dna",dna)).first();
        return dnaDTO;
    }
}
