
# Pré requis
* JVM 1.7 minimum
* Maven 3
* Un vrai shell (comme bash)

# Documentation
## Fonctionnelle
[Mower 2](documents/mower2.pdf "libellé du sujet")
### Version 1.2
* ajout des obstacles
* ajout de tondeurs customisable
### Version 1.3
* ajout d'un tondeur multi-threaded

# Installation & exécution
* Récupération de sources `git clone https://github.com/christophe-martel/xebia-lawnmower.git`
* Préparation
    * Etre au bon endroit `cd xebia-lawnmower`
    * Un classique : `mvn clean`
    * Des tests ? `mvn test`
    * Un peu d'audit ? `mvn sonar:sonar`

# Exécution
## Méthode dite "La RACHE"
Cf. http://www.byatoo.com/la-rache/
* `mvn exec:java`

## How To...
* Préparation du package
    * `mvn package`
* Exécution
    * `java -jar target/cma-xebia-lawnmower-1.2-full.jar`
* Guide utilisateur
    * `java -jar target/cma-xebia-lawnmower-1.2-full.jar --help`
    

# Aide
En cas d'erreur, utiliser de la poudre verte
Cf. http://www.poudreverte.org/
