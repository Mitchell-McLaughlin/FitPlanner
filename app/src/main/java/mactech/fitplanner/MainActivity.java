package mactech.fitplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showSchedule(View view) {
        Intent schedule = new Intent(this, Schedule.class);
        startActivity(schedule);
    }
    public void ShowMealPlan(View view){
        Intent mealPlan = new Intent(this, MealPlan.class);
        startActivity(mealPlan);
    }
}
