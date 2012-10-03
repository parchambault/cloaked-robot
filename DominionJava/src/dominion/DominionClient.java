package dominion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import core.service.ClientChatService;
import core.service.IAuthenticationService;
import core.service.impl.AuthenticationService;
import core.valueobj.LoginInfo;
import core.valueobj.PlayerInfo;
import dominion.panel.ActiveGamePanel;
import dominion.panel.gameconfig.GameSetupPanel;
import exchange.rmi.RmiClient;
import exchange.rmi.RmiConnectionInfo;
import exchange.rmi.exception.ServiceNotFoundException;
import exchange.rmi.service.IRmiService;
import gui.layout.CustomTableLayout;
import gui.panel.ChatPanel;
import gui.panel.LoginPanel;
import gui.util.GUIUtils;

public class DominionClient
{
    private static final Logger  LOG = LogManager.getLogger(DominionClient.class);

    public static DominionClient mSingletonInstance;

    // container info
    private PlayerInfo           mPlayerInfo;

    // Service info
    private RmiClient            mRmiClient;
    private ClientChatService    mClientChatService;

    private JFrame               mMainFrameQuickRef;

    public enum DominionClientComponent
    {
        DominionClient,

        PANEL_LOGIN,
        PANEL_CHAT,
        PANEL_GAME_SETUP,
        PANEL_ACTIVE_GAME,
    }

    private DominionClient()
    {
        Runtime.getRuntime()
               .addShutdownHook(new Thread() {
                   @Override
                   public void run()
                   {
                       shutdown();
                   }
               });
    };

    public static DominionClient getInstance()
    {
        if (mSingletonInstance == null)
        {
            LOG.debug("Creating singleton instance of DominionClient.");
            mSingletonInstance = new DominionClient();
            mSingletonInstance.initialize();
        }
        return mSingletonInstance;
    }

    private void initialize()
    {
        GUIUtils.loadFrameFromStorage(DominionClientComponent.DominionClient);
        mMainFrameQuickRef = GUIUtils.getFrameComponent(DominionClientComponent.DominionClient.toString(),
                                                        800,
                                                        800);
        GUIUtils.addPanelComponent(DominionClientComponent.PANEL_ACTIVE_GAME,
                                   new ActiveGamePanel());
        GUIUtils.addPanelComponent(DominionClientComponent.PANEL_CHAT,
                                   new ChatPanel());
        GUIUtils.addPanelComponent(DominionClientComponent.PANEL_LOGIN,
                                   new LoginPanel());
        GUIUtils.addPanelComponent(DominionClientComponent.PANEL_GAME_SETUP,
                                   new GameSetupPanel());

        mMainFrameQuickRef.setJMenuBar(createMenuBar());
        mMainFrameQuickRef.getJMenuBar()
                          .setVisible(false);

        showLoginLayout();
        mMainFrameQuickRef.setVisible(true);
    }

    public void login(RmiConnectionInfo iServerConnectionInfo,
                      LoginInfo iLoginInfo)
        throws RemoteException, ServiceNotFoundException
    {
        // try
        // {
        mRmiClient = new RmiClient(iServerConnectionInfo);

        IRmiService authenticationServer = ((mRmiClient.getRemoteService(AuthenticationService.SERVICE_NAME)));
        mPlayerInfo = ((IAuthenticationService) authenticationServer).login(iLoginInfo);

        ChatPanel wChatPanel = (ChatPanel) GUIUtils.getComponent(DominionClientComponent.PANEL_CHAT);
        mClientChatService = new ClientChatService(mPlayerInfo,
                                                   wChatPanel);
        mClientChatService.startExchange(iServerConnectionInfo);
        wChatPanel.setClientChatService(mClientChatService);

        mMainFrameQuickRef.getJMenuBar()
                          .setVisible(true);
        resetFrameContent();
        showGameLayout();

        // }
        // catch (Throwable ex)
        // {
        // LOG.error("Unable to connect to server.",
        // ex);
        // }
    }

    private void resetFrameContent()
    {
        mMainFrameQuickRef.getContentPane()
                          .removeAll();
        mMainFrameQuickRef.getContentPane()
                          .validate();
        mMainFrameQuickRef.getContentPane()
                          .repaint();
    }

    private void showLoginLayout()
    {
        resetFrameContent();
        CustomTableLayout loginLayout = new CustomTableLayout();
        loginLayout.addRow(0.2);
        loginLayout.addRow(CustomTableLayout.FILL);
        loginLayout.addRow(0.2);
        loginLayout.addColumn(0.2);
        loginLayout.addColumn(CustomTableLayout.FILL);
        loginLayout.addColumn(0.2);

        mMainFrameQuickRef.setLayout(loginLayout);
        mMainFrameQuickRef.add(GUIUtils.getComponent(DominionClientComponent.PANEL_LOGIN),
                               "1,1");
        mMainFrameQuickRef.getContentPane()
                          .validate();
        mMainFrameQuickRef.getContentPane()
                          .repaint();
    }

    private void showMainLayout()
    {
        resetFrameContent();
        CustomTableLayout mainLayout = new CustomTableLayout();
        mainLayout.addRow(CustomTableLayout.FILL);
        mainLayout.addRow(0.25);
        mainLayout.addColumn(CustomTableLayout.FILL);

        mMainFrameQuickRef.setLayout(mainLayout);
        mMainFrameQuickRef.add(GUIUtils.getComponent(DominionClientComponent.PANEL_GAME_SETUP),
                               "0,0");
        mMainFrameQuickRef.add(GUIUtils.getComponent(DominionClientComponent.PANEL_CHAT),
                               "0,1");
        mMainFrameQuickRef.getContentPane()
                          .validate();
        mMainFrameQuickRef.getContentPane()
                          .repaint();
    }

    private void showGameLayout()
    {
        resetFrameContent();
        CustomTableLayout gameLayout = new CustomTableLayout();
        gameLayout.addRow(CustomTableLayout.FILL);
        gameLayout.addRow(0.25);
        gameLayout.addColumn(CustomTableLayout.FILL);

        mMainFrameQuickRef.setLayout(gameLayout);
        mMainFrameQuickRef.add(GUIUtils.getComponent(DominionClientComponent.PANEL_ACTIVE_GAME),
                               "0,0");
        mMainFrameQuickRef.add(GUIUtils.getComponent(DominionClientComponent.PANEL_CHAT),
                               "0,1");
        mMainFrameQuickRef.getContentPane()
                          .validate();
        mMainFrameQuickRef.getContentPane()
                          .repaint();
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
        catch (Throwable ex)
        {
            LOG.error("Exception caught while shutting down.",
                      ex);
        }
    }

    public JMenuBar createMenuBar()
    {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        // Create the menu bar.
        menuBar = new JMenuBar();

        // Build the first menu.
        menu = new JMenu("Game");
        menu.getAccessibleContext()
            .setAccessibleDescription("The only menu in this program that has menu items");
        menuBar.add(menu);

        // a group of JMenuItems
        menuItem = new JMenuItem("Setup a game");
        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                showMainLayout();
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("See game panel");
        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                showGameLayout();
            }
        });
        menu.add(menuItem);

        return menuBar;
    }
}
