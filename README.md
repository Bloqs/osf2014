Open Source Framework
======================================================

Gamification API
------------

1) Présentation : 

API de Gamification pour divers applications. Il permet de gérer des applications avec leur utilisateurs ainsi que des badges, rules, events et même des classements.

Présentation PowerPoint => https://drive.google.com/file/d/0B_T2yzmXk7OiamtxbzdxTDdMcFk/edit?usp=sharing


2) Documentation dans apiary :

	http://docs.rggamification.apiary.io/


3) Comment utiliser l'API

	a) Lancer NetBeans IDE 7.4 RC1
	b) Dans NetBeans, ouvrir le menu 'Team' --> Remote --> Clone
	c) Dans la fenêtre de configuration qui s'ouvre remplir le champ 'Repository URL' : https://github.com/Bloqs/osf.git
	d) Importer le projet Gamification
	e) Dans NetBeans, aller dans le sous-menu 'Service', puis cliquer sur 'Databases', puis clique droit sur 'JavaDB' puis créer 'Create Database...'
	f) Dans la fenêtre qui s'ouvre, ajouter les informations suivantes puis valider par le bouton 'OK':
	 	'Database Name': gamificationDB
	  	'User Name': gamification
	  	'Password': gamificationpw
	g) Compiler le projet avec les dépendances : Dans le sous-menu 'Projects', clique droit sur le projet 'Gamification' puis lancer 'Build with Dependencies'
	h) Faite un run du projet ou un run debug puis tester l'api rest soit par le plugin restclient dans Mozilla Firefox soit avec cURL 

4) Règles implémentées dans Gamification

	a) L'apiKey est générée par le code de Gamification à sa création
	b) Le secret est choisi par l'utilisateur du Gamification engine lors de la création d'une application
	c) Un joueur ne peut exister sans lien avec une application
	d) Une règle ne peut exister sans lient avec une application
	e) Un event ne peut exister sans lien avec un joueur
	f) Un badge ne peut être supprimé que s'il y a aucune règle qui le mentionne et qu'aucun joueur ne le possède. Il peut par contre être modifié.
	g) A la suppression d'une application, les joueurs et les règles liés à cette application sont supprimés par cascade
	h) A la suppression d'un joueur les events du joueurs sont supprimés en cascade
	i) Un même badge n'est attribué qu'une seul fois a un joueur donné
	j) Un event d'un même type peut être lancé sur un même joueur, les points selon la règle liée par le type seront additionnés aux points du joueur

5) Exemples

	POST avec Header JSON

	Ajout application : 
	http://localhost:8080/Gamification/api/applications/
	{"name":"nom app","description":"description app","apiSecret":"secret"}
	
	Ajout d'un joueur : 
	http://localhost:8080/Gamification/api/applications/{apiKey}/secret/players/
	{"firstName":"joueur 1","lastName":"nom joueur 1","email":"joueur@hei-vd.ch","numberOfPoints":"0"}
	
	Ajout d'une règle
	http://localhost:8080/Gamification/api/applications/{apiKey}/secret/rules/
	{"onEventType":"Inscription","numberOfPoints":"2"}
	
	Ajout d'un badge numéro 1:
	http://localhost:8080/Gamification/api/badges/
	{"name":"Badge bon joueur","description":"Badge obtenu apres avoir eu plus de 300 points","icon":"icon http://www.images.com/2651456"}
	
	Ajout d'une règle avec attribution d'un badge badge numéro 1:
	http://localhost:8080/Gamification/api/applications/{apiKey}/secret/rules/
	{"onEventType":"b bon joueur et +300","numberOfPoints":"300","badge":{"id":"1"}}
	
	Ajout d'un event :
	http://localhost:8080/Gamification/api/applications/{apiKey}/secret/players/1/events/
	{"type":"b bon joueur et +300"}
	
	GET
	Afficher informations joueurs :
	http://localhost:8080/Gamification/api/applications/{apiKey}/secret/players/
	
	Afficher informations events du joueur 1:
	http://localhost:8080/Gamification/api/applications/{apiKey}/secret/players/1/events/
	
	Afficher les joueurs avec le plus de points:
	http://localhost:8080/Gamification/api/applications/{apiKey}/secret/leaderboard/



