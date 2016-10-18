package mactech.fitplanner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Schedule extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText mitchsInput;
    TextView taskText;
    MyDBHandler dbHandler;
    ListAdapter taskAdapter;
    TaskDao taskDao;
    Spinner daySpinner;
    ArrayList<Task> tasks;
    ListView taskListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_schedule);
        taskDao = new TaskDao();
        daySpinner = (Spinner) findViewById(R.id.spinner);
        final AdapterView.OnItemSelectedListener l = daySpinner.getOnItemSelectedListener();

        daySpinner.setOnItemSelectedListener(l);
        mitchsInput = (EditText) findViewById(R.id.inputTask);
       // taskText = (TextView) findViewById(R.id.displayText);
        dbHandler = new MyDBHandler(this, null, null, 1);
        tasks = dbHandler.getTaskNames("Sunday");
        taskAdapter = new ScheduleAdapter(this, tasks);
        taskListView = (ListView) findViewById(R.id.listView);
        if (taskListView != null) {
            taskListView.setAdapter(taskAdapter);
        }

        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();
                Toast.makeText(getBaseContext(),
                        "You have selected item : " + daySpinner.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT).show();
                UpdateListViewByDay(daySpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    public void UpdateListViewByDay(String day){
        tasks = dbHandler.getTaskNames(day);
        taskAdapter = new ScheduleAdapter(this, tasks);
        taskListView.setAdapter(taskAdapter);
    }


    public void addTask(View view) {
        Task task = new Task(mitchsInput.getText().toString());
        Spinner daySpinner = (Spinner) findViewById(R.id.spinner);
        task.set_day(daySpinner.getSelectedItem().toString());
        dbHandler.addTask(task);
        UpdateListViewByDay(daySpinner.getSelectedItem().toString());
        //printDatabase();


    }

    public void deleteTask(View view)
    {
        String inputText = mitchsInput.getText().toString();
        dbHandler.deleteTask(inputText);
        UpdateListViewByDay(daySpinner.getSelectedItem().toString());
    }

    public void printDatabase()
    {
        taskText.setText(dbHandler.databaseToString());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        UpdateListViewByDay(daySpinner.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        UpdateListViewByDay(daySpinner.getSelectedItem().toString());
    } //android forces us to override this.
}
