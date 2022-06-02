package com.example.pokercalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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

    TextView foldEarly;
    TextView callEarly;
    TextView raiseEarly;

    TextView foldMid;
    TextView callMid;
    TextView raiseMid;

    TextView foldLate;
    TextView callLate;
    TextView raiseLate;

    TextView foldBlind;
    TextView callBlind;
    TextView raiseBlind;

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
//        text area
        foldEarly = (TextView) findViewById(R.id.foldEarly);
        callEarly = (TextView) findViewById(R.id.callEarly);
        raiseEarly = (TextView) findViewById(R.id.raiseEarly);

        foldMid = (TextView) findViewById(R.id.foldMid);
        callMid = (TextView) findViewById(R.id.callMid);
        raiseMid = (TextView) findViewById(R.id.raiseMid);

        foldLate = (TextView) findViewById(R.id.foldLate);
        callLate = (TextView) findViewById(R.id.callLate);
        raiseLate = (TextView) findViewById(R.id.raiseLate);

        foldBlind = (TextView) findViewById(R.id.foldBlind);
        callBlind = (TextView) findViewById(R.id.callBlind);
        raiseBlind = (TextView) findViewById(R.id.raiseBlind);
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
        }
        else
        {
            symbol = secondCard.getSymbol().toLowerCase();
            color = secondCard.getColor().toLowerCase();
            int resID = getResources().getIdentifier((color + symbol) , "drawable", getPackageName());
            secondCardImage.setImageResource(resID);
        }
        changeText(Dealer.calculateBestPlay(firstCard,secondCard));
//        String out = Integer.toString(Dealer.calculateBestPlay(firstCard,secondCard));
//        Toast.makeText(getApplicationContext(),out,Toast.LENGTH_LONG).show();
    }

    private void changeText(int option)
    {
        String call20 = "Call20";
        String call = "Call";
        String fold = "Fold";
        String raise = "Raise";
        String error = "Error";

        if(option == 0)
        {
            foldEarly.setText(fold);
            callEarly.setText(fold);
            raiseEarly.setText(fold);

            foldMid.setText(fold);
            callMid.setText(fold);
            raiseMid.setText(fold);

            foldLate.setText(fold);
            callLate.setText(fold);
            raiseLate.setText(fold);

            foldBlind.setText(fold);
            callBlind.setText(fold);
            raiseBlind.setText(fold);
        }
        else if(option == 1)
        {
            foldEarly.setText(fold);
            callEarly.setText(fold);
            raiseEarly.setText(fold);

            foldMid.setText(fold);
            callMid.setText(call);
            raiseMid.setText(fold);

            foldLate.setText(raise);
            callLate.setText(call);
            raiseLate.setText(fold);

            foldBlind.setText(fold);
            callBlind.setText(call);
            raiseBlind.setText(fold);
        }
        else if(option == 2)
        {
            foldEarly.setText(fold);
            callEarly.setText(fold);
            raiseEarly.setText(fold);

            foldMid.setText(fold);
            callMid.setText(fold);
            raiseMid.setText(fold);

            foldLate.setText(raise);
            callLate.setText(fold);
            raiseLate.setText(fold);

            foldBlind.setText(raise);
            callBlind.setText(call);
            raiseBlind.setText(fold);
        }
        else if(option == 3)
        {
            foldEarly.setText(fold);
            callEarly.setText(fold);
            raiseEarly.setText(fold);

            foldMid.setText(fold);
            callMid.setText(call);
            raiseMid.setText(fold);

            foldLate.setText(raise);
            callLate.setText(call);
            raiseLate.setText(fold);

            foldBlind.setText(raise);
            callBlind.setText(call);
            raiseBlind.setText(fold);
        }
        else if(option == 4)
        {
            foldEarly.setText(fold);
            callEarly.setText(fold);
            raiseEarly.setText(fold);

            foldMid.setText(fold);
            callMid.setText(fold);
            raiseMid.setText(fold);

            foldLate.setText(raise);
            callLate.setText(call);
            raiseLate.setText(fold);

            foldBlind.setText(raise);
            callBlind.setText(call);
            raiseBlind.setText(fold);
        }
        else if(option == 5)
        {
            foldEarly.setText(fold);
            callEarly.setText(fold);
            raiseEarly.setText(fold);

            foldMid.setText(raise);
            callMid.setText(fold);
            raiseMid.setText(fold);

            foldLate.setText(raise);
            callLate.setText(raise);
            raiseLate.setText(fold);

            foldBlind.setText(raise);
            callBlind.setText(call);
            raiseBlind.setText(fold);
        }
        else if(option == 6 || option == 9 )
        {
            foldEarly.setText(raise);
            callEarly.setText(raise);
            raiseEarly.setText(raise);

            foldMid.setText(raise);
            callMid.setText(raise);
            raiseMid.setText(raise);

            foldLate.setText(raise);
            callLate.setText(raise);
            raiseLate.setText(raise);

            foldBlind.setText(raise);
            callBlind.setText(raise);
            raiseBlind.setText(raise);
        }
        else if(option == 7)
        {
            foldEarly.setText(fold);
            callEarly.setText(fold);
            raiseEarly.setText(call20);

            foldMid.setText(call);
            callMid.setText(call);
            raiseMid.setText(call20);

            foldLate.setText(raise);
            callLate.setText(call);
            raiseLate.setText(call20);

            foldBlind.setText(call);
            callBlind.setText(call);
            raiseBlind.setText(call20);
        }
        else if(option == 8 )
        {
            foldEarly.setText(raise);
            callEarly.setText(raise);
            raiseEarly.setText(call20);

            foldMid.setText(raise);
            callMid.setText(raise);
            raiseMid.setText(call20);

            foldLate.setText(raise);
            callLate.setText(raise);
            raiseLate.setText(call20);

            foldBlind.setText(raise);
            callBlind.setText(raise);
            raiseBlind.setText(call20);
        }
        else
        {
            foldEarly.setText(error);
            callEarly.setText(error);
            raiseEarly.setText(error);

            foldMid.setText(error);
            callMid.setText(error);
            raiseMid.setText(error);

            foldLate.setText(error);
            callLate.setText(error);
            raiseLate.setText(error);

            foldBlind.setText(error);
            callBlind.setText(error);
            raiseBlind.setText(error);
        }
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
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
    }
}