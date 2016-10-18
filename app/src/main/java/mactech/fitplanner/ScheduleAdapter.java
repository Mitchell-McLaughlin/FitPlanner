package mactech.fitplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import mactech.fitplanner.R;

/**
 * Created by Mitchell on 8/3/2016.
 */
public class ScheduleAdapter extends ArrayAdapter<Task> {
        public ScheduleAdapter(Context context, ArrayList<Task> tasks) {
            super(context, R.layout.task_row, tasks);
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater tasksInflater = LayoutInflater.from(getContext());
        View customView = tasksInflater.inflate(R.layout.task_row, parent, false);

        Task singleTask = getItem(position);
        TextView taskName = (TextView) customView.findViewById(R.id.taskName);
        TextView taskDay = (TextView) customView.findViewById(R.id.taskDate);

        taskName.setText(singleTask.get_taskName());
        taskDay.setText(singleTask.get_Day());


        return customView;
    }
}
