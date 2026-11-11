package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public final class AppTheme {

    private AppTheme() {
        // Tema hanya digunakan melalui konstanta dan method.
    }

    // ===== WARNA TEMA =====
    public static final Color BACKGROUND =
            new Color(30, 34, 48);

    public static final Color CARD_BACKGROUND =
            new Color(48, 54, 72);

    public static final Color TEXT =
            new Color(245, 245, 250);

    public static final Color GOLD =
            new Color(255, 215, 0);
            
    // Batas level untuk Mini-Challenge
    public static final int HIGH_LEVEL_THRESHOLD = 5;

    public static final Color BORDER_COLOR =
            new Color(100, 110, 145);

    public static final Color BUTTON_BACKGROUND =
            new Color(83, 92, 150);

    public static final Color DANGER =
            new Color(190, 65, 75);

    // ===== FONT TEMA =====
    public static final Font TITLE_FONT =
            new Font(Font.SANS_SERIF, Font.BOLD, 22);

    public static final Font BODY_FONT =
            new Font(Font.SANS_SERIF, Font.PLAIN, 14);

    public static final Font EMPHASIS_FONT =
            new Font(Font.SANS_SERIF, Font.BOLD, 14);

    // ===== BORDER TEMA =====
    public static final Border CARD_BORDER =
            BorderFactory.createLineBorder(BORDER_COLOR, 1);

    public static final Border PANEL_BORDER =
            BorderFactory.createEmptyBorder(12, 12, 12, 12);

    // ===== WARNA BERDASARKAN ROLE =====
    public static Color colorFor(String role) {
        if (role == null) {
            return CARD_BACKGROUND;
        }

        switch (role.trim().toLowerCase()) {
            case "warrior":
            case "fighter":
            case "tank":
                return new Color(120, 55, 65);

            case "mage":
            case "wizard":
                return new Color(55, 75, 145);

            case "archer":
            case "ranger":
                return new Color(45, 115, 80);

            case "healer":
            case "support":
                return new Color(125, 75, 125);

            case "assassin":
                return new Color(85, 75, 100);

            default:
                // Role lain tetap memperoleh warna yang konsisten.
                int index = Math.floorMod(
                        role.trim().toLowerCase().hashCode(), 3);

                if (index == 0) {
                    return new Color(75, 85, 120);
                } else if (index == 1) {
                    return new Color(75, 105, 100);
                } else {
                    return new Color(115, 85, 70);
                }
        }
    }

    // Border kartu mengikuti role.
    public static Border borderFor(String role) {
        return BorderFactory.createMatteBorder(
                1, 5, 1, 1, colorFor(role));
    }
}

