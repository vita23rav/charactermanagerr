
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

    private final JTextField txtName =
            new JTextField(12);

    private final JComboBox<CharacterType> cmbType =
            new JComboBox<>(CharacterType.values());

    private final JSpinner spnLevel =
            new JSpinner(
                    new SpinnerNumberModel(1, 1, 100, 1));

    private final JPanel characterPanel =
            new JPanel();

    private final JLabel lblStatistics =
            new JLabel();

    private String selectedCharacterName = null;

    public MainWindow() {

        CharacterManager manager =
                new CharacterManager();

        controller =
                new CharacterController(manager);

        setTitle("Character Manager v0.4");

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        // Panel utama
        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        mainPanel.setBackground(
                AppTheme.BACKGROUND);

        mainPanel.setBorder(
                new EmptyBorder(15, 15, 15, 15));

        // Judul
        JLabel title =
                new JLabel("CHARACTER MANAGER v0.4");

        title.setFont(
                AppTheme.TITLE_FONT);

        title.setForeground(
                AppTheme.TITLE_COLOR);

        // Panel input
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

        // Tombol
        JButton btnAdd =
                new JButton("Tambah Karakter");

        JButton btnSample =
                new JButton("Muat 6 Karakter");

        JButton btnRemove =
                new JButton("Hapus Terpilih");

        inputPanel.add(btnAdd);
        inputPanel.add(btnSample);
        inputPanel.add(btnRemove);

        // Panel kartu
        characterPanel.setLayout(
                new BoxLayout(
                        characterPanel,
                        BoxLayout.Y_AXIS));

        characterPanel.setBackground(
                AppTheme.BACKGROUND);

        JScrollPane scrollPane =
                new JScrollPane(characterPanel);

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder());

        scrollPane.getViewport().setBackground(
                AppTheme.BACKGROUND);

        // Panel bawah
        JPanel bottomPanel =
                new JPanel(new BorderLayout());

        bottomPanel.setBackground(
                AppTheme.PANEL_BACKGROUND);

        bottomPanel.setBorder(
                AppTheme.PANEL_BORDER);

        lblStatistics.setForeground(
                AppTheme.TEXT_COLOR);

        lblStatistics.setFont(
                AppTheme.NORMAL_FONT);

        bottomPanel.add(
                lblStatistics,
                BorderLayout.CENTER);

        JButton btnStrongest =
                new JButton("Karakter Terkuat");

        bottomPanel.add(
                btnStrongest,
                BorderLayout.EAST);

        // Susunan utama
        mainPanel.add(
                title,
                BorderLayout.NORTH);

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

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH);

        setContentPane(mainPanel);

        // Aksi tombol
        btnAdd.addActionListener(
                e -> addCharacter());

        btnSample.addActionListener(
                e -> loadSamples());

        btnRemove.addActionListener(
                e -> removeSelected());

        btnStrongest.addActionListener(
                e -> showStrongest());

        // Tampilan awal
        refreshCharacters();

        pack();

        setMinimumSize(
                new Dimension(850, 650));

        setLocationRelativeTo(null);
    }

    // Tambah karakter
    private void addCharacter() {

        String name =
                txtName.getText().trim();

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

    // Muat enam karakter
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

        refreshCharacters();
    }

    // Hapus karakter terpilih
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

    // Membuat kartu karakter
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

        // Nama
        JLabel lblName =
                new JLabel(
                        character.getName());

        lblName.setFont(
                AppTheme.NAME_FONT);

        lblName.setForeground(
                AppTheme.TEXT_COLOR);

        // Mini-challenge level emas
        if (character.getLevel()
                >= AppTheme.GOLD_LEVEL) {

            lblName.setForeground(
                    AppTheme.GOLD);

            lblName.setFont(
                    AppTheme.GOLD_FONT);
        }

        // Detail
        String roleLevelColor =
                character.getLevel()
                >= AppTheme.GOLD_LEVEL
                ? "#FFD700"
                : "#FFFFFF";

        JLabel lblDetails =
                new JLabel(
                        "<html>"
                        + "<span style='color:"
                        + roleLevelColor
                        + "; font-weight:bold;'>"
                        + "Role: "
                        + character.getType()
                        + " | Level: "
                        + character.getLevel()
                        + "</span>"
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

        if (character.getName().equals(
                selectedCharacterName)) {

            card.setBorder(
                    AppTheme.SELECTED_BORDER);
        }

        return card;
    }

    // Refresh kartu
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

    // Statistik
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
    }

    // Karakter terkuat
    private void showStrongest() {

        Character strongest =
                controller.findStrongest();

        if (strongest == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Belum ada karakter!");

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Karakter Terkuat: "
                + strongest.getName()
                + "\nRole: "
                + strongest.getType()
                + "\nLevel: "
                + strongest.getLevel()
                + "\nHP: "
                + strongest.getHitPoints()
                + "\nAttack: "
                + strongest.getAttackPower());
    }
}
