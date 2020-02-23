package wordseach.model;

public class IntPair
{
    private int rowDirection;
    private int colDirection;

    public IntPair(int directionRow, int directionCol)
    {
        this.rowDirection = directionRow;
        this.colDirection = directionCol;
    }

    public int getRowDirection()
    {
        return this.rowDirection;
    }

    public int getColDirection()
    {
        return this.colDirection;
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
            if(rowDirection == other.getRowDirection()
                    && colDirection == other.getColDirection())
            {
                return true;
            }
        }
        return false;
    }
}
