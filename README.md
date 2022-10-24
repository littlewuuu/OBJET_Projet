# OBJET_Projet

**This project is the TP for the OBJET course in ECN 2022 S7.**

To mark different **_creature_** or **_object_** information on the world map **_OCCUPIED_**, we use the following markers: 

-1 : NuageToxique : Players in range of NuageToxique's attack are subject to a continuous attack.

1 : Joueur; 

2 : Fleche; 

3 : Epee; 

4 : PotionSoin : Can restore the player's life value

5 : Archer;

 6 : Geurrier; 

7 : Paysan; 

8 : Lapin; 

9 : Loup : There is a certain probability that it will attack the player.

10: Epinard : Can increase a player's attack value in certain rounds. 

0 : is not occupied;

This information is displayed on the game panel.

UML diagram: https://drive.google.com/file/d/104r5bmn8PTezpQTqheFJOeT6hGcsbT_8/view?usp=sharing

## 0. Game description

The game contains a total of creatures and items from the above categories. The system generates creatures and items randomly, each creature is a thread that enables automatic movement at regular intervals.

Players within a certain range of **NuageToxique** will take continuous damage.

Players within a certain range of **Loup** have a chance of receiving an attack

## 1. Player selection

1. The user first selects whether they want to start a new game or resume from an existing record (done at the terminal)

2. The user then selects the player type: Archer or Guerrier

    Archer can not pick up Epee, Archer initially has 10 fleche.

    Guerrier can not pick up Fleche.

    Both can pick up PotionSoin and Epinard.

3. The game ends when the player has less than 0 lives and is prompted to save the game and whether to restart it.

## 2. Operating Instructions

Keyboard up, down, left and right to control the movement of the player.

1. **P key**：Pick up the object directly in front of the player (the player has the **direction** attribute to determine if it is directly in front of the player).

2. **G key**：Use of medicines to restore ptVie.

3. **C key**：Attack, can only attack creatures directly in front of it (judged by the **direction** attribute), close combat or remote combat.

   Remote combat: Archer with Fleche; Guerrier with Epee.

   Each attack consumes one fleche, regardless of whether it hits or misses.

   Each attack with Epee reduces the life of the Epee.

4. **K key**：To test the use of PosionSoin, use the **K** key to make the player -10 life directly.

5. **L key**: Using Epinard.

## 3. Final result

<img width="1030" alt="Screenshot 2022-10-24 at 21 52 09" src="https://user-images.githubusercontent.com/95653923/197622685-d3b507db-8b63-46db-9a09-429859fe7d54.png">
