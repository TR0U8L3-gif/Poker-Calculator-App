package com.example.pokercalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import cards.Card;
import cardsDealer.Dealer;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, SeekBar.OnSeekBarChangeListener{

    Button buttonSpinner;
    Button buttonSeekBar;

    ConstraintLayout cardChooserSpinner;
    ConstraintLayout cardChooserSeekBar;
    boolean layout = false;

//  cardChooserSpinner layout
    ImageView firstCardImage;
    ImageView secondCardImage;

    Spinner firstCardSymbol;
    Spinner firstCardColor;
    Spinner secondCardSymbol;
    Spinner secondCardColor;

//  cardChooserSpinner layout
    ImageView firstCardImageSeekBar;
    ImageView secondCardImageSeekBar;

    SeekBar firstCardSymbolSeekBar;
    SeekBar firstCardColorSeekBar;
    SeekBar secondCardSymbolSeekBar;
    SeekBar secondCardColorSeekBar;

//  plays and stats table layout
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

    TextView bestPlay;

    Card firstCard = new Card(0,0);
    Card secondCard = new Card(0,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        menu
        buttonSpinner = (Button) findViewById(R.id.buttonSpinner);
        buttonSeekBar = (Button) findViewById(R.id.buttonSeekBar);

        buttonSpinner.setAlpha((float) 1);
        buttonSeekBar.setAlpha((float) 0.75);
        layout = false;

        buttonSpinner.setOnClickListener(this);
        buttonSeekBar.setOnClickListener(this);

//        layouts
        cardChooserSpinner = (ConstraintLayout) findViewById(R.id.cardChooserSpinner);
        cardChooserSeekBar = (ConstraintLayout) findViewById(R.id.cardChooserSeekBar);

        cardChooserSpinner.setVisibility(View.VISIBLE);
        cardChooserSeekBar.setVisibility(View.GONE);

//        images
        firstCardImage = (ImageView) findViewById(R.id.firstCardImage);
        secondCardImage = (ImageView) findViewById(R.id.secondCardImage);

        firstCardImageSeekBar = (ImageView) findViewById(R.id.firstCardImageSeekBar);
        secondCardImageSeekBar = (ImageView) findViewById(R.id.secondCardImageSeekBar);

//        spinners
        firstCardSymbol = (Spinner) findViewById(R.id.firstCardSymbol);
        firstCardColor = (Spinner) findViewById(R.id.firstCardColor);
        secondCardSymbol = (Spinner) findViewById(R.id.secondCardSymbol);
        secondCardColor = (Spinner) findViewById(R.id.secondCardColor);

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
//        seekbar
         firstCardSymbolSeekBar = (SeekBar) findViewById(R.id.firstCardSymbolSeekBar);
         firstCardColorSeekBar = (SeekBar) findViewById(R.id.firstCardColorSeekBar);
         secondCardSymbolSeekBar = (SeekBar) findViewById(R.id.secondCardSymbolSeekBar);
         secondCardColorSeekBar = (SeekBar) findViewById(R.id.secondCardColorSeekBar);

        firstCardSymbolSeekBar.setOnSeekBarChangeListener(this);
        firstCardColorSeekBar.setOnSeekBarChangeListener(this);
        secondCardSymbolSeekBar.setOnSeekBarChangeListener(this);
        secondCardColorSeekBar.setOnSeekBarChangeListener(this);

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

        bestPlay = (TextView) findViewById(R.id.bestPlay);

    }

    private void changeMenu(int scene)
    {
        if(scene%2==0)
        {
            buttonSpinner.setAlpha((float) 1);
            buttonSeekBar.setAlpha((float) 0.75);
            cardChooserSpinner.setVisibility(View.VISIBLE);
            cardChooserSeekBar.setVisibility(View.GONE);
        }
        else
        {
            buttonSeekBar.setAlpha((float) 1);
            buttonSpinner.setAlpha((float) 0.75);
            cardChooserSpinner.setVisibility(View.GONE);
            cardChooserSeekBar.setVisibility(View.VISIBLE);
        }
    }

    private void changeCard(boolean card)
    {
        String symbol;
        String color;

        if(!card)
        {
            if(!layout)
            {
                firstCardSymbolSeekBar.setProgress(firstCard.getSymbolInt());
                firstCardColorSeekBar.setProgress(firstCard.getColorInt());
            }
            else
            {
                firstCardSymbol.setSelection(firstCard.getSymbolInt());
                firstCardColor.setSelection(firstCard.getColorInt());
            }

            symbol = firstCard.getSymbol().toLowerCase();
            color = firstCard.getColor().toLowerCase();
            int resID = getResources().getIdentifier((color + symbol) , "drawable", getPackageName());
            firstCardImage.setImageResource(resID);
            firstCardImageSeekBar.setImageResource(resID);
        }
        else
        {
            if(!layout)
            {
                secondCardSymbolSeekBar.setProgress(secondCard.getSymbolInt());
                secondCardColorSeekBar.setProgress(secondCard.getColorInt());
            }
            else
            {
                secondCardSymbol.setSelection(secondCard.getSymbolInt());
                secondCardColor.setSelection(secondCard.getColorInt());
            }
            symbol = secondCard.getSymbol().toLowerCase();
            color = secondCard.getColor().toLowerCase();
            int resID = getResources().getIdentifier((color + symbol) , "drawable", getPackageName());
            secondCardImage.setImageResource(resID);
            secondCardImageSeekBar.setImageResource(resID);
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

        List<String> options = Arrays.asList(
                "Shit, just shit"
                ,"Suited Connectors"
                ,"Offsuit Face Cards"
                ,"Suited Face Cards"
                ,"Low Suited Aces"
                ,"Middle Aces"
                ,"High Aces"
                ,"Low Pairs"
                ,"Middle Pairs"
                ,"High Pairs");
        bestPlay.setText(options.get(option));
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

//  button
    @Override
    public void onClick(View view) {
        int itemId = view.getId();
        if(itemId == R.id.buttonSpinner)
        {
            changeMenu(0);
            layout = false;
        }
        if(itemId == R.id.buttonSeekBar)
        {
            changeMenu(1);
            layout = true;
        }
    }

//  spinner
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

//  seekBar
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int itemId = seekBar.getId();

        if(itemId == R.id.firstCardSymbolSeekBar)
        {
            firstCard.setSymbol(progress);
            changeCard(false);
        }
        if(itemId == R.id.firstCardColorSeekBar)
        {
            firstCard.setColor(progress);
            changeCard(false);
        }
        if(itemId == R.id.secondCardSymbolSeekBar)
        {
            secondCard.setSymbol(progress);
            changeCard(true);
        }
        if(itemId == R.id.secondCardColorSeekBar)
        {
            secondCard.setColor(progress);
            changeCard(true);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}