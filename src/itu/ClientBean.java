package itu;

import itu.panels.FriendsPanel;
import itu.panels.LoginPanel;
import itu.panels.MessengerPanel;
import itu.panels.RegistrationPanel;

//  Bean obsahujici odkazy na jednotlive casti
//
public class ClientBean {
    
    private LoginPanel login;
    private RegistrationPanel registration;
    private MessengerPanel messenger;
    private Connection connection;
    private ClientFrame frame;
    private FriendsPanel friendsList;
    
    public ClientBean(ClientFrame frame) {
        
        // init all
        friendsList = new FriendsPanel();
        messenger = new MessengerPanel(this);
        connection = new Connection(this);
        login = new LoginPanel(this);
        registration = new RegistrationPanel(this);
        
        
        this.frame = frame;
        
    }

    public LoginPanel getLogin() {
        return login;
    }

    public RegistrationPanel getRegistration() {
        return registration;
    }

    public MessengerPanel getMessenger() {
        return messenger;
    }

    public Connection getConnection() {
        return connection;
    }

    public ClientFrame getFrame() {
        return frame;
    }

    public FriendsPanel getFriendsList() {
        return friendsList;
    }
    
    
    
    
}
