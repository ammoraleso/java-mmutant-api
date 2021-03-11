package com.mutantapi.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.conversions.Bson;

public abstract class AbstractDao<T> {

    protected static MongoClient client;
    protected static MongoDatabase database;

    protected abstract T add(final T object);
    protected abstract Bson createDocument(final T object);

    public static void initializeDataBase(){
        try {
            Dotenv dotenv = Dotenv.load();
            MongoClientURI mongoClientURI = new MongoClientURI(dotenv.get("DATABASE_CONNECTION"));
            client = new MongoClient(mongoClientURI);
            database = client.getDatabase(dotenv.get("DATABASE_NAME"));
        }catch (Exception e){
            System.err.println("Error inicializando la base de datos: " + e);
        }
    }



}
