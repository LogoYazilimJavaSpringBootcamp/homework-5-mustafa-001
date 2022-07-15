## **HOMEWORK-5**
1. Spring Profile nedir? Properties ya da yml dosya formları ile isbasi uygulamasına test
profile ekleyin.(5 Puan)

Spring Profile uygulamanın farklı versiyonları/aşamalarında çalışacak Bean'leri seçmemize yarayan teknolojidir.
Örneğin dev ve production profilleri belirleyerek geliştirme ve canlı aşamalarında farklı veritabanı konfigürasyonları kullanabiliriz. Yada testler sırasında farklı bir profile ait kodu kullanabiliriz.

`@Profile` anotasyonu, XML'de tanımlayarak bir bean'in hangi profile ait olduğunu belirtebiliriz. 
`context.setInıtParameter("spring.profile.active", "dev") metodu, web.xml, JVM parametresii, ortam değişkeni yada Maven ile o an aktif olan profili seçebiliriz. Örn:

```
@Profile("dev")
public class DataGenerator {
```
Burada DataGenerator sınıfı verilen profile göre çalışıp çalışmayacaktır. [DataGenerator](movies/src/main/java/mutlu/movies/config/DataGenerator.java)


movies/src/main/resources/application.properties

https://www.baeldung.com/spring-profiles


2. SQL injection örnekleyin. Nasıl korunabiliriz?(5 Puan)

SQL injection kullanıcı girdisine göre oluşturulan SQL komutlarının istenilen dışında veritabanına zarar verebilecek SQL komutlarının çalıştırılmasıdır. Örneğin `"SELECT * FROM users WHERE name = ?"` komutunda kullanıcıdan bir isim onu kullanmak üzere tasarlanmışken gelen bir `isim; DROP TABLE users;` komutu veritabanının users tablosunun silinmesine neden olur.

Korunmak için veritabanı kullanıcı ve yetki kontrolü,  Data Sanitization, `NamedTemplate`, SQL escaping gibi yöntemler kullanılabilir. Burada ana mantık kullanıcıdan gelen girdinin SQL'de komut olarak çalıştırlmasını engellemektdir.

https://www.baeldung.com/sql-injection

3. Aşağıdaki kurallara göre bir film öneri uygulaması yazın. (90 Puan)

### **Teknolojiler;**
* Min Java8
* Spring Boot
* Restfull
* MySQL - Postgre - Mongo(Her servis farklı database kullanabilir)
* RabbitMQ

### **Gereksinimler;**

* Kullanıcı sisteme kayıt olup, login olabilmelidir.(Login işlemi için email ve şifre bilgileri
gereklidir.)
* Kullanıcı şifresi istediğiniz bir hashing algoritmasıyla database kaydedilmelidir.
* Kullanıcılar sisteme film ekleyebilir ve bu filmler herkes tarafından görülebilir.
* Kullanıcı kendi eklediği filmleri görebilmeli.(Profil sayfası gibi düşünün)
* Kullanıcı şifresini ve ismini değiştirebilir.
* Ücretli üye olmayan kullanıcılar sadece 3 film ekleyebilir.
* Ücretli üye olmayan kullanıcılar filmlere yorum yapamaz.
* Sisteme yeni bir film girildiğinde kullanıcılara email gider.
* Sistemi takip edebilmek için gerekli gördüğünüz yerlere Log ekleyin.

### **Sistem Kabulleri;**

* Ödeme işlemi senkron gerçekleşmelidir.
* Ödeme servisi sadece ödeme bilgilerini kaydeder ve başarılı response döner.
* Email gönderme işlemi asenkron gerçekleşmelidir.
* Üyelikler 1-3-6-12 ay olarak alınabilir.
