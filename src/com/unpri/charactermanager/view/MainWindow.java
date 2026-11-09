
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.CharacterManager;
import com.unpri.charactermanager.controller.CharacterController;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainWindow extends JFrame {

    private final CharacterController controller;

    private final JTextField txtName = new JTextField(12);

    private final JComboBox<CharacterType> cmbType =
            new JComboBox<>(CharacterType.values());

    private final JSpinner spnLevel =
            new JSpinner(
                    new SpinnerNumberModel(1, 1, 100, 1));

    private final JPanel characterPanel = new JPanel();

    private final JLabel lblStatistics = new JLabel();

    private final JLabel lblTotal = new JLabel();
    private final JLabel lblAverage = new JLabel();
    private final JLabel lblStrongest = new JLabel();
    private final JLabel lblRoles = new JLabel();

    private String selectedCharacterName = null;

    public MainWindow() {

        CharacterManager manager = new CharacterManager();

        controller = new CharacterController(manager);

        setTitle("Character Manager v0.5");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // =========================
        // PANEL UTAMA
        // =========================

        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        mainPanel.setBackground(AppTheme.BACKGROUND);

        mainPanel.setBorder(
                new EmptyBorder(15, 15, 15, 15));

        // =========================
        // HEADER / NORTH
        // =========================

        JPanel headerPanel =
                new JPanel(new BorderLayout(5, 10));

        headerPanel.setBackground(AppTheme.BACKGROUND);

        JLabel title =
                new JLabel("CHARACTER MANAGER v0.5");

        title.setFont(AppTheme.TITLE_FONT);

        title.setForeground(AppTheme.TITLE_COLOR);

        title.setBorder(
                new EmptyBorder(0, 0, 5, 0));

        headerPanel.add(
                title,
                BorderLayout.NORTH);

        // Panel statistik tambahan GridLayout 2x2

        JPanel statisticsPanel =
                new JPanel(new GridLayout(2, 2, 8, 8));

        statisticsPanel.setBackground(
                AppTheme.PANEL_BACKGROUND);

        statisticsPanel.setBorder(
                AppTheme.PANEL_BORDER);

        lblTotal.setForeground(AppTheme.TEXT_COLOR);
        lblAverage.setForeground(AppTheme.TEXT_COLOR);
        lblStrongest.setForeground(AppTheme.TEXT_COLOR);
        lblRoles.setForeground(AppTheme.TEXT_COLOR);

        lblTotal.setFont(AppTheme.NORMAL_FONT);
        lblAverage.setFont(AppTheme.NORMAL_FONT);
        lblStrongest.setFont(AppTheme.NORMAL_FONT);
        lblRoles.setFont(AppTheme.NORMAL_FONT);

        statisticsPanel.add(lblTotal);
        statisticsPanel.add(lblAverage);
        statisticsPanel.add(lblStrongest);
        statisticsPanel.add(lblRoles);

        headerPanel.add(
                statisticsPanel,
                BorderLayout.SOUTH);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH);

        // =========================
        // PANEL INPUT KARAKTER
        // =========================

        JPanel inputPanel =
                new JPanel(new FlowLayout(
                        FlowLayout.LEFT, 5, 5));

        inputPanel.setBackground(
                AppTheme.PANEL_BACKGROUND);

        JLabel lblNama = new JLabel("Nama:");
        JLabel lblRole = new JLabel("Role:");
        JLabel lblLevel = new JLabel("Level:");

        lblNama.setForeground(AppTheme.TEXT_COLOR);
        lblRole.setForeground(AppTheme.TEXT_COLOR);
        lblLevel.setForeground(AppTheme.TEXT_COLOR);

        lblNama.setFont(AppTheme.NORMAL_FONT);
        lblRole.setFont(AppTheme.NORMAL_FONT);
        lblLevel.setFont(AppTheme.NORMAL_FONT);

        inputPanel.add(lblNama);
        inputPanel.add(txtName);

        inputPanel.add(lblRole);
        inputPanel.add(cmbType);

        inputPanel.add(lblLevel);
        inputPanel.add(spnLevel);

        JButton btnAdd =
                new JButton("Tambah Karakter");

        JButton btnSample =
                new JButton("Muat 8 Karakter");

        JButton btnRemove =
                new JButton("Hapus Terpilih");

        inputPanel.add(btnAdd);
        inputPanel.add(btnSample);
        inputPanel.add(btnRemove);

        // =========================
        // PANEL DAFTAR KARAKTER
        // =========================

        characterPanel.setLayout(
                new BoxLayout(
                        characterPanel,
                        BoxLayout.Y_AXIS));

        characterPanel.setBackground(
                AppTheme.BACKGROUND);

        // JScrollPane

        JScrollPane scrollPane =
                new JScrollPane(characterPanel);

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder());

        scrollPane.setPreferredSize(
                new Dimension(550, 350));

        scrollPane.getViewport().setBackground(
                AppTheme.BACKGROUND);

        // =========================
        // CENTER PANEL
        // =========================

        JPanel centerPanel =
                new JPanel(new BorderLayout(5, 5));

        centerPanel.setBackground(
                AppTheme.BACKGROUND);

        centerPanel.add(
                inputPanel,
                BorderLayout.NORTH);

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER);

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER);

        // =========================
        // LEGENDA ROLE / EAST
        // =========================

        JPanel legendPanel =
                new JPanel();

        legendPanel.setLayout(
                new BoxLayout(
                        legendPanel,
                        BoxLayout.Y_AXIS));

        legendPanel.setBackground(
                AppTheme.PANEL_BACKGROUND);

        legendPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        AppTheme.PANEL_BORDER,
                        BorderFactory.createEmptyBorder(
                                5, 5, 5, 5)));

        JLabel lblLegendTitle =
                new JLabel("LEGENDA ROLE");

        lblLegendTitle.setForeground(
                AppTheme.TITLE_COLOR);

        lblLegendTitle.setFont(
                AppTheme.NAME_FONT);

        lblLegendTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT);

        legendPanel.add(lblLegendTitle);

        legendPanel.add(
                Box.createVerticalStrut(10));

        // Legenda berasal dari CharacterType.values()

        for (CharacterType type :
                CharacterType.values()) {

            JPanel rolePanel =
                    new JPanel(new FlowLayout(
                            FlowLayout.LEFT, 5, 3));

            rolePanel.setBackground(
                    AppTheme.PANEL_BACKGROUND);

            rolePanel.setAlignmentX(
                    Component.LEFT_ALIGNMENT);

            JLabel colorLabel =
                    new JLabel("     ");

            colorLabel.setOpaque(true);

            colorLabel.setBackground(
                    AppTheme.colorFor(type));

            colorLabel.setBorder(
                    BorderFactory.createLineBorder(
                            AppTheme.BORDER_COLOR));

            JLabel roleLabel =
                    new JLabel(type.toString());

            roleLabel.setForeground(
                    AppTheme.TEXT_COLOR);

            roleLabel.setFont(
                    AppTheme.NORMAL_FONT);

            rolePanel.add(colorLabel);
            rolePanel.add(roleLabel);

            legendPanel.add(rolePanel);

            legendPanel.add(
                    Box.createVerticalStrut(5));
        }

        mainPanel.add(
                legendPanel,
                BorderLayout.EAST);

        // =========================
        // STATUS BAR / SOUTH
        // =========================

        JPanel statusPanel =
                new JPanel(new BorderLayout());

        statusPanel.setBackground(
                AppTheme.PANEL_BACKGROUND);

        statusPanel.setBorder(
                AppTheme.PANEL_BORDER);

        JLabel lblStatus =
                new JLabel("Character Manager siap digunakan");

        lblStatus.setForeground(
                AppTheme.TEXT_COLOR);

        lblStatus.setFont(
                AppTheme.NORMAL_FONT);

        JLabel lblVersion =
                new JLabel("v0.5");

        lblVersion.setForeground(
                AppTheme.TITLE_COLOR);

        lblVersion.setFont(
                AppTheme.NAME_FONT);

        statusPanel.add(
                lblStatus,
                BorderLayout.WEST);

        statusPanel.add(
                lblVersion,
                BorderLayout.EAST);

        mainPanel.add(
                statusPanel,
                BorderLayout.SOUTH);

        // =========================
        // SET CONTENT PANE
        // =========================

        setContentPane(mainPanel);

        // =========================
        // AKSI TOMBOL
        // =========================

        btnAdd.addActionListener(
                e -> addCharacter());

        btnSample.addActionListener(
                e -> loadSamples());

        btnRemove.addActionListener(
                e -> removeSelected());

        // =========================
        // TAMPILAN AWAL
        // =========================

        refreshCharacters();

        pack();

        setMinimumSize(
                new Dimension(850, 650));

        setLocationRelativeTo(null);
    }

    // =========================
    // TAMBAH KARAKTER
    // =========================

    private void addCharacter() {

        String name = txtName.getText().trim();

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nama karakter tidak boleh kosong!");

            return;
        }

        Character character =
                new Character(
                        name,
                        (CharacterType)
                                cmbType.getSelectedItem(),
                        (int) spnLevel.getValue());

        if (controller.addCharacter(character)) {

            txtName.setText("");

            refreshCharacters();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Karakter gagal ditambahkan "
                    + "atau nama sudah ada!");
        }
    }

    // =========================
    // MUAT 8 KARAKTER
    // =========================

    private void loadSamples() {

        if (controller.getTotalCharacters() > 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Karakter sudah dimuat!");

            return;
        }

        controller.addCharacter(
                new Character(
                        "Arjuna",
                        CharacterType.WARRIOR,
                        5));

        controller.addCharacter(
                new Character(
                        "Merlin",
                        CharacterType.MAGE,
                        3));

        controller.addCharacter(
                new Character(
                        "Athena",
                        CharacterType.ARCHER,
                        4));

        controller.addCharacter(
                new Character(
                        "Hector",
                        CharacterType.WARRIOR,
                        6));

        controller.addCharacter(
                new Character(
                        "Luna",
                        CharacterType.HEALER,
                        2));

        controller.addCharacter(
                new Character(
                        "Ragnar",
                        CharacterType.MAGE,
                        10));

        controller.addCharacter(
                new Character(
                        "Diana",
                        CharacterType.ARCHER,
                        7));

        controller.addCharacter(
                new Character(
                        "Freya",
                        CharacterType.HEALER,
                        8));

        refreshCharacters();
    }

    // =========================
    // HAPUS KARAKTER
    // =========================

    private void removeSelected() {

        if (selectedCharacterName == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih kartu karakter terlebih dahulu!");

            return;
        }

        if (controller.removeCharacter(
                selectedCharacterName)) {

            selectedCharacterName = null;

            refreshCharacters();
        }
    }

    // =========================
    // MEMBUAT KARTU KARAKTER
    // =========================

    private JPanel createCharacterCard(
            Character character) {

        JPanel card =
                new JPanel(new BorderLayout(5, 5));

        card.setBackground(
                AppTheme.colorFor(
                        character.getType()));

        card.setOpaque(true);

        card.setBorder(
                AppTheme.CARD_BORDER);

        // Nama karakter

        JLabel lblName =
                new JLabel(
                        character.getName());

        lblName.setFont(
                AppTheme.NAME_FONT);

        lblName.setForeground(
                AppTheme.TEXT_COLOR);

        // Level emas

        if (character.getLevel()
                >= AppTheme.GOLD_LEVEL) {

            lblName.setForeground(
                    AppTheme.GOLD);

            lblName.setFont(
                    AppTheme.GOLD_FONT);
        }

        // Detail karakter

        JLabel lblDetails =
                new JLabel(
                        "<html>"
                        + "Role: "
                        + character.getType()
                        + " | Level: "
                        + character.getLevel()
                        + "<br>"
                        + "HP: "
                        + character.getHitPoints()
                        + " | Attack: "
                        + character.getAttackPower()
                        + "</html>");

        lblDetails.setForeground(
                AppTheme.TEXT_COLOR);

        lblDetails.setFont(
                AppTheme.NORMAL_FONT);

        // Tombol hapus

        JButton btnDelete =
                new JButton("Hapus");

        btnDelete.setBackground(
                AppTheme.DELETE_COLOR);

        btnDelete.setForeground(
                AppTheme.TEXT_COLOR);

        btnDelete.setFont(
                AppTheme.NORMAL_FONT);

        btnDelete.setOpaque(true);

        btnDelete.setBorderPainted(false);

        btnDelete.addActionListener(e -> {

            if (controller.removeCharacter(
                    character.getName())) {

                selectedCharacterName = null;

                refreshCharacters();
            }
        });

        card.add(
                lblName,
                BorderLayout.NORTH);

        card.add(
                lblDetails,
                BorderLayout.CENTER);

        card.add(
                btnDelete,
                BorderLayout.EAST);

        // Klik kartu untuk memilih

        card.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e) {

                        selectedCharacterName =
                                character.getName();

                        refreshCharacters();
                    }
                });

        // Border kartu terpilih

        if (character.getName().equals(
                selectedCharacterName)) {

            card.setBorder(
                    AppTheme.SELECTED_BORDER);
        }

        return card;
    }

    // =========================
    // REFRESH KARTU
    // =========================

    private void refreshCharacters() {

        characterPanel.removeAll();

        for (Character character :
                controller.getAllCharacters()) {

            JPanel card =
                    createCharacterCard(character);

            card.setAlignmentX(
                    Component.LEFT_ALIGNMENT);

            card.setMaximumSize(
                    new Dimension(
                            Integer.MAX_VALUE,
                            65));

            characterPanel.add(card);

            characterPanel.add(
                    Box.createVerticalStrut(5));
        }

        updateStatistics();

        characterPanel.revalidate();

        characterPanel.repaint();
    }

    // =========================
    // UPDATE STATISTIK
    // =========================

    private void updateStatistics() {

        Character strongest =
                controller.findStrongest();

        String strongestText =
                strongest == null
                ? "-"
                : strongest.getName()
                + " (Level "
                + strongest.getLevel()
                + ")";

        lblStatistics.setText(
                "<html>"
                + "Total karakter: "
                + controller.getTotalCharacters()
                + "<br>"
                + "Rata-rata level: "
                + String.format(
                        "%.2f",
                        controller.getAverageLevel())
                + "<br>"
                + "Level tertinggi: "
                + strongestText
                + "</html>");

        // Statistik tambahan GridLayout 2x2

        lblTotal.setText(
                "Total: "
                + controller.getTotalCharacters());

        lblAverage.setText(
                "Rata-rata level: "
                + String.format(
                        "%.2f",
                        controller.getAverageLevel()));

        lblStrongest.setText(
                "Terkuat: "
                + strongestText);

        lblRoles.setText(
                "Jumlah role: "
                + CharacterType.values().length);
    }
}
