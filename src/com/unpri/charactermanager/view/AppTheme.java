
package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.unpri.charactermanager.model.CharacterType;

public class AppTheme {

    // Background
    public static final Color BACKGROUND =
            new Color(30, 34, 48);

    public static final Color PANEL_BACKGROUND =
            new Color(40, 47, 65);

    public static final Color TITLE_COLOR =
            new Color(255, 215, 0);

    public static final Color TEXT_COLOR =
            Color.WHITE;

    public static final Color GOLD =
            new Color(255, 215, 0);

    public static final Color BUTTON_COLOR =
            new Color(65, 85, 145);

    public static final Color DELETE_COLOR =
            new Color(190, 55, 70);

    public static final Color BORDER_COLOR =
            new Color(100, 110, 135);

    // Batas level mini-challenge
    public static final int GOLD_LEVEL = 5;

    // Font
    public static final Font TITLE_FONT =
            new Font("Arial", Font.BOLD, 24);

    public static final Font NAME_FONT =
            new Font("Arial", Font.BOLD, 14);

    public static final Font NORMAL_FONT =
            new Font("Arial", Font.PLAIN, 14);

    public static final Font GOLD_FONT =
            new Font("Arial", Font.BOLD, 14);

    // Border
    public static final Border CARD_BORDER =
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            BORDER_COLOR, 1),
                    BorderFactory.createEmptyBorder(
                            8, 8, 8, 8));

    public static final Border SELECTED_BORDER =
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            GOLD, 2),
                    BorderFactory.createEmptyBorder(
                            7, 7, 7, 7));

    public static final Border PANEL_BORDER =
            BorderFactory.createEmptyBorder(
                    10, 10, 10, 10);

    // Warna berdasarkan Role
    public static Color colorFor(CharacterType type) {

        if (type == null) {
            return PANEL_BACKGROUND;
        }

        switch (type) {

            case WARRIOR:
                return new Color(120, 55, 65);

            case MAGE:
                return new Color(55, 80, 145);

            case ARCHER:
                return new Color(40, 115, 80);

            case HEALER:
                return new Color(120, 65, 125);

            default:
                return PANEL_BACKGROUND;
        }
    }

    // Kompatibilitas dengan kode sebelumnya
    public static Color getCharacterColor(
            CharacterType type) {

        return colorFor(type);
    }
}
