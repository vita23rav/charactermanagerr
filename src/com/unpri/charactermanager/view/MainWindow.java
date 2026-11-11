package com.unpri.charactermanager.view;

import com.unpri.charactermanager.controller.CharacterController;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainWindow extends JFrame {

    private final CharacterController controller;

    private final JTextField nameField;
    private final JComboBox<CharacterType> typeCombo;
    private final JSpinner levelSpinner;

    private final DefaultTableModel tableModel;
    private final JTable characterTable;

    private final JLabel totalLabel;
    private final JLabel averageLabel;
    private final JLabel strongestLabel;

    public MainWindow(CharacterController controller) {
        this.controller = controller;

        setTitle("Character Manager v0.1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nameField = new JTextField(12);
        typeCombo = new JComboBox<>(CharacterType.values());
        levelSpinner = new JSpinner(
                new SpinnerNumberModel(1, 1, 100, 1));

        tableModel = new DefaultTableModel(
                new String[]{"Nama", "Tipe", "Level", "HP", "Attack"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        characterTable = new JTable(tableModel);
        characterTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
        characterTable.setRowHeight(24);
        characterTable.setBackground(Color.WHITE);
        characterTable.setGridColor(Color.LIGHT_GRAY);

        totalLabel = new JLabel();
        averageLabel = new JLabel();
        strongestLabel = new JLabel();

        buildWindow();
        refreshCharacters();

        setSize(700, 480);
        setLocationRelativeTo(null);
    }

    private void buildWindow() {

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("CHARACTER MANAGER");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(
                BorderFactory.createTitledBorder("Tambah Karakter"));

        JButton addButton = new JButton("Tambah");
        JButton deleteButton = new JButton("Hapus Terpilih");

        formPanel.add(new JLabel("Nama:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Tipe:"));
        formPanel.add(typeCombo);
        formPanel.add(new JLabel("Level:"));
        formPanel.add(levelSpinner);
        formPanel.add(addButton);
        formPanel.add(deleteButton);

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(characterTable);
        scrollPane.setPreferredSize(new Dimension(650, 250));

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(
                BorderFactory.createTitledBorder("Daftar Karakter"));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel statsPanel = new JPanel(
                new GridLayout(3, 1, 5, 5));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(
                BorderFactory.createTitledBorder("Statistik"));

        statsPanel.add(totalLabel);
        statsPanel.add(averageLabel);
        statsPanel.add(strongestLabel);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(statsPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        addButton.addActionListener(e -> addCharacter());
        deleteButton.addActionListener(e -> deleteCharacter());
    }

    private void refreshCharacters() {

        tableModel.setRowCount(0);

        for (Character c : controller.getAllCharacters()) {
            tableModel.addRow(new Object[]{
                    c.getName(),
                    c.getType(),
                    c.getLevel(),
                    c.getHitPoints(),
                    c.getAttackPower()
            });
        }

        totalLabel.setText(
                "Total karakter: " + controller.getTotalCharacters());

        averageLabel.setText(String.format(
                java.util.Locale.US,
                "Rata-rata Level: %.2f",
                controller.getAverageLevel()));

        Character strongest = controller.findStrongest();

        strongestLabel.setText(
                "Level tertinggi: "
                + (strongest == null
                ? "Belum ada karakter"
                : strongest.getName()
                + " (Level " + strongest.getLevel() + ")"));
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
                    this, "Silakan pilih tipe karakter.");
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
                    this, "Gagal menambahkan karakter.");
        }
    }

    private void deleteCharacter() {

        int selectedRow = characterTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this, "Pilih karakter yang ingin dihapus.");
            return;
        }

        String name = tableModel.getValueAt(
                selectedRow, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Hapus karakter " + name + "?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            controller.removeCharacter(name);
            refreshCharacters();
        }
    }
}
