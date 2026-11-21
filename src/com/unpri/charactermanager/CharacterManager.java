
package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import java.util.ArrayList;

/**
 * Mengelola kumpulan karakter dan menyediakan fitur analitik.
 */
public class CharacterManager {
    private static final int MAX_CHARACTERS = 20;
    private final ArrayList<Character> characters = new ArrayList<>();

    /**
     * Memeriksa apakah nama karakter sudah digunakan.
     *
     * @param name nama yang akan diperiksa
     * @return true jika nama sudah digunakan
     */
    public boolean isNameTaken(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        String normalizedName = name.trim();

        for (Character character : characters) {
            if (character.getName() != null
                    && character.getName().trim()
                    .equalsIgnoreCase(normalizedName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Menambahkan karakter jika nama belum digunakan.
     *
     * @param character karakter yang akan ditambahkan
     * @return true jika berhasil ditambahkan
     */
    public boolean addCharacter(Character character) {
        if (character == null || character.getName() == null
                || character.getName().trim().isEmpty()) {
            return false;
        }

        if (isNameTaken(character.getName())) {
            return false;
        }
        if (characters.size() >= MAX_CHARACTERS) {
            return false;
        }
        characters.add(character);
        return true;
    }

    /**
     * Mencari karakter berdasarkan nama.
     *
     * @param name nama yang dicari
     * @return karakter yang ditemukan, atau null jika tidak ada
     */
    public Character findByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        String normalizedName = name.trim();

        for (Character character : characters) {
            if (character.getName() != null
                    && character.getName().trim()
                    .equalsIgnoreCase(normalizedName)) {
                return character;
            }
        }

        return null;
    }

    /**
     * Menghapus karakter berdasarkan nama.
     *
     * @param name nama karakter yang akan dihapus
     * @return true jika karakter berhasil dihapus
     */
    public boolean removeCharacter(String name) {
        Character character = findByName(name);

        if (character == null) {
            return false;
        }

        return characters.remove(character);
    }

    /**
     * Mengembalikan jumlah karakter yang tersimpan.
     */
    public int getTotalCharacters() {
        return characters.size();
    }

    /**
     * Mengembalikan salinan daftar karakter.
     */
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }

    /**
     * Menghitung rata-rata level seluruh karakter.
     */
    public double getAverageLevel() {
        if (characters.isEmpty()) {
            return 0.0;
        }

        int totalLevel = 0;

        for (Character character : characters) {
            totalLevel += character.getLevel();
        }

        return (double) totalLevel / characters.size();
    }

    /**
     * Mencari semua karakter berdasarkan tipe.
     */
    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> result = new ArrayList<>();

        if (type == null) {
            return result;
        }

        for (Character character : characters) {
            if (character.getType() == type) {
                result.add(character);
            }
        }

        return result;
    }

    /**
     * Mencari karakter dengan level tertinggi.
     */
    public Character findStrongest() {
        if (characters.isEmpty()) {
            return null;
        }

        Character strongest = characters.get(0);

        for (Character character : characters) {
            if (character.getLevel() > strongest.getLevel()) {
                strongest = character;
            }
        }

        return strongest;
    }
}
