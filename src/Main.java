import discord.Client;
import io.qt.core.Qt.FocusPolicy;
import io.qt.widgets.*;
import io.qt.widgets.QLineEdit.EchoMode;

public class Main {
  static final Client client = new Client();

  static void showLoginDialog() {
    final var dialog = new QInputDialog();
    dialog.setLabelText("Bot token:");
    dialog.setOkButtonText("Set Token");
    dialog.setTextEchoMode(EchoMode.Password);
    dialog.setMaximumSize(200, 100);
    dialog.setFocusPolicy(FocusPolicy.StrongFocus);
    dialog.accepted.connect(() -> client.api.setToken(dialog.textValue()));
    dialog.exec();
  }

  public static void main(String[] args) {
    QApplication.initialize(args);

    final var mw = new QMainWindow();

    // setup tabs
    final var tabw = new QTabWidget();
    tabw.addTab(new QTreeWidget(), "Commands");
    tabw.addTab(new QTreeWidget(), "Bot Info");
    
    // setup menu bar
    final var menu_bar = mw.menuBar();
    final var login_menu = menu_bar.addMenu("Token");
    login_menu.addAction("Set Token...").triggered.connect(() -> showLoginDialog());
    final var fetch_menu = menu_bar.addMenu("Fetch");
    fetch_menu.addAction("Fetch Current Application").triggered.connect(() -> {
      final var o = client.api.get("/oauth2/applications/@me");
      System.out.println(o);
    });
    fetch_menu.addAction("Fetch Commands").triggered.connect(() -> {});
    menu_bar.show();

    // setup main window
    mw.setWindowTitle("Discord Bot Command Manager");
    mw.setMinimumSize(800, 600);
    mw.setCentralWidget(tabw);

    // show main window and start qt event loop
    mw.show();
    QApplication.exec();
  }
}