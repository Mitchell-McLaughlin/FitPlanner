package mactech.fitplanner;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Mitchell on 8/3/2016.
 */
public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 12;
    private static final String DATABASE_NAME = "tasks.db";
    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TASKNAME = "_taskName";
    public static final String COLUMN_DAY = "_day";
    public static int taskCount;
/*
    public static final String TABLE_CREDENTIALS = "credentials";
    public static final String COLUMN_USERID = "_userid";
    public static final String COLUMN_USERNAME = "_username";
    public static final String COLUMN_PASSWORD = "_password";
*/
    public static final String TABLE_RECIPES = "recipes";
    public static final String COLUMN_RECIPEID = "_recipeid";
    public static final String COLUMN_RECIPENAME = "_recipename";

    public static final String TABLE_INGREDIENTS = "ingredients";
    public static final String COLUMN_INGREDIENTID = "_ingredientid";
    public static final String COLUMN_INGREDIENTNAME = "_ingredientname";
    public static final String COLUMN_INGREDIENTAMOUNT = "_ingredientamount";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    /*
    DB
    Tasks - Id, taskname, day
    Credentials - Userid, username, password,



     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_TASKS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_TASKNAME + " TEXT , " +
                COLUMN_DAY + " TEXT " +
                ");";
        db.execSQL(query);
//        String CreateCredentials = "CREATE TABLE " + TABLE_CREDENTIALS + "(" +
//                COLUMN_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
//                COLUMN_USERNAME + " TEXT , " +
//                COLUMN_PASSWORD + " TEXT " +
//                ");";
//        db.execSQL(CreateCredentials);

        String CreateRecipes = "CREATE TABLE " + TABLE_RECIPES + "(" +
                COLUMN_RECIPEID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_RECIPENAME + " TEXT " +
                ");";

        db.execSQL(CreateRecipes);

        String CreateIngredients = "CREATE TABLE " + TABLE_INGREDIENTS + "(" +
                COLUMN_INGREDIENTID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_INGREDIENTNAME + " TEXT " +
                COLUMN_INGREDIENTAMOUNT + " TEXT " +
                ");";

        db.execSQL(CreateIngredients);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREDENTIALS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);

        onCreate(db);
    }

    //Add new row to the database
    public void addTask(Task task)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASKNAME, task.get_taskName());
        values.put(COLUMN_DAY, task.get_Day());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TASKS, null, values);
        db.close();
    }

    //Delete a product from the database
    public void deleteTask(String taskName)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TASKS + " WHERE " + COLUMN_TASKNAME + " =\"" + taskName + "\";");
    }

    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TASKS + " WHERE 1;";
        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        if(c!=null)
        {
            if(c.moveToNext())
            {
                do{
                    dbString += c.getString(c.getColumnIndex(COLUMN_TASKNAME));
                        dbString += "\n";

                }while(c.moveToNext());
            }
        }
        c.moveToFirst();
        db.close();

        return dbString;
    }
    public ArrayList<Task> tasksToArray(){String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Task> taskList = new ArrayList<Task>();
        Task iterator;
        String query = "SELECT * FROM " + TABLE_TASKS + " WHERE 1;";
        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        if(c!=null)
        {
            if(c.moveToNext())
            {
                do{
                    iterator = new Task(c.getString(c.getColumnIndex(COLUMN_TASKNAME)));
                    iterator.set_day(c.getString(c.getColumnIndex(COLUMN_DAY)));
                    taskList.add(iterator);
                }while(c.moveToNext());
            }
        }
        c.moveToFirst();
        db.close();

        return taskList;
    }


    public ArrayList<Task> getTaskNames(String day) {
        Task iterator;
        ArrayList<Task> taskList = new ArrayList<Task>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TASKS + " WHERE " + COLUMN_DAY + " LIKE\"" + day + "\";";
        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        if (c != null) {
            if (c.moveToNext()) {
                do {
                    iterator = new Task(c.getString(c.getColumnIndex(COLUMN_TASKNAME)));
                    iterator.set_day(c.getString(c.getColumnIndex(COLUMN_DAY)));
                    taskList.add(iterator);
                } while (c.moveToNext());
            }
        }
        c.moveToFirst();
        db.close();

        return taskList;
    }



    public int getTaskCount() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT count(*) FROM " + TABLE_TASKS + " WHERE 1";
        Cursor c = db.rawQuery(query,  new String[]{String.valueOf(1)});
        while(c.moveToFirst())
        {
            taskCount = c.getInt(0);
        }

        return taskCount;
    }


}
