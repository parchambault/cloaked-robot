package gui.valueobj;

import gui.GUIType;
import org.jdom.Element;

public class GeneralComponentValueObj
{
    public static final String ATTRIBUTE_DESCRIPTION = "Description";
    public static final String ATTRIBUTE_NAME        = "Name";
    public static final String ATTRIBUTE_TYPE        = "Type";
    public static final String ATTRIBUTE_VALUE       = "Value";

    public static final String XML_HEAD_TAG          = "Component";

    private String             componentDescription  = "";
    private String             componentName         = "";
    private GUIType            componentType         = GUIType.UNKNOWN;
    private String             componentValue        = "";

    public GeneralComponentValueObj()
    {
    }

    public GeneralComponentValueObj(Element xmlRoot)
    {
        fromXml(xmlRoot);
    }

    public void fromXml(Element root)
    {
        setComponentType(GUIType.valueOf(root.getAttributeValue(ATTRIBUTE_TYPE)));
        setComponentName(root.getAttributeValue(ATTRIBUTE_NAME));
        setComponentDescription(root.getAttributeValue(ATTRIBUTE_DESCRIPTION));
        setComponentValue(root.getAttributeValue(ATTRIBUTE_VALUE));
    }

    public Element toXml()
    {
        Element root = new Element(XML_HEAD_TAG);

        root.setAttribute(ATTRIBUTE_TYPE,
                          getComponentType().toString());
        root.setAttribute(ATTRIBUTE_NAME,
                          getComponentName());
        root.setAttribute(ATTRIBUTE_DESCRIPTION,
                          getComponentDescription());
        root.setAttribute(ATTRIBUTE_VALUE,
                          getComponentValue());

        return root;
    }

    public String getComponentDescription()
    {
        return componentDescription;
    }

    public String getComponentName()
    {
        return componentName;
    }

    public GUIType getComponentType()
    {
        return componentType;
    }

    public String getComponentValue()
    {
        return componentValue;
    }

    public void setComponentDescription(String componentDescription)
    {
        this.componentDescription = componentDescription;
    }

    public void setComponentName(String componentName)
    {
        this.componentName = componentName;
    }

    public void setComponentType(GUIType componentType)
    {
        this.componentType = componentType;
    }

    public void setComponentValue(String componentValue)
    {
        this.componentValue = componentValue;
    }
}
