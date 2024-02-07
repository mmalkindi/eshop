# E-Shop
*Repositori Tugas Individu untuk mata kuliah Pemrograman Lanjut Semester Genap 2023/2024.*

```credential
Nama  : Muhammad Milian Alkindi
Kelas : AdPro-B (Reguler)
```

|               Jump ke tutorial               |
|:--------------------------------------------:|
| [Tutorial 01](#tutorial-01-coding-standards) |


## Tutorial 01: Coding Standards
### Reflection 1
Sejauh ini, sudah ada beberapa _coding standard_ yang saya terapkan untuk proyek ini, antara lain:
**Clean Code** dan **Git Flow**. Penamaan atribut, variabel dan fungsi, struktur data, serta penggunaan Git
sudah sesuai dengan standar yang dijelaskan di Modul 01. Walau demikian, ada function yang melanggar **Command Query Separation**
seperti function `create()` yang mengembalikan object `Product` walaupun tidak ada indikasi _return_ dari penamaan function.

Ada juga beberapa _coding standard_ lainnya yang terlewati seperti: _Error Handling_, _Secure Coding_, dan _Testing_.
**Secure Coding** belum diterapkan karena belum perlu ada autentikasi maupun otorisasi pengguna
untuk melakukan `CREATE`, `UPDATE`, maupun `DELETE` data ke sistem. 
Standar **Testing** akan diterapkan pada tahap kedua Tutorial ini.

### Reflection 2
**Answer 1:**
Setelah menulis unit test untuk eshop, saya mulai paham kepentingannya dalam software development.
Setiap method yang ada di suatu Class sebaiknya memiliki unit test yang relevan- baik itu getter, setter, constructor, 
atau method-method lainnya.
Untuk mengetahui apakah unit test yang dibuat sudah mencukupi atau belum, bisa dicek persentase _Code Coverage_.
Apabila masih kurang, maka masih ada yang bisa dites. Walaupun begitu, **100% Code Coverage** belum berarti program
terjamin _bug-free_, karena bisa saja ada kesalahan dalam _logic function_ atau tidak merangkap _unexpected behavior_.

**Answer 2:**
Test functional yang memverifikasi banyak item dalam list Product bisa termasuk ke test suite yang _redundant_, karena
`CreateProductFunctionalTest` sudah mengecek banyak item yang ditampilkan pada step terakhir.

---
[↑ Back to top](#e-shop)