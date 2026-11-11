package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import java.util.ArrayList;

/**
 * Mengelola kumpulan karakter dan menyediakan fitur analitik.
 */
public class CharacterManager {

    private final ArrayList<Character> characters = new ArrayList<>();

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

        if (findByName(character.getName()) != null) {
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
        if (name == null) {
            return null;
        }

        for (Character character : characters) {
            if (character.getName() != null
                    && character.getName().equals(name)) {
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
     *
     * @return jumlah karakter
     */
    public int getTotalCharacters() {
        return characters.size();
    }

    /**
     * Mengembalikan salinan daftar karakter.
     *
     * @return salinan daftar karakter
     */
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }

    /**
     * Menghitung rata-rata level seluruh karakter.
     *
     * @return rata-rata level, atau 0.0 jika daftar kosong
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
     *
     * @param type tipe karakter yang dicari
     * @return daftar baru berisi karakter dengan tipe tersebut
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
     *
     * @return karakter dengan level tertinggi, atau null jika kosong
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
