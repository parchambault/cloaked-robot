package gui.util;

import gui.GUIType;
import gui.callback.IButtonCallback;
import gui.component.CardIconLabel;
import gui.valueobj.GUIComponentsListValueObj;
import gui.valueobj.GeneralComponentValueObj;
import gui.valueobj.factory.GeneralComponentFactory;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import dominion.card.Card;

public class GUIUtils
{
    private static final Logger               LOG           = LogManager.getLogger(GUIUtils.class);
    private static HashMap<String, Component> componentsMap = new HashMap<String, Component>();

    private GUIUtils()
    {
    }

    public static Component getComponent(final Enum componentId)
    {
        Component componentSelected = componentsMap.get(componentId.toString());
        if (componentSelected == null)
        {
            throw new IllegalArgumentException("Unknown component: " + componentId.toString());
        }
        return componentSelected;
    }

    public static String getTextFieldInput(final Enum componentId)
    {
        Component componentSelected = componentsMap.get(componentId.toString());
        if (componentSelected == null)
        {
            throw new IllegalArgumentException("Getting input from an unknown component: " + componentId.toString());
        }

        return ((JTextField) componentSelected).getText();
    }

    @Deprecated
    public static Object getComponentInput(GUIType componentType,
                                           final Enum componentId)
    {
        Component componentSelected = componentsMap.get(componentId.toString());
        if (componentSelected == null)
        {
            throw new IllegalArgumentException("Getting input from an unknown component: " + componentId.toString());
        }

        switch (componentType)
        {
            case TEXTFIELD:
                return ((JTextField) componentSelected).getText();
            default:
                throw new IllegalArgumentException("Unsupported input getter for componentType: " + componentType);
        }
    }

    public static Component addButtonComponent(final Enum buttonId,
                                               final IButtonCallback callbackInterface)
    {
        JButton generatedButon = (JButton) generateComponent(GUIType.BUTTON,
                                                             buttonId,
                                                             null);
        generatedButon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                callbackInterface.processButtonClicked(buttonId);
            }
        });
        return generatedButon;
    }

    public static Component addComponent(GUIType componentType,
                                         Enum componentId,
                                         String componentDefaultValue)
    {
        return generateComponent(componentType,
                                 componentId,
                                 componentDefaultValue);
    }

    public static void addPanelComponent(final Enum componentId,
                                         JPanel panelToAdd)
    {
        validateComponentUniqueness(componentId);
        componentsMap.put(componentId.toString(),
                          panelToAdd);
    }

    public static void loadFrameFromStorage(Enum componentId)
    {
        loadXmlComponents(componentId.toString());
    }

    private static void loadXmlComponents(String fileName)
    {
        try
        {
            File xmlFile = new File("/home/devjava/GUIState/" + fileName + ".xml");
            if (xmlFile.exists())
            {
                SAXBuilder xmlBuilder = new SAXBuilder();
                Document xmlDoc = xmlBuilder.build(xmlFile);

                GUIComponentsListValueObj generalComponentsList =
                        new GUIComponentsListValueObj(xmlDoc.getRootElement());
                for (GeneralComponentValueObj generalComponentEntry : generalComponentsList.getComponentsList())
                {
                    componentsMap.put(generalComponentEntry.getComponentName(),
                                      GeneralComponentFactory.generateComponent(generalComponentEntry));
                }
            }
        }
        catch (Throwable e)
        {
            LOG.error("Unable to loadXmlComponents from file: ",
                      e);
        }
    }

    private static void saveXmlComponents(String fileName)
    {
        FileOutputStream xml_fos = null;

        try
        {
            File xmlFile = new File("/home/devjava/GUIState/" + fileName + ".xml");
            if (!xmlFile.exists())
            {
                FileUtil.createFile(xmlFile);
            }

            xml_fos = new FileOutputStream(xmlFile);

            GUIComponentsListValueObj componentsListValueObj = new GUIComponentsListValueObj();
            for (Entry<String, Component> componentEntry : componentsMap.entrySet())
            {
                GeneralComponentValueObj generatedComponentValueObj =
                        GeneralComponentFactory.generateGeneralComponent(componentEntry.getKey(),
                                                                         componentEntry.getValue());
                if (generatedComponentValueObj != null)
                {
                    componentsListValueObj.getComponentsList()
                                          .add(generatedComponentValueObj);
                }
            }

            Document xmlDoc = new Document(componentsListValueObj.toXml());
            XMLOutputter xmlWriter = new XMLOutputter(Format.getPrettyFormat());

            xmlWriter.output(xmlDoc,
                             xml_fos);
            xml_fos.close();
        }
        catch (Throwable e)
        {
            LOG.error("Unable to saveXmlComponents from file: ",
                      e);
        }
    }

    public static JFrame getFrameComponent(final String frameName,
                                           final int width,
                                           final int height)
    {
        JFrame frameComponent = null;
        if (componentsMap.get(frameName) != null && componentsMap.get(frameName) instanceof JFrame)
        {
            frameComponent = (JFrame) componentsMap.get(frameName);
        }
        else
        {
            frameComponent = new JFrame(frameName);
            frameComponent.setSize(width,
                                   height);
            componentsMap.put(frameName,
                              frameComponent);
        }

        addSaveOnClose(frameName,
                       frameComponent);
        return frameComponent;
    }

    public static void addSaveOnClose(final String frameName,
                                      final JFrame frame)
    {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                saveXmlComponents(frameName);
                frame.dispose();
            }
        });
    }

    private static Component generateComponent(GUIType componentType,
                                               final Enum componentId,
                                               String componentDefaultValue)
    {
        validateComponentUniqueness(componentId);
        Component generatedComponent = componentsMap.get(componentId.toString());

        if (generatedComponent == null)
        {

            switch (componentType)
            {
                case BUTTON:
                    generatedComponent = new JButton(componentId.toString());
                    break;
                case COMBO_BOX:
                    generatedComponent = new JComboBox(new DefaultComboBoxModel());
                    break;
                case LABEL:
                    generatedComponent = new JLabel(componentDefaultValue);
                    break;
                case CARD_ICON_LABEL:
                    generatedComponent = new CardIconLabel();
                    if (componentDefaultValue != null && !componentDefaultValue.isEmpty())
                    {
                        ((CardIconLabel) generatedComponent).setCard(Card.valueOf(componentDefaultValue));
                    }
                    break;
                case CHECK_BOX:
                    generatedComponent = new JCheckBox(componentDefaultValue);
                    break;
                case LIST:
                    generatedComponent = new JList(new DefaultListModel());
                    break;
                case RADIO_BUTTON:
                    generatedComponent = new JRadioButton(componentDefaultValue);
                    break;
                case TEXTAREA:
                    generatedComponent = new JTextArea();
                    break;
                case TEXTFIELD:
                    generatedComponent = new JTextField(componentDefaultValue);
                    break;
                case PASSWORD_FIELD:
                    generatedComponent = new JPasswordField(componentDefaultValue);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported component yet: " + componentType);
            }
            componentsMap.put(componentId.toString(),
                              generatedComponent);
        }
        return generatedComponent;
    }

    private static void validateComponentUniqueness(Enum componentId)
    {
        if (componentsMap.containsKey(componentId.toString()))
        {
            LOG.debug("ComponentId: " + componentId.toString() + " already exists.");
        }
    }
}
