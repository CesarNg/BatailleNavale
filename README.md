# Devoir : Bataille navale
## Fonctionnement
Notre implémentation du jeu *(mode console)* couvre toute les **fonctionnalités** du jeu classique et se déroule selon ce *workflow* : 

1. Le jeu demande aux joueurs de positionner leurs bateaux en choisissant des coordonnées *(x,y)*; et ce pour chacun des bateaux à savoir :
   - Porte avion
   - Croiseur
   - Torpilleur
   - Contre torpilleur
   - Sous-marin
2. Le jeu se déroule ensuite en **tours**, où chaque joueur est invité à choisir des coordonnées *(x,y)* d'attaque. 
Si le joueur rate le bateau adverse, le joueur adverse peut avant d'attaquer lui aussi, choisir de **déplacer** ou non un de ses bateaux.
3. Le jeu boucle ainsi jusqu'à ce qu'un des deux joueurs n'a plus de bateaux.

## Organisation du projet
Le projet est organisé en différents packages, à savoir : 
- main : point de départ du programme
- game : contient tout le business pour gérer les comportements et le déroulement jeu
- logic : est la partie qui gère la vérification et la logique du jeu
- exception : pour la partie création d'exception spécifique
- tests : contient des cas de tests

Notes : 
Les tests ont été réalisés avec [JUnit 4.12](https://github.com/junit-team/junit4/releases).
