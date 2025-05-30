# Codage - Projet Java Multi-modules

Ce projet est une collection de modules Java pour l'étude et l'expérimentation de différents algorithmes de codage, de compression, de stéganographie et de théorie de l'information. Il utilise Spring Boot et Spring Cloud pour certains modules, et Maven pour la gestion multi-modules.

## Modules principaux

- **core** : Fonctions utilitaires et logiques de base partagées.
- **sardinas-patterson** : Implémentation de l'algorithme de Sardinas-Patterson pour vérifier si un code est préfixe.
- **huffman** : Compression et décompression de données avec l'algorithme de Huffman.
- **wav-steganography** : Module dédié à la stéganographie dans les fichiers audio WAV.
- **steganographie** : Techniques de stéganographie diverses.
- **api-gateway** : Gateway API basée sur Spring Cloud Gateway.
- **console** : Application console pour tester et démontrer les fonctionnalités des modules.

## Prérequis

- Java 17+
- Maven 3.8+

## Compilation

Dans le dossier racine du projet :

```sh
mvn clean install
```

## Lancement d'un module Spring Boot

Exemple pour lancer le module `api-gateway` :

```sh
cd api-gateway
mvn spring-boot:run
```

## Structure du projet

- `core/` : Utilitaires et logiques partagées
- `sardinas-patterson/` : Algorithme Sardinas-Patterson
- `huffman/` : Compression Huffman
- `wav-steganography/` : Stéganographie audio WAV
- `steganographie/` : Stéganographie (autres techniques)
- `api-gateway/` : Gateway API Spring Cloud
- `console/` : Application console

## Auteurs

- Ando
- Collaborateurs éventuels

## Licence

Ce projet est sous licence MIT.
