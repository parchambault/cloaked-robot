package dominion.panel.activegame;

import gui.GUIType;
import gui.layout.CustomTableLayout;
import gui.util.GUIUtils;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import dominion.card.Card;

public class CommonCardPanel
    extends JPanel
{
    private static final Logger LOG = LogManager.getLogger(CommonCardPanel.class);

    public enum CommonCardPanelComponent
    {
        LBL_COUNT_COPPER,
        LBL_COUNT_SILVER,
        LBL_COUNT_GOLD,
        LBL_COUNT_POTION,
        LBL_COUNT_ESTATE,
        LBL_COUNT_DUCHY,
        LBL_COUNT_PROVINCE,
        LBL_COUNT_CURSE,

        CARDICON_COPPER,
        CARDICON_SILVER,
        CARDICON_GOLD,
        CARDICON_POTION,
        CARDICON_ESTATE,
        CARDICON_DUCHY,
        CARDICON_PROVINCE,
        CARDICON_CURSE,
    };

    public CommonCardPanel()
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
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        mainLayout.addColumn(CustomTableLayout.FILL);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        mainLayout.addColumn(CustomTableLayout.FILL);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        mainLayout.addColumn(CustomTableLayout.FILL);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);

        this.setLayout(mainLayout);

        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       CommonCardPanelComponent.CARDICON_COPPER,
                                       Card.COPPER.toString()),
                 "1,1");
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       CommonCardPanelComponent.CARDICON_SILVER,
                                       Card.SILVER.toString()),
                 "3,1");
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       CommonCardPanelComponent.CARDICON_GOLD,
                                       Card.GOLD.toString()),
                 "5,1");
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       CommonCardPanelComponent.CARDICON_POTION,
                                       Card.POTION.toString()),
                 "7,1");
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       CommonCardPanelComponent.CARDICON_ESTATE,
                                       Card.ESTATE.toString()),
                 "9,1");
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       CommonCardPanelComponent.CARDICON_DUCHY,
                                       Card.DUCHY.toString()),
                 "11,1");
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       CommonCardPanelComponent.CARDICON_PROVINCE,
                                       Card.PROVINCE.toString()),
                 "13,1");
        this.add(GUIUtils.addComponent(GUIType.CARD_ICON_LABEL,
                                       CommonCardPanelComponent.CARDICON_CURSE,
                                       Card.CURSE.toString()),
                 "15,1");

        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       CommonCardPanelComponent.LBL_COUNT_COPPER,
                                       null),
                 "1,3");
        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       CommonCardPanelComponent.LBL_COUNT_SILVER,
                                       null),
                 "3,3");
        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       CommonCardPanelComponent.LBL_COUNT_GOLD,
                                       null),
                 "5,3");
        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       CommonCardPanelComponent.LBL_COUNT_POTION,
                                       null),
                 "7,3");
        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       CommonCardPanelComponent.LBL_COUNT_ESTATE,
                                       null),
                 "9,3");
        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       CommonCardPanelComponent.LBL_COUNT_DUCHY,
                                       null),
                 "11,3");
        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       CommonCardPanelComponent.LBL_COUNT_PROVINCE,
                                       null),
                 "13,3");
        this.add(GUIUtils.addComponent(GUIType.LABEL,
                                       CommonCardPanelComponent.LBL_COUNT_CURSE,
                                       null),
                 "15,3");
        this.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    private void initListener()
    {
    }
}
