package cardsDealer;

import java.util.Objects;

import cards.Card;

public class Dealer {
    /*
        best play output:
        0 = Shit, just shit
        1 = Suited Connectors ex: T9s - 54s
        2 = Offsuit Face Cards (o stands for offsuit) ex: KQo, KJo, KTo, QJo, QTo, JTo
        3 = Suited Face Cards (s stands for suited) ex: KQs, KJs, KTs, QJs, QTs, JTs
        4 = Low Suited Aces ex: A9s - A2s
        5 = Middle Aces ex: AQ, AJ, AT
        6 = High Aces ex. AK
        7 = Low Pairs ex: 99 - 22
        8 = Middle Pairs ex: JJ, TT
        9 = High Pairs ex: AA, KK, QQ
     */
    public static int calculateBestPlay(Card firstCard, Card secondCard)
    {
        String firstSymbol = firstCard.getSymbol();
        String firstColor = firstCard.getColor();
        String secondSymbol = secondCard.getSymbol();
        String secondColor = secondCard.getColor();

        if(Objects.equals(firstSymbol, secondSymbol)) // Pairs   output: 9-7
        {
            if (firstSymbol == "A" || firstSymbol == "K" || firstSymbol == "Q") return 9;
            else if(firstSymbol == "J" || firstSymbol == "10") return 8;
            else return 7;
        }
        else if(Objects.equals(firstSymbol, "A") || Objects.equals(secondSymbol, "A")) // Aces   output: 6-4
        {
            if(Objects.equals(firstSymbol, "A"))
            {
                if(secondSymbol == "K") return 6;
                else if(secondSymbol == "Q" || secondSymbol == "J" || secondSymbol == "10"  ) return 5;
                else if(Objects.equals(firstColor, secondColor)) return 4;
                else return 0;
            }
            else
            {
                if(firstSymbol == "K") return 6;
                else if(firstSymbol == "Q" || firstSymbol == "J" || firstSymbol == "10"  ) return 5;
                else if(Objects.equals(firstColor, secondColor)) return 4;
                else return 0;
            }
        }
        else if((Objects.equals(secondSymbol, "K") || Objects.equals(secondSymbol, "Q") || Objects.equals(secondSymbol, "J") || Objects.equals(secondSymbol, "10")) && (Objects.equals(firstSymbol, "K") || Objects.equals(firstSymbol, "Q") || Objects.equals(firstSymbol, "J") || Objects.equals(firstSymbol, "10"))) // Faces
        {
            if(Objects.equals(firstColor,secondColor)) return 3;
            else return 2;
        }
        else if(Objects.equals(firstColor,secondColor))
        {
            if(((Objects.equals(firstSymbol, "10") && Objects.equals(secondSymbol, "9")) || (Objects.equals(secondSymbol, "10") && Objects.equals(firstSymbol, "9")))
                || ((Objects.equals(firstSymbol, "9") && Objects.equals(secondSymbol, "8")) || (Objects.equals(secondSymbol, "9") && Objects.equals(firstSymbol, "8")))
                || ((Objects.equals(firstSymbol, "8") && Objects.equals(secondSymbol, "7")) || (Objects.equals(secondSymbol, "8") && Objects.equals(firstSymbol, "7")))
                || ((Objects.equals(firstSymbol, "7") && Objects.equals(secondSymbol, "6")) || (Objects.equals(secondSymbol, "7") && Objects.equals(firstSymbol, "6")))
                || ((Objects.equals(firstSymbol, "6") && Objects.equals(secondSymbol, "5")) || (Objects.equals(secondSymbol, "6") && Objects.equals(firstSymbol, "5")))
                || ((Objects.equals(firstSymbol, "5") && Objects.equals(secondSymbol, "4")) || (Objects.equals(secondSymbol, "5") && Objects.equals(firstSymbol, "4")))
                ) return 1;
            else return 0;
        }
        else return 0;
    }
}
