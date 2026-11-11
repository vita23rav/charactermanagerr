package com.unpri.charactermanager.view;

import com.unpri.charactermanager.controller.CharacterController;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private final CharacterController controller;

    private final JTextField nameField;
    private final JComboBox<CharacterType> typeCombo;
    private final JSpinner levelSpinner;

    private final JPanel cardsPanel;

    private final JLabel totalLabel;
    private final JLabel averageLabel;
    private final JLabel strongestLabel;

    public MainWindow(CharacterController controller) {
        this.controller = controller;

        setTitle("Character Manager v0.4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nameField = new JTextField(12);
        typeCombo = new JComboBox<>(CharacterType.values());
        levelSpinner = new JSpinner(
                new SpinnerNumberModel(1, 1, 100, 1));

        cardsPanel = new JPanel();
        cardsPanel.setLayout(
                new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBackground(AppTheme.BACKGROUND);
        cardsPanel.setOpaque(true);
        cardsPanel.setBorder(AppTheme.PANEL_BORDER);

        totalLabel = new JLabel();
        averageLabel = new JLabel();
        strongestLabel = new JLabel();

        buildWindow();
        refreshCharacters();

        // Ukuran jendela dihitung dari preferred size komponen.
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
    }

    private void buildWindow() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(AppTheme.BACKGROUND);
        mainPanel.setOpaque(true);
        mainPanel.setBorder(AppTheme.PANEL_BORDER);

        JLabel titleLabel = new JLabel("CHARACTER MANAGER v0.4");
        titleLabel.setFont(AppTheme.TITLE_FONT);
        titleLabel.setForeground(AppTheme.GOLD);

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formPanel.setBackground(AppTheme.CARD_BACKGROUND);
        formPanel.setOpaque(true);
        formPanel.setBorder(AppTheme.CARD_BORDER);

        JLabel nameLabel = new JLabel("Nama:");
        JLabel typeLabel = new JLabel("Role:");
        JLabel levelLabel = new JLabel("Level:");

        styleLabel(nameLabel);
        styleLabel(typeLabel);
        styleLabel(levelLabel);

        nameField.setFont(AppTheme.BODY_FONT);
        typeCombo.setFont(AppTheme.BODY_FONT);
        levelSpinner.setFont(AppTheme.BODY_FONT);

        JButton addButton = new JButton("Tambah Karakter");
        addButton.setFont(AppTheme.EMPHASIS_FONT);
        addButton.setBackground(AppTheme.BUTTON_BACKGROUND);
        addButton.setForeground(AppTheme.TEXT);
        addButton.setOpaque(true);
        addButton.setToolTipText("Tambahkan karakter baru");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(typeLabel);
        formPanel.add(typeCombo);
        formPanel.add(levelLabel);
        formPanel.add(levelSpinner);
        formPanel.add(addButton);

        JPanel topPanel = new JPanel(new BorderLayout(5, 10));
        topPanel.setBackground(AppTheme.BACKGROUND);
        topPanel.setOpaque(true);
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.getViewport().setBackground(AppTheme.BACKGROUND);
        scrollPane.setBorder(AppTheme.CARD_BORDER);
        scrollPane.setPreferredSize(new Dimension(650, 300));

        JPanel statsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        statsPanel.setBackground(AppTheme.CARD_BACKGROUND);
        statsPanel.setOpaque(true);
        statsPanel.setBorder(AppTheme.CARD_BORDER);

        styleLabel(totalLabel);
        styleLabel(averageLabel);
        styleLabel(strongestLabel);

        statsPanel.add(totalLabel);
        statsPanel.add(averageLabel);
        statsPanel.add(strongestLabel);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(statsPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        addButton.addActionListener(e -> addCharacter());
    }

    private void styleLabel(JLabel label) {
        label.setFont(AppTheme.BODY_FONT);
        label.setForeground(AppTheme.TEXT);
        label.setOpaque(false);
    }

    private void refreshCharacters() {
        cardsPanel.removeAll();

        for (Character c : controller.getAllCharacters()) {
            cardsPanel.add(createCharacterCard(c));
            cardsPanel.add(Box.createVerticalStrut(8));
        }

        totalLabel.setText(
                "Total karakter: " + controller.getTotalCharacters());

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

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private JPanel createCharacterCard(Character c) {
        String role = String.valueOf(c.getType());

        JPanel card = new JPanel(new BorderLayout(10, 5));
        card.setBackground(AppTheme.colorFor(role));
        card.setOpaque(true);
        card.setBorder(AppTheme.borderFor(role));
        card.setMaximumSize(new Dimension(
                Integer.MAX_VALUE, 105));

        JPanel informationPanel = new JPanel();
        informationPanel.setLayout(
                new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
        informationPanel.setBackground(AppTheme.colorFor(role));
        informationPanel.setOpaque(true);

        JLabel nameLabel = new JLabel(c.getName());
        nameLabel.setFont(AppTheme.EMPHASIS_FONT);
        nameLabel.setForeground(AppTheme.TEXT);

        JLabel roleLabel = new JLabel(
                "Role: " + role
                + " | Level: " + c.getLevel());
        roleLabel.setFont(AppTheme.BODY_FONT);
        roleLabel.setForeground(AppTheme.TEXT);

        JLabel statsLabel = new JLabel(
                "HP: " + c.getHitPoints()
                + " | Attack: " + c.getAttackPower());
        statsLabel.setFont(AppTheme.BODY_FONT);
        statsLabel.setForeground(AppTheme.TEXT);

        // Mini-Challenge: level minimal 5 berwarna emas dan tebal.
        if (c.getLevel() >= AppTheme.HIGH_LEVEL_THRESHOLD) {
            roleLabel.setForeground(AppTheme.GOLD);
            roleLabel.setFont(AppTheme.EMPHASIS_FONT);
            roleLabel.setToolTipText(
                    "Karakter level tinggi: " + c.getLevel());
        }

        // Properti tambahan 1: tooltip detail HP dan Attack.
        card.setToolTipText(
                "Nama: " + c.getName()
                + " | HP: " + c.getHitPoints()
                + " | Attack: " + c.getAttackPower());

        informationPanel.add(nameLabel);
        informationPanel.add(roleLabel);
        informationPanel.add(statsLabel);

        JButton deleteButton = new JButton("Hapus");
        deleteButton.setFont(AppTheme.BODY_FONT);
        deleteButton.setBackground(AppTheme.DANGER);
        deleteButton.setForeground(AppTheme.TEXT);
        deleteButton.setOpaque(true);

        // Properti tambahan 2: border kartu berbeda berdasarkan role.
        deleteButton.setToolTipText(
                "Hapus karakter " + c.getName());

        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Hapus karakter " + c.getName() + "?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                controller.removeCharacter(c.getName());
                refreshCharacters();
            }
        });

        card.add(informationPanel, BorderLayout.CENTER);
        card.add(deleteButton, BorderLayout.EAST);

        return card;
    }

    private void addCharacter() {
        String name = nameField.getText().trim();
        CharacterType type =
                (CharacterType) typeCombo.getSelectedItem();
        int level = (Integer) levelSpinner.getValue();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Nama karakter wajib diisi.");
            return;
        }

        if (type == null) {
            JOptionPane.showMessageDialog(
                    this, "Silakan pilih role karakter.");
            return;
        }

        if (controller.addCharacter(name, type, level)) {
            refreshCharacters();
            nameField.setText("");
            levelSpinner.setValue(1);

            JOptionPane.showMessageDialog(
                    this, "Karakter berhasil ditambahkan.");
        } else {
            JOptionPane.showMessageDialog(
                    this, "Gagal menambahkan karakter. "
                    + "Periksa nama duplikat atau data.");
        }
    }
}

