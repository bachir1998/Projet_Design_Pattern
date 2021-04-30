package appli;

import java.io.File;
import java.util.Collection;

public class ContextStrategyTri
{
    public InterfaceTri interfaceTri;

    public ContextStrategyTri(InterfaceTri interfaceTri)
    {
        this.interfaceTri= interfaceTri;
    }

    public Collection<People> execute(File file, String _start, String _stop)
    {
        return interfaceTri.Tri(file,_start,_stop);
    }
}
