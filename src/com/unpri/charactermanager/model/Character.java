
package com.unpri.charactermanager.model;

import java.util.Objects;

/**
 * Merepresentasikan satu karakter dalam aplikasi Character Manager.
 * Semua atribut menggunakan private untuk menerapkan encapsulation.
 */
public class Character {

    private int id;
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;

    /**
     * Constructor untuk membuat karakter baru.
     */
    public Character(String name, CharacterType type, int level) {
        validateName(name);
        validateType(type);
        validateLevel(level, type);

        this.name = name.trim();
        this.type = type;
        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
        this.mana = 0;
    }

    /**
     * Constructor untuk memuat karakter dengan data lengkap.
     */
    public Character(int id, String name, CharacterType type,
                     int level, int hitPoints, int attackPower) {

        validateName(name);
        validateType(type);
        validateLevel(level, type);

        this.id = id;
        this.name = name.trim();
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
        this.mana = 0;
    }

    /**
     * Validasi nama karakter.
     */
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Nama karakter tidak boleh kosong."
            );
        }
    }

    /**
     * Validasi tipe karakter.
     */
    private void validateType(CharacterType type) {
        if (type == null) {
            throw new IllegalArgumentException(
                "Role karakter harus dipilih."
            );
        }
    }

    /**
     * Validasi level dan aturan khusus MAGE.
     */
    private void validateLevel(int level, CharacterType type) {
        if (level < 1 || level > 99) {
            throw new IllegalArgumentException(
                "Level harus berada antara 1 sampai 99."
            );
        }

        if (type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException(
                "Karakter MAGE tidak boleh memiliki level di atas 60."
            );
        }
    }

    private int calculateBaseHP() {
        return 100 + (level * 10);
    }

    private int calculateBaseAttack() {
        return 10 + (level * 5);
    }

    // Getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CharacterType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getMana() {
        return mana;
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name.trim();
    }

    public void setType(CharacterType type) {
        validateType(type);
        validateLevel(this.level, type);
        this.type = type;
    }

    public void setLevel(int level) {
        validateLevel(level, this.type);

        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException(
                "Mana tidak boleh negatif."
            );
        }

        this.mana = mana;
    }

    /**
     * Memeriksa apakah karakter masih hidup.
     */
    public boolean isAlive() {
        return hitPoints > 0;
    }

    /**
     * Membandingkan karakter berdasarkan nama.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Character other = (Character) obj;
        return Objects.equals(this.name, other.name);
    }

    /**
     * Menghasilkan hash berdasarkan nama karakter.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Representasi teks karakter.
     */
    @Override
    public String toString() {
        return "Character{id=" + id
            + ", name='" + name + '\''
            + ", type=" + type
            + ", level=" + level
            + ", hp=" + hitPoints
            + ", atk=" + attackPower
            + ", mana=" + mana
            + '}';
    }
}
