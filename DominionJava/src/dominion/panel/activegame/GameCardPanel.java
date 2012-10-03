package dominion.panel.activegame;

import gui.GUIType;
import gui.component.CardIconLabel;
import gui.layout.CustomTableLayout;
import gui.util.GUIUtils;
import java.util.Collection;
import java.util.TreeSet;
import javax.swing.JPanel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import dominion.card.Card;
import dominion.card.comparator.CostComparator;

public class GameCardPanel
    extends JPanel
{
    private static final Logger LOG        = LogManager.getLogger(GameCardPanel.class);

    private TreeSet<Card>       mCardSetup = new TreeSet<Card>(new CostComparator());

    public enum GameCardPanelComponent
    {
        LBL_COUNT_CARD_1,
        LBL_COUNT_CARD_2,
        LBL_COUNT_CARD_3,
        LBL_COUNT_CARD_4,
        LBL_COUNT_CARD_5,
        LBL_COUNT_CARD_6,
        LBL_COUNT_CARD_7,
        LBL_COUNT_CARD_8,
        LBL_COUNT_CARD_9,
        LBL_COUNT_CARD_10,

        CARDICON_CARD_1,
        CARDICON_CARD_2,
        CARDICON_CARD_3,
        CARDICON_CARD_4,
        CARDICON_CARD_5,
        CARDICON_CARD_6,
        CARDICON_CARD_7,
        CARDICON_CARD_8,
        CARDICON_CARD_9,
        CARDICON_CARD_10,
    };

    public GameCardPanel()
    {
        initUI();
        initListener();
    }

    private void initUI()
    {
        CustomTableLayout mainLayout = new CustomTableLayout();
        mainLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        mainLayout.addRow(CustomTableLayout.FILL);
        mainLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        mainLayout.addRow(CustomTableLayout.PREFERRED);
        mainLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        mainLayout.addRow(CustomTableLayout.FILL);
        mainLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        mainLayout.addRow(CustomTableLayout.PREFERRED);
        mainLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);
        mainLayout.addColumn(CustomTableLayout.FILL);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        mainLayout.addColumn(CustomTableLayout.FILL);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        mainLayout.addColumn(CustomTableLayout.FILL);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        mainLayout.addColumn(CustomTableLayout.FILL);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        mainLayout.addColumn(CustomTableLayout.FILL);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);

        this.setLayout(mainLayout);
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       GameCardPanelComponent.CARDICON_CARD_1,
                                       null),
                 "1,1");
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       GameCardPanelComponent.CARDICON_CARD_2,
                                       null),
                 "3,1");
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       GameCardPanelComponent.CARDICON_CARD_3,
                                       null),
                 "5,1");
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       GameCardPanelComponent.CARDICON_CARD_4,
                                       null),
                 "7,1");
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       GameCardPanelComponent.CARDICON_CARD_5,
                                       null),
                 "9,1");
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       GameCardPanelComponent.CARDICON_CARD_6,
                                       null),
                 "1,5");
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       GameCardPanelComponent.CARDICON_CARD_7,
                                       null),
                 "3,5");
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       GameCardPanelComponent.CARDICON_CARD_8,
                                       null),
                 "5,5");
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       GameCardPanelComponent.CARDICON_CARD_9,
                                       null),
                 "7,5");
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       GameCardPanelComponent.CARDICON_CARD_10,
                                       null),
                 "9,5");

        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       GameCardPanelComponent.LBL_COUNT_CARD_1,
                                       null),
                 "1,3");
        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       GameCardPanelComponent.LBL_COUNT_CARD_2,
                                       null),
                 "3,3");
        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       GameCardPanelComponent.LBL_COUNT_CARD_3,
                                       null),
                 "5,3");
        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       GameCardPanelComponent.LBL_COUNT_CARD_4,
                                       null),
                 "7,3");
        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       GameCardPanelComponent.LBL_COUNT_CARD_5,
                                       null),
                 "9,3");
        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       GameCardPanelComponent.LBL_COUNT_CARD_6,
                                       null),
                 "1,7");
        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       GameCardPanelComponent.LBL_COUNT_CARD_7,
                                       null),
                 "3,7");
        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       GameCardPanelComponent.LBL_COUNT_CARD_8,
                                       null),
                 "5,7");
        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       GameCardPanelComponent.LBL_COUNT_CARD_9,
                                       null),
                 "7,7");
        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       GameCardPanelComponent.LBL_COUNT_CARD_10,
                                       null),
                 "9,7");
    }

    private void initListener()
    {
    }

    public void setGameCard(Collection<Card> iCardList)
    {
        mCardSetup.clear();
        for (Card wCardEntry : iCardList)
        {
            mCardSetup.add(wCardEntry);
        }

        ((CardIconLabel) GUIUtils.getComponent(GameCardPanelComponent.CARDICON_CARD_1)).setCard((Card) mCardSetup.toArray()[0]);
        ((CardIconLabel) GUIUtils.getComponent(GameCardPanelComponent.CARDICON_CARD_2)).setCard((Card) mCardSetup.toArray()[1]);
        ((CardIconLabel) GUIUtils.getComponent(GameCardPanelComponent.CARDICON_CARD_3)).setCard((Card) mCardSetup.toArray()[2]);
        ((CardIconLabel) GUIUtils.getComponent(GameCardPanelComponent.CARDICON_CARD_4)).setCard((Card) mCardSetup.toArray()[3]);
        ((CardIconLabel) GUIUtils.getComponent(GameCardPanelComponent.CARDICON_CARD_5)).setCard((Card) mCardSetup.toArray()[4]);
        ((CardIconLabel) GUIUtils.getComponent(GameCardPanelComponent.CARDICON_CARD_6)).setCard((Card) mCardSetup.toArray()[5]);
        ((CardIconLabel) GUIUtils.getComponent(GameCardPanelComponent.CARDICON_CARD_7)).setCard((Card) mCardSetup.toArray()[6]);
        ((CardIconLabel) GUIUtils.getComponent(GameCardPanelComponent.CARDICON_CARD_8)).setCard((Card) mCardSetup.toArray()[7]);
        ((CardIconLabel) GUIUtils.getComponent(GameCardPanelComponent.CARDICON_CARD_9)).setCard((Card) mCardSetup.toArray()[8]);
        ((CardIconLabel) GUIUtils.getComponent(GameCardPanelComponent.CARDICON_CARD_10)).setCard((Card) mCardSetup.toArray()[9]);
    }
}
