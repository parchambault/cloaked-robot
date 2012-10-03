package dominion.panel.activegame;

import gui.layout.CustomTableLayout;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import core.valueobj.PlayerInfo;

public class OpponentListPanel
    extends JPanel
{
    private static final Logger                LOG              = LogManager.getLogger(OpponentListPanel.class);

    private HashMap<String, OpponentInfoPanel> mOpponentInfoMap = new HashMap<String, OpponentInfoPanel>();

    public OpponentListPanel()
    {
        initUI();
        initListener();
    }

    private void initUI()
    {
        CustomTableLayout mainLayout = new CustomTableLayout();

        mainLayout.addColumn(CustomTableLayout.FILL);

        this.setLayout(mainLayout);
        this.setBorder(BorderFactory.createTitledBorder("Opponent(s"));
    }

    private void initListener()
    {
    }

    public void addOpponent(PlayerInfo iPlayerInfo)
    {
        OpponentInfoPanel wInfoPanel = new OpponentInfoPanel(iPlayerInfo);
        mOpponentInfoMap.put(iPlayerInfo.getPlayerName(),
                             wInfoPanel);

        ((CustomTableLayout) this.getLayout()).addRow(CustomTableLayout.PREFERRED);
        this.add(wInfoPanel,
                 (((CustomTableLayout) this.getLayout()).getNumRow() - 1) + ", 0");
        this.validate();
        this.repaint();
    }

    public OpponentInfoPanel getOpponentInfoPanel(PlayerInfo iPlayerInfo)
    {
        return mOpponentInfoMap.get(iPlayerInfo.getPlayerName());
    }
}
