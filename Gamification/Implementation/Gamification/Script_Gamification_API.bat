echo off
cls
REM
echo Lancement de l'API...
echo Verification d'une API vide sans applications [GET]
echo.
curl http://localhost:8080/Gamification/api/applications/
echo.
pause
cls
REM
echo Creation d'une application [POST]
echo.
echo Name: 		My_application
echo Description: 	This_is_a_description_for_my_application
echo Api Secret: 	477
echo.
pause
curl -i -X POST -H "Content-Type: application/json" -d '{\"name\":\"My_application\",\"description\":\"This_is_a_description_for_my_application\",\"apiSecret\":477}' http://localhost:8080/Gamification/api/applications/
echo.
pause
cls
echo Modification de l'apiKey pour la suite du script
echo.
echo Remplacer l'apiKey dans la Base de donnee par 1
echo.
pause
cls
echo Creation de 4 joueurs [POST]
echo.
echo Joueur X: Nom 		Prenom 		Email 		Points
echo.
echo Joueur 1: Romain 	Gisiger 	rg@example.ch 	0
echo Joueur 2: Oscar 	Soto 		os@example.ch 	0
echo Joueur 3: Khaled 	Basbous 	kb@example.ch 	0
echo Joueur 4: Jean-Luc	Pasquier 	jlp@example.ch 	0
echo.
pause
echo.
curl -i -X POST -H "Content-Type: application/json" -d '{\"firstName\":\"Romain\",\"lastName\":\"Gisiger\",\"email\":\"rg@example.ch\",\"numberOfPoints\":0}' http://localhost:8080/Gamification/api/applications/1/477/players
echo.
curl -i -X POST -H "Content-Type: application/json" -d '{\"firstName\":\"Oscar\",\"lastName\":\"Soto\",\"email\":\"os@example.ch\",\"numberOfPoints\":0}' http://localhost:8080/Gamification/api/applications/1/477/players
echo.
curl -i -X POST -H "Content-Type: application/json" -d '{\"firstName\":\"Khaled\",\"lastName\":\"Basbous\",\"email\":\"kb@example.ch\",\"numberOfPoints\":0}' http://localhost:8080/Gamification/api/applications/1/477/players
echo.
curl -i -X POST -H "Content-Type: application/json" -d '{\"firstName\":\"Jean-Luc\",\"lastName\":\"Pasquier\",\"email\":\"jlp@example.ch\",\"numberOfPoints\":0}' http://localhost:8080/Gamification/api/applications/1/477/players
echo.
pause
cls
REM
echo Recuperation des joueurs [GET]
pause
echo.
curl http://localhost:8080/Gamification/api/applications/1/477/players
echo.
pause REM Affichage des 4 joueurs dans la base de donnees
cls
REM
echo Creation d'un badge [POST]
echo.
echo Name: 		Badge_Membre
echo Description:	Description_badge
echo Icon:		image.png
echo.
pause
curl -i -X POST -H "Content-Type: application/json" -d '{\"name\":\"\",\"description\":\"Description_badge\",\"icon\":\"image.png\"}' http://localhost:8080/Gamification/api/badges
echo.
pause
cls
REM
echo Creation de 3 regles dans l'application [POST]
echo.
echo Rule X:	onEventType	Points	BadgesId
echo.
echo Rule 1:	Event_number_1	-10	null
echo Rule 2:	Event_number_2	-30	1
echo Rule 3:	Event_number_3	50	null
echo.
pause
curl -i -X POST -H "Content-Type: application/json" -d '{\"onEventType\":\"Event_number_1\",\"numberOfPoints\":-10}' http://localhost:8080/Gamification/api/applications/1/477/rules
echo.
curl -i -X POST -H "Content-Type: application/json" -d '{\"onEventType\":\"Event_number_2\",\"numberOfPoints\":-30,\"badges\":{\"id\":1}}' http://localhost:8080/Gamification/api/applications/1/477/rules
echo.
curl -i -X POST -H "Content-Type: application/json" -d '{\"onEventType\":\"Event_number_3\",\"numberOfPoints\":50}' http://localhost:8080/Gamification/api/applications/1/477/rules
echo.
pause
cls
REM
echo Recuperation des regles [GET]
echo.
pause
echo.
curl http://localhost:8080/Gamification/api/applications/1/477/rules
REM echo Affichage defectueux
echo.
pause
cls
REM
echo Creation de 2 evenements pour le joueur 2 [POST]
echo.
echo Event X:	type
echo.
echo Event 1: 	Event_number_1
echo Event 2: 	Event_number_2
echo.
pause
curl -i -X POST -H "Content-Type: application/json" -d '{\"type\":\"Event_number_1\"}' http://localhost:8080/Gamification/api/applications/1/477/players/2/events
echo.
curl -i -X POST -H "Content-Type: application/json" -d '{\"type\":\"Event_number_2\"}' http://localhost:8080/Gamification/api/applications/1/477/players/2/events
echo.
pause
cls
REM
echo Recuperation des evenements du joueur 2 [GET]
echo.
pause
echo.
curl http://localhost:8080/Gamification/api/applications/1/477/players/2/events
echo.
pause
cls
REM
echo Affichage des joueurs [GET]
echo.
pause
echo.
curl http://localhost:8080/Gamification/api/applications/1/477/players
pause
cls
REM
echo Suppression des joueurs 2 (avec ses evenements) et 4 [DELETE]
echo.
pause
echo.
curl -i -X DELETE http://localhost:8080/Gamification/api/applications/1/477/players/2
echo.
curl -i -X DELETE http://localhost:8080/Gamification/api/applications/1/477/players/4
echo.
pause
cls
REM
echo Modification des joueur 1 et 3 [PUT]
echo.
echo De:
echo Joueur 1: Romain 	Gisiger 	rg@example.ch 	0
echo Joueur 3: Khaled 	Basbous 	kb@example.ch 	0
echo.
echo A:
echo Joueur 1: Romain 	G 	romain.gisiger@master.ch 	80
echo Joueur 3: Khaled 	B 	khaled.basbous@master.ch 	40
echo.
pause
curl -i -X PUT -H "Content-Type: application/json" -d '{\"firstName\":\"Romain\",\"lastName\":\"G\",\"email\":\"romain.gisiger@master.ch\",\"numberOfPoints\":80}' http://localhost:8080/Gamification/api/applications/1/477/players/1
echo.
curl -i -X PUT -H "Content-Type: application/json" -d '{\"firstName\":\"Khaled\",\"lastName\":\"B\",\"email\":\"khaled.basbous@master.ch\",\"numberOfPoints\":40}' http://localhost:8080/Gamification/api/applications/1/477/players/3
echo.
pause
cls
REM
echo Affichage des joueurs [GET]
echo.
pause
echo.
curl http://localhost:8080/Gamification/api/applications/1/477/players
pause
cls
REM
echo Affichage de la leaderboard [GET]
echo.
pause
echo.
curl http://localhost:8080/Gamification/api/applications/1/477/leaderboard
pause
cls
REM
echo Modification de l'application [PUT]
echo.
echo De:
echo Name: 		My_application
echo Description: 	This_is_a_description_for_my_application
echo Api Secret: 	477
echo.
echo A:
echo Name: 		My_updated_application
echo Description: 	New_description_for_my_application
echo Api Secret: 	54
echo.
pause
curl -i -X PUT -H "Content-Type: application/json" -d '{\"name\":\"My_updated_application\",\"description\":\"New_description_for_my_application\",\"apiSecret\":54}' http://localhost:8080/Gamification/api/applications/1/477/
pause
cls
REM
echo Affichage de l'application modifiee [GET]
echo.
pause
echo.
curl http://localhost:8080/Gamification/api/applications/
echo.
pause
cls
REM
echo Test de suppression de l'application avec son ancien code Secret [DELETE]
echo.
pause
echo.
curl -i -X DELETE http://localhost:8080/Gamification/api/applications/1/477/
pause
cls
REM
echo Suppression de l'application avec son nouveau code Secret [DELETE]
echo.
pause
echo.
curl -i -X DELETE http://localhost:8080/Gamification/api/applications/1/54/
pause
