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

    public boolean addCharacter(Character character) {
        return manager.addCharacter(character);
    }

    public boolean removeCharacter(String name) {
        return manager.removeCharacter(name);
    }

    public Character findByName(String name) {
        return manager.findByName(name);
    }

    public ArrayList<Character> getAllCharacters() {
        return manager.getAllCharacters();
    }

    public int getTotalCharacters() {
        return manager.getTotalCharacters();
    }

    public double getAverageLevel() {
        return manager.getAverageLevel();
    }

    public ArrayList<Character> findByType(CharacterType type) {
        return manager.findByType(type);
    }

    public Character findStrongest() {
        return manager.findStrongest();
    }
}