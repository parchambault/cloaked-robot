package dominion.panel.activegame;

import gui.component.CardIconLabel;
import gui.component.CardIconLabel.IconType;
import gui.layout.CustomTableLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import dominion.card.Card;

public class HandCardPanel
    extends JPanel
{
    private static final Logger LOG            = LogManager.getLogger(HandCardPanel.class);

    private JPanel              mCardPanel     = new JPanel();
    private JScrollPane         mCardContainer = new JScrollPane(mCardPanel);

    public enum DynamicCardListPanelComponent
    {
    };

    public HandCardPanel()
    {
        initUI();
        initListener();
    }

    private void initUI()
    {
        CustomTableLayout mainLayout = new CustomTableLayout();
        mainLayout.addRow(CustomTableLayout.FILL);
        mainLayout.addColumn(CustomTableLayout.FILL);

        CustomTableLayout cardLayout = new CustomTableLayout();
        cardLayout.addRow(CustomTableLayout.PREFERRED);
        mCardPanel.setLayout(cardLayout);

        this.setLayout(mainLayout);
        this.add(mCardContainer,
                 "0,0");
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),
                                                        "Card(s) in hand"));
    }

    private void initListener()
    {
    }

    public void addCard(Card iCard)
    {
        CardIconLabel wCard = new CardIconLabel(IconType.HAND);
        wCard.setCard(iCard);
        ((CustomTableLayout) mCardPanel.getLayout()).addColumn(CustomTableLayout.PREFERRED);
        mCardPanel.add(wCard,
                       (((CustomTableLayout) mCardPanel.getLayout()).getNumColumn() - 1) + ", 0");
        this.validate();
        this.repaint();
    }
}
