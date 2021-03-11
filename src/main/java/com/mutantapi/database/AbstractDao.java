package com.mutantapi.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.mongodb.morphia.Datastore;

public abstract class AbstractDao<T> {

    protected static MongoClient client;
    protected static MongoDatabase database;

    protected abstract T add(final T object);
    protected abstract Bson createDocument(final T object);


    public static void initializeDataBase(){
        try {
            final Dotenv dotenv = Dotenv.load();
            final MongoClientURI mongoClientURI = new MongoClientURI(dotenv.get("DATABASE_CONNECTION"));
            client = new MongoClient(mongoClientURI);
            CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration
                    .CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                            org.bson.codecs.configuration.CodecRegistries
                                    .fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            database = client.getDatabase(dotenv.get("DATABASE_NAME")).withCodecRegistry(pojoCodecRegistry);
        }catch (Exception e){
            System.err.println("Error inicializando la base de datos: " + e);
        }
    }



}
