package com.example.fibro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Activity for showing list of saved days
 */
public class GraphDetailActivity extends AppCompatActivity {
    ListView graphLs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_detail);
        graphLs = findViewById(R.id.graphList);
        graphLs.setAdapter(new ArrayAdapter<Day>( //set a ArrayAdapter and get the days array
                this,
                android.R.layout.simple_list_item_1,
                Days.getInstance().getDays()
        ));
        graphLs.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapterView, View v, int i, long l){
                Intent nextActivity = new Intent(GraphDetailActivity.this, DayDetailActivity.class);
                nextActivity.putExtra("EXTRA", i); //Send the index of tapped item to DayDetailActivity
                startActivity(nextActivity);
            }
        });
    }
}