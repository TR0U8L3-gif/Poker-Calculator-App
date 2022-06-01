package com.example.pokercalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import cards.Card;
import cardsDealer.Dealer;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageView firstCardImage;
    ImageView secondCardImage;

    Spinner firstCardSymbol;
    Spinner firstCardColor;
    Spinner secondCardSymbol;
    Spinner secondCardColor;

    Card firstCard = new Card(0,0);
    Card secondCard = new Card(0,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        images
        firstCardImage = (ImageView) findViewById(R.id.firstCardImage);
        secondCardImage = (ImageView) findViewById(R.id.secondCardImage);
//        spinners
        firstCardSymbol = (Spinner) findViewById(R.id.firstCardSymbol);
        firstCardColor = (Spinner) findViewById(R.id.firstCardColor);
        secondCardSymbol = (Spinner) findViewById(R.id.secondCardSymbol);
        secondCardColor = (Spinner) findViewById(R.id.secondCardColor);
//        firstCardImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        firstCardImage.setImageResource(R.drawable.d2);
        secondCardImage.setImageResource(R.drawable.dj);
//        setting spinners values
        ArrayAdapter<CharSequence> symbols = ArrayAdapter.createFromResource(this, R.array.card_symbols, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> colors = ArrayAdapter.createFromResource(this, R.array.card_colors, android.R.layout.simple_spinner_dropdown_item);
        symbols.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colors.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        firstCardSymbol.setAdapter(symbols);
        secondCardSymbol.setAdapter(symbols);
        firstCardColor.setAdapter(colors);
        secondCardColor.setAdapter(colors);

        firstCardSymbol.setOnItemSelectedListener(this);
        secondCardSymbol.setOnItemSelectedListener(this);
        firstCardColor.setOnItemSelectedListener(this);
        secondCardColor.setOnItemSelectedListener(this);

    }
    private void changeCard(boolean card)
    {
        String symbol;
        String color;

        if(!card)
        {
            symbol = firstCard.getSymbol().toLowerCase();
            color = firstCard.getColor().toLowerCase();
            int resID = getResources().getIdentifier((color + symbol) , "drawable", getPackageName());
            firstCardImage.setImageResource(resID);
//            Toast.makeText(getApplicationContext(),("FC: "+color+symbol),Toast.LENGTH_LONG).show();
        }
        else
        {
            symbol = secondCard.getSymbol().toLowerCase();
            color = secondCard.getColor().toLowerCase();
            int resID = getResources().getIdentifier((color + symbol) , "drawable", getPackageName());
            secondCardImage.setImageResource(resID);
//            Toast.makeText(getApplicationContext(),("SC: "+color+symbol),Toast.LENGTH_LONG).show();
        }
         String out = Integer.toString(Dealer.calculateBestPlay(firstCard,secondCard));
        Toast.makeText(getApplicationContext(),out,Toast.LENGTH_LONG).show();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        int itemId = parent.getId();

        if(itemId == R.id.firstCardSymbol)
        {
            firstCard.setSymbol(position);
            changeCard(false);
        }
        if(itemId == R.id.firstCardColor)
        {
            firstCard.setColor(position);
            changeCard(false);
        }
        if(itemId == R.id.secondCardSymbol)
        {
            secondCard.setSymbol(position);
            changeCard(true);
        }
        if(itemId == R.id.secondCardColor)
        {
            secondCard.setColor(position);
            changeCard(true);
        }

//        Toast.makeText(getApplicationContext(),out,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}