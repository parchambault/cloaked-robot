package gui.panel;

import dominion.DominionClient;
import exchange.rmi.RmiConnectionInfo;
import exchange.rmi.exception.ServiceNotFoundException;
import gui.GUIType;
import gui.callback.IButtonCallback;
import gui.layout.CustomTableLayout;
import gui.util.GUIUtils;
import java.rmi.RemoteException;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import core.valueobj.LoginInfo;

public class LoginPanel
    extends JPanel
    implements IButtonCallback
{
    private static final Logger LOG = LogManager.getLogger(LoginPanel.class);

    public enum LoginPanelComponent
    {
        BTN_CONNECT_TO_SERVER,

        TXTAREA_LOGIN_ERR_MSG,

        TXTFLD_SERVER_HOST,
        TXTFLD_SERVER_PORT,
        TXTFLD_USERNAME,
        TXTFLD_PASSWORD,

        LBL_HOST,
        LBL_PORT,
        LBL_USERNAME,
        LBL_PASSWORD,
    };

    public LoginPanel()
    {
        initUI();
        initListener();
    }

    private void initUI()
    {
        CustomTableLayout mainLayout = new CustomTableLayout();
        mainLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        mainLayout.addRow(CustomTableLayout.PREFERRED);
        mainLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        mainLayout.addRow(CustomTableLayout.PREFERRED);
        mainLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        mainLayout.addRow(0.2);
        mainLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        mainLayout.addRow(CustomTableLayout.FILL);
        mainLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);
        mainLayout.addColumn(CustomTableLayout.FILL);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);

        this.setLayout(mainLayout);

        this.add(initUserInfoPanel(),
                 "1,1");
        this.add(initServerInfoPanel(),
                 "1,3");
        this.add(GUIUtils.addButtonComponent(LoginPanelComponent.BTN_CONNECT_TO_SERVER,
                                             this),
                 "1,5");

        // JTextArea wErrMsgArea = (JTextArea) GUIUtils.addComponent(GUIType.TEXTAREA,
        // LoginPanelComponent.TXTAREA_LOGIN_ERR_MSG,
        // "error message are written here...");
        // wErrMsgArea.setEditable(false);
        // wErrMsgArea.setLineWrap(true);
        // wErrMsgArea.setWrapStyleWord(true);

        // this.add(wErrMsgArea,
        // "1,7");
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                                                        "Connection Setup"));

    }

    private void initListener()
    {
    }

    private JPanel initUserInfoPanel()
    {
        CustomTableLayout subLoginLayout = new CustomTableLayout();
        subLoginLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        subLoginLayout.addRow(CustomTableLayout.PREFERRED);
        subLoginLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        subLoginLayout.addRow(CustomTableLayout.PREFERRED);
        subLoginLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        subLoginLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);
        subLoginLayout.addColumn(CustomTableLayout.PREFERRED);
        subLoginLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        subLoginLayout.addColumn(CustomTableLayout.FILL);
        subLoginLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);

        JPanel subLoginPanel = new JPanel(subLoginLayout);
        subLoginPanel.add(GUIUtils.addComponent(GUIType.LABEL,
                                                LoginPanelComponent.LBL_USERNAME,
                                                "Username: "),
                          "1,1");
        subLoginPanel.add(GUIUtils.addComponent(GUIType.TEXTFIELD,
                                                LoginPanelComponent.TXTFLD_USERNAME,
                                                ""),
                          "3,1");
        subLoginPanel.add(GUIUtils.addComponent(GUIType.LABEL,
                                                LoginPanelComponent.LBL_PASSWORD,
                                                "Password: "),
                          "1,3");
        subLoginPanel.add(GUIUtils.addComponent(GUIType.PASSWORD_FIELD,
                                                LoginPanelComponent.TXTFLD_PASSWORD,
                                                ""),
                          "3,3");
        subLoginPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(),
                                                                 "User info"));
        return subLoginPanel;
    }

    private JPanel initServerInfoPanel()
    {
        CustomTableLayout subServerLayout = new CustomTableLayout();
        subServerLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        subServerLayout.addRow(CustomTableLayout.PREFERRED);
        subServerLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        subServerLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);
        subServerLayout.addColumn(CustomTableLayout.PREFERRED);
        subServerLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        subServerLayout.addColumn(CustomTableLayout.FILL);
        subServerLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        subServerLayout.addColumn(CustomTableLayout.PREFERRED);
        subServerLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        subServerLayout.addColumn(0.3);
        subServerLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);

        JPanel subServerPanel = new JPanel(subServerLayout);
        subServerPanel.add(GUIUtils.addComponent(GUIType.LABEL,
                                                 LoginPanelComponent.LBL_HOST,
                                                 "Host: "),
                           "1,1");
        subServerPanel.add(GUIUtils.addComponent(GUIType.TEXTFIELD,
                                                 LoginPanelComponent.TXTFLD_SERVER_HOST,
                                                 ""),
                           "3,1");
        subServerPanel.add(GUIUtils.addComponent(GUIType.LABEL,
                                                 LoginPanelComponent.LBL_PORT,
                                                 "Port: "),
                           "5,1");
        subServerPanel.add(GUIUtils.addComponent(GUIType.TEXTFIELD,
                                                 LoginPanelComponent.TXTFLD_SERVER_PORT,
                                                 ""),
                           "7,1");
        subServerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),
                                                                  "Server info"));
        return subServerPanel;
    }

    @Override
    public void processButtonClicked(Enum iButtonId)
    {
        switch ((LoginPanelComponent) iButtonId)
        {
            case BTN_CONNECT_TO_SERVER:
                try
                {
                    connectToServer();
                }
                catch (Throwable ex)
                {
                    // TODO : Auto-generated catch block REVOIR comment la validation est faite
                    // ((JTextArea)
                    // GUIUtils.getComponent(LoginPanelComponent.TXTAREA_LOGIN_ERR_MSG)).setText(ex.getMessage());
                    LOG.error("Unable to connect to server...",
                              ex);
                }
                break;
        }
    }

    // private boolean isValidInputField()
    // {
    // boolean wIsValid = true;
    // JTextField wPortFld = (JTextField) GUIUtils.getComponent(componentsList.TXTFLD_SERVER_PORT);
    // JTextField wHostFld = (JTextField) GUIUtils.getComponent(componentsList.TXTFLD_SERVER_HOST);
    // JTextField wUsernameFld = (JTextField) GUIUtils.getComponent(componentsList.TXTFLD_USERNAME);
    //
    // try
    // {
    // Integer.parseInt(wPortFld.getText());
    // wPortFld.setBorder(BorderFactory.createEtchedBorder());
    // }
    // catch (Throwable ex)
    // {
    // wIsValid = false;
    // wPortFld.setBorder(BorderFactory.createLineBorder(Color.RED));
    // }
    //
    // if (wHostFld.getText()
    // .isEmpty())
    // {
    // wIsValid = false;
    // wHostFld.setBorder(BorderFactory.createLineBorder(Color.RED));
    // }
    // else
    // {
    // wHostFld.setBorder(BorderFactory.createEtchedBorder());
    // }
    //
    // if (wUsernameFld.getText()
    // .isEmpty())
    // {
    // wIsValid = false;
    // wUsernameFld.setBorder(BorderFactory.createLineBorder(Color.RED));
    // }
    // else
    // {
    // wUsernameFld.setBorder(BorderFactory.createEtchedBorder());
    // }
    //
    // return wIsValid;
    // }

    private void connectToServer()
        throws RemoteException, ServiceNotFoundException
    {
        // if (!isValidInputField())
        // {
        // return;
        // }

        // TODO : voir pour effectuer une validation des champs et kke chose de beau au GUI
        RmiConnectionInfo wServerConnectionInfo =
                new RmiConnectionInfo(Integer.parseInt(GUIUtils.getTextFieldInput(LoginPanelComponent.TXTFLD_SERVER_PORT)),
                                      GUIUtils.getTextFieldInput(LoginPanelComponent.TXTFLD_SERVER_HOST));

        LoginInfo wLoginInfo = new LoginInfo((GUIUtils.getTextFieldInput(LoginPanelComponent.TXTFLD_USERNAME)),
                                             GUIUtils.getTextFieldInput(LoginPanelComponent.TXTFLD_PASSWORD));

        DominionClient.getInstance()
                      .login(wServerConnectionInfo,
                             wLoginInfo);
    }
}
