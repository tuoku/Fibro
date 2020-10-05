package com.example.fibro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.Series;

import java.text.Format;
import java.text.SimpleDateFormat;

public class GraphActivity extends AppCompatActivity {
    GraphView graph;
    public static SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        graph = findViewById(R.id.graph);
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    Format formatter = new SimpleDateFormat("dd-MM");
                    return formatter.format(value);
                }
                return super.formatLabel(value, isValueX);
            }
        });

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxY(5.0);
        graph.getViewport().setMinY(1.0);
        graph.getViewport().setMaxX(Graph.getInstance().series.getLowestValueX() + (1000*60*60*24*7));
        graph.getViewport().setMinX(Graph.getInstance().series.getLowestValueX());
        graph.getViewport().setScrollable(true);
        // graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(7);
        graph.getGridLabelRenderer().setHumanRounding(true);
        graph.addSeries(Graph.getInstance().getData());
    }
}