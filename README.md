# Character Manager — TP3 (Java Visual SIH-6406)
1. Deskripsi Proyek

   Character Manager merupakan program berbasis Java yang digunakan untuk mengelola karakter dengan atribut dan perilaku        tertentu. Proyek ini menerapkan konsep pemrograman berorientasi objek, seperti class, encapsulation, constructor, dan        enum, untuk mengatur data serta perilaku karakter. Program dijalankan melalui terminal dan menampilkan informasi             karakter, termasuk status hidup melalui method isAlive().

2. Screenshot Output Terminal

   Screenshot berikut menunjukkan hasil eksekusi program Character Manager, termasuk informasi karakter dan hasil               pemanggilan method isAlive().
   <img width="640" height="359" alt="Screenshots output-terminal" src="https://github.com/user-attachments/assets/2100e768-488a-4828-afa6-868187485677" />

3. Struktur Folder Project
```text

CharacterManager/
├── out/
│   └── Main.class
├── Screenshot/
│   ├── Screenshots output-crud tp05.png
│   ├── Screenshots output-reference tp05.png
│   ├── Screenshots output-terminal.png
│   ├── Screenshots sebelum tp08.png
│   ├── Screenshots sebelum tp10.png
│   ├── Screenshots sesudah tp08.png
│   └── Screenshots sesudah tp10.png
│   ├── Screenshots Resize-Awal tp12.png
│   ├── Screenshots Resize-Diperbesar tp12.png
│   └── Screenshots Resize-Diperkecil tp12.png
├── src/
│   ├── Main.java
│   └── com/
│       └── unpri/
│           └── charactermanager/
│               ├── CharacterManager.java
│               ├── controller/
│               │   └── CharacterController.java
│               ├── model/
│               │   ├── Character.java
│               │   └── CharacterType.java
│               └── view/
│                   ├── AppTheme.java
│                   └── MainWindow.java
└── README.md


```
4. Informasi Mahasiswa
   
   Nama Lengkap: Ravita Marsanda

   NIM: 253303621209

   Mata Kuliah: Java Visual

   Link Google Docs TP03: [Buka Dokumen TP03](https://docs.google.com/document/d/1vhce2Y4HyO6x0SCRgWV_Nax_UEtUrbtKarFv6Gh-N2Q/edit?usp=sharing)

---

# Character Manager Sprint #2 — TP5 (Java Visual)
1. Deskripsi

   TP5 mengembangkan aplikasi Character Manager dengan fitur
   pengelolaan karakter, validasi nama duplikat, pencarian
   karakter, analisis level, dan pengujian object reference.

3. Fitur yang Diimplementasikan
    - `addCharacter()` — menambahkan karakter dan menolak nama duplikat.
    - `findByName()` — mencari karakter berdasarkan nama.
    - `removeCharacter()` — menghapus karakter berdasarkan nama.
    - `getTotalCharacters()` — menghitung jumlah karakter.
    - `getAllCharacters()` — mengembalikan salinan daftar karakter.
    - `getAverageLevel()` — menghitung rata-rata level karakter.
    - `findByType()` — mencari karakter berdasarkan tipe.
    - `findStrongest()` — mencari karakter dengan level tertinggi.

4. Bukti Output Program

   Output CRUD dan Analistik
   <img width="640" height="359" alt="Screenshots output-crud tp05" src="https://github.com/user-attachments/assets/46ff2cd4-5d1c-4a48-b960-2350332c47cd" />

   Eksperimen Object Reference
   <img width="640" height="359" alt="Screenshots output-reference tp05" src="https://github.com/user-attachments/assets/40cbaca4-a786-45db-a24f-8cd721b905a6" />

6. Analisis Object Reference
   Method `getAllCharacters()` pada class `CharacterManager` mengembalikan salinan daftar karakter menggunakan `new             ArrayList<>(characters)`. Tujuannya adalah agar perubahan pada struktur daftar salinan tidak memengaruhi daftar karakter     internal.

   Pengujian dilakukan dengan membuat salinan daftar karakter, kemudian mengosongkannya menggunakan method `clear()`. Hasil     pengujian menunjukkan bahwa daftar salinan berisi 0 karakter, sedangkan daftar asli tetap berisi 3 karakter.

   Hal ini membuktikan bahwa perubahan struktur pada daftar salinan tidak menghapus data dari daftar internal                   `CharacterManager`. Salinan tersebut termasuk *shallow copy*, karena daftar yang dibuat berbeda dari daftar asli, tetapi     objek `Character` di dalam kedua daftar masih dapat merujuk pada objek yang sama.

   Dengan demikian, penggunaan salinan daftar membantu menjaga enkapsulasi dan mencegah perubahan struktur daftar dari luar     secara langsung memengaruhi data internal.

5. Hasil Pengujian
    - Tiga karakter berhasil ditambahkan.
    - Penambahan nama Arjuna yang duplikat ditolak.
    - Karakter Merlin berhasil ditemukan.
    - Rata-rata level awal adalah 4.0.
    - Karakter dengan level tertinggi adalah Arjuna.
    - Penghapusan Athena berhasil.
    - Rata-rata level daftar kosong adalah 0.0.

6. Analisis dan Rancangan
   Dokumen analisis reference, primitive value, static,
   equals(), dan encapsulation dapat dibaca melalui tautan berikut:
   Link Google Docs TP05: [Buka Dokumen Analisis TP05](https://docs.google.com/document/d/1GzebjEp3sTkrF4PuDUfzQFZfKf8m9ZaMpIXgwOyeQ6A/edit?usp=sharing)



---

# Character Manager Sprint #3 — TP8 (Java Visual)
1. Deskripsi
   TP8 mengembangkan aplikasi Character Manager dari program berbasis terminal menjadi aplikasi GUI (Graphical User             Interface) menggunakan Java Swing. Aplikasi ini menerapkan konsep event-driven programming dan arsitektur Model-View-        Controller (MVC). Pengguna dapat melihat daftar karakter, menambahkan karakter baru, menghapus karakter, serta melihat       statistik jumlah karakter, rata-rata level, dan karakter dengan level tertinggi.

2. Screenshot MainWindow

   Sebelum Perubahan Data
   <img width="640" height="359" alt="Screenshots sebelum tp08" src="https://github.com/user-attachments/assets/3dd28771-3d7d-4338-9839-290e5360edf4" />

   Screenshot ini menunjukkan lima karakter awal, yaitu Arjuna, Merlin, Athena, Hector, dan Luna. Total karakter adalah 5,      rata-rata level 4.00, dan karakter dengan level tertinggi adalah Hector pada level 6.

   Sesudah Perubahan Data
   <img width="640" height="359" alt="Screenshots sesudah tp08" src="https://github.com/user-attachments/assets/3c80d693-aab0-4f49-b3e8-485688e0844e" />

   Setelah menambahkan Thor dengan tipe WARRIOR level 8 dan Ravita dengan tipe WARRIOR level 7, jumlah karakter menjadi 7.      Rata-rata level berubah menjadi 5.00, sedangkan Thor menjadi karakter dengan level tertinggi pada level 8.

   Perubahan tersebut membuktikan bahwa tabel dan statistik diperbarui secara dinamis ketika data karakter berubah.

3. Aliran Data dan Hubungannya dengan MVC
   Program dimulai dari `Main.java`, yang membuat objek `CharacterManager` dan menambahkan lima karakter awal. Selanjutnya,     `Main.java` membuat objek `CharacterController` dan mengirimkannya ke `MainWindow` untuk menampilkan antarmuka pengguna.

   `CharacterManager` bertanggung jawab mengelola kumpulan karakter, seperti menambah, mencari, menghapus, dan menghitung        statistik. `CharacterController` menjadi penghubung antara tampilan dan pengelolaan data dengan meneruskan permintaan        pengguna kepada `CharacterManager`.

   `MainWindow` berfungsi sebagai View yang menampilkan data dalam JTable, menyediakan tombol tambah dan hapus, serta            memperbarui statistik setelah perubahan data. Pemisahan tanggung jawab tersebut mencerminkan prinsip MVC, yaitu Model        mengelola data, View menampilkan antarmuka, dan Controller mengatur komunikasi antara keduanya.

4. Karakter Baru dan Alasan Pemilihan Tipe/Level

   Dua karakter tambahan yang digunakan dalam pengembangan TP8 adalah:

    - **Thor** — tipe WARRIOR, level 8. Karakter ini dipilih sebagai contoh karakter dengan level tinggi untuk membuktikan         bahwa statistik karakter terkuat diperbarui secara otomatis.
    - **Ravita** — tipe WARRIOR, level 7. Karakter ini ditambahkan untuk menambah variasi data dan membuktikan bahwa jumlah         karakter serta rata-rata level berubah setelah penambahan data.

    Kedua karakter tersebut digunakan untuk memperlihatkan bahwa aplikasi mampu menampilkan data baru tanpa perlu menuliskan     setiap baris karakter secara manual di tabel.

6. Penerapan Event-Driven Programming

   Aplikasi menggunakan `ActionListener` melalui lambda expression pada tombol Tambah dan Hapus Terpilih. Ketika pengguna       menekan tombol Tambah, program membaca nama, tipe, dan level dari komponen input, kemudian meminta Controller menambahkan    karakter.

   Ketika tombol Hapus Terpilih ditekan, aplikasi memeriksa baris yang dipilih dan meminta konfirmasi sebelum menghapus         karakter. Setelah operasi berhasil, tabel dan statistik diperbarui melalui method `refreshCharacters()`.

   Pendekatan ini berbeda dari program sekuensial berbasis terminal karena alur tindakan GUI ditentukan oleh interaksi          pengguna terhadap komponen antarmuka.

8. Penerapan MVC dan SwingUtilities

   `MainWindow` menggunakan `JFrame` sebagai jendela utama, `JTable` untuk menampilkan data karakter, serta `JLabel` untuk       menampilkan statistik. Data tabel diperbarui menggunakan `DefaultTableModel`, sehingga perubahan daftar karakter dapat       ditampilkan kembali tanpa membuat tabel baru.

    Pada `Main.java`, `SwingUtilities.invokeLater()` digunakan untuk menjalankan pembuatan dan penampilan GUI melalui Event      Dispatch Thread (EDT). Hal ini mengikuti praktik Swing agar pembuatan dan pembaruan komponen antarmuka dilakukan pada        thread yang sesuai.

10. Link Google Docs Bagian D

    Link Google Docs Analisis GUI & MVC TP8:                                                                                     [https://docs.google.com/document/d/1xaVNnBSSAwupSRytRFSCggkXEiObcNUqvgPgy6Qzllo/edit?usp=sharing]


---


# TP10 — Theming, Properti Komponen & Analisis MVC

1. Screenshot Sebelum dan Sesudah
   
   **Sebelum (v0.3)**
   
   <img width="640" height="359" alt="Screenshots sebelum tp10" src="https://github.com/user-attachments/assets/64cceff6-b44d-4283-84c3-72fa49a14ee2" />

   **Sesudah (v0.4)**
   
   <img width="640" height="359" alt="Screenshots sesudah tp10" src="https://github.com/user-attachments/assets/3e0ce49d-ed99-4c9d-8b36-72a048d58b25" />

3. Pemisahan Warna pada AppTheme dan Character
   Warna tampilan aplikasi disimpan di kelas `AppTheme` yang berada pada package `view` agar pengaturan visual terpusat dan     konsisten. Kelas `Character` pada package `model` bertanggung jawab menyimpan data karakter, seperti nama, role, level,      HP, dan Attack, bukan mengatur warna tampilannya. Pemisahan ini membuat perubahan tema tidak mengharuskan perubahan pada     model karakter. Pendekatan tersebut juga mendukung pola MVC dan mempermudah pemeliharaan aplikasi.

4. Dua Properti Tambahan

   **Tooltip HP/Attack:** menampilkan informasi HP dan Attack ketika kursor diarahkan ke kartu karakter. Tujuannya agar         pengguna dapat melihat detail karakter dengan mudah tanpa memenuhi tampilan kartu.
   **Border berdasarkan role:** garis tepi kartu mengikuti warna role karakter. Tujuannya agar perbedaan role lebih mudah       dikenali dan tampilan antarkartu lebih konsisten.

5. Google Docs Bagian D

   **Tautan analisis:** [https://docs.google.com/document/d/1MfQJ8VF39tAgRKz8frrUosh0cDtyTOBKGv4JTDBQ_AA/edit?usp=sharing]


---


# TP12 — Analisis Layout & MVC
**A. Implementasi Layout**

Pada TP12, aplikasi Character Manager dikembangkan menjadi versi v0.5 dengan menerapkan Layout Manager pada Java Swing. Aplikasi menggunakan BorderLayout sebagai layout utama dengan empat wilayah yang terisi, yaitu NORTH, CENTER, EAST, dan SOUTH.

Implementasi layout pada aplikasi adalah sebagai berikut:
   - NORTH: Menampilkan header aplikasi dan panel statistik menggunakan GridLayout 2x2.
   - CENTER: Menampilkan daftar karakter yang dapat di-scroll menggunakan JScrollPane.
   - EAST: Menampilkan legenda role karakter menggunakan BoxLayout.Y_AXIS.
   - SOUTH: Menampilkan status aplikasi di sebelah kiri dan versi v0.5 di sebelah kanan.

Daftar karakter berada di dalam JPanel yang menggunakan BoxLayout.Y_AXIS. Panel tersebut dimasukkan ke dalam JScrollPane agar pengguna dapat melihat seluruh kartu karakter meskipun jumlah karakter bertambah.

Legenda role diturunkan dari CharacterType.values() sehingga seluruh role yang tersedia dapat ditampilkan secara otomatis. Warna dan font menggunakan konfigurasi dari AppTheme.

Pada implementasi ini tidak digunakan setLayout(null) maupun pengaturan posisi manual menggunakan setBounds() pada MainWindow. Package model tetap tidak berubah.

**B. Perluasan & Mini-Challenge**

Pada TP12, aplikasi Character Manager ditambahkan beberapa fitur untuk meningkatkan tampilan dan informasi aplikasi.

Fitur yang ditambahkan meliputi:
   - Status bar dengan teks "Character Manager siap digunakan" di sebelah kiri dan versi "v0.5" di sebelah kanan.
   - Minimal delapan karakter dengan variasi level dan role.
   - Panel statistik tambahan menggunakan GridLayout 2x2.
   - Informasi total karakter, rata-rata level, karakter terkuat, dan jumlah role.
   - Legenda role yang menampilkan WARRIOR, MAGE, ARCHER, dan HEALER.
   - Daftar karakter menggunakan JScrollPane dengan scrollbar vertikal.

Panel statistik dipilih karena dapat menyajikan empat informasi penting secara teratur dalam dua baris dan dua kolom. Layout ini membuat informasi lebih mudah dibaca tanpa mengganggu daftar karakter utama.

**C. Dokumentasi README**
1. Wireframe Struktur Layout

   Berikut adalah wireframe struktur panel pada aplikasi Character Manager v0.5.

   <img width="3374" height="2174" alt="wireframe" src="https://github.com/user-attachments/assets/e37bbdb3-d5f5-40c8-bca0-833aacfc2a35" />

   
2. Screenshot Pengamatan Resize

   Screenshot 1 — Ukuran Awal
   
   <img width="640" height="359" alt="Screenshots Resize-Awal tp12" src="https://github.com/user-attachments/assets/b0ddb6fa-9b3c-414f-b630-c4452579386d" />

   
   Pada ukuran awal, aplikasi menampilkan judul Character Manager v0.5, statistik karakter, panel input, daftar karakter,
   legenda role, dan status bar. Daftar karakter menggunakan scrollbar vertikal karena seluruh kartu karakter belum dapat
   ditampilkan sekaligus.

   Screenshot 2 — Jendela Diperbesar (Maximize)
   
   <img width="640" height="359" alt="Screenshots Resize-Diperbesar tp12" src="https://github.com/user-attachments/assets/feb267dd-bcfe-4101-9029-c8d1cfbddb77" />


   Ketika jendela diperbesar, ruang tampilan aplikasi menjadi lebih luas. Daftar karakter terlihat lebih banyak dibandingkan
   ukuran awal. Panel statistik, legenda role, dan status bar tetap berada pada posisi masing-masing. Scrollbar vertikal
   masih terlihat karena jumlah kartu karakter melebihi ruang yang tersedia.

   Screenshot 3 — Jendela Diperkecil

   <img width="640" height="359" alt="Screenshots Resize-Diperkecil tp12" src="https://github.com/user-attachments/assets/932161c5-cd77-49aa-82be-3ef0ed89b371" />


   Ketika jendela diperkecil, ruang daftar karakter menjadi lebih terbatas. Beberapa kartu karakter tidak dapat ditampilkan
   secara bersamaan. Scrollbar vertikal tetap tersedia di sisi kanan daftar karakter sehingga pengguna dapat melihat
   karakter lainnya dengan melakukan scrolling.

4. Tabel Pengamatan Resize

   <img width="1536" height="1024" alt="Tabel Pengamatan Resize" src="https://github.com/user-attachments/assets/42cdce09-142b-4585-90a9-767c1ace95c4" />


5. Penjelasan Struktur Panel

   Aplikasi Character Manager v0.5 menggunakan BorderLayout sebagai layout utama untuk membagi jendela menjadi beberapa
   wilayah. Wilayah NORTH digunakan untuk header dan panel statistik, CENTER digunakan untuk daftar karakter, EAST digunakan
   untuk legenda role, dan SOUTH digunakan untuk status bar. Daftar karakter disusun menggunakan BoxLayout.Y_AXIS dan
   ditempatkan di dalam JScrollPane agar pengguna dapat melihat karakter lainnya melalui scrolling. Struktur panel ini
   membuat tampilan aplikasi lebih rapi dan dapat menyesuaikan perubahan ukuran jendela.

6. Alasan Panel Tambahan dan Layout yang Ditolak

   Panel tambahan yang dibuat pada TP12 adalah panel statistik menggunakan GridLayout 2x2. Layout ini dipilih karena dapat
   menyusun empat informasi statistik secara teratur dalam dua baris dan dua kolom. Informasi yang ditampilkan meliputi
   total karakter, rata-rata level, karakter terkuat, dan jumlah role. Panel statistik diletakkan pada bagian NORTH di bawah
   judul aplikasi agar informasi utama mudah dilihat oleh pengguna.

   BoxLayout.Y_AXIS dipilih untuk menyusun kartu karakter dan legenda role secara vertikal. FlowLayout digunakan pada panel
   input agar komponen input dan tombol tersusun secara horizontal.

   Layout yang ditolak adalah setLayout(null) karena posisi dan ukuran komponen harus ditentukan secara manual menggunakan
   setBounds(). Hal ini dapat menyebabkan tampilan tidak rapi ketika ukuran jendela berubah. GridLayout juga tidak dipilih
   untuk daftar karakter utama karena setiap kartu lebih sesuai disusun secara vertikal dan ditampilkan melalui scrolling.

7. Link Google Docs Bagian D

   Dokumen Google Docs Analisis Layout & MVC:

   [https://docs.google.com/document/d/18mFo_vSmwv3Lg73tkWLc6AzVvI-_XeOR_VmplPwLcBY/edit?usp=sharing]


