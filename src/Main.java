import com.unpri.charactermanager.CharacterManager;
import com.unpri.charactermanager.controller.CharacterController;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        CharacterManager manager = new CharacterManager();

        // Lima karakter awal
        manager.addCharacter(
                new Character("Arjuna", CharacterType.WARRIOR, 5));

        manager.addCharacter(
                new Character("Merlin", CharacterType.MAGE, 3));

        manager.addCharacter(
                new Character("Athena", CharacterType.ARCHER, 4));

        manager.addCharacter(
                new Character("Hector", CharacterType.WARRIOR, 6));

        manager.addCharacter(
                new Character("Luna", CharacterType.HEALER, 2));

        CharacterController controller =
                new CharacterController(manager);

        // Menjalankan GUI melalui Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow(controller);
            window.setVisible(true);
        });
    }
}
