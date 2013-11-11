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

5) Exemples de test avec un client REST

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

6) Exemple de test avec cURL pour Windows (échappement de guillemet dans le passage de JSON)

	Création: POST
	Mise à jour: PUT
	Obtention: GET
	Suppression: DELETE
	
	
	___________________________________________________________________________________________________________________________________
	-----------------------------------------------------------------------------------------------------------------------------------
	/badges
	GET:
	curl http://localhost:8080/Gamification/api/badges
	
	POST:
	curl -i -X POST -H "Content-Type: application/json" -d '{\"name\":\"Badge_People\",\"description\":\"Description_badge\",\"icon\":\"image.png\"}' http://localhost:8080/Gamification/api/badges
	
	___________________________________________________________________________________________________________________________________
	-----------------------------------------------------------------------------------------------------------------------------------
	/badges/{badgeId}
	GET:
	curl http://localhost:8080/Gamification/api/badges/1
	
	PUT:
	curl -i -X PUT -H "Content-Type: application/json" -d '{\"name\":\"Badge_membre\",\"description\":\"Mon_badge\",\"icon\":\"image5.png\"}' http://localhost:8080/Gamification/api/badges/1
	
	DELETE:
	curl -i -X DELETE http://localhost:8080/Gamification/api/badges/1
	
	___________________________________________________________________________________________________________________________________
	-----------------------------------------------------------------------------------------------------------------------------------
	/applications
	GET:
	curl http://localhost:8080/Gamification/api/applications/
	
	POST:
	curl -i -X POST -H "Content-Type: application/json" -d '{\"name\":\"My_Game\",\"description\":\"This_is_a_description_for_My_Game\",\"apiSecret\":477}' http://localhost:8080/Gamification/api/applications/
	
	___________________________________________________________________________________________________________________________________
	-----------------------------------------------------------------------------------------------------------------------------------
	/applications/{apiKey}
	GET:
	curl http://localhost:8080/Gamification/api/applications/1/
	
	/applications/{apiKey}/{apiSecret}
	PUT:
	curl -i -X PUT -H "Content-Type: application/json" -d '{\"name\":\"My_updated_game\",\"description\":\"New_description_for_My_Game\",\"apiSecret\":54}' http://localhost:8080/Gamification/api/applications/1/477/
	
	DELETE:
	curl -i -X DELETE http://localhost:8080/Gamification/api/applications/1/477/
	
	___________________________________________________________________________________________________________________________________
	-----------------------------------------------------------------------------------------------------------------------------------
	/applications/{apiKey}/{apiSecret}/players
	GET:
	curl http://localhost:8080/Gamification/api/applications/1/477/players
	
	POST:
	curl -i -X POST -H "Content-Type: application/json" -d '{\"firstName\":\"Romain\",\"lastName\":\"Gisiger\",\"email\":\"rg@example.ch\",\"numberOfPoints\":380}' http://localhost:8080/Gamification/api/applications/1/477/players
	
	___________________________________________________________________________________________________________________________________
	-----------------------------------------------------------------------------------------------------------------------------------
	/applications/{apiKey}/{apiSecret}/players/{playerId}
	GET:
	curl http://localhost:8080/Gamification/api/applications/1/477/players/1
	
	PUT:
	curl -i -X PUT -H "Content-Type: application/json" -d '{\"firstName\":\"Romain\",\"lastName\":\"G\",\"email\":\"romain.gisiger@master.ch\",\"numberOfPoints\":40}' http://localhost:8080/Gamification/api/applications/1/477/players/1
	
	DELETE:
	curl -i -X DELETE http://localhost:8080/Gamification/api/applications/1/477/players/1
	
	___________________________________________________________________________________________________________________________________
	-----------------------------------------------------------------------------------------------------------------------------------
	/applications/{apiKey}/{apiSecret}/players/{playerId}/events
	GET:
	curl http://localhost:8080/Gamification/api/applications/1/477/players/1/events
	
	POST:
	curl -i -X POST -H "Content-Type: application/json" -d '{\"type\":\"Event_number_1\"}' http://localhost:8080/Gamification/api/applications/1/477/players/1/events
	
	___________________________________________________________________________________________________________________________________
	-----------------------------------------------------------------------------------------------------------------------------------
	/applications/{apiKey}/{apiSecret}/players/{playerId}/events/{eventId}
	GET:
	curl http://localhost:8080/Gamification/api/applications/1/477/players/1/events/1
	
	PUT:
	curl -i -X PUT -H "Content-Type: application/json" -d '{\"type\":\"Event_number_37\"}' http://localhost:8080/Gamification/api/applications/1/477/players/1/events/1
	
	DELETE:
	curl -i -X DELETE http://localhost:8080/Gamification/api/applications/1/477/players/1/events/1
	
	___________________________________________________________________________________________________________________________________
	-----------------------------------------------------------------------------------------------------------------------------------
	/applications/{apiKey}/{apiSecret}/rules
	GET:
	curl http://localhost:8080/Gamification/api/applications/1/477/rules
	
	POST:
	curl -i -X POST -H "Content-Type: application/json" -d '{\"onEventType\":\"Rule_number_3\",\"numberOfPoints\":10}' http://localhost:8080/Gamification/api/applications/1/477/rules
	
	___________________________________________________________________________________________________________________________________
	-----------------------------------------------------------------------------------------------------------------------------------
	/applications/{apiKey}/{apiSecret}/rules/{ruleId}
	GET:
	curl http://localhost:8080/Gamification/api/applications/1/477/rules/1
	
	PUT:
	curl -i -X PUT -H "Content-Type: application/json" -d '{\"onEventType\":\"Rule_number_45\",\"numberOfPoints\":-10}' http://localhost:8080/Gamification/api/applications/1/477/rules/1
	
	DELETE:
	curl -i -X DELETE http://localhost:8080/Gamification/api/applications/1/477/rules/1

	___________________________________________________________________________________________________________________________________
	-----------------------------------------------------------------------------------------------------------------------------------
	/applications/{apiKey}/{apiSecret}/leaderboard
	GET:
	curl http://localhost:8080/Gamification/api/applications/1/477/leaderboard

