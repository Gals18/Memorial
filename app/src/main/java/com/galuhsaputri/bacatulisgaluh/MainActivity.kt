package com.galuhsaputri.bacatulisgaluh
//kode diatas merupakan nama paket

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.galuhsaputri.bacatulisgaluh.databinding.ActivityMainBinding
import com.galuhsaputri.bacatulisgaluh.model.InternalFileRepository
import com.galuhsaputri.bacatulisgaluh.model.Note
import com.galuhsaputri.bacatulisgaluh.model.NoteRepository

//Setelah Anda mengimpor kelas dari paket yang berbeda,
// Anda akan dapat merujuk ke kelas itu tanpa menentukan jalur yang sepenuhnya memenuhi syarat untuk kelas tertentu.
// Jadi, setelah Anda menambahkan pernyataan 'impor android.app.Activity;' di bagian atas file,
// Anda akan dapat memperoleh kelas Anda dari Aktivitas seperti 'Snake kelas publik memperluas Aktivitas'
// tanpa harus mengatakan 'snake kelas publik memperluas android.app.Activity'.

class MainActivity : AppCompatActivity() { //merupakan kelas utama yang nantinya menjalankan berbagai fungsi dari kelas lain
    private val repo: NoteRepository by lazy { InternalFileRepository(this) }
    //kode diatas merupakan deklarasi suatu properti yang digunakan untuk menyimpan data kedalam repositori.
    private lateinit var binding: ActivityMainBinding
    //kode diatas merupakan method untuk binding. Sebelumnya,
    // View Binding adalah fitur yang dapat membantu kita agar lebih mudah untuk berinteraksi dengan
    // View yang ada pada Layout XML kita (contohnya findViewById, Kotlin Synthetic, dan lain-lain).

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    //koed diatas merupakan method overide. Override atau
        // overriding merupakan salah satu fungsi dan juga method dari sebuah super class
        // atau juga disebut dengan kelas induk yang telah ditulis ulang pada subclass lainnya
        // atau disebut dengan kelas anak, terdapat beberapa
        // aturan dalam penggunaan fungsi overriding

        binding.btnWrite.setOnClickListener {
            if (binding.editFileName.text.isNotEmpty()) {
                try {
                    repo.addNote(
                        Note(
                            binding.editFileName.text.toString(),
                            binding.editTeksCatatan.text.toString()
                        )
                    )
                } catch (e: Exception) {
                    Toast.makeText(this, "File Write Failed", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
                binding.editFileName.text.clear()
                binding.editTeksCatatan.text.clear()
            } else {
                Toast.makeText(this, "Please provide a Filename", Toast.LENGTH_LONG).show()
            }
        }
    //kode diatas merupakan kode eksekusi tombol klik mulai. dimana sebelum kita mengisikan data kita perl klik mulai dan
        //dan untuk menyimpan data yang telah dimasukan. kemudian setelah klik menyimpan maka kolom akan di reset kembali
        //sehingga bekas masukan user langsung terhapus. hal ini dipengaruhi oleh fungsi clear()

        binding.btnRead.setOnClickListener {
            if (binding.editFileName.text.isNotEmpty()) {
                try {
                    val note = repo.getNote(binding.editFileName.text.toString())
                    binding.editTeksCatatan.setText(note.noteText)
                } catch (e: Exception) {
                    Toast.makeText(this, "File Read Failed", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(this, "Please provide a Filename", Toast.LENGTH_LONG).show()
            }
        }
    //kode diatas merupakan kode untuk menjalankan tombol read atau tombol baca.
    //dalam tombol ini bekerja apabila nama file telah dicantumkan atau editfilename tidak kosong
    //kemudian akan dipanggil dengan fungsi get. namun jika kita tidak memasukan nama file namun tetap dijalankan maka akan menampilkan
    //pesan tolong masukan nama file.
    //namun apabila dijalankan menampilkan file yang dibaca salah, maka file tersebut tidak ada.

        binding.btnDelete.setOnClickListener {
            if (binding.editFileName.text.isNotEmpty()) {
                try {
                    if (repo.deleteNote(binding.editFileName.text.toString())) {
                        Toast.makeText(this, "File Deleted", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "File Could Not Be Deleted", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "File Delete Failed", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
                binding.editFileName.text.clear()
                binding.editTeksCatatan.text.clear()
            } else {
                Toast.makeText(this, "Please provide a Filename", Toast.LENGTH_LONG).show()
            }
 //pada method diatas merupakan method hasil dari action tombol delete
 //pada method ini akan dijalankan ketika tombol tersebut diklik maka akan menghapus data apabila datanya telah masuk
// apabila datanya tidak ada maka tidak akan berjalan dan menampilkan pesan masukan nama file.
        }
    }
}