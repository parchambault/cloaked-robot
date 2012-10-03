package gui.component;

import gui.util.ImageUtil;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import dominion.card.Card;

public class CardIconLabel
    extends JLabel
{
    private static final long serialVersionUID = -7472752312027570957L;

    public enum IconType
    {
        HAND(110),
        PURCHASE_FIELD(140),
        STANDARD(140);

        private int mPreferredSize;

        private IconType(int iPreferredSize)
        {
            mPreferredSize = iPreferredSize;
        }

        public int getPreferredSize()
        {
            return mPreferredSize;
        }
    };

    private static final int MINIMUM_SIZE = 50;
    private static final int MAXIMUM_SIZE = 350;

    private Card             mCardIcon;

    public CardIconLabel()
    {
        this(IconType.STANDARD);
    }

    public CardIconLabel(IconType iType)
    {
        this.setPreferredSize(new Dimension(iType.getPreferredSize(),
                                            iType.getPreferredSize()));
        this.setMinimumSize(new Dimension(MINIMUM_SIZE,
                                          MINIMUM_SIZE));
        this.setMaximumSize(new Dimension(MAXIMUM_SIZE,
                                          MAXIMUM_SIZE));

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e)
            {
                if (mCardIcon == null)
                {
                    return;
                }
                JLabel wComponentSource = ((JLabel) e.getSource());

                ImageUtil.scaleImageToLabel(mCardIcon,
                                            wComponentSource);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                if (mCardIcon != null)
                {
                    ((JLabel) e.getSource()).setToolTipText(mCardIcon.name());
                }
            }
        });
    }

    public Card getCard()
    {
        return mCardIcon;
    }

    public void setCard(Card iCard)
    {
        mCardIcon = iCard;
        if (getWidth() == 0 || getHeight() == 0)
        {
            this.setIcon(ImageUtil.getCardImage(iCard));
        }
        else
        {
            // int iSquareSize = Math.min(getWidth(),
            // getHeight());
            this.setIcon(ImageUtil.getScaledCard(iCard,
                                                 getWidth(),
                                                 getHeight()));
        }
    }
}
