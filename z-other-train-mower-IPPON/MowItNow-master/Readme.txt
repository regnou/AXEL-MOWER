Readme de MowItNow - Stanislas Zarka, 04/09/2012
------------------

Environnement
-------------
Langage Java, JVM 1.6

Architecture
------------
Classe principale Main
	> Interface FileService
		> Service FileServiceImpl
	> Beans Mower, Plan
	
Description fonctionnelle
-------------------------
La soci�t� MowItNow a d�cid� de d�velopper une tondeuse � gazon automatique, destin�e aux surfaces rectangulaires.

La tondeuse peut �tre programm�e pour parcourir l'int�gralit� de la surface.
La position de la tondeuse est repr�sent�e par une combinaison de coordonn�es (x,y) et d'une lettre indiquant l'orientation selon la notation cardinale anglaise (N,E,W,S). La pelouse est divis�e en grille pour simplifier la navigation. 

Par exemple, la position de la tondeuse peut �tre � 0, 0, N �, ce qui signifie qu'elle se situe dans le coin inf�rieur gauche de la pelouse, et orient�e vers le Nord.

Pour contr�ler la tondeuse, on lui envoie une s�quence simple de lettres. Les lettres possibles sont � D �, � G � et � A �. � D � et � G � font pivoter la tondeuse de 90� � droite ou � gauche respectivement, sans la d�placer. � A � signifie que l'on avance la tondeuse d'une case dans la direction � laquelle elle fait face, et sans modifier son orientation.

Si la position apr�s mouvement est en dehors de la pelouse, la tondeuse ne bouge pas, conserve son orientation et traite la commande suivante. 

On assume que la case directement au Nord de la position (x, y) a pour coordonn�es (x, y+1).

Pour programmer la tondeuse, on lui fournit un fichier d'entr�e construit comme suit :
�	La premi�re ligne correspond aux coordonn�es du coin sup�rieur droit de la pelouse, celles du coin inf�rieur gauche sont suppos�es �tre (0,0)
�	La suite du fichier permet de piloter toutes les tondeuses qui ont �t� d�ploy�es. Chaque tondeuse a deux lignes la concernant :
�	la premi�re ligne donne la position initiale de la tondeuse, ainsi que son orientation. La position et l'orientation sont fournies sous la forme de 2 chiffres et une lettre, s�par�s par un espace
�	la seconde ligne est une s�rie d'instructions ordonnant � la tondeuse d'explorer la pelouse. Les instructions sont une suite de caract�res sans espaces.

Chaque tondeuse se d�place de fa�on s�quentielle, ce qui signifie que la seconde tondeuse ne bouge que lorsque la premi�re a ex�cut� int�gralement sa s�rie d'instructions.

Lorsqu'une tondeuse ach�ve une s�rie d'instruction, elle communique sa position et son orientation.