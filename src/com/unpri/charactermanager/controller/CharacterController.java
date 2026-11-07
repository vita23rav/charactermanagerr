package com.unpri.charactermanager.controller;

import com.unpri.charactermanager.CharacterManager;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

import java.util.ArrayList;

public class CharacterController {

    private final CharacterManager manager;

    public CharacterController(CharacterManager manager) {
        this.manager = manager;
    }

    public ArrayList<Character> getAllCharacters() {
        return manager.getAllCharacters();
    }

    public boolean addCharacter(
            String name, CharacterType type, int level) {

        if (name == null || name.trim().isEmpty()
                || type == null || level < 1) {
            return false;
        }

        Character character =
                new Character(name.trim(), type, level);

        return manager.addCharacter(character);
    }

    public boolean removeCharacter(String name) {
        return manager.removeCharacter(name);
    }

    public int getTotalCharacters() {
        return manager.getTotalCharacters();
    }

    public double getAverageLevel() {
        return manager.getAverageLevel();
    }

    public Character findStrongest() {
        return manager.findStrongest();
    }
}
