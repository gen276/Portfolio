# 開発の目的
１、ポートフォリオ(2022年12月～2023年2月開発)内容のブラッシュアップ

２、フレームワーク(SpringFramework)の活用練習,基本・詳細設計書から本格的に開発する練習

# 制作背景

何かを達成しようとした時,初心や当初歩みたいと感じていたステップを見失いがちだと感じた。

改善策は以下の二点です。

**・自身の設定した目標を常に確認しながら作業に取り組む**

**・作業への取り組むビジョンを明確にする,ステップ毎に切り分けて作業を行う**


以上の作業について、以前はノートで管理をしていました。

自分自身が半年間無職の期間中に取り組んできた方法なので将来的に誰かのお役にも立てるようなアプリを開発できれば良いなと感じております。

現在は設計段階(2023年10月28日現在)で学習から開始ですがコツコツ取り組んでいきたいと思います。

# 開発目標スケジュール(目標:2024年3月完成)
・ポートフォリオ弱点分析,設計書参考書(現場で役立つシステム設計の原則)を確認しつつ改善箇所確認(～6/27)

・springframework学習(SpringFramework超入門),設計書イメージ作成(平日)(～7/15) 済

・基本,詳細設計書(DB設計,オブジェクト図)作成(～7/30)
※Javasilver学習の為中断

→新規目標:～11/10

・PostgreSQL内テーブル変更(～11/10)

・フロント部分内容修正(～8/15)
※Javasilver学習の為中断
→新規目標:設計書作成後適宜作成

・内部処理部分修正(～8/30)
※Javasilver学習の為中断
→新規目標:～1/31

# 使用技術

・SpringFramework

・javascript

・HTML,CSS

・PostgreSQL 42.5.0

# 開発環境

・openjdk-11.0.15

・Tomcat 10.0.23

・Eclipse

・VScode(フロント部分実装時)

# 機能一覧

〇登録,ログイン機能

・ユーザー新規登録機能

・ユーザーログイン

〇目標・ステップ登録

・長期目標・ステップ設定,期日の設定

・目標達成の為のステップ登録(5件まで)

〇達成状況確認
・ステータス(達成,実行中,未着手)の選択

〇履歴確認
・目標一覧作成機能(最新データから10件抽出,登録日と目標・ステップ内容・達成状況の確認が可能)

# 全体の流れ
１、新規登録(もしくはログイン)

２、設定目標確認

3、学習の開始

4、質問や疑問点が発生した場合はメモ機能に登録

# 工夫したい事

**○SpringFrameworkを用いた実装**

現場で良く使用されるSpringFrameworkを実際に使用してみる

**○基本設計,詳細設計から作り込む**

データベース構成,処理機能,フロント画面の実装状況全てにおいてリニューアルした内容に更新したい

# 作業記録

10/22(日) DB設計書作成(テーブル定義書,ER図)

次予定:10/28(土)PostgreSQL内容変更(DB設計書の内容を基に作成)

10/28(土) PostgreSQL内容変更(DB設計書の内容を基に作成)

結果:完了

次予定:10/29(日)オブジェクト図_機能概要作成(全体の構成と内容を意識して作成)

結果:12機能中5機能完了

次予定:11/4(土)オブジェクト図_機能概要作成(続き作成)

結果:11/5(日)新規登録,登録確認画面作成(新規登録画面完成)

次予定:11/11(土)登録確認,登録完了画面作成(続き作成)

結果: