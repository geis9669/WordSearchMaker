package wordsearch.model;

import java.util.*;

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

    /**
     * this gets any valid pair that has yet to be gotten if there are non it will return null
     * @return the intPair if finds or null if all are true.
     */
    public IntPair getRandomDirection()
    {
        // get all keys that are not true have not been returned
        List<IntPair> keys = new ArrayList<>();
        for(Map.Entry<IntPair,Boolean> entry : possibleDirections.entrySet())
        {
            if(!entry.getValue())
            {
                keys.add(entry.getKey());
            }
        }
        //this makes sure there is a key
        if(keys.size()== 0)
        {
            return null;
        }
        //this gets a random key
        IntPair pair = keys.get((int) (Math.random()*keys.size()));
        possibleDirections.replace(pair,true);
        return pair;
    }

    /**
     * checks if all the values are true
     * @return true if all are true other wise false
     */
    public boolean allTrue()
    {
        for(boolean value: possibleDirections.values())
        {
            if(!value) {
                return false;
            }
        }
        return true;
    }


    /**
     * this method allows you to set the key values to true or false manually.
     * @param key an IntPair that has -1,-1 to 1,1 values for the first and second number.
     * @param value either true or false. true means it has been gotten and I don't want it.
     */
    public void set(IntPair key, boolean value)
    {
        possibleDirections.replace(key, value);
    }

    public void reset()
    {
        // this one also uses lamda to go through the list.
        //possibleDirections.forEach((key,value) -> possibleDirections.replace(key, false));

        // this one uses lamda to go through the map
        possibleDirections.replaceAll((key,value) -> false);

        /*// this one uses a for each to go through the map
        for(Map.Entry<IntPair, Boolean> entry : possibleDirections.entrySet())
        {
            entry.setValue(false);
        }

        // this method makes use of the iterator to go through the map
        Iterator<Entry<IntPair, Boolean>> iterator1 = possibleDirections.entrySet().iterator();
        while(iterator1.hasNext()){
            Map.Entry<IntPair, Boolean> pair =
                    (Map.Entry<IntPair,Boolean>) iterator1.next();
            pair.setValue(false);
        }
        */
    }


}

