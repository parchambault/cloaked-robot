package gui.panel;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import core.message.ChatPost;
import core.message.ChatPost.ChatMsgType;
import core.service.ClientChatService;
import exchange.listener.IMessageListener;
import gui.GUIType;
import gui.layout.CustomTableLayout;
import gui.util.GUIUtils;
import gui.util.GlobalDefs;

public class ChatPanel
    extends JPanel
    implements IMessageListener<ChatPost>
{
    private static final Logger LOG                = LogManager.getLogger(ChatPanel.class);

    private static final int    MAX_CHAT_LINE_SIZE = 100;

    private JScrollPane         mScrollingChatPanel;
    private JScrollPane         mScrollingPlayerList;
    private ClientChatService   mClientChatService;

    public enum ChatPanelComponent
    {
        TXTFLD_CHAT_INPUT,

        TXTAREA_CHAT,

        LIST_PLAYERS,
    };

    public ChatPanel()
    {
        initUI();
        initListener();
    }

    public void setClientChatService(ClientChatService iClientChatService)
    {
        mClientChatService = iClientChatService;
    }

    private void initUI()
    {
        CustomTableLayout mainLayout = new CustomTableLayout();
        mainLayout.addRow(CustomTableLayout.FILL);
        mainLayout.addColumn(0.25);
        mainLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        mainLayout.addColumn(CustomTableLayout.FILL);

        this.setLayout(mainLayout);

        JList wPlayerList = (JList) GUIUtils.addComponent(GUIType.LIST,
                                                          ChatPanelComponent.LIST_PLAYERS,
                                                          "");
        wPlayerList.setFocusable(false);
        wPlayerList.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
                                                               "Players"));
        mScrollingPlayerList = new JScrollPane(wPlayerList);
        mScrollingPlayerList.setWheelScrollingEnabled(true);

        this.add(mScrollingPlayerList,
                 "0,0");
        this.add(initChatContentPanel(),
                 "2,0");
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                                                        "Chat"));
    }

    private void initListener()
    {
        final JTextField wChatInput = (JTextField) GUIUtils.getComponent(ChatPanelComponent.TXTFLD_CHAT_INPUT);
        wChatInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == 10 && wChatInput.getText()
                                                      .length() > 0)
                {
                    postMessage();
                }
            }
        });

        JTextArea wChatArea = (JTextArea) GUIUtils.getComponent(ChatPanelComponent.TXTAREA_CHAT);
        wChatArea.getDocument()
                 .addDocumentListener(new DocumentListener() {

                     @Override
                     public void removeUpdate(DocumentEvent e)
                     {
                     }

                     @Override
                     public void insertUpdate(DocumentEvent e)
                     {
                         SwingUtilities.invokeLater(new ChatMaxSizeController());
                     }

                     @Override
                     public void changedUpdate(DocumentEvent e)
                     {
                     }
                 });
    }

    private JPanel initChatContentPanel()
    {
        CustomTableLayout subChatLayout = new CustomTableLayout();
        subChatLayout.addRow(CustomTableLayout.FILL);
        subChatLayout.addRow(CustomTableLayout.Y_COMPONENT_TO_COMPONENT);
        subChatLayout.addRow(CustomTableLayout.PREFERRED);
        subChatLayout.addColumn(CustomTableLayout.X_COMPONENT_TO_COMPONENT);
        subChatLayout.addColumn(CustomTableLayout.FILL);

        JPanel subChatPanel = new JPanel(subChatLayout);
        JTextArea wChatArea = (JTextArea) GUIUtils.addComponent(GUIType.TEXTAREA,
                                                                ChatPanelComponent.TXTAREA_CHAT,
                                                                "");
        wChatArea.setEditable(false);
        wChatArea.setLineWrap(true);
        wChatArea.setWrapStyleWord(true);

        mScrollingChatPanel = new JScrollPane(wChatArea);
        mScrollingChatPanel.setWheelScrollingEnabled(true);

        subChatPanel.add(mScrollingChatPanel,
                         "1,0");
        subChatPanel.add(GUIUtils.addComponent(GUIType.TEXTFIELD,
                                               ChatPanelComponent.TXTFLD_CHAT_INPUT,
                                               ""),
                         "1,2");
        return subChatPanel;
    }

    @Override
    public void processMessage(ChatPost iMessageReceived)
    {
        switch (iMessageReceived.getType())
        {
            case POST_MESSAGE:
                processPostMessage(iMessageReceived);
                break;
            case PLAYER_JOIN:
                processPlayerJoined(iMessageReceived);
                break;
            case PLAYER_LEFT:
                processPlayerLeft(iMessageReceived);
                break;
        }
    }

    private void postMessage()
    {
        JTextField wChatInput = (JTextField) GUIUtils.getComponent(ChatPanelComponent.TXTFLD_CHAT_INPUT);

        ChatPost wChatMessage = new ChatPost(ChatMsgType.POST_MESSAGE);
        wChatMessage.setPlayerInfo(mClientChatService.getPlayerInfo());
        wChatMessage.setChatMessage(wChatInput.getText());
        wChatInput.setText("");
        try
        {
            mClientChatService.sendMessage(wChatMessage);
        }
        catch (Throwable ex)
        {
            LOG.error("Unable to post message: " + wChatMessage.getChatMessage(),
                      ex);
        }
    }

    private void processPostMessage(ChatPost iMessageReceived)
    {
        JTextArea wChatArea = (JTextArea) GUIUtils.getComponent(ChatPanelComponent.TXTAREA_CHAT);
        wChatArea.append(String.format("%1$tT - %2$s : %3$s",
                                       Calendar.getInstance(),
                                       iMessageReceived.getPlayerInfo()
                                                       .getPlayerName(),
                                       iMessageReceived.getChatMessage()));
        wChatArea.append(GlobalDefs.END_OF_LINE);
        wChatArea.setCaretPosition(wChatArea.getDocument()
                                            .getEndPosition()
                                            .getOffset() - 1);
        wChatArea.invalidate();
    }

    private void processPlayerJoined(ChatPost iMessageReceived)
    {
        JList wPlayerList = (JList) GUIUtils.getComponent(ChatPanelComponent.LIST_PLAYERS);
        ((DefaultListModel) wPlayerList.getModel()).addElement(iMessageReceived.getPlayerInfo());
        wPlayerList.updateUI();
    }

    private void processPlayerLeft(ChatPost iMessageReceived)
    {
        JList wPlayerList = (JList) GUIUtils.getComponent(ChatPanelComponent.LIST_PLAYERS);
        ((DefaultListModel) wPlayerList.getModel()).removeElement(iMessageReceived.getPlayerInfo());
        wPlayerList.updateUI();
    }

    private class ChatMaxSizeController
        implements Runnable
    {

        @Override
        public void run()
        {
            JTextArea wChatArea = (JTextArea) GUIUtils.getComponent(ChatPanelComponent.TXTAREA_CHAT);
            if (wChatArea.getLineCount() > MAX_CHAT_LINE_SIZE)
            {
                try
                {
                    wChatArea.getDocument()
                             .remove(wChatArea.getLineStartOffset(0),
                                     wChatArea.getLineEndOffset(0));
                    wChatArea.setCaretPosition(wChatArea.getDocument()
                                                        .getEndPosition()
                                                        .getOffset() - 1);
                    wChatArea.invalidate();
                }
                catch (BadLocationException ex)
                {
                    LOG.error("Unable to truncate chat size.",
                              ex);
                }
            }
        }
    }
}
