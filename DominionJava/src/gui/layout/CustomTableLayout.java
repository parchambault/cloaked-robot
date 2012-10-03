package gui.layout;

import layout.TableLayout;

public class CustomTableLayout
    extends TableLayout
{
    private static final long  serialVersionUID         = 455406098009407740L;

    public static final double X_BORDER_TO_COMPONENT    = 4d;
    public static final double Y_BORDER_TO_COMPONENT    = 4d;

    public static final double X_COMPONENT_TO_PANEL     = 2d;
    public static final double Y_COMPONENT_TO_PANEL     = 2d;

    public static final double X_COMPONENT_TO_COMPONENT = 3d;
    public static final double Y_COMPONENT_TO_COMPONENT = 3d;

    public void addColumn(double iColumnSize)
    {
        insertColumn(getNumColumn(),
                     iColumnSize);
    }

    public void addRow(double iColumnSize)
    {
        insertRow(getNumRow(),
                  iColumnSize);
    }
}
