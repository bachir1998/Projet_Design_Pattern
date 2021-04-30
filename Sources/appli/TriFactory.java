package appli;

public class TriFactory
{
    public InterfaceTri getTypetri(String TypeTri)
    {
        if(TypeTri.equalsIgnoreCase("TriByName"))
        {
            return new TriByName();
        }

        else if (TypeTri.equalsIgnoreCase("TriById"))
        {
            return  new TriById();
        }
        return null;
    }
}
