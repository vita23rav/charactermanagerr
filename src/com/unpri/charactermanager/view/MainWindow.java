
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.controller.CharacterController;
import com.unpri.charactermanager.model.Character;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private final CharacterController controller;

    private final JLabel totalLabel;
    private final JLabel averageLabel;
    private final JLabel strongestLabel;

    private final JPanel characterListPanel;
    private final CharacterFormPanel formPanel;

    public MainWindow(CharacterController controller) {

        this.controller = controller;

        setTitle("Character Manager v0.6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        totalLabel = new JLabel();
        averageLabel = new JLabel();
        strongestLabel = new JLabel();

        characterListPanel = new JPanel();
        characterListPanel.setLayout(
                new BoxLayout(characterListPanel, BoxLayout.Y_AXIS));
        characterListPanel.setBackground(AppTheme.BACKGROUND);

        formPanel = new CharacterFormPanel(
                controller,
                ignored -> refreshCharacters()
        );

        buildWindow();
        refreshCharacters();

        setSize(700, 650);
        setMinimumSize(new Dimension(600, 500));
        setLocationRelativeTo(null);
    }

    private void buildWindow() {

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(AppTheme.BACKGROUND);
        mainPanel.setBorder(AppTheme.PANEL_BORDER);

        // ===== HEADER =====

        JLabel titleLabel =
                new JLabel("CHARACTER MANAGER v0.6");

        titleLabel.setFont(AppTheme.TITLE_FONT);
        titleLabel.setForeground(AppTheme.GOLD);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(AppTheme.BACKGROUND);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // ===== FORM =====

        JPanel topPanel = new JPanel(new BorderLayout(5, 10));
        topPanel.setBackground(AppTheme.BACKGROUND);

        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);

        // ===== DAFTAR KARAKTER =====

        JScrollPane scrollPane =
                new JScrollPane(characterListPanel);

        scrollPane.setBorder(AppTheme.CARD_BORDER);
        scrollPane.getViewport().setBackground(AppTheme.BACKGROUND);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // ===== STATISTIK =====

        JPanel statsPanel = new JPanel(
                new GridLayout(3, 1, 5, 5));

        statsPanel.setBackground(AppTheme.CARD_BACKGROUND);
        statsPanel.setBorder(BorderFactory.createCompoundBorder(
                AppTheme.CARD_BORDER,
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        statsPanel.add(totalLabel);
        statsPanel.add(averageLabel);
        statsPanel.add(strongestLabel);

        styleStatLabel(totalLabel);
        styleStatLabel(averageLabel);
        styleStatLabel(strongestLabel);

        // ===== SUSUNAN UTAMA =====

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(statsPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void styleStatLabel(JLabel label) {
        label.setFont(AppTheme.BODY_FONT);
        label.setForeground(AppTheme.TEXT);
    }

    /**
     * Memperbarui daftar kartu dan statistik.
     */
    private void refreshCharacters() {

        characterListPanel.removeAll();

        for (Character character : controller.getAllCharacters()) {

            characterListPanel.add(createCharacterCard(character));

            characterListPanel.add(Box.createVerticalStrut(8));
        }

        if (controller.getTotalCharacters() == 0) {

            JLabel emptyLabel = new JLabel(
                    "Belum ada karakter.");

            emptyLabel.setForeground(AppTheme.TEXT);
            emptyLabel.setFont(AppTheme.BODY_FONT);

            emptyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            characterListPanel.add(emptyLabel);
        }

        updateStatistics();

        characterListPanel.revalidate();
        characterListPanel.repaint();
    }

    /**
     * Membuat kartu karakter berdasarkan role.
     */
    private JPanel createCharacterCard(Character character) {

        JPanel card = new JPanel(new BorderLayout(10, 5));

        card.setBackground(
                AppTheme.colorFor(character.getType().name()));

        card.setBorder(
                AppTheme.borderFor(character.getType().name()));

        card.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 85));

        card.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Informasi karakter
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(
                new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        infoPanel.setOpaque(false);
        infoPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 8, 5, 5));

        JLabel nameLabel = new JLabel(character.getName());
        nameLabel.setFont(AppTheme.EMPHASIS_FONT);
        nameLabel.setForeground(AppTheme.TEXT);

        JLabel roleLabel = new JLabel(
                "Role: " + character.getType()
                + " | Level: " + character.getLevel());

        roleLabel.setFont(AppTheme.EMPHASIS_FONT);

        if (character.getLevel()
                >= AppTheme.HIGH_LEVEL_THRESHOLD) {

            roleLabel.setForeground(AppTheme.GOLD);

        } else {

            roleLabel.setForeground(AppTheme.TEXT);
        }

        JLabel detailLabel = new JLabel(
                "HP: " + character.getHitPoints()
                + " | Attack: " + character.getAttackPower());

        detailLabel.setFont(AppTheme.BODY_FONT);
        detailLabel.setForeground(AppTheme.TEXT);

        infoPanel.add(nameLabel);
        infoPanel.add(roleLabel);
        infoPanel.add(detailLabel);

        // Tombol hapus
        JButton deleteButton = new JButton("Hapus");

        deleteButton.setBackground(AppTheme.DANGER);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(AppTheme.BODY_FONT);
        deleteButton.setFocusPainted(false);
        deleteButton.setPreferredSize(
                new Dimension(75, 55));

        deleteButton.addActionListener(e -> {

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Hapus karakter "
                    + character.getName() + "?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                controller.removeCharacter(
                        character.getName());

                refreshCharacters();
            }
        });

        card.add(infoPanel, BorderLayout.CENTER);
        card.add(deleteButton, BorderLayout.EAST);

        return card;
    }

    /**
     * Memperbarui statistik karakter.
     */
    private void updateStatistics() {

        totalLabel.setText(
                "Total karakter: "
                + controller.getTotalCharacters());

        averageLabel.setText(String.format(
                java.util.Locale.US,
                "Rata-rata level: %.2f",
                controller.getAverageLevel()));

        Character strongest = controller.findStrongest();

        strongestLabel.setText(
                "Level tertinggi: "
                + (strongest == null
                ? "Belum ada karakter"
                : strongest.getName()
                + " (Level " + strongest.getLevel() + ")"));
    }
}
