package com.example.testto_doapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText taskEditText;
    private Button addButton;
    private Button deleteButton;
    private ListView taskListView;
    private ArrayList<String> taskList;
    private ArrayAdapter<String> taskAdapter;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskEditText = findViewById(R.id.taskEditText);
        addButton = findViewById(R.id.addButton);
        deleteButton = findViewById(R.id.deleteButton);
        taskListView = findViewById(R.id.taskListView);

        taskList = new ArrayList<>();
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        taskListView.setAdapter(taskAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTask();
            }
        });

        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                selectTask(position);
            }
        });

        taskListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    private void addTask() {
        String task = taskEditText.getText().toString();
        if (!task.isEmpty()) {
            taskList.add(task);
            taskAdapter.notifyDataSetChanged();
            taskEditText.setText("");
        }
    }

    private void deleteTask() {
        if (selectedPosition != -1) {
            taskList.remove(selectedPosition);
            taskAdapter.notifyDataSetChanged();
            selectedPosition = -1;
            taskListView.clearChoices();
        }
    }

    private void selectTask(int position) {
        if (position != selectedPosition) {
            selectedPosition = position;
        } else {
            selectedPosition = -1;
        }
        taskAdapter.notifyDataSetChanged();
    }
}