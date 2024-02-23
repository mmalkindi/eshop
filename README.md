# E-Shop
*Repositori Tugas Individu untuk mata kuliah Pemrograman Lanjut Semester Genap 2023/2024.*

```credential
Nama  : Muhammad Milian Alkindi
Kelas : AdPro-B (Reguler)
```

|                    Jump ke tutorial                    |
|:------------------------------------------------------:|
|        [Module 01](#module-01-coding-standards)        |
|          [Module 02](#module-02-cicd--devops)          |
| [Module 03](#module-03-maintainability--oo-principles) |

## Module 03: Maintainability & OO Principles
### Reflection
> Explain what principles you apply to your project!

Saya telah mengaplikasikan _SOLID principles_ di project ini, yaitu:

#### 1. Single Responsibility Principle
Memecah module sehingga tiap file hanya memiliki 1 class yang bertanggung jawab atas suatu feature/behavior. Tiap function
yang ada juga hanya memiliki satu tugas sehingga lebih mudah untuk ditest, maintain, juga lebih terorganisir.
Di exercise ini, saya memisahkan kelas CarController dari `ProductController.java` ke file `CarController.java` dan juga
menghilangkan _return type_ dari function yang tidak memerlukannya (`create`, `update`, `delete`). 
Perubahan-perubahan tersebut adalah contoh implementasi SRP di project ini.

#### 2. Open-Closed Principle
Module yang ada harus dapat diekstensi (menambah fitur) namun tidak untuk diubah/modifikasi (kecuali untuk bug fix).
Project ini sudah menerapkan OCP karena tiap module hanya menambahkan fitur dari _parentnya_ dan tidak memodifikasi fitur
yang di*inherit*.

#### 3. Liskov Substitution Principle
Apabila class A merupakan subclass dari class B, maka semua function/method dari class B dapat dipanggil melalui object class A
dan hasilnya ekivalen dengan apabila dipanggil melalui object class B. Di project ini, LSP sudah diterapkan
(partially because there's no inheritance yet).

#### 4. Interface Segregation Principle
Interface besar dipecah sehingga hanya memiliki spesifikasi function/method yang paling simpel saja.
Ini dilakukan supaya _class_ yang akan mengimplementasi interface tersebut tidak terbebani dengan function/method yang tidak penting.
Contoh di proyek ini, interface `CarService` hanya memiliki method yang berhubungan dengan `CarRepository` sehingga
`CarServiceImpl` hanya perlu mengimplementasi method-method yang relevan.

#### 5. Dependency Inversion Principle
Module dari package yang berbeda menggunakan _abstract_ class/interface dan bukan implementasinya.
...

> Explain the advantages of applying SOLID principles to your project with examples

Uhh

> Explain the disadvantages of not applying SOLID principles to your project with examples

Uhh

## Module 02: CI/CD & DevOps
### Reflection
> List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

Saya mengubah method-method CRUD di repository dan service supaya tidak return type yang tidak diperlukan.
Ini termasuk improvement dari sisi Clean Code.
Dari segi Code Quality improvement yang terdeteksi oleh Sonarcloud, saya memperbaiki penggunaan type specification
yang seharusnya tidak diperlukan.
Saya juga menambahkan unit tests untuk ProductService.


> Look at your CI/CD workflows (GitHub)/pipelines (GitLab). 
> Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? 
> Explain the reasons!

Menurut saya, CI/CD workflow yang ada di repo GitHub saya sudah mengikuti definisinya.
Workflow Continuous Integration seperti `sonarcloud.yml` dan `ci.yml` akan men-_trigger_ code analysis menggunakan gradle.
Sementara itu, workflow Continuous Development (`deploy.yml`) akan men-_trigger_ redeploy apps/service ke koyeb.
Di Koyeb dan Sonarcloud, terjadi Code Analysis dan Deployment sendiri yang terpisah dengan GitHub Actions.

## Module 01: Coding Standards
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