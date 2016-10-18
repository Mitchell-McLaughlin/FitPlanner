package mactech.fitplanner;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Mitchell on 8/11/2016.
 */
public class TaskDao extends AppCompatActivity {
    MyDBHandler dbHandler;

    public TaskDao(){
        dbHandler = new MyDBHandler(this, null, null, 1);
    }
    //Take in mock context for Unit Tests
    public TaskDao(Context context)
    {
        dbHandler = new MyDBHandler(context, null, null, 1);
    }

    public ArrayList<Task> getTasksByDay(String day){
        return dbHandler.getTaskNames(day);
    }

    public int getTaskCount(){
        return dbHandler.getTaskCount();
    }


}
