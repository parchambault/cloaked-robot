package gui.valueobj;

import org.jdom.Element;

public class FrameComponentValueObj
        extends GeneralComponentValueObj
{
    public static final String ATTRIBUTE_HEIGHT    = "height";
    public static final String ATTRIBUTE_WIDTH     = "width";
    public static final String ATTRIBUTE_X         = "x_anchor";
    public static final String ATTRIBUTE_Y         = "y_anchor";

    public static final String XML_POSITIONING_TAG = "FramePositioning";

    private int                height              = 10;
    private int                width               = 10;
    private int                xAnchor             = 0;
    private int                yAnchor             = 0;

    public FrameComponentValueObj()
    {
    }

    public FrameComponentValueObj(Element xmlRoot)
    {
        fromXml(xmlRoot);
    }

    @Override
    public void fromXml(Element root)
    {
        super.fromXml(root);
        setxAnchor(Integer.parseInt(root.getChild(XML_POSITIONING_TAG)
                                        .getAttributeValue(ATTRIBUTE_X)));
        setyAnchor(Integer.parseInt(root.getChild(XML_POSITIONING_TAG)
                                        .getAttributeValue(ATTRIBUTE_Y)));
        setWidth(Integer.parseInt(root.getChild(XML_POSITIONING_TAG)
                                      .getAttributeValue(ATTRIBUTE_WIDTH)));
        setHeight(Integer.parseInt(root.getChild(XML_POSITIONING_TAG)
                                       .getAttributeValue(ATTRIBUTE_HEIGHT)));
    }

    @Override
    public Element toXml()
    {
        Element root = super.toXml();
        Element framePositioning = new Element(XML_POSITIONING_TAG);

        root.addContent(framePositioning);
        framePositioning.setAttribute(ATTRIBUTE_X,
                                      Long.toString(getxAnchor()));
        framePositioning.setAttribute(ATTRIBUTE_Y,
                                      Long.toString(getyAnchor()));
        framePositioning.setAttribute(ATTRIBUTE_WIDTH,
                                      Long.toString(getWidth()));
        framePositioning.setAttribute(ATTRIBUTE_HEIGHT,
                                      Long.toString(getHeight()));
        return root;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getxAnchor()
    {
        return xAnchor;
    }

    public void setxAnchor(int xAnchor)
    {
        this.xAnchor = xAnchor;
    }

    public int getyAnchor()
    {
        return yAnchor;
    }

    public void setyAnchor(int yAnchor)
    {
        this.yAnchor = yAnchor;
    }
}
