# Qu'est-ce que Springboot ?

Spring Boot est un framework qui facilite le développement d'applications fondées sur Spring en offrant des outils permettant d'obtenir une application packagée en jar , totalement autonome.
L'interêt de Spring Boot est qu'il  permet de simplifier la configuration de Spring, grâce à __2 fonctionnalités principales__:
1. __L'auto-configuration__
   
   Cette fonctionnalité est la plus importante de Spring Boot. Elle permet de configurer automatiquement votre application à partir des jar trouvés dans votre Classpath. En d'autres termes, si vous avez importé des dépendances, Spring Boot ira consulter cette liste puis produira la configuration nécessaire pour que tout fonctionne correctement.
Donc, au lieu d'avoir un fichier de configuration ``.xml`` pour chaque dépendance, cette ligne suffit: ``@EnableAutoConfiguration``.
   Avec cette annotation, Spring Boot ira scanner la liste des dépendances. Il créera la configuration nécessaire et l'ajoutera à ``ApplicationContext``.


2. __Les starters__
   
    Les starters viennent compléter l'auto-configuration et font gagner énormément de temps, notamment lorsqu'on commence le développement d'un Microservice.
Un starter va apporter à votre projet un ensemble de dépendances, communément utilisées pour un type de projet donné. Ceci va vous permettre de créer un "squelette" prêt à l'emploi très rapidement.
   Tous les starters de Spring Boot sont au format ``spring-boot-starter-NOM_DU_STARTER``

L'autre énorme avantage est la gestion des versions. Plus besoin de chercher quelles versions sont compatibles puis de les ajouter une à une dans le pom.xml ! Il vous suffit d'ajouter une simple dépendance au starter de votre choix. Cette dépendance va alors ajouter, à son tour, les éléments dont elle dépend, avec les bonnes versions.

Exemple, en ajoutant une dépendance de cette façon:
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

Et en ayant déclarer ceci:
```
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.5.9.RELEASE</version>
</parent>
```
Ce dernier tag permet au ``pom`` d'hériter des propriétés d'un autre ``pom`` qui hérite de : ``spring-boot-dependencies``. Il permet de définir principalement :

 - La version de Java à utiliser. Dans ce cas, c'est la 1.6, nous verrons dans le prochain chapitre comment la surcharger pour utiliser Java 1.8.

 - Une liste complète des versions des dépendances prises en charge, ce qui permet d'ajouter des dépendances sans indiquer leur version comme dans le pom.xml du starter spring-boot-starter-web vu plus haut. Vous allez donc pouvoir ajouter les dépendances de votre choix, sans vous soucier des versions.


### Bean


### Autowiring
Permet d'instancier la classe directement en injectant les dépendances décrites dans les paramètres du constructeur.


### Thymeleaf
Thymeleaf est un moteur de template, une bibliothèque écrite en JAVA. Il permet à un développeur de définir un modèle de page HTML, XHTML ou HTML5 et de le remplir ultérieurement de données pour générer la page finale. Par conséquent, il réalise une partie Model-View d'un modèle Model-View-Controller .
Le principe de conception important de Thymeleaf est qu'un modèle lui-même doit être correctement écrit (X) HTML.
