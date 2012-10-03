package gui.testFrame;

import gui.GUIType;
import gui.callback.IButtonCallback;
import gui.layout.CustomTableLayout;
import gui.util.GUIUtils;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUITestFrame
    implements IButtonCallback
{
    private enum componentsList
    {
        GuiTestFrame,
        BUTTON_CLICK,
        TEXT_FIELD_INPUT1
    };

    // private ResourceBundle resources;

    public GUITestFrame()
    {
    }

    public void initUI()
    {
        CustomTableLayout mainPanelLayout = new CustomTableLayout();
        mainPanelLayout.addRow(CustomTableLayout.Y_BORDER_TO_COMPONENT);
        mainPanelLayout.addRow(CustomTableLayout.PREFERRED);
        mainPanelLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        mainPanelLayout.addRow(CustomTableLayout.PREFERRED);
        mainPanelLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        mainPanelLayout.addRow(CustomTableLayout.PREFERRED);
        mainPanelLayout.addRow(CustomTableLayout.Y_BORDER_TO_COMPONENT);
        mainPanelLayout.addColumn(CustomTableLayout.X_BORDER_TO_COMPONENT);
        mainPanelLayout.addColumn(CustomTableLayout.FILL);
        mainPanelLayout.addColumn(CustomTableLayout.X_BORDER_TO_COMPONENT);

        JFrame mainFrame = GUIUtils.getFrameComponent("GUITestFrame",
                                                      150,
                                                      200);
        JPanel mainPanel = new JPanel(mainPanelLayout);
        mainPanel.add(GUIUtils.addButtonComponent(componentsList.BUTTON_CLICK,
                                                  this),
                      "1,1");
        mainPanel.add(GUIUtils.addComponent(GUIType.TEXTFIELD,
                                            componentsList.TEXT_FIELD_INPUT1,
                                            "helloWorld!"),
                      "1,3");

        // ImageIcon icon = ImageUtil.createImageIcon("dominionCardRessource/french/dominion/Bucheron.gif",
        // "");
        //
        // ImageIcon iconResized = new ImageIcon(ImageUtil.getScaledImage(icon.getImage(),
        // 200,
        // 200));
        // iconResized.setDescription("+ 1 achat\n+ 2 pièces");
        // JLabel myLabelWithImage = new JLabel(iconResized);

        // mainPanel.add(myLabelWithImage,
        // "1, 5");

        mainFrame.setTitle("Randomized Windows" + Math.round((Math.random() * 100)));
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args)
    {
        GUITestFrame testFrame = new GUITestFrame();
        testFrame.initUI();
    }

    @Override
    public void processButtonClicked(Enum buttonId)
    {
        JOptionPane.showMessageDialog(GUIUtils.getFrameComponent("GUITestFrame",
                                                                 150,
                                                                 200),
                                      GUIUtils.getComponentInput(GUIType.TEXTFIELD,
                                                                 componentsList.TEXT_FIELD_INPUT1),
                                      "Message",
                                      JOptionPane.PLAIN_MESSAGE);
    }
}
