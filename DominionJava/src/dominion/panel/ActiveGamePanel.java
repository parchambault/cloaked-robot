package dominion.panel;

import gui.callback.IButtonCallback;
import gui.layout.CustomTableLayout;
import gui.util.GUIUtils;
import javax.swing.JPanel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import dominion.card.Card;
import dominion.panel.activegame.CommonCardPanel;
import dominion.panel.activegame.GameCardPanel;
import dominion.panel.activegame.HandCardPanel;
import dominion.panel.activegame.OpponentListPanel;

public class ActiveGamePanel
    extends JPanel
    implements IButtonCallback
{
    private static final Logger LOG = LogManager.getLogger(ActiveGamePanel.class);

    public enum ActiveGamePanelComponent
    {
        PANEL_OPPONENT_LIST,
        PANEL_GAME_CARD,
        PANEL_COMMON_CARD,
        PANEL_PLAYER_HAND,

        BUTTON_TEST_SOMETIN,
    };

    public ActiveGamePanel()
    {
        GUIUtils.addPanelComponent(ActiveGamePanelComponent.PANEL_OPPONENT_LIST,
                                   new OpponentListPanel());
        GUIUtils.addPanelComponent(ActiveGamePanelComponent.PANEL_GAME_CARD,
                                   new GameCardPanel());
        GUIUtils.addPanelComponent(ActiveGamePanelComponent.PANEL_COMMON_CARD,
                                   new CommonCardPanel());
        GUIUtils.addPanelComponent(ActiveGamePanelComponent.PANEL_PLAYER_HAND,
                                   new HandCardPanel());
        initUI();
        initListener();
    }

    private void initUI()
    {
        CustomTableLayout mainLayout = new CustomTableLayout();
        mainLayout.addRow(CustomTableLayout.FILL);
        mainLayout.addColumn(CustomTableLayout.FILL);
        mainLayout.addColumn(0.16);

        CustomTableLayout wLeftGameLayout = new CustomTableLayout();
        wLeftGameLayout.addRow(0.25);
        wLeftGameLayout.addRow(CustomTableLayout.FILL);
        wLeftGameLayout.addRow(CustomTableLayout.PREFERRED);
        wLeftGameLayout.addColumn(CustomTableLayout.FILL);

        JPanel wLeftGamePanel = new JPanel(wLeftGameLayout);
        wLeftGamePanel.add(GUIUtils.getComponent(ActiveGamePanelComponent.PANEL_COMMON_CARD),
                           "0,0");
        wLeftGamePanel.add(GUIUtils.getComponent(ActiveGamePanelComponent.PANEL_GAME_CARD),
                           "0,1");
        wLeftGamePanel.add(GUIUtils.getComponent(ActiveGamePanelComponent.PANEL_PLAYER_HAND),
                           "0,2");

        CustomTableLayout wRightGameLayout = new CustomTableLayout();
        wRightGameLayout.addColumn(CustomTableLayout.FILL);
        wRightGameLayout.addRow(CustomTableLayout.FILL);
        wRightGameLayout.addRow(CustomTableLayout.PREFERRED);

        JPanel wRightGamePanel = new JPanel(wRightGameLayout);
        wRightGamePanel.add(GUIUtils.getComponent(ActiveGamePanelComponent.PANEL_OPPONENT_LIST),
                            "0,0");
        wRightGamePanel.add(GUIUtils.addButtonComponent(ActiveGamePanelComponent.BUTTON_TEST_SOMETIN,
                                                        this),
                            "0,1");

        this.setLayout(mainLayout);
        this.add(wLeftGamePanel,
                 "0,0");
        this.add(wRightGamePanel,
                 "1,0");
    }

    private void initListener()
    {
    }

    @Override
    public void processButtonClicked(Enum iButtonId)
    {
        switch ((ActiveGamePanelComponent) iButtonId)
        {
            case BUTTON_TEST_SOMETIN:
                HandCardPanel wHandCardPanel =
                        (HandCardPanel) GUIUtils.getComponent(ActiveGamePanel.ActiveGamePanelComponent.PANEL_PLAYER_HAND);
                wHandCardPanel.addCard(Card.ALCHEMIST);
                this.validate();
                this.repaint();
                break;
        }
    }
}
