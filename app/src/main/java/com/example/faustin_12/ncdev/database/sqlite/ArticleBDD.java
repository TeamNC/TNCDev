package com.example.faustin_12.ncdev.database.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.faustin_12.ncdev.model.sample.Enclosure;
import com.example.faustin_12.ncdev.model.sample.FeedItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FAUSTIN-12 on 23/09/2016.
 */
public class ArticleBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "article.db";

    private static final String TABLE_ARTICLES = "table_articles";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_TITRE = "Titre";
    private static final int NUM_COL_TITRE = 1;
    private static final String COL_DESCRIPTION = "Description";
    private static final int NUM_COL_DESCRIPTION = 2;
    private static final String COL_IMAGE_URL = "Image";
    private static final int NUM_COL_IMAGE_URL = 3;
    private static final String COL_IMAGE_URLI = "ImageI";
    private static final int NUM_COL_IMAGE_URLI = 4;
    private static final String COL_PUBDATE = "Date";
    private static final int NUM_COL_PUBDATE = 5;
    private static final String COL_COMMENTSNOMBER = "NBComentaire";
    private static final int NUM_COL_COMMENTSNOMBER = 6;


    private SQLiteDatabase bdd;
    private ArticleDataBase maBaseSQLite;

    public ArticleBDD(Context context){
        //On crée la BDD et sa table
        maBaseSQLite = new ArticleDataBase(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public void ugrade(){
        maBaseSQLite.onUpgrade(bdd,VERSION_BDD,VERSION_BDD );
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }
    public void setBDD(SQLiteDatabase database){
        this.bdd = database;
    }

    public long insertArticle(FeedItem article){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_TITRE, article.getTitle());
        values.put(COL_DESCRIPTION, article.getDescription());
        values.put(COL_IMAGE_URL, article.getEnclosure().getEnclosureLink());
        values.put(COL_IMAGE_URLI, article.getInternalImageUrl());
        values.put(COL_PUBDATE, article.getPubDate());
        values.put(COL_COMMENTSNOMBER, article.getComments());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_ARTICLES, null, values);
    }

    public int updateArticle(int id, FeedItem article){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_TITRE, article.getTitle());
        values.put(COL_DESCRIPTION, article.getDescription());
        values.put(COL_IMAGE_URL, article.getEnclosure().getEnclosureLink());
        values.put(COL_IMAGE_URLI, article.getInternalImageUrl());
        values.put(COL_PUBDATE, article.getPubDate());
        values.put(COL_COMMENTSNOMBER, article.getComments());
        return bdd.update(TABLE_ARTICLES, values, COL_ID + " = " +id, null);
    }

    public int removeArticleWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_ARTICLES, COL_ID + " = " +id, null);
    }

    public FeedItem getArticleWithTitre(String titre){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_ARTICLES, new String[] {COL_ID, COL_TITRE, COL_DESCRIPTION, COL_IMAGE_URL, COL_IMAGE_URLI, COL_PUBDATE, COL_COMMENTSNOMBER}
                , COL_TITRE + " LIKE \"" + titre +"\"", null, null, null, null);
        return cursorToArticle(c);
    }

    public FeedItem getArticleWithID(int id){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_ARTICLES, new String[] {COL_ID, COL_TITRE, COL_DESCRIPTION, COL_IMAGE_URL, COL_IMAGE_URLI, COL_PUBDATE, COL_COMMENTSNOMBER}
                , COL_ID + " LIKE \"" + id +"\"", null, null, null, null);
        return cursorToArticle(c);
    }

    public List<FeedItem> getAllArticle() {
        List<FeedItem> articles = new ArrayList<>();

        Cursor cursor = bdd.query(TABLE_ARTICLES,
                new String[] {COL_ID, COL_TITRE, COL_DESCRIPTION, COL_IMAGE_URL, COL_IMAGE_URLI, COL_PUBDATE, COL_COMMENTSNOMBER}
                , null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            FeedItem article = cursorToArticleIni(cursor);
            articles.add(article);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return articles;
    }

    //Cette méthode permet de convertir un cursor en un livre
    private FeedItem cursorToArticle(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        FeedItem article = new FeedItem();
        Enclosure tempEnclosure = new Enclosure();
        tempEnclosure.setEnclosureType("Default");
        tempEnclosure.setEnclosureLink(c.getString(NUM_COL_IMAGE_URL));
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        article.setId(c.getInt(NUM_COL_ID));
        article.setTitle(c.getString(NUM_COL_TITRE));
        article.setDescription(c.getString(NUM_COL_DESCRIPTION));
        article.setEnclosure(tempEnclosure);
        article.setPubDate(c.getString(NUM_COL_PUBDATE));
        article.setComments(c.getString(NUM_COL_COMMENTSNOMBER));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return article;
    }

    private FeedItem cursorToArticleIni(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        FeedItem article = new FeedItem();
        Enclosure tempEnclosure = new Enclosure();
        tempEnclosure.setEnclosureType("Default");
        tempEnclosure.setEnclosureLink(c.getString(NUM_COL_IMAGE_URL));
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        article.setId(c.getInt(NUM_COL_ID));
        article.setTitle(c.getString(NUM_COL_TITRE));
        article.setDescription(c.getString(NUM_COL_DESCRIPTION));
        article.setEnclosure(tempEnclosure);
        article.setPubDate(c.getString(NUM_COL_PUBDATE));
        article.setComments(c.getString(NUM_COL_COMMENTSNOMBER));

        //On retourne le livre
        return article;
    }
}
