
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.controller.CharacterController;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import javax.swing.border.Border;
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
        setBorder(BorderFactory.createTitledBorder("Tambah Karakter"));

        nameField = new JTextField(15);
        typeCombo = new JComboBox<>(CharacterType.values());

        levelSpinner = new JSpinner(
                new SpinnerNumberModel(1, 1, 99, 1));

        // Menyimpan border asli setiap komponen.
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
        label.setFont(new Font("Arial", Font.PLAIN, 11));
        return label;
    }

    private void buildForm() {

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.anchor = GridBagConstraints.WEST;

        addRow(gbc, 0, "Nama:", nameField, nameError);
        addRow(gbc, 1, "Role:", typeCombo, typeError);
        addRow(gbc, 2, "Level:", levelSpinner, levelError);

        JButton addButton = new JButton("Tambah Karakter");

        gbc.gridx = 1;
        gbc.gridy = 6;

        add(addButton, gbc);

        addButton.addActionListener(e -> submitForm());
        nameField.addActionListener(e -> submitForm());
    }

    private void addRow(
            GridBagConstraints gbc,
            int row,
            String label,
            JComponent input,
            JLabel error) {

        gbc.gridx = 0;
        gbc.gridy = row * 2;

        add(new JLabel(label), gbc);

        gbc.gridx = 1;
        add(input, gbc);

        gbc.gridy = row * 2 + 1;
        add(error, gbc);
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
     * Validasi nama secara berurutan:
     * kelengkapan, panjang, format, dan keunikan.
     * Mengembalikan pesan error atau null jika valid.
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

        // Aturan tambahan: tidak boleh ada spasi ganda.
        if (name.contains("  ")) {
            return "Nama tidak boleh menggunakan spasi ganda.";
        }

        // 4. Keunikan
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

        // Membersihkan spasi di awal dan akhir.
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

        // Membaca nilai spinner setelah commitEdit().
        int level;

        try {
            levelSpinner.commitEdit();
            level = (Integer) levelSpinner.getValue();

        } catch (ParseException ex) {
            showLevelError("Level harus berupa angka yang valid.");
            return;
        }

        // Validasi Model dilakukan melalui Controller dan Character.
        try {

            boolean success =
                    controller.addCharacter(name, type, level);

            if (success) {

                nameField.setText("");
                typeCombo.setSelectedIndex(0);
                levelSpinner.setValue(1);

                // Memperbarui daftar karakter.
                onSuccess.accept(null);

                nameField.requestFocusInWindow();

            } else {
                showNameError(
                        "Karakter gagal ditambahkan. Periksa kembali input.");
            }

        } catch (IllegalArgumentException ex) {

            // Pesan exception Model ditampilkan di form.
            showLevelError(ex.getMessage());
        }
    }
}
