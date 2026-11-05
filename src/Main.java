import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Character Manager v0.1 ===");

        // Membuat 3 karakter
        Character hero = new Character(
            "Arjuna", CharacterType.WARRIOR, 5
        );

        Character wizard = new Character(
            "Merlin", CharacterType.MAGE, 3
        );

        Character scout = new Character(
            "Athena", CharacterType.ARCHER, 4
        );

        // Menampilkan 3 karakter
        System.out.println(hero);
        System.out.println(wizard);
        System.out.println(scout);

        // Demo Level Up
        System.out.println();
        System.out.println("=== Demo Level Up ===");

        System.out.println(">> " + hero.getName() + " level up!");

        hero.setLevel(hero.getLevel() + 1);

        System.out.println("After level up: " + hero);

        // Informasi karakter
        System.out.println();
        System.out.println("=== Informasi Karakter ===");

        System.out.println("Nama karakter: " + hero.getName());
        System.out.println("Jenis karakter: " + hero.getType());
        System.out.println("Level: " + hero.getLevel());
        System.out.println("HP saat ini: " + hero.getHitPoints());
        System.out.println("Attack Power: " + hero.getAttackPower());

        // ===== BAGIAN B: MINI CHALLENGE =====
        System.out.println();
        System.out.println("=== Mini Challenge Bagian B ===");

        // Membuat 1 karakter baru
        Character healer = new Character(
            "Luna", CharacterType.HEALER, 2
        );

        // Mengatur mana
        healer.setMana(50);

        // Menampilkan karakter baru
        System.out.println("Karakter baru: " + healer);

        // Memanggil isAlive()
        System.out.println(
            "Apakah karakter masih hidup? " + healer.isAlive()
        );

        // Menampilkan mana
        System.out.println("Mana karakter: " + healer.getMana());
    }
}

