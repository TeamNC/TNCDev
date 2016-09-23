package com.example.faustin_12.ncdev.database.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by FAUSTIN-12 on 23/09/2016.
 */
public class ArticleDataBase extends SQLiteOpenHelper {
    //private static String DB_PATH = "/data/data/com.example.faustin_12.ncdev/databases/";
    //private static final String NOM_BDD = "article.db";
    private static final String TABLE_ARTICLES = "table_articles";
    private static final String COL_ID = "ID";
    private static final String COL_TITRE = "Titre";
    private static final String COL_DESCRIPTION = "Description";
    private static final String COL_IMAGE_URL = "Image";
    private static final String COL_PUBDATE = "Date";
    private static final String COL_COMMENTSNOMBER = "NBComentaire";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_ARTICLES + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TITRE + " TEXT NOT NULL DEFAULT '', "
            + COL_DESCRIPTION + " TEXT NOT NULL DEFAULT '', " + COL_IMAGE_URL + " TEXT NOT NULL DEFAULT '', "
            + COL_PUBDATE + " VARCHAR, " + COL_COMMENTSNOMBER + " VARCHAR);";

    public ArticleDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD

        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTICLES + ";");
        onCreate(db);
    }
}
