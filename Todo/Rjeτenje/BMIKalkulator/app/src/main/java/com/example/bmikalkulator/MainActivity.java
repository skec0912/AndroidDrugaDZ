package com.example.bmikalkulator;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	EditText etTezina;
	EditText etVisina;
	Button btnIzracunaj;
	TextView tvBMI;
	TextView tvOpis;
	ImageView ivBMI;
	Drawable dSretan;
	Drawable dTuzan;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        this.etTezina = (EditText) this.findViewById(R.id.etTezina);
        this.etVisina = (EditText) this.findViewById(R.id.etVisina);
        this.btnIzracunaj = (Button) this.findViewById(R.id.btnIzracunaj);
        this.tvBMI = (TextView) this.findViewById(R.id.tvBMI);
        this.tvOpis = (TextView) this.findViewById(R.id.tvOpis);
        this.ivBMI = (ImageView) this.findViewById(R.id.ivBMI);
        
        this.dSretan = this.getResources().getDrawable(R.drawable.sretan);
        this.dTuzan = this.getResources().getDrawable(R.drawable.tuzan);
        
        this.btnIzracunaj.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				double tezina = -1;
		        double visina = -1;
		        try
		        {
		        	tezina = Double.parseDouble(etTezina.getText().toString());
		        }
		        catch (Exception ex) {}
		        
		        try
		        {
		        	visina = Double.parseDouble(etVisina.getText().toString());
		        }
		        catch (Exception ex) {}
		        
		        double bmi = izracunajBMI(tezina, visina);
		        
		        if (bmi == -1)
		        {
		        	tvBMI.setText("");
		        	tvOpis.setText("");
		        	ivBMI.setImageDrawable(null);
		        }
		        else
		        {
		        	tvBMI.setText(String.format("%.1f", bmi));
		        	if (bmi <= 20)
		        	{
		        		tvOpis.setText(getResources().getString(R.string.text_pothranjeno));
		        		ivBMI.setBackground(dTuzan);
		        	}
		        	else if (bmi <= 25)
		        	{
		        		tvOpis.setText(getResources().getString(R.string.text_normalno));
		        		ivBMI.setBackground(dSretan);
		        	}
		        	else
		        	{
		        		tvOpis.setText(getResources().getString(R.string.text_prekomjerno));
		        		ivBMI.setBackground(dTuzan);
		        	}
		        }
			}
		});
    }
    
    private double izracunajBMI (double tezina, double visina)
    {
    	if (tezina <= 0 || visina <= 0)
    		return -1;
    	
    	// visina je unesena u centimetrima, pretvorba u metre
    	if (visina >= 3)
    		visina = visina / 100;
    	
    	return tezina / (visina * visina);
    }
}
