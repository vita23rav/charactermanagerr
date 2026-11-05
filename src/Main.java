import java.util.ArrayList;

import com.unpri.charactermanager.CharacterManager;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Character Manager TP5 ===");

        // Membuat CharacterManager
        CharacterManager manager = new CharacterManager();

        // ===== BAGIAN A: CRUD =====

        System.out.println();
        System.out.println("=== Bagian A: CRUD ===");

        Character hero = new Character(
                "Arjuna", CharacterType.WARRIOR, 5);

        Character wizard = new Character(
                "Merlin", CharacterType.MAGE, 3);

        Character scout = new Character(
                "Athena", CharacterType.ARCHER, 4);

        System.out.println("Tambah Arjuna: "
                + manager.addCharacter(hero));

        System.out.println("Tambah Merlin: "
                + manager.addCharacter(wizard));

        System.out.println("Tambah Athena: "
                + manager.addCharacter(scout));

        // Membuktikan nama duplikat ditolak
        Character duplicate = new Character(
                "arjuna", CharacterType.WARRIOR, 2);

        System.out.println("Tambah Arjuna duplikat: "
                + manager.addCharacter(duplicate));

        // Menampilkan semua karakter
        System.out.println();
        System.out.println("=== Daftar Karakter ===");

        for (Character character : manager.getAllCharacters()) {
            System.out.println(character);
        }

        // Mencari karakter
        System.out.println();
        System.out.println("=== Find By Name ===");

        Character found = manager.findByName("Merlin");

        if (found != null) {
            System.out.println("Karakter ditemukan: " + found);
        } else {
            System.out.println("Karakter tidak ditemukan");
        }

        // Total karakter
        System.out.println();
        System.out.println("Total karakter: "
                + manager.getTotalCharacters());

        // ===== BAGIAN B: MINI CHALLENGE =====

        System.out.println();
        System.out.println("=== Bagian B: Mini Challenge ===");

        System.out.println("Rata-rata level: "
                + manager.getAverageLevel());

        System.out.println();
        System.out.println("Karakter bertipe WARRIOR:");

        ArrayList<Character> warriors =
                manager.findByType(CharacterType.WARRIOR);

        for (Character character : warriors) {
            System.out.println(character);
        }

        // ===== BAGIAN C: ANALITIK =====

        System.out.println();
        System.out.println("=== Bagian C: Analitik ===");

        Character strongest = manager.findStrongest();

        if (strongest != null) {
            System.out.println("Karakter terkuat: "
                    + strongest.getName());

            System.out.println("Attack Power tertinggi: "
                    + strongest.getAttackPower());
        }

        // ===== EKSPERIMEN GETALLCHARACTERS =====

        System.out.println();
        System.out.println("=== Eksperimen Defensive Copy ===");

        ArrayList<Character> copy =
                manager.getAllCharacters();

        System.out.println("Jumlah sebelum copy.clear(): "
                + manager.getTotalCharacters());

        copy.clear();

        System.out.println("Jumlah setelah copy.clear(): "
                + manager.getTotalCharacters());

        // ===== REMOVE CHARACTER =====

        System.out.println();
        System.out.println("=== Remove Character ===");

        System.out.println("Hapus Athena: "
                + manager.removeCharacter("Athena"));

        System.out.println("Total karakter setelah dihapus: "
                + manager.getTotalCharacters());
    }
}
