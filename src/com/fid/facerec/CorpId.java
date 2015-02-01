package com.fid.facerec;



import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.CustomLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewDataInterface;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.ValueDependentColor;



public class CorpId extends Activity {

	ImageView imgTakenPhoto;
	TextView nameView;
	TextView corpidView;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whos);
        
        float angry = 0;
		float disgust = 0;
		float sadness = 0;
		float happiness = 0;
		float neutral = 0;
		float surprise = 0;
		float fear =0;
		
        nameView = (TextView)findViewById(R.id.name);
        corpidView = (TextView)findViewById(R.id.corpID);
        
        Intent intent = getIntent();
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
        String  resjson = intent.getExtras().getString("resjson");        
        
        Log.e("RESPONSE IN ANTR", resjson);
        
        try {
			JSONObject json = (JSONObject)new JSONParser().parse(resjson);
			
			String name = (String) json.get("name");
			String corpid = (String) json.get("corpid");
			
			nameView.setText(name);
			nameView.setTextColor(Color.BLUE);
			corpidView.setText(corpid);
			corpidView.setTextColor(Color.BLUE);
			
			String sDisgust = (String) json.get("disgust");
			String sAnger = (String) json.get("anger");
			String sSadness = (String) json.get("sadness");
			String sNeutral = (String) json.get("neutral");
			String sSurprise = (String) json.get("surprise");
			String sFear = (String) json.get("fear");
			String sHappiness = (String) json.get("happiness"); 
			
			disgust = (Float.parseFloat(sDisgust))*100;
			angry = (Float.parseFloat(sAnger))*100;
			sadness = (Float.parseFloat(sSadness))*100;
			neutral = (Float.parseFloat(sNeutral))*100;
			surprise = (Float.parseFloat(sSurprise))*100;
			fear = (Float.parseFloat(sFear))*100;
			happiness = (Float.parseFloat(sHappiness))*100;
			
			/*disgust = ((Integer) json.get("disgust"))*100;
			sadness = ((Integer) json.get("sadness"))*100;
			happiness = ((Integer) json.get("hapiness"))*100;
			neutral = ((Integer) json.get("neutral"))*100;
			surprise = ((Integer) json.get("surprise"))*100;
			fear = ((Integer) json.get("fear"))*100;*/
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        imgTakenPhoto = (ImageView) findViewById(R.id.imageView1);
        imgTakenPhoto.setImageBitmap(bitmap);
        
        GraphViewSeriesStyle barStyle = new GraphViewSeriesStyle();
        barStyle.thickness = 5;
        barStyle.setValueDependentColor(new ValueDependentColor() {
            @Override
            public int get(GraphViewDataInterface data) {
                return Color.rgb(205, 0, 0);
            }
        });

        
        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
        	      new GraphViewData(1, angry)
        	    , new GraphViewData(2, disgust)
        	    , new GraphViewData(3, sadness)
        	    , new GraphViewData(4, happiness)
        	    , new GraphViewData(5, neutral)
        	    , new GraphViewData(6, surprise)
        	    , new GraphViewData(7, fear)
        	    
        	});
        	 
        GraphView graphView = new BarGraphView(
        	    this // context
        	    , "Emotions" // heading
        	);
        	
        /*	graphView.setCustomLabelFormatter(new CustomLabelFormatter() 
            {
                @Override
                public String formatLabel(double value, boolean isValueX) 
                {
                    if (isValueX)
                    {
                        if(value == 1){
                        	return "Angry";
                        }
                        else if((value > 2 && value <3) || value == 2){
                        	return "Disgust";
                        }
                        else if((value > 3 && value <4) || value == 3){
                        	return "Sadness";
                        }
                        else if((value > 4 && value <5) || value == 4){
                        	return "Happiness";
                        }
                        else if((value > 5 && value <6 )|| value == 5){
                        	return "Neutral";
                        }
                        else if((value > 6 && value <7 )|| value == 6){
                        	return "Surprise";
                        }
                        else if(value == 7 ){
                        	return "Fear";
                        }
                    }
                    
                    return null; // let graphview generate Y-axis label for us
                }
            });*/
        	
        String [] emotions={"        Angry","                      Disgust","                 Sadness","                Happiness","                Neutral","             Surprise","           Fear",""};
        graphView.setHorizontalLabels(emotions); 
        String [] per={"100","90","80","70","60","50","40","30","20","10","0"};
        graphView.setVerticalLabels(per);
        	graphView.addSeries(exampleSeries); // data
        	
        	graphView.setViewPort(0, 10);
            graphView.setScalable(false);
        	
            graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.MAGENTA);
            graphView.getGraphViewStyle().setVerticalLabelsColor(Color.GREEN);

            graphView.getGraphViewStyle().setTextSize(40);
            graphView.getGraphViewStyle().setGridColor(Color.WHITE);
            graphView.getGraphViewStyle().setVerticalLabelsWidth(80);
            graphView.setBackgroundColor(Color.WHITE);
            
        	//graphView.getGraphViewStyle().setGridStyle(GridStyle.HORIZONTAL);
        	LinearLayout layout = (LinearLayout) findViewById(R.id.graphView);
         	layout.addView(graphView);
     
       
        
      
        
       
        
    }
}
