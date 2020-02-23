package wordseach.model;

import java.util.Map;
import java.util.HashMap;

import java.util.Map.Entry;
import java.util.Iterator;

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

        return new IntPair(0,0);
    }

    public void reset()
    {
        possibleDirections.forEach((key,value) -> possibleDirections.replace(key, false));

        possibleDirections.replaceAll((key,value) -> false);

        for(Map.Entry<IntPair, Boolean> entry : possibleDirections.entrySet())
        {
            entry.setValue(false);
        }


        Iterator<Entry<IntPair, Boolean>> iterator1 = possibleDirections.entrySet().iterator();
        while(iterator1.hasNext()){
            Map.Entry<IntPair, Boolean> pair =
                    (Map.Entry<IntPair,Boolean>) iterator1.next();
            pair.setValue(false);

        }

    }


}

