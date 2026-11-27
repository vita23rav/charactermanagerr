
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.controller.CharacterController;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.ParseException;
import java.util.function.Consumer;

public class CharacterFormPanel extends JPanel {

    private final CharacterController controller;
    private final Consumer<Void> onSuccess;

    private final JTextField nameField;
    private final JComboBox<CharacterType> typeCombo;
    private final JSpinner levelSpinner;

    private final JLabel nameError;
    private final JLabel typeError;
    private final JLabel levelError;

    private final Border normalNameBorder;
    private final Border normalTypeBorder;
    private final Border normalLevelBorder;

    private final Border errorBorder =
            BorderFactory.createLineBorder(Color.RED, 2);

    public CharacterFormPanel(
            CharacterController controller,
            Consumer<Void> onSuccess) {

        this.controller = controller;
        this.onSuccess = onSuccess;

        setLayout(new GridBagLayout());
        setBackground(AppTheme.CARD_BACKGROUND);

        TitledBorder titledBorder =
                BorderFactory.createTitledBorder(
                        AppTheme.CARD_BORDER,
                        "Tambah Karakter");

        titledBorder.setTitleColor(AppTheme.TEXT);
        titledBorder.setTitleFont(AppTheme.EMPHASIS_FONT);

        setBorder(BorderFactory.createCompoundBorder(
                titledBorder,
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        nameField = new JTextField(12);
        typeCombo = new JComboBox<>(CharacterType.values());

        levelSpinner = new JSpinner(
                new SpinnerNumberModel(1, 1, 99, 1));

        // Menyimpan border asli.
        normalNameBorder = nameField.getBorder();
        normalTypeBorder = typeCombo.getBorder();
        normalLevelBorder = levelSpinner.getBorder();

        nameError = createErrorLabel();
        typeError = createErrorLabel();
        levelError = createErrorLabel();

        buildForm();
    }

    private JLabel createErrorLabel() {
        JLabel label = new JLabel(" ");
        label.setForeground(Color.RED);
        label.setFont(new Font("Arial", Font.PLAIN, 10));
        return label;
    }

    private JLabel createFieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(AppTheme.TEXT);
        label.setFont(AppTheme.BODY_FONT);
        return label;
    }

    private void buildForm() {

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(4, 5, 2, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Baris utama: label dan input.
        addComponent(createFieldLabel("Nama:"), gbc, 0, 0);
        addComponent(nameField, gbc, 1, 0);

        addComponent(createFieldLabel("Role:"), gbc, 2, 0);
        addComponent(typeCombo, gbc, 3, 0);

        addComponent(createFieldLabel("Level:"), gbc, 4, 0);
        addComponent(levelSpinner, gbc, 5, 0);

        JButton addButton = new JButton("Tambah Karakter");
        addButton.setBackground(AppTheme.BUTTON_BACKGROUND);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(AppTheme.EMPHASIS_FONT);
        addButton.setFocusPainted(false);

        addComponent(addButton, gbc, 6, 0);

        // Baris kedua: pesan error tepat di bawah input.
        addComponent(nameError, gbc, 1, 1);
        addComponent(typeError, gbc, 3, 1);
        addComponent(levelError, gbc, 5, 1);

        addButton.addActionListener(e -> submitForm());
        nameField.addActionListener(e -> submitForm());
    }

    private void addComponent(
            Component component,
            GridBagConstraints gbc,
            int column,
            int row) {

        gbc.gridx = column;
        gbc.gridy = row;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;

        if (component == nameError
                || component == typeError
                || component == levelError) {
            gbc.insets = new Insets(0, 5, 3, 5);
        } else {
            gbc.insets = new Insets(4, 5, 2, 5);
        }

        add(component, gbc);
    }

    /**
     * Menghapus pesan error dan mengembalikan border asli.
     */
    private void clearErrors() {

        nameError.setText(" ");
        typeError.setText(" ");
        levelError.setText(" ");

        nameField.setBorder(normalNameBorder);
        typeCombo.setBorder(normalTypeBorder);
        levelSpinner.setBorder(normalLevelBorder);
    }

    private void showNameError(String message) {

        nameError.setText(message);
        nameField.setBorder(errorBorder);
        nameField.requestFocusInWindow();
    }

    private void showTypeError(String message) {

        typeError.setText(message);
        typeCombo.setBorder(errorBorder);
        typeCombo.requestFocusInWindow();
    }

    private void showLevelError(String message) {

        levelError.setText(message);
        levelSpinner.setBorder(errorBorder);
        levelSpinner.requestFocusInWindow();
    }

    /**
     * Validasi nama:
     * kelengkapan, panjang, format,
     * spasi ganda, dan keunikan.
     */
    private String validateName(String name) {

        // 1. Kelengkapan
        if (name == null || name.isEmpty()) {
            return "Nama karakter wajib diisi.";
        }

        // 2. Panjang
        if (name.length() < 3 || name.length() > 20) {
            return "Nama harus terdiri dari 3 sampai 20 karakter.";
        }

        // 3. Format
        if (!name.matches("[a-zA-Z ]+")) {
            return "Nama hanya boleh berisi huruf dan spasi.";
        }

        // 4. Aturan tambahan: spasi ganda
        if (name.contains("  ")) {
            return "Nama tidak boleh menggunakan spasi ganda.";
        }

        // 5. Keunikan
        if (controller.isNameTaken(name)) {
            return "Nama karakter sudah digunakan.";
        }

        return null;
    }

    /**
     * Memproses input dan menambahkan karakter.
     */
    private void submitForm() {

        clearErrors();

        String name = nameField.getText().trim();

        // Validasi nama.
        String nameErrorMessage = validateName(name);

        if (nameErrorMessage != null) {
            showNameError(nameErrorMessage);
            return;
        }

        // Validasi role.
        CharacterType type =
                (CharacterType) typeCombo.getSelectedItem();

        if (type == null) {
            showTypeError("Silakan pilih role karakter.");
            return;
        }

        // Membaca spinner setelah commitEdit().
        int level;

        try {
            levelSpinner.commitEdit();
            level = (Integer) levelSpinner.getValue();

        } catch (ParseException ex) {
            showLevelError("Level harus berupa angka yang valid.");
            return;
        }

        // Validasi Model melalui Controller dan Character.
        try {

            boolean success =
                    controller.addCharacter(name, type, level);

            if (success) {

                nameField.setText("");
                typeCombo.setSelectedIndex(0);
                levelSpinner.setValue(1);

                onSuccess.accept(null);

                nameField.requestFocusInWindow();

            } else {

                if (controller.getTotalCharacters() >= 20) {
                    showNameError(
                            "Jumlah karakter sudah mencapai batas 20.");
                } else {
                    showNameError(
                            "Karakter gagal ditambahkan.");
                }
            }

        } catch (IllegalArgumentException ex) {

            // Menampilkan pesan kesalahan Model.
            if (ex.getMessage() != null
                    && ex.getMessage().toLowerCase().contains("role")) {
                showTypeError(ex.getMessage());
            } else if (ex.getMessage() != null
                    && ex.getMessage().toLowerCase().contains("nama")) {
                showNameError(ex.getMessage());
            } else {
                showLevelError(ex.getMessage());
            }
        }
    }
}
