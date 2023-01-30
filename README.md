# 独学支援(TODOリスト作成)アプリ

ID認証→ToDoリスト作成→学習の開始

※学習時間計測・疑問点メモ部分作成中

# 開発の目的
１、Javaの基本文法・JSP/サーブレット・CRUD連携・MVCモデルの理解度確認,理解の定着

２、独学支援のアイテムとして完成後使えるものになるように、便利な機能を盛り込んだシステムを懸案しつつ開発中

# 制作背景

前職退職後の独学時、モチベーション維持の難しさを感じました。

改善策は以下の二点です。

**・作業内容の記録と今後のステップを日々確認しつつ学習を行い、ステップが完了した後にマークを付ける**

**・時間を計測してどれくらい自分が過去に学習を頑張ったのかを確認できるようにする**

**※現在はまだ上記の機能はシステムに実装出来ておりません**


以上の作業について、以前はノートで管理をしていました。

ただし、今後行いたい作業が増えた時の管理の難しさを懸案し、システムで管理をしたいと考えるようになりました。

学習の一環の意味も込めてシステムの開発を開始しました。

# 使用技術

・Servlet

・JSP

・javascript

・HTML,CSS

・PostgreSQL 42.5.0

※Javaの仕組みや理解を深めるために、フレームワークは使わずに実装しました。

# 開発環境

・openjdk-11.0.15

・Tomcat 10.0.23

・Eclipse

・VScode(フロント部分実装時)

# 機能一覧

〇基本機能

・ユーザー新規登録機能

・ユーザーログイン機能

〇目標登録機能

・月目標,週目標,ステップ登録・更新機能

・目標表示内容変更機能

・目標達成状況確認機能(ボタン押下)

・目標一覧作成機能(最新データから10件抽出,登録日と目標・ステップ内容・達成状況の確認が可能)

# 全体の流れ
１、新規登録(もしくはログイン)

２、設定目標確認(日曜日を起点とする週ごとに変更)

※未設定の場合『未設定です』と表示される

3、学習の開始

4、質問や疑問点が発生した場合はメモ機能に登録

**※現在は2まで実装中です**

# 工夫した事

**○フレームワークを使わずに開発**

Java自体の言語理解を深めるためにフレームワークを使わずに開発しました。

フレームワークを使えば簡単に実装できた部分もあったかもしれませんが、基本部分の理解を深めたいと考えましたので、

言語だけで実装するということにこだわって開発しました。


**○DAOや類似機能をまとめる事によるコードの可読性向上**

コードの可読性を向上させる為に類似機能をまとめる工夫をしました


**○設計書の作成と確認しながらの開発**

開発にあたり設計書を作成し、その通りに実装できるように工夫をしました。

# 今後追加したい機能

・学習開始時間と終了時間の記録

・質問メモの作成と表示機能

・学習時間のグラフ化

・パスワードのハッシュ化 など
