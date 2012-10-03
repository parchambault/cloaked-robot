package gui.valueobj.factory;

import gui.GUIType;
import gui.valueobj.FrameComponentValueObj;
import gui.valueobj.GeneralComponentValueObj;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jdom.Element;

public class GeneralComponentFactory
{
    private static final Logger LOG = LogManager.getLogger(GeneralComponentFactory.class);

    private GeneralComponentFactory()
    {
    }

    public static GeneralComponentValueObj generateGeneralComponent(GUIType guiType,
                                                                    Element rootElement)
    {
        switch (guiType)
        {
            case CHECK_BOX:
                return new GeneralComponentValueObj(rootElement);
            case RADIO_BUTTON:
                return new GeneralComponentValueObj(rootElement);
            case FRAME:
                return new FrameComponentValueObj(rootElement);
                // case COMBO_BOX:
            case TEXTFIELD:
                return new GeneralComponentValueObj(rootElement);
            default:
                throw new IllegalArgumentException("Unsupported componentType: " + guiType
                        + " caught while creating GUIComponentsListValueObj");

        }
    }

    public static GeneralComponentValueObj generateGeneralComponent(String componentName,
                                                                    Component component)
    {
        GeneralComponentValueObj componentValueObj = null;
        if (component instanceof JFrame)
        {
            componentValueObj = new FrameComponentValueObj();
            componentValueObj.setComponentType(GUIType.FRAME);
            componentValueObj.setComponentName(componentName);
            componentValueObj.setComponentDescription(((JFrame) component).getTitle());
            ((FrameComponentValueObj) componentValueObj).setxAnchor(((JFrame) component).getX());
            ((FrameComponentValueObj) componentValueObj).setyAnchor(((JFrame) component).getY());
            ((FrameComponentValueObj) componentValueObj).setHeight(((JFrame) component).getHeight());
            ((FrameComponentValueObj) componentValueObj).setWidth(((JFrame) component).getWidth());
        }
        else if (component instanceof JTextField && !(component instanceof JPasswordField))
        {
            componentValueObj = new GeneralComponentValueObj();
            componentValueObj.setComponentType(GUIType.TEXTFIELD);
            componentValueObj.setComponentName(componentName);
            componentValueObj.setComponentValue(((JTextField) component).getText());
        }
        else if (component instanceof JCheckBox)
        {
            componentValueObj = new GeneralComponentValueObj();
            componentValueObj.setComponentType(GUIType.CHECK_BOX);
            componentValueObj.setComponentName(componentName);
            componentValueObj.setComponentValue(Boolean.toString(((JCheckBox) component).isSelected()));
            componentValueObj.setComponentDescription(((JCheckBox) component).getText());
        }
        else if (component instanceof JRadioButton)
        {
            componentValueObj = new GeneralComponentValueObj();
            componentValueObj.setComponentType(GUIType.RADIO_BUTTON);
            componentValueObj.setComponentName(componentName);
            componentValueObj.setComponentValue(Boolean.toString(((JRadioButton) component).isSelected()));
            componentValueObj.setComponentDescription(((JRadioButton) component).getText());
        }
        // else if (component instanceof JComboBox)
        // {
        // Object selectedItem = ((JComboBox) component).getModel()
        // .getSelectedItem();
        // componentValueObj = new GeneralComponentValueObj();
        // componentValueObj.setComponentType(GUIType.COMBO_BOX);
        // componentValueObj.setComponentName(componentName);
        // componentValueObj.setComponentValue(selectedItem != null ? selectedItem.toString() : "");
        // }

        return componentValueObj;
    }

    public static Component generateComponent(GeneralComponentValueObj generalComponent)
    {
        switch (generalComponent.getComponentType())
        {
            case CHECK_BOX:
                JCheckBox checkBoxComponent = new JCheckBox(generalComponent.getComponentDescription());
                checkBoxComponent.setSelected(Boolean.parseBoolean(generalComponent.getComponentValue()));
                return checkBoxComponent;
            case RADIO_BUTTON:
                JRadioButton radioButtonComponent = new JRadioButton(generalComponent.getComponentDescription());
                radioButtonComponent.setSelected(Boolean.parseBoolean(generalComponent.getComponentValue()));
                return radioButtonComponent;
            case FRAME:
                JFrame frameComponent = new JFrame(generalComponent.getComponentDescription());
                frameComponent.setBounds(((FrameComponentValueObj) generalComponent).getxAnchor(),
                                         ((FrameComponentValueObj) generalComponent).getyAnchor(),
                                         ((FrameComponentValueObj) generalComponent).getWidth(),
                                         ((FrameComponentValueObj) generalComponent).getHeight());
                return frameComponent;
            case TEXTFIELD:
                JTextField textfieldComponent = new JTextField(generalComponent.getComponentValue());
                return textfieldComponent;
                // case COMBO_BOX:
                // JComboBox comboBoxComponent = new JComboBox(generalComponent.getComponentValue());
                // return comboBoxComponent;
            default:
                throw new IllegalArgumentException("Unsupported componentType: " + generalComponent.getComponentType()
                        + " caught while creating component from GeneralComponentValueObj");

        }
    }
}
