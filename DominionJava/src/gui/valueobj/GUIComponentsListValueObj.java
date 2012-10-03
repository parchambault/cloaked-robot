package gui.valueobj;

import gui.GUIType;
import gui.valueobj.factory.GeneralComponentFactory;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Element;

public class GUIComponentsListValueObj
{
    public static final String             XML_COMPONENTS_LIST_TAG = "ComponentsList";

    private List<GeneralComponentValueObj> componentsList          = new ArrayList<GeneralComponentValueObj>();

    public GUIComponentsListValueObj()
    {
    }

    public GUIComponentsListValueObj(Element xmlRoot)
    {
        fromXml(xmlRoot);
    }

    public void fromXml(Element root)
    {
        GeneralComponentValueObj generalComponent = null;

        componentsList.clear();
        for (Element childEntry : (List<Element>) root.getChildren(GeneralComponentValueObj.XML_HEAD_TAG))
        {
            generalComponent =
                    GeneralComponentFactory.generateGeneralComponent(GUIType.valueOf(childEntry.getAttributeValue(GeneralComponentValueObj.ATTRIBUTE_TYPE)),
                                                                     childEntry);
            componentsList.add(generalComponent);
        }
    }

    public Element toXml()
    {
        Element root = new Element(XML_COMPONENTS_LIST_TAG);

        for (GeneralComponentValueObj componentEntry : componentsList)
        {
            root.addContent(componentEntry.toXml());
        }

        return root;
    }

    public List<GeneralComponentValueObj> getComponentsList()
    {
        return componentsList;
    }

    public void setComponentsList(List<GeneralComponentValueObj> componentsList)
    {
        this.componentsList = componentsList;
    }
}