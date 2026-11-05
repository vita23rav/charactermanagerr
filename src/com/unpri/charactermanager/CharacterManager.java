package com.unpri.charactermanager;

import java.util.ArrayList;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

/**
 * Class untuk mengelola daftar karakter.
 * Menggunakan prinsip Encapsulation.
 */
public class CharacterManager {

    // ArrayList disimpan secara private
    private final ArrayList<Character> characterList =
            new ArrayList<>();

    /**
     * Menambahkan karakter ke daftar.
     * Karakter null, nama kosong, dan nama duplikat ditolak.
     *
     * @param character karakter yang akan ditambahkan
     * @return true jika berhasil, false jika ditolak
     */
    public boolean addCharacter(Character character) {

        if (character == null) {
            return false;
        }

        if (character.getName() == null
                || character.getName().trim().isEmpty()) {
            return false;
        }

        for (Character existing : characterList) {
            if (existing.equals(character)) {
                return false;
            }
        }

        characterList.add(character);
        return true;
    }

    /**
     * Mencari karakter berdasarkan nama.
     *
     * @param name nama karakter
     * @return karakter yang ditemukan atau null
     */
    public Character findByName(String name) {

        if (name == null) {
            return null;
        }

        for (Character character : characterList) {
            if (character.getName().equals(name)) {
                return character;
            }
        }

        return null;
    }

    /**
     * Menghapus karakter berdasarkan nama.
     *
     * @param name nama karakter
     * @return true jika berhasil dihapus
     */
    public boolean removeCharacter(String name) {

        Character character = findByName(name);

        if (character != null) {
            characterList.remove(character);
            return true;
        }

        return false;
    }

    /**
     * Mengembalikan jumlah karakter.
     *
     * @return total karakter
     */
    public int getTotalCharacters() {
        return characterList.size();
    }

    /**
     * Mengembalikan salinan daftar karakter.
     * Tidak mengekspos ArrayList asli.
     *
     * @return salinan ArrayList karakter
     */
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characterList);
    }

    // ===== BAGIAN B: MINI CHALLENGE =====

    /**
     * Menghitung rata-rata level semua karakter.
     *
     * @return rata-rata level atau 0 jika daftar kosong
     */
    public double getAverageLevel() {

        if (characterList.isEmpty()) {
            return 0;
        }

        int totalLevel = 0;

        for (Character character : characterList) {
            totalLevel += character.getLevel();
        }

        return (double) totalLevel / characterList.size();
    }

    /**
     * Mencari semua karakter berdasarkan tipe.
     *
     * @param type tipe karakter
     * @return ArrayList baru berisi karakter dengan tipe tersebut
     */
    public ArrayList<Character> findByType(CharacterType type) {

        ArrayList<Character> result = new ArrayList<>();

        if (type == null) {
            return result;
        }

        for (Character character : characterList) {
            if (character.getType() == type) {
                result.add(character);
            }
        }

        return result;
    }

    // ===== BAGIAN C: FITUR ANALITIK =====

    /**
     * Mencari karakter dengan Attack Power tertinggi.
     *
     * @return karakter terkuat atau null jika daftar kosong
     */
    public Character findStrongest() {

        if (characterList.isEmpty()) {
            return null;
        }

        Character strongest = characterList.get(0);

        for (Character character : characterList) {
            if (character.getAttackPower()
                    > strongest.getAttackPower()) {
                strongest = character;
            }
        }

        return strongest;
    }
}
