package gui.testFrame;

import dominion.card.Card;
import dominion.card.CardSet;
import exchange.rmi.RmiClient;
import exchange.rmi.RmiConnectionInfo;
import exchange.rmi.service.IRmiService;
import gui.GUIType;
import gui.callback.IButtonCallback;
import gui.layout.CustomTableLayout;
import gui.util.GUIUtils;
import gui.util.ImageUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import common.startup.AbstractStartupClass;
import core.service.IAuthenticationService;
import core.service.impl.AuthenticationService;
import core.valueobj.LoginInfo;
import core.valueobj.PlayerInfo;

public class testGameSetupGUI
    extends AbstractStartupClass
    implements IButtonCallback
{
    static
    {
        mClassNameStartup = testGameSetupGUI.class.getName();
    }

    private static final Logger LOG = LogManager.getLogger(testGameSetupGUI.class);

    private RmiClient           mRmiClient;
    private PlayerInfo          mPlayerInfo;

    private enum componentsList
    {
        CreateGameSetup,

        ADD_CARD_BUTTON,
        BTN_CONNECT_TO_SERVER,

        TXTFLD_RESIZE,
        TXTFLD_SERVER_HOST,
        TXTFLD_SERVER_PORT,

        LBL_CARD_ICON,
        LBL_TO_COMPLETE,
        LBL_HOST,
        LBL_PORT,

        CBOX_SET_LIST,
        CBOX_CARD_LIST,

        LEFT_PANEL,
        RIGHT_PANEL,
        BOTTOM_PANEL,
    };

    // private ResourceBundle resources;

    public testGameSetupGUI()
    {
        Runtime.getRuntime()
               .addShutdownHook(new Thread() {
                   @Override
                   public void run()
                   {
                       shutdown();
                   }
               });
    }

    private void initUI()
    {
        GUIUtils.loadFrameFromStorage(componentsList.CreateGameSetup);
        initLeftPanel();
        initRightPanel();
        initBottomPanel();
        initListeners();
        setupCardBoxValues();
        updateCardPicture();

        CustomTableLayout mainFrameLayout = new CustomTableLayout();
        mainFrameLayout.addRow(CustomTableLayout.Y_BORDER_TO_COMPONENT);
        mainFrameLayout.addRow(CustomTableLayout.PREFERRED);
        mainFrameLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        mainFrameLayout.addRow(CustomTableLayout.PREFERRED);
        mainFrameLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        mainFrameLayout.addRow(CustomTableLayout.FILL);
        mainFrameLayout.addRow(CustomTableLayout.Y_BORDER_TO_COMPONENT);
        mainFrameLayout.addColumn(CustomTableLayout.X_BORDER_TO_COMPONENT);
        mainFrameLayout.addColumn(CustomTableLayout.FILL);
        mainFrameLayout.addColumn(CustomTableLayout.X_BORDER_TO_COMPONENT);

        CustomTableLayout subTopPanelLayout = new CustomTableLayout();
        subTopPanelLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        subTopPanelLayout.addRow(CustomTableLayout.PREFERRED);
        subTopPanelLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        subTopPanelLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);
        subTopPanelLayout.addColumn(CustomTableLayout.PREFERRED);
        subTopPanelLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        subTopPanelLayout.addColumn(CustomTableLayout.FILL);
        subTopPanelLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);

        JPanel subTopPanel = new JPanel(subTopPanelLayout);
        subTopPanel.add(GUIUtils.getComponent(componentsList.LEFT_PANEL),
                        "1,1");
        subTopPanel.add(GUIUtils.getComponent(componentsList.RIGHT_PANEL),
                        "3,1");

        JFrame mainFrame = GUIUtils.getFrameComponent(componentsList.CreateGameSetup.toString(),
                                                      800,
                                                      800);
        mainFrame.setLayout(mainFrameLayout);
        mainFrame.add(subTopPanel,
                      "1,1");
        mainFrame.add(GUIUtils.getComponent(componentsList.BOTTOM_PANEL),
                      "1,3");

        mainFrame.setVisible(true);
    }

    private void initLeftPanel()
    {
        CustomTableLayout leftPanelLayout = new CustomTableLayout();
        leftPanelLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        leftPanelLayout.addRow(CustomTableLayout.PREFERRED);
        leftPanelLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        leftPanelLayout.addRow(CustomTableLayout.PREFERRED);
        leftPanelLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        leftPanelLayout.addRow(CustomTableLayout.PREFERRED);
        leftPanelLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        leftPanelLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);
        leftPanelLayout.addColumn(CustomTableLayout.FILL);
        leftPanelLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);

        JPanel leftPanel = new JPanel(leftPanelLayout);
        JComboBox wSetList = (JComboBox) GUIUtils.addComponent(GUIType.COMBO_BOX,
                                                               componentsList.CBOX_SET_LIST,
                                                               "");
        for (CardSet iSetEntry : CardSet.values())
        {
            ((DefaultComboBoxModel) wSetList.getModel()).addElement(iSetEntry);
        }
        leftPanel.add(wSetList,
                      "1,1");
        leftPanel.add(GUIUtils.addComponent(GUIType.COMBO_BOX,
                                            componentsList.CBOX_CARD_LIST,
                                            ""),
                      "1,3");

        leftPanel.add(GUIUtils.addButtonComponent(componentsList.ADD_CARD_BUTTON,
                                                  this),
                      "1,5");
        GUIUtils.addPanelComponent(componentsList.LEFT_PANEL,
                                   leftPanel);
    }

    private void initRightPanel()
    {
        CustomTableLayout rightPanelLayout = new CustomTableLayout();
        rightPanelLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        rightPanelLayout.addRow(CustomTableLayout.PREFERRED);
        rightPanelLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        rightPanelLayout.addRow(CustomTableLayout.PREFERRED);
        rightPanelLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        rightPanelLayout.addColumn(CustomTableLayout.X_BORDER_TO_COMPONENT);
        rightPanelLayout.addColumn(CustomTableLayout.FILL);
        rightPanelLayout.addColumn(CustomTableLayout.X_BORDER_TO_COMPONENT);

        JPanel rightPanel = new JPanel(rightPanelLayout);
        rightPanel.add(GUIUtils.addComponent(GUIType.TEXTFIELD,
                                             componentsList.TXTFLD_RESIZE,
                                             ""),
                       "1,1");
        rightPanel.add(GUIUtils.addComponent(GUIType.LABEL,
                                             componentsList.LBL_CARD_ICON,
                                             ""),
                       "1,3");
        GUIUtils.addPanelComponent(componentsList.RIGHT_PANEL,
                                   rightPanel);
    }

    private void initBottomPanel()
    {
        CustomTableLayout mainBottomLayout = new CustomTableLayout();
        mainBottomLayout.addRow(CustomTableLayout.Y_BORDER_TO_COMPONENT);
        mainBottomLayout.addRow(CustomTableLayout.PREFERRED);
        mainBottomLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        mainBottomLayout.addRow(CustomTableLayout.PREFERRED);
        mainBottomLayout.addRow(CustomTableLayout.Y_BORDER_TO_COMPONENT);
        mainBottomLayout.addColumn(CustomTableLayout.X_BORDER_TO_COMPONENT);
        mainBottomLayout.addColumn(CustomTableLayout.FILL);
        mainBottomLayout.addColumn(CustomTableLayout.X_BORDER_TO_COMPONENT);

        CustomTableLayout subBottomLayout = new CustomTableLayout();
        subBottomLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        subBottomLayout.addRow(CustomTableLayout.PREFERRED);
        subBottomLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        subBottomLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);
        subBottomLayout.addColumn(CustomTableLayout.PREFERRED);
        subBottomLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        subBottomLayout.addColumn(CustomTableLayout.FILL);
        subBottomLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        subBottomLayout.addColumn(CustomTableLayout.PREFERRED);
        subBottomLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        subBottomLayout.addColumn(100);
        subBottomLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);

        JPanel subBottomPanel = new JPanel(subBottomLayout);
        subBottomPanel.add(GUIUtils.addComponent(GUIType.LABEL,
                                                 componentsList.LBL_HOST,
                                                 "Host:"),
                           "1,1");
        subBottomPanel.add(GUIUtils.addComponent(GUIType.TEXTFIELD,
                                                 componentsList.TXTFLD_SERVER_HOST,
                                                 "localhost"),
                           "3,1");
        subBottomPanel.add(GUIUtils.addComponent(GUIType.LABEL,
                                                 componentsList.LBL_PORT,
                                                 "Port:"),
                           "5,1");
        subBottomPanel.add(GUIUtils.addComponent(GUIType.TEXTFIELD,
                                                 componentsList.TXTFLD_SERVER_PORT,
                                                 "10200"),
                           "7,1");

        JPanel bottomPanel = new JPanel(mainBottomLayout);
        bottomPanel.add(subBottomPanel,
                        "1,1");
        bottomPanel.add(GUIUtils.addButtonComponent(componentsList.BTN_CONNECT_TO_SERVER,
                                                    this),
                        "1,3");
        GUIUtils.addPanelComponent(componentsList.BOTTOM_PANEL,
                                   bottomPanel);
    }

    private void initListeners()
    {
        JComboBox setBox = (JComboBox) GUIUtils.getComponent(componentsList.CBOX_SET_LIST);
        JComboBox cardBox = (JComboBox) GUIUtils.getComponent(componentsList.CBOX_CARD_LIST);
        JTextField resizeFld = (JTextField) GUIUtils.getComponent(componentsList.TXTFLD_RESIZE);

        setBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setupCardBoxValues();
            }
        });

        cardBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                updateCardPicture();
            }
        });

        resizeFld.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e)
            {
                updateCardPicture();
            }
        });
    }

    @Override
    public void startup()
    {
        testGameSetupGUI testFrame = new testGameSetupGUI();
        testFrame.initUI();
        testFrame.initListeners();
        testFrame.setupCardBoxValues();
    }

    @Override
    public void processButtonClicked(Enum buttonId)
    {
        switch ((componentsList) buttonId)
        {
            case BTN_CONNECT_TO_SERVER:
                if (mRmiClient == null)
                {
                    connectToServer();
                }
                break;
            case ADD_CARD_BUTTON:
                break;
        }
    }

    private void setupCardBoxValues()
    {
        JComboBox setBox = (JComboBox) GUIUtils.getComponent(componentsList.CBOX_SET_LIST);
        JComboBox cardBox = (JComboBox) GUIUtils.getComponent(componentsList.CBOX_CARD_LIST);

        DefaultComboBoxModel newModel = new DefaultComboBoxModel();
        for (Card aCard : Card.values())
        {
            if (aCard.getCardSet() == (CardSet) setBox.getSelectedItem())
            {
                newModel.addElement(aCard);
            }
        }
        cardBox.setModel(newModel);

    }

    private void updateCardPicture()
    {
        JComboBox cardBox = (JComboBox) GUIUtils.getComponent(componentsList.CBOX_CARD_LIST);
        JLabel iconLabel = (JLabel) GUIUtils.getComponent(componentsList.LBL_CARD_ICON);

        int resize = -1;
        try
        {
            resize = Integer.parseInt(GUIUtils.getTextFieldInput(componentsList.TXTFLD_RESIZE));
        }
        catch (Throwable ex)
        {
            LOG.debug(ex.getMessage());
        }

        if (resize != -1)
        {
            iconLabel.setIcon(ImageUtil.getScaledCard((Card) cardBox.getSelectedItem(),
                                                      resize,
                                                      resize));
        }
        else
        {
            iconLabel.setIcon(ImageUtil.getCardImage((Card) cardBox.getSelectedItem()));
        }
    }

    private void connectToServer()
    {
        RmiConnectionInfo wServerConnectionInfo = null;
        try
        {
            wServerConnectionInfo =
                    new RmiConnectionInfo(Integer.parseInt(GUIUtils.getTextFieldInput(componentsList.TXTFLD_SERVER_PORT)),
                                          GUIUtils.getTextFieldInput(componentsList.TXTFLD_SERVER_HOST));
            mRmiClient = new RmiClient(wServerConnectionInfo);
            IRmiService authenticationServer = ((mRmiClient.getRemoteService(AuthenticationService.SERVICE_NAME)));
            mPlayerInfo = ((IAuthenticationService) authenticationServer).login(new LoginInfo("TestGui",
                                                                                              "12345"));
            LOG.info("ClientId is: " + mPlayerInfo.getPlayerId());

        }
        catch (Throwable ex)
        {
            LOG.error("Unable to connect to server: " + wServerConnectionInfo.toString(),
                      ex);
            mRmiClient = null;
        }
    }

    private void shutdown()
    {
        if (mRmiClient == null)
        {
            return;
        }

        try
        {
            IRmiService authenticationServer = ((mRmiClient.getRemoteService(AuthenticationService.SERVICE_NAME)));
            ((IAuthenticationService) authenticationServer).logout(mPlayerInfo);
        }
        catch (Throwable e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
