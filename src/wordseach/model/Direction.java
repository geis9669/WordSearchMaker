package wordseach.model;

import java.util.Map;
import java.util.HashMap;

public class Direction {
    private Map<IntPair, Boolean> possibleDirections;

    public Direction()
    {
        possibleDirections = new HashMap<IntPair,Boolean>();
        // this fills the possible directions with all the combinations of -1 0 and 1
        for(int row = -1; row<2; row++)
        {
            for(int col = -1; col<2; col++)
            {
                possibleDirections.put(new IntPair(row,col), false);
            }
        }
    }

    public IntPair getRandomDirection()
    {

    }

    public void reset()
    {
        
    }


}

