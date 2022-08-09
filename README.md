# HW 0805 part2-fix

由於git出問題，所以我建立新的 repo 重新推上去

# 這個專案需要串資料庫執行！
## 請在本地建立 DB 為 applestore
```
CREATE DATABASE  applestore
```
# 請使用 API Tester 測試 API

連結：
https://chrome.google.com/webstore/detail/talend-api-tester-free-ed/aejoelaoggembcahagimdiliamlcdmfm?hl=zh-TW

加入擴充功能，並打開 API Tester
左下角 Import AppleStore.json 就能 Call API

![](https://i.imgur.com/tdC11GI.png)

* DB : MySql
```
<dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
            <version>8.0.28</version>
</dependency>
```
* application.properties設定
```
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/cafe?serverTimezone=Asia/Taipei&characterEncoding=utf-8
spring.datasource.username="Your DB name"
spring.datasource.password="Your DB password"

spring.jackson.time-zone=GMT+8
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
```
* API : JDBC

## 請先在本地的資料庫建立兩個 TABLE

```=mysql
CREATE TABLE meal
(
    meal_id         INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    meal_name       VARCHAR(128) NOT NULL,
    price              INT          NOT NULL,
    description        VARCHAR(1024),
    created_date       TIMESTAMP    NOT NULL,
    last_modified_date TIMESTAMP    NOT NULL
);


INSERT INTO meal (meal_name, price, description, created_date, last_modified_date) VALUES ('MacBook Air M2', 37800, '丐版神機', '2022-03-01 02:41:28', '2022-03-01 02:41:32');
```

```=mysql
CREATE TABLE  user
(
    user_id            INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email              VARCHAR(256) NOT NULL UNIQUE KEY,
    password           VARCHAR(256) NOT NULL,
    phone_number       INT(128)      NOT NULL,
    created_date       TIMESTAMP    NOT NULL,
    last_modified_date TIMESTAMP    NOT NULL
)
```

## API流程

### getAllMeal 確認名額

GET http://localhost:8080/meals

### register 註冊帳號

POST http://localhost:8080/user/register

```json=
{

  "email":"howerd871024@gmail.com",
  "password": "123456",
  "phoneNumber":"0905976832"

}
```

### updatePhoneNumber 修改手機號碼

PUT http://localhost:8080/user/1

```json=
{

  "email":"howerd871024@gmail.com",
  "password": "123456",
  "phoneNumber":"0911588996"

}
```
### deleteMeal 刪除商品
DELETE http://localhost:8080/meal/1

## PS
* 此專案我誤會意思了，所以有往 Order Meal 的方向構思，因此我就改了一些地方，並增加一些功能。

* 所以這專案，不只上述這些 API，仍然有商品的 CRUD、使用者 login, register，並且刪除了單純只有 Order 的功能

* 如果還有時間可以重寫，我的設計方向會修改成：

    1. Meal 改成 Product，並新增 Quota 名額的欄位，給予判斷式，如果名額為0，則事件處理，跳出訊息：「沒有名額」。
    2. 新增 Order 與 Order Item ，新增 Order 的 CRUD 功能並將  Order Item 裡面放入所有的 Products 去計算所有商品的金額 = OrderTotal。
    3. 新增 UserName 的欄位與功能

* Thanks for codeReview
