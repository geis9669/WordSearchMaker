package wordsearch.tester;

import wordsearch.model.Direction;
import wordsearch.model.IntPair;

public class DirectionTester {


    public static void main(String[] args)
    {
        Direction direction = new Direction();

        String text ="";
        for(int time = 0; time < 9; time++)
        {
            IntPair pair= direction.getRandomDirection();
            text += "" + pair.getFirst()+"  "+ pair.getSecond()+ "\n";
        }
        System.out.println(text);

        direction.reset();
        direction.set(new IntPair(0,0), true);
        text = "";
        for(int time = 0; time < 9; time++)
        {
            IntPair pair= direction.getRandomDirection();
            if(pair != null)
            {
                text += "" + pair.getFirst()+"  "+ pair.getSecond()+ "\n";
            }
            else
            {
                text+= "null\n";
            }
        }
        System.out.println(text);
    }
}
