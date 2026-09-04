# Pokemon CRUD

以 Spring Boot 打造的極簡寶可夢管理系統，展示完整的後端與資料庫互動閉環（Create / Read / Update / Delete）。

## 功能

- **寶可夢圖鑑瀏覽（唯讀）**：資料來源為 [pokemon-crawler](https://github.com/f2855631/pokemon-crawler) 爬蟲產出的官方寶可夢資料，應用程式啟動時自動抓取並匯入資料庫作為種子資料。
- **收服紀錄 CRUD**：使用者可以從圖鑑中選擇一隻寶可夢標記為「已收服」，並新增暱稱、收服日期；收服清單可以查看、編輯、刪除（放生）。

## 技術棧

- Java 17+ / Spring Boot 4
- Spring Data JPA + Hibernate
- H2（預設，內嵌檔案式資料庫）／MySQL（可切換，見下方說明）
- Thymeleaf
- Lombok
- Maven

## 資料模型

```
Pokemon (圖鑑，唯讀)
   └──< CaughtPokemon (使用者收服紀錄)
         - nickname    暱稱
         - caughtDate  收服日期
```

一隻圖鑑寶可夢可以對應多筆收服紀錄（多對一關聯）。

## 專案結構

```
entity/       Pokemon、CaughtPokemon
repository/   Spring Data JPA repositories
service/      CRUD 業務邏輯、GitHub JSON 種子資料匯入
controller/   路由
templates/    Thymeleaf 頁面(pokemon/、caught/)
```

## 啟動方式

```
./mvnw spring-boot:run
```

預設使用內嵌 H2 資料庫，資料檔存放在 `./data/pokemon`（重開機資料仍會保留）。啟動後：

- 應用程式：http://localhost:8080
- H2 Console：http://localhost:8080/h2-console（JDBC URL: `jdbc:h2:file:./data/pokemon`，帳號 `sa`，密碼留空）

第一次啟動時，若資料庫是空的，會自動從 GitHub 抓取寶可夢圖鑑資料匯入。

## 切換為 MySQL

編輯 `src/main/resources/application.properties`，將 datasource 設定換成：

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pokemon
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=你的帳號
spring.datasource.password=你的密碼
```

其餘 Entity / Repository / Service / Controller 都不需要修改。

## 路由

| 路由 | 說明 |
|------|------|
| `GET /pokemons` | 瀏覽寶可夢圖鑑（唯讀） |
| `GET /caught` | 查看我收服的寶可夢清單 |
| `GET /caught/new` | 新增收服紀錄表單 |
| `POST /caught` | 新增收服紀錄 |
| `GET /caught/{id}/edit` | 編輯收服紀錄表單 |
| `POST /caught/{id}` | 更新收服紀錄 |
| `POST /caught/{id}/delete` | 刪除(放生)收服紀錄 |
