package wordsearch.model;

public final class IntPair
{
    private int firstInt;
    private int secondInt;

    public IntPair(int firstInt, int secondInt)
    {
        this.firstInt = firstInt;
        this.secondInt = secondInt;
    }

    public int getFirst()
    {
        return this.firstInt;
    }

    public int getSecond()
    {
        return this.secondInt;
    }

    public int hashCode()
    {
        int hash = 7;
        hash += 31 * hash + (int) firstInt;
        hash += 31 * hash + (int) secondInt;
        return hash;
    }

    public boolean equals(Object object)
    {
        // check if they take up the same space in memory
        if(this == object)
        {
            return true;
        }
        // checks if the object is null
        if(object == null)
        {
            return false;
        }

        // are the two items its holding the same ints
        if(object instanceof IntPair)
        {
            IntPair other = (IntPair) object;
            if(firstInt == other.getFirst()
                    && secondInt == other.getSecond())
            {
                return true;
            }
        }
        return false;
    }
}
