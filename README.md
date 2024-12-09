# Minecraft Survival Plugin - Système de Classe DXp & Shop

## Description
Ce plugin **Minecraft Survival** permet d'ajouter un **système de classes ** (points d'expérience de classe) et un **shop** dans votre serveur Minecraft. Les joueurs peuvent choisir des classes avec des capacités spéciales, gagner des points d'expérience de classe en accomplissant des actions et utiliser un **shop** pour acheter des objets et des améliorations uniques. De plus, ce plugin ajoute des fonctionnalités de gestion de maisons, de commerce, d'enchères, et de gestion des chunks.

---

## Fonctionnalités
- **Système de Classes ** :
  - Les joueurs peuvent choisir une classe (Guerrier, Mage, Archer, Mineur).
  - Chaque classe a des compétences et des capacités uniques.
  - Les joueurs gagnent des **DXp** (points d'expérience de classe) en accomplissant des actions spécifiques et en progressant dans le jeu.
  - L'utilisation des **DXp** permet d'améliorer les compétences de la classe choisie.

- **Shop** :
  - Un shop intégré permet aux joueurs d'acheter des objets spéciaux, des équipements et des améliorations pour leurs classes.
  - Le shop utilise les **DXp** comme moyen d'achat ou la monnaie en jeu.
  - Possibilité de personnaliser les items du shop via des fichiers de configuration.

- **Commandes de Maison** :
  - **/sethome [nom]** : Définit un point de téléportation à la maison du joueur.
  - **/delhome [nom]** : Supprime un point de téléportation de la maison du joueur.
  - **/home [nom]** : Téléporte le joueur à un point de maison défini.

- **Commandes de Commerce** :
  - **/trade [joueur]** : Ouvre un menu pour permettre à deux joueurs de commercer des objets entre eux.
  
- **Système d'Enchères** :
  - **/auction [start] [prix]** : Lance une enchère pour un objet avec un prix de départ.
  - **/bet  [montant]** : Fait une offre sur une enchère en cours.
  - **/auction end** : Termine une enchère.

- **Système de Claim/Unclaim de Chunks** :
  - **/claim** : Permet au joueur de revendiquer un chunk pour protéger sa zone.
  - **/unclaim** : Permet au joueur de libérer un chunk précédemment revendiqué.

- **Personnalisation** :
  - Le plugin est hautement configurable pour ajuster les classes, les capacités, le contenu du shop, ainsi que les commandes de maison, de commerce et d'enchères.

---

## Prérequis
- **Minecraft**: Version 1.14 ou supérieure
- **Serveur**: Spigot 
- **Java**: Version 8 ou supérieure

---



## Utilisation

### Commandes principales :
- **/classe** : Permet aux joueurs de voir la liste des classes disponibles et de choisir la classe qu'ils veulent rejoindre.
- **/currentclasse** : Affiche les informations détaillées sur la classe actuelle du joueur, ainsi que ses compétences.
- **/upgradeclasse** : Affiche le nombre de de points de classe du joueur et les options disponibles pour les utiliser.
- **/shop** : Ouvre l'interface du shop pour acheter des items.
  
### Commandes de Maison :
- **/sethome [nom]** : Définit un point de téléportation à la maison du joueur.
- **/delhome [nom]** : Supprime un point de téléportation de la maison du joueur.
- **/home [nom]** : Téléporte le joueur à un point de maison défini.

- **Commandes de Commerce** :
  - **/trade [joueur]** : Ouvre un menu pour permettre à deux joueurs de commercer des objets entre eux.
  
- **Système d'Enchères** :
  - **/auction [start] [prix]** : Lance une enchère pour un objet avec un prix de départ.
  - **/bet  [montant]** : Fait une offre sur une enchère en cours.
  - **/auction end** : Termine une enchère.

- **Système de Claim/Unclaim de Chunks** :
  - **/claim** : Permet au joueur de revendiquer un chunk pour protéger sa zone.
  - **/unclaim** : Permet au joueur de libérer un chunk précédemment revendiqué.
---

## Fichiers de Configuration
Le plugin utilise plusieurs fichiers de configuration dans le dossier `plugins/SurvivalClassesDXP` :

- **config.yml** : Configuration principale pour les classes, le système de DXp et les paramètres du shop.
- **shop.yml** : Liste des objets disponibles à l'achat dans le shop et leurs prix.
- **classes.yml** : Définitions détaillées des classes disponibles, de leurs compétences et de leurs bonus.

---

## Notes
- Ce plugin est compatible avec Spigot . Il peut nécessiter des ajustements pour d'autres types de serveurs Minecraft.
- Assurez-vous d'avoir une sauvegarde de vos fichiers de configuration avant d'apporter des modifications majeures.
- Le système de ckasses est basé sur des événements spécifiques du jeu (combats, exploration, etc.), et chaque classe peut avoir un gain de points de classe  unique.

---

## Contribution
N'hésitez pas à soumettre des problèmes ou des demandes de fonctionnalités via des **issues** ou en envoyant une **pull request**. Toute contribution est la bienvenue !

---

## Remerciements
  - Un grand merci aux développeurs de Spigot et Paper pour leurs outils qui facilitent le développement de plugins.
