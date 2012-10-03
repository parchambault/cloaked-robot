package dominion.panel.activegame;

import gui.layout.CustomTableLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import core.valueobj.PlayerInfo;

public class OpponentInfoPanel
    extends JPanel
{
    private static final Logger LOG = LogManager.getLogger(OpponentInfoPanel.class);

    private PlayerInfo          mPlayerInfo;

    // Since we can have multiple opponent info panel, cannot have unique GUI entry ID
    public enum OpponentInfoPanelComponent
    {
    };

    private JLabel mLblHand         = new JLabel("Hand: ");
    private JLabel mLblDeck         = new JLabel("Deck: ");
    private JLabel mLblGraveyard    = new JLabel("Graveyard: ");

    private JLabel mCardInHand      = new JLabel();
    private JLabel mCardInDeck      = new JLabel();
    private JLabel mCardInGraveyard = new JLabel();

    public OpponentInfoPanel(PlayerInfo iPlayerInfo)
    {
        mPlayerInfo = iPlayerInfo;
        initUI();
        initListener();
    }

    private void initUI()
    {
        CustomTableLayout mainLayout = new CustomTableLayout();
        mainLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        mainLayout.addRow(CustomTableLayout.FILL);
        mainLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        mainLayout.addRow(CustomTableLayout.FILL);
        mainLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        mainLayout.addRow(CustomTableLayout.FILL);
        mainLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_PANEL);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);
        mainLayout.addColumn(CustomTableLayout.PREFERRED);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        mainLayout.addColumn(CustomTableLayout.FILL);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_PANEL);

        this.setLayout(mainLayout);
        this.add(mLblHand,
                 "1,1");
        this.add(mLblDeck,
                 "1,3");
        this.add(mLblGraveyard,
                 "1,5");
        this.add(mCardInHand,
                 "3,1");
        this.add(mCardInDeck,
                 "3,3");
        this.add(mCardInGraveyard,
                 "3,5");
        this.setBorder(BorderFactory.createTitledBorder(mPlayerInfo.getPlayerName()));
    }

    private void initListener()
    {
    }

    public void setCardInHand(int iNumber)
    {
        mCardInHand.setText(Integer.toString(iNumber));
        this.invalidate();
        this.repaint();
    }

    public void setCardInDeck(int iNumber)
    {
        mCardInDeck.setText(Integer.toString(iNumber));
        this.invalidate();
        this.repaint();
    }

    public void setCardInGraveyard(int iNumber)
    {
        mCardInGraveyard.setText(Integer.toString(iNumber));
        this.invalidate();
        this.repaint();
    }
}
