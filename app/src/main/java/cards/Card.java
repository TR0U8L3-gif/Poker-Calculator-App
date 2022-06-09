package cards;

import java.util.*;

public class Card {
    public List<String> symbols = Arrays.asList("2","3","4","5","6","7","8","9","10","J","Q","K","A");
    public List<String> colors = Arrays.asList("C","H","S","D"); // diamonds clubs hearts spades

    private String symbol = "undefined";
    private String color = "undefined";
    private int weight = -1;

    public Card(String symbol, String color)
    {
        setSymbol(symbol);
        setColor(color);
    }
    public Card(int symbol, int color)
    {
        setSymbol(symbol);
        setColor(color);
    }

    public String getColor() {
        return color;
    }
    public int getColorInt() { return colors.indexOf(color); }

    public void setColor(String color) {
        if(colors.contains(color)) this.color = color;
        else this.color = "undefined";
    }

    public void setColor(int color)
    {
        if(color >= 0 && color < colors.size()) this.color = colors.get(color);
        else this.color = "undefined";
    }

    public String getSymbol() {
        return symbol;
    }
    public int getSymbolInt() { return weight; }

    public void setSymbol(String symbol) {
        if(symbols.contains(symbol))
        {
            this.symbol = symbol;
            this.weight = symbols.indexOf(symbol);
        }
        else
        {
            this.symbol = "undefined";
            this.weight = -1;
        }
    }

    public void setSymbol(int symbol)
    {
        if (symbol >= 0 && symbol < symbols.size())
        {
            this.symbol = symbols.get(symbol);
            this.weight = symbol;
        }
        else
        {
            this.symbol = "undefined";
            this.weight = -1;
        }
    }

    public int getWeight() {
        return weight;
    }
}
