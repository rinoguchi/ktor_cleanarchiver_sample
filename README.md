# Ktorでクリーンアーキテクチャを試してみる

このプロジェクトでは、Ktorをつかってクリーンアーキテクチャを試してみています。
以下のブログで詳細を記載しています。  
https://rinoguchi.hatenablog.com/entry/2020/04/24/100000

## 作るもの
メモを編集するためのAPI群

## URL
* 追加: POST http://localhost8080/memos/new
* 取得: GET http://localhost8080/memos/{id}
* 変更: PUT http://localhost8080/memos/{id}
* 削除: DELETE http://localhost8080/memos/{id}
* 一覧: GET http://localhost8080/memos/list 

# 開発環境構築

* git clone
    ```sh
    git clone git@github.com:rinoguchi/ktor_cleanarchiver_sample.git
    cd ktor_cleanarchiver_sample 
    ```

* Ideaでプロジェクトを開く

* Refresh All Gradle Project

* PostgreSQLを起動
    ```sh
    docker-compose up -d
    ```

* DBマイグレート
    ```sh
    ./gradlew flywayMigrate
    ```

* アプリ起動
    ```sh
    ./gradlew run
    ```


