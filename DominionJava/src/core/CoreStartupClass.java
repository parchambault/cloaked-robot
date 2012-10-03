package core;

import exchange.rmi.RmiConnectionInfo;
import gui.GUIType;
import gui.callback.IButtonCallback;
import gui.layout.CustomTableLayout;
import gui.util.GUIUtils;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import common.startup.AbstractStartupClass;

public class CoreStartupClass
    extends AbstractStartupClass
    implements IButtonCallback
{
    private CoreServer mCoreServer = null;

    static
    {
        mClassNameStartup = CoreStartupClass.class.getName();
    }

    private enum componentsList
    {
        CoreServer,

        BTN_START_SERVER,

        TXTFLD_SERVER_HOST,
        TXTFLD_SERVER_PORT,

        LBL_HOST,
        LBL_PORT,
    };

    @Override
    public void startup()
        throws RemoteException
    {
        initUI();
        initListeners();
    }

    private void initUI()
    {
        GUIUtils.loadFrameFromStorage(componentsList.CoreServer);

        CustomTableLayout mainFrameLayout = new CustomTableLayout();
        mainFrameLayout.addRow(CustomTableLayout.Y_BORDER_TO_COMPONENT);
        mainFrameLayout.addRow(CustomTableLayout.PREFERRED);
        mainFrameLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        mainFrameLayout.addRow(CustomTableLayout.PREFERRED);
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
        subTopPanelLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        subTopPanelLayout.addColumn(CustomTableLayout.PREFERRED);
        subTopPanelLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        subTopPanelLayout.addColumn(100);
        subTopPanelLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);

        JPanel subTopPanel = new JPanel(subTopPanelLayout);
        subTopPanel.add(GUIUtils.addComponent(GUIType.LABEL,
                                              componentsList.LBL_HOST,
                                              "Host:"),
                        "1,1");
        subTopPanel.add(GUIUtils.addComponent(GUIType.LABEL,
                                              componentsList.LBL_PORT,
                                              "Port:"),
                        "5,1");
        subTopPanel.add(GUIUtils.addComponent(GUIType.TEXTFIELD,
                                              componentsList.TXTFLD_SERVER_HOST,
                                              "localhost"),
                        "3,1");
        subTopPanel.add(GUIUtils.addComponent(GUIType.TEXTFIELD,
                                              componentsList.TXTFLD_SERVER_PORT,
                                              "10200"),
                        "7,1");

        JFrame mainFrame = GUIUtils.getFrameComponent(componentsList.CoreServer.toString(),
                                                      400,
                                                      400);
        mainFrame.setLayout(mainFrameLayout);
        mainFrame.add(subTopPanel,
                      "1,1");
        mainFrame.add(GUIUtils.addButtonComponent(componentsList.BTN_START_SERVER,
                                                  this),
                      "1,3");

        mainFrame.setVisible(true);
    }

    private void initListeners()
    {
        JFrame mainFrame = (JFrame) GUIUtils.getComponent(componentsList.CoreServer);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                shutdown();
            }
        });
    }

    @Override
    public void processButtonClicked(Enum buttonId)
    {
        switch ((componentsList) buttonId)
        {
            case BTN_START_SERVER:
                startCoreServer();
                break;
        }
    }

    private void startCoreServer()
    {
        if (mCoreServer == null)
        {
            try
            {
                mCoreServer = CoreServer.getInstance();
                mCoreServer.startServer(new RmiConnectionInfo(Integer.parseInt((String) GUIUtils.getComponentInput(GUIType.TEXTFIELD,
                                                                                                                   componentsList.TXTFLD_SERVER_PORT)),
                                                              (String) GUIUtils.getComponentInput(GUIType.TEXTFIELD,
                                                                                                  componentsList.TXTFLD_SERVER_HOST)));
            }
            catch (RemoteException e)
            {
                // TODO Auto-generated catch stub
                e.printStackTrace();
            }
        }
    }

    private void shutdown()
    {
        if (mCoreServer != null)
        {
            mCoreServer.stopServer();
        }
    }
}
