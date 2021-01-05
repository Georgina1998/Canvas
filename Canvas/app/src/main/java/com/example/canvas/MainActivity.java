package com.example.canvas;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.canvas.Adapters.ToolsAdapter;
import com.example.canvas.Common.Common;
import com.example.canvas.Interface.ToolsListener;
import com.example.canvas.Model.ToolsItem;
import com.example.canvas.Widget.PaintView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ToolsListener {

    PaintView mPaintView;
    int colorBackground, colorBrush;
    int brushSize, eraserSize;
    CustomShape mCustomShape;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intTools();

    }

    private void intTools() {

        colorBackground = Color.WHITE;
        colorBrush = Color.BLACK;

        eraserSize = brushSize = 12;
        mPaintView = findViewById(R.id.paint_view);


        RecyclerView recyclerView = findViewById(R.id.recycler_view_tools);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        ToolsAdapter toolsAdapter = new ToolsAdapter(loadTools(), this);
        recyclerView.setAdapter((toolsAdapter));
    }

    private List<ToolsItem> loadTools(){

        List<ToolsItem> result = new ArrayList<>();

        result.add(new ToolsItem(R.drawable.ic_grid_on_24px,Common.GRID));
        result.add(new ToolsItem(R.drawable.ic_format_shapes_24px,Common.SHAPE));
        result.add(new ToolsItem(R.drawable.ic_brush_24px, Common.BRUSH));
        result.add(new ToolsItem(R.drawable.ic_erase_24px,Common.ERASER));
        result.add(new ToolsItem(R.drawable.ic_color_lens_24px,Common.COLORS));
        result.add(new ToolsItem(R.drawable.ic_format_color_fill_24px,Common.BACKGROUND));
        result.add(new ToolsItem(R.drawable.ic_undo_24px,Common.RETURN));


        return result;
    }

    public void finishPaint(View view) {
        finish();
    }

    public void shareApp(View view) {
    }

    public void showFiles(View view) {
    }

    public void saveFile(View view) {
    }

    @Override
    public void onSelected(String name) {

        switch (name){
            case Common.GRID:
                showGrid();
                mPaintView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.onTouchEvent(event);
                        return true;
                    }
                });

                break;

            case Common.SHAPE:
                mCustomShape = new CustomShape(this);
                setContentView(mCustomShape);
                break;

            case Common.BRUSH:
                mPaintView.disableEraser();
                showDialogSize(false);
                break;

            case Common.ERASER:
                mPaintView.enableEraser();
                showDialogSize(true);
                break;

            case Common.RETURN:
                mPaintView.returnLastAction();
                break;

            case Common.BACKGROUND:
                updateColor(name);
                break;

            case Common.COLORS:
                updateColor(name);
                break;
        }
    }

    private void showGrid() {
        Grid grid = new Grid(this, null);
    }

    private void updateColor(String name) {
        int color;

        if (name.equals(Common.BACKGROUND)) {
            color = colorBackground;
        }else {
            color = colorBrush;
        }
    }

    private void showDialogSize(boolean isEraser) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null, false);

        TextView toolsSelected = view.findViewById(R.id.tools_selected);
        TextView statusSize = view.findViewById(R.id.status_size);
        ImageView ivTools = view.findViewById(R.id.iv_tools);
        SeekBar seekBar = view.findViewById(R.id.seekbar_size);
        seekBar.setMax(99);

        if(isEraser){
            toolsSelected.setText("Eraser Size");
            ivTools.setImageResource(R.drawable.ic_clearpx);
            statusSize.setText("Selected Size:" +eraserSize);
        }else{
            toolsSelected.setText("Brush Size");
            ivTools.setImageResource(R.drawable.ic_brushpx);
            statusSize.setText("Selected Size:" +brushSize);
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(isEraser){
                    eraserSize = progress+1;
                    statusSize.setText("Selected Size:" +eraserSize);
                    mPaintView.setSizeEraser(eraserSize);
                }else{
                    brushSize = progress+1;
                    statusSize.setText("Selected Size:" +brushSize);
                    mPaintView.setSizeEraser(brushSize);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setView(view);
        builder.show();
    }

    @Override
    public boolean onTouchEvent(final MotionEvent ev){
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:{
                float posX = ev.getX();
                float posY = ev.getY();

                float x1 = 50, x2 = 250, y1 = 50, y2 = 250;
                if((posX >= x1 && posX <= x2) && (posY >= y1 && posY <= y2)){
                    //Input Dialog
                    AlertDialog.Builder inputalert = new AlertDialog.Builder(this);
                    inputalert.setMessage("Input Number");

                    final EditText input = new EditText(this);
                    inputalert.setView(input);

                    inputalert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Editable value = input.getText();
                        }
                    });

                    inputalert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Cancel
                        }
                    });
                } else {
                }
                break;
            }
            case MotionEvent.ACTION_UP:{
                break;
            }
            case MotionEvent.ACTION_MOVE:{
                break;
            }
        }
        return false;
    }

    
}