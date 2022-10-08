# OBJET_Projet

**该项目是 ECN 2022 S7 学年 OBJET 课程的 TP**

To mark different **_creature_** or **_objet_** information on the **_OCCUPIED_**, we use the following markers

-1 : NuageToxique；1 : Joueur；2 : Fleche；3 : Epee；4 : PotionSoin；

5 : Archer；6 : Geurrier；7 : Paysan；8 : Lapin；9 : Loup

该信息显示在游戏面板上


UML diagram: https://drive.google.com/file/d/104r5bmn8PTezpQTqheFJOeT6hGcsbT_8/view?usp=sharing
## 0. 游戏说明
游戏中一共包含以上几类的生物和物品，系统随机生成生物和物品，每个生物是一个线程，实现每隔一段时间自动移动。

在 run() 方法中更改时间。


## 1. 玩家选择
用户首先选择玩家类型: Archer or Guerrier

Archer 不能捡起 Epee

Guerrier 不能捡起 Fleche

两者都可以捡起 PotionSoin

## 2. 操作说明
键盘上下左右控制人物的移动。

1. P 键：捡起玩家正前方的物品（玩家有 direction 属性判断是否在玩家正前方）。

2. G 键：使用药品恢复。

3. C 键：攻击，只能攻击正前方的生物（direction 属性判断），有近战和远战

    远战：Archer 使用Fleche；Guerrier 使用 Epee

4. K 键：为了测试药品使用，可以使用 k 键直接使玩家生命-10。

## 3. 待完善
1. NuageToxique 需要实时判断在其攻击范围内是否有玩家，若有，实施攻击。
2. Loup 等其他生物会有攻击功能，可以给他们添加 direction 表示他们的方向，只能攻击正前方的玩家。
3. Nnourriture 类。
4. 各种生物和物品的显示可以有不同的外观。
5. 地图尺寸
6. 注释
7. 使用按钮来进行角色选择

## 4. 效果

<img width="1047" alt="Screenshot 2022-10-08 at 17 50 59" src="https://user-images.githubusercontent.com/95653923/194716114-78b1f22d-e30b-44be-b4c2-86c036bed395.png">

