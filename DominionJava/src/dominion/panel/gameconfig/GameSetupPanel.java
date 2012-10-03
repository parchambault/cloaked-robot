package dominion.panel.gameconfig;

import gui.GUIType;
import gui.callback.IButtonCallback;
import gui.component.CardIconLabel;
import gui.layout.CustomTableLayout;
import gui.util.GUIUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import dominion.card.Card;
import dominion.card.CardSet;
import dominion.card.DominionSet;
import dominion.panel.ActiveGamePanel;
import dominion.panel.activegame.GameCardPanel;

public class GameSetupPanel
    extends JPanel
    implements IButtonCallback
{
    private static final Logger LOG = LogManager.getLogger(GameSetupPanel.class);

    public enum GameSetupPanelComponent
    {
        BTN_ADD_CARD_SETUP,
        BTN_CLEAR_CARD_SETUP,
        BTN_SELECT_CARD_PRESET,
        BTN_CREATE_GAME,

        CBOX_SET_LIST,
        CBOX_SET_PRESET_LIST,
        CBOX_CARD_LIST,

        CHBOX_USE_DOMINION,
        CHBOX_USE_INTRIGUE,
        CHBOX_USE_SEASIDE,
        CHBOX_USE_ALCHEMY,

        CARDICON_PREVIEW,
    };

    public GameSetupPanel()
    {
        initUI();
        initListener();

        setupCardBoxValues();
        updateCardPicture();
    }

    private void initUI()
    {
        CustomTableLayout mainLayout = new CustomTableLayout();
        mainLayout.addRow(0.5);
        mainLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        mainLayout.addRow(CustomTableLayout.FILL);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);
        mainLayout.addColumn(CustomTableLayout.FILL);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);

        this.setLayout(mainLayout);

        this.add(initSubTopPanel(),
                 "1,0");
        this.add(new JPanel(),
                 "1,2");
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                                                        "Game Setup"));

    }

    private JPanel initSubTopPanel()
    {
        CustomTableLayout subTopLayout = new CustomTableLayout();
        subTopLayout.addRow(CustomTableLayout.FILL);
        subTopLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);
        subTopLayout.addColumn(CustomTableLayout.FILL);
        subTopLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        subTopLayout.addColumn(CustomTableLayout.PREFERRED);
        subTopLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        subTopLayout.addColumn(CustomTableLayout.PREFERRED);
        subTopLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);

        JPanel subTopPanel = new JPanel(subTopLayout);
        subTopPanel.add(new JPanel(),
                        "1,0");
        subTopPanel.add(initCardChooserPanel(),
                        "3,0");
        subTopPanel.add(initCardViewerPanel(),
                        "5,0");
        return subTopPanel;
    }

    private JPanel initGameConfigPanel()
    {
        CustomTableLayout gameConfigLayout = new CustomTableLayout();
        gameConfigLayout.addRow(CustomTableLayout.FILL);

        JPanel gameConfigPanel = new JPanel(gameConfigLayout);

        return gameConfigPanel;
    }

    private JPanel initCardChooserPanel()
    {
        CustomTableLayout cardChooserLayout = new CustomTableLayout();
        cardChooserLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        cardChooserLayout.addRow(CustomTableLayout.PREFERRED);
        cardChooserLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        cardChooserLayout.addRow(CustomTableLayout.PREFERRED);
        cardChooserLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        cardChooserLayout.addRow(CustomTableLayout.PREFERRED);
        cardChooserLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        cardChooserLayout.addRow(CustomTableLayout.PREFERRED);
        cardChooserLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        cardChooserLayout.addRow(CustomTableLayout.PREFERRED);
        cardChooserLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        cardChooserLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);
        cardChooserLayout.addColumn(CustomTableLayout.FILL);
        cardChooserLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);

        JComboBox wSetList = (JComboBox) GUIUtils.addComponent(GUIType.COMBO_BOX,
                                                               GameSetupPanelComponent.CBOX_SET_LIST,
                                                               "");
        for (CardSet wSetEntry : CardSet.values())
        {
            if (wSetEntry != CardSet.COMMON)
            {
                ((DefaultComboBoxModel) wSetList.getModel()).addElement(wSetEntry);
            }
        }

        JPanel cardChooserPanel = new JPanel(cardChooserLayout);
        cardChooserPanel.add(wSetList,
                             "1,1");
        cardChooserPanel.add(GUIUtils.addComponent(GUIType.COMBO_BOX,
                                                   GameSetupPanelComponent.CBOX_CARD_LIST,
                                                   ""),
                             "1,3");
        cardChooserPanel.add(GUIUtils.addComponent(GUIType.COMBO_BOX,
                                                   GameSetupPanelComponent.CBOX_SET_PRESET_LIST,
                                                   ""),
                             "1,5");
        cardChooserPanel.add(GUIUtils.addButtonComponent(GameSetupPanelComponent.BTN_SELECT_CARD_PRESET,
                                                         this),
                             "1,7");
        cardChooserPanel.add(GUIUtils.addButtonComponent(GameSetupPanelComponent.BTN_CLEAR_CARD_SETUP,
                                                         this),
                             "1,9");

        cardChooserPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                                                                    "Card pickup"));
        return cardChooserPanel;
    }

    private JPanel initCardViewerPanel()
    {
        CustomTableLayout cardViewerLayout = new CustomTableLayout();
        cardViewerLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        cardViewerLayout.addRow(CustomTableLayout.FILL);
        cardViewerLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        cardViewerLayout.addRow(CustomTableLayout.PREFERRED);
        cardViewerLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        cardViewerLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);
        cardViewerLayout.addColumn(CustomTableLayout.FILL);
        cardViewerLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);

        JPanel cardViewerPanel = new JPanel(cardViewerLayout);
        cardViewerPanel.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                                  GameSetupPanelComponent.CARDICON_PREVIEW,
                                                  null),
                            "1,1");
        cardViewerPanel.add(GUIUtils.addButtonComponent(GameSetupPanelComponent.BTN_ADD_CARD_SETUP,
                                                        this),
                            "1,3");
        cardViewerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                                                                   "Card viewer"));
        return cardViewerPanel;
    }

    private void initListener()
    {
        JComboBox wSetBox = (JComboBox) GUIUtils.getComponent(GameSetupPanelComponent.CBOX_SET_LIST);
        JComboBox wCardBox = (JComboBox) GUIUtils.getComponent(GameSetupPanelComponent.CBOX_CARD_LIST);
        // JLabel wImageIcon = (JLabel) GUIUtils.getComponent(GameSetupPanelComponent.LBL_CARD_ICON);

        wSetBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setupCardBoxValues();
                updateCardPicture();
            }
        });

        wCardBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                updateCardPicture();
            }
        });

        // wImageIcon.addComponentListener(new ComponentAdapter() {
        // @Override
        // public void componentResized(ComponentEvent e)
        // {
        // JLabel wComponentSource = ((JLabel) e.getSource());
        //
        // JLabel wIconLabel = (JLabel) GUIUtils.getComponent(GameSetupPanelComponent.LBL_CARD_ICON);
        // JComboBox wCardBox = (JComboBox) GUIUtils.getComponent(GameSetupPanelComponent.CBOX_CARD_LIST);
        // wIconLabel.setIcon(ImageUtil.getScaledCard((Card) wCardBox.getSelectedItem(),
        // Math.min(200,
        // wComponentSource.getWidth()),
        // Math.min(200,
        // wComponentSource.getHeight())));
        // }
        // });
    }

    @Override
    public void processButtonClicked(Enum iButtonId)
    {
        switch ((GameSetupPanelComponent) iButtonId)
        {
            case BTN_ADD_CARD_SETUP:
                addSelectedCard();
                break;
            case BTN_CLEAR_CARD_SETUP:
                break;
            case BTN_SELECT_CARD_PRESET:
                selectCardPreset();
                break;
        }
    }

    private void setupCardBoxValues()
    {
        JComboBox wSetBox = (JComboBox) GUIUtils.getComponent(GameSetupPanelComponent.CBOX_SET_LIST);
        JComboBox wCardBox = (JComboBox) GUIUtils.getComponent(GameSetupPanelComponent.CBOX_CARD_LIST);
        JComboBox wPresetBox = (JComboBox) GUIUtils.getComponent(GameSetupPanelComponent.CBOX_SET_PRESET_LIST);

        DefaultComboBoxModel wCardBoxModel = new DefaultComboBoxModel();
        DefaultComboBoxModel wPresetBoxModel = new DefaultComboBoxModel();

        for (Card wCardEntry : Card.values())
        {
            if (wCardEntry.getCardSet() == (CardSet) wSetBox.getSelectedItem())
            {
                wCardBoxModel.addElement(wCardEntry);
            }
        }

        for (DominionSet wPresetEntry : DominionSet.values())
        {
            if (wPresetEntry.getCardSet() == (CardSet) wSetBox.getSelectedItem())
            {
                wPresetBoxModel.addElement(wPresetEntry);
            }
        }
        wCardBox.setModel(wCardBoxModel);
        wPresetBox.setModel(wPresetBoxModel);
    }

    private void updateCardPicture()
    {
        JComboBox cardBox = (JComboBox) GUIUtils.getComponent(GameSetupPanelComponent.CBOX_CARD_LIST);
        CardIconLabel cardIcon = (CardIconLabel) GUIUtils.getComponent(GameSetupPanelComponent.CARDICON_PREVIEW);

        cardIcon.setCard((Card) cardBox.getSelectedItem());
        cardIcon.invalidate();
    }

    private void addSelectedCard()
    {
        JComboBox cardBox = (JComboBox) GUIUtils.getComponent(GameSetupPanelComponent.CBOX_CARD_LIST);
    }

    private void selectCardPreset()
    {
        JComboBox wPresetBox = (JComboBox) GUIUtils.getComponent(GameSetupPanelComponent.CBOX_SET_PRESET_LIST);
        DominionSet wPresetSelected = (DominionSet) wPresetBox.getSelectedItem();
        if (wPresetSelected == null)
        {
            return;
        }

        ((GameCardPanel) GUIUtils.getComponent(ActiveGamePanel.ActiveGamePanelComponent.PANEL_GAME_CARD)).setGameCard(wPresetSelected.getPreselectedGameSetup());
    }
}
