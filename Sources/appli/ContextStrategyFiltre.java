package appli;

public class ContextStrategyFiltre {
    public InterfaceFiltre interfaceFiltre;

    public ContextStrategyFiltre (InterfaceFiltre interfaceFiltre)
    {
        this.interfaceFiltre = interfaceFiltre;
    }

    public String getHtmlCode()
    {
        return interfaceFiltre.getHtmlCode();
    }
}
