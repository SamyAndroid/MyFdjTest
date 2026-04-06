package com.rdissi.myfdjtest.ui.toolkit

import com.rdissi.myfdjtest.data.remote.model.LeaguesResponseJson
import com.rdissi.myfdjtest.data.source.JsonConverter.toLeaguesResponse
import com.rdissi.myfdjtest.domain.model.League
import com.google.gson.Gson
import com.rdissi.myfdjtest.data.remote.model.TeamsResponseJson
import com.rdissi.myfdjtest.data.source.JsonConverter.toTeams
import com.rdissi.myfdjtest.domain.model.Team

object MockDataSource {

    fun getMockLeagues(): List<League> {
        val leaguesResponseJson: LeaguesResponseJson = Gson().fromJson(leaguesJsonString, LeaguesResponseJson::class.java)
        return leaguesResponseJson.toLeaguesResponse().leagues
    }

    fun getMockTeams(): List<Team> {
        val teamsResponseJson: TeamsResponseJson = Gson().fromJson(teamsJsonString, TeamsResponseJson::class.java)
        return teamsResponseJson.toTeams().teams
    }

    fun getFirstMockTeam(): Team = getMockTeams()[0]

    private const val leaguesJsonString = "{\n" +
        "  \"leagues\": [\n" +
        "    {\n" +
        "      \"idLeague\": \"4328\",\n" +
        "      \"strLeague\": \"English Premier League\",\n" +
        "      \"strSport\": \"Soccer\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"idLeague\": \"4329\",\n" +
        "      \"strLeague\": \"English League Championship\",\n" +
        "      \"strSport\": \"Soccer\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"idLeague\": \"4330\",\n" +
        "      \"strLeague\": \"Scottish Premier League\",\n" +
        "      \"strSport\": \"Soccer\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"idLeague\": \"4331\",\n" +
        "      \"strLeague\": \"German Bundesliga\",\n" +
        "      \"strSport\": \"Soccer\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"idLeague\": \"4332\",\n" +
        "      \"strLeague\": \"Italian Serie A\",\n" +
        "      \"strSport\": \"Soccer\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"idLeague\": \"4334\",\n" +
        "      \"strLeague\": \"French Ligue 1\",\n" +
        "      \"strSport\": \"Soccer\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"idLeague\": \"4335\",\n" +
        "      \"strLeague\": \"Spanish La Liga\",\n" +
        "      \"strSport\": \"Soccer\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"idLeague\": \"4336\",\n" +
        "      \"strLeague\": \"Greek Superleague Greece\",\n" +
        "      \"strSport\": \"Soccer\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"idLeague\": \"4337\",\n" +
        "      \"strLeague\": \"Dutch Eredivisie\",\n" +
        "      \"strSport\": \"Soccer\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"idLeague\": \"4338\",\n" +
        "      \"strLeague\": \"Belgian Pro League\",\n" +
        "      \"strSport\": \"Soccer\"\n" +
        "    }\n" +
        "  ]\n" +
        "}"

    private const val teamsJsonString = "{\n" +
        "  \"teams\": [\n" +
        "    {\n" +
        "      \"idTeam\": \"134709\",\n" +
        "      \"idESPN\": \"7868\",\n" +
        "      \"idAPIfootball\": \"77\",\n" +
        "      \"intLoved\": null,\n" +
        "      \"strTeam\": \"Angers\",\n" +
        "      \"strTeamAlternate\": \"Angers SCO\",\n" +
        "      \"strTeamShort\": \"\",\n" +
        "      \"intFormedYear\": \"1919\",\n" +
        "      \"strSport\": \"Soccer\",\n" +
        "      \"strLeague\": \"French Ligue 1\",\n" +
        "      \"idLeague\": \"4334\",\n" +
        "      \"strLeague2\": \"Coupe de France\",\n" +
        "      \"idLeague2\": \"4484\",\n" +
        "      \"strLeague3\": \"\",\n" +
        "      \"idLeague3\": null,\n" +
        "      \"strLeague4\": \"\",\n" +
        "      \"idLeague4\": null,\n" +
        "      \"strLeague5\": \"\",\n" +
        "      \"idLeague5\": null,\n" +
        "      \"strLeague6\": \"\",\n" +
        "      \"idLeague6\": null,\n" +
        "      \"strLeague7\": \"\",\n" +
        "      \"idLeague7\": null,\n" +
        "      \"strDivision\": null,\n" +
        "      \"idVenue\": \"29067\",\n" +
        "      \"strStadium\": \"Stade Raymond Kopa\",\n" +
        "      \"strKeywords\": \"Le SCO\",\n" +
        "      \"strRSS\": \"http://www.angers-sco.fr/index/rss\",\n" +
        "      \"strLocation\": \"Angers\",\n" +
        "      \"intStadiumCapacity\": \"18752\",\n" +
        "      \"strWebsite\": \"www.angers-sco.fr\",\n" +
        "      \"strFacebook\": \"www.facebook.com/AngersSCO\",\n" +
        "      \"strTwitter\": \"twitter.com/angerssco\",\n" +
        "      \"strInstagram\": \"www.instagram.com/angers_sco\",\n" +
        "      \"strDescriptionEN\": \"Angers Sporting Club de l'Ouest, commonly referred to as Angers SCO or simply Angers, is a French professional football club based in Angers in Pays de la Loire in western France. The club was founded in 1919 and plays in Ligue 2, the second division of Football in France, having achieved promotion to the league in 2015 after 21 years. It plays its home matches at the Stade Raymond Kopa. The club has played 23 seasons in the top division, Ligue 1, and has also participated in the UEFA Cup after finishing 4th in 1971–72 season.\",\n" +
        "      \"strDescriptionDE\": null,\n" +
        "      \"strDescriptionFR\": \"L'Angers Sporting Club de l'Ouest, couramment abrégé en Angers SCO ou SCO d'Angers, est un club de football fondé en 1919 et situé à Angers (Maine-et-Loire, région Pays de la Loire).\\r\\n\\r\\nLe SCO d'Angers a remporté à deux reprises le championnat de Division 2 (en 1969, 1976), il a disputé par ailleurs 23 saisons en Division 1. Il a atteint une seule fois la finale de la Coupe de France en 1957 (défaite 3-6 contre le Toulouse FC). Sous la présidence de Willy Bernard, qui l'a racheté en 2006, Angers SCO a progressé du National à la Ligue 2 et est désormais présidé par Saïd Chabane depuis 2011. Durant la saison 2010-2011, l'équipe angevine est allée jusqu'aux demi-finales de la coupe de France, éliminée durant celle-ci par le Paris-Saint-Germain (1-3) 2. Le SCO récidive en 2013-2014 et se fait également éliminer en demi-finale par le Stade rennais FC (3-2). Après 21 ans d'absence dans l'élite du football français, le SCO d'Angers revient en Ligue 1 pour la saison 2015-2016.\\r\\n\\r\\nLe club dispute ses matchs à domicile au Stade Jean-Bouin.\",\n" +
        "      \"strDescriptionCN\": null,\n" +
        "      \"strDescriptionIT\": \"L'Angers Sporting Club de l'Ouest, meglio nota come Angers SCO, o più semplicemente Angers, è una società calcistica francese con sede nella città di Angers. Fondata nel 1919, la squadra milita nella Ligue 1.\",\n" +
        "      \"strDescriptionJP\": null,\n" +
        "      \"strDescriptionRU\": null,\n" +
        "      \"strDescriptionES\": \"El Angers Sporting Club de l'Ouest (Angers SCO) es un club de fútbol francés de la ciudad de Angers en Maine-et-Loire. Fue fundado en 1919 y juega en la Ligue 1.\",\n" +
        "      \"strDescriptionPT\": null,\n" +
        "      \"strDescriptionSE\": null,\n" +
        "      \"strDescriptionNL\": null,\n" +
        "      \"strDescriptionHU\": null,\n" +
        "      \"strDescriptionNO\": null,\n" +
        "      \"strDescriptionIL\": null,\n" +
        "      \"strDescriptionPL\": null,\n" +
        "      \"strColour1\": \"#000000\",\n" +
        "      \"strColour2\": \"#ffffff\",\n" +
        "      \"strColour3\": \"#d9c395\",\n" +
        "      \"strGender\": \"Male\",\n" +
        "      \"strCountry\": \"France\",\n" +
        "      \"strBadge\": \"https://r2.thesportsdb.com/images/media/team/badge/ix6q4w1678808069.png\",\n" +
        "      \"strLogo\": \"https://r2.thesportsdb.com/images/media/team/logo/6myqnc1622560256.png\",\n" +
        "      \"strFanart1\": \"https://r2.thesportsdb.com/images/media/team/fanart/d3rxh71549710963.jpg\",\n" +
        "      \"strFanart2\": \"https://r2.thesportsdb.com/images/media/team/fanart/65lg3c1549710970.jpg\",\n" +
        "      \"strFanart3\": \"https://r2.thesportsdb.com/images/media/team/fanart/sf0e8x1549710976.jpg\",\n" +
        "      \"strFanart4\": \"https://r2.thesportsdb.com/images/media/team/fanart/anrakh1549710983.jpg\",\n" +
        "      \"strBanner\": \"https://r2.thesportsdb.com/images/media/team/banner/fu0ijw1549713907.jpg\",\n" +
        "      \"strEquipment\": \"https://www.thesportsdb.com/images/media/team/equipment/qxibjk1765915065.png\",\n" +
        "      \"strYoutube\": \"\",\n" +
        "      \"strLocked\": \"unlocked\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"idTeam\": \"134788\",\n" +
        "      \"idESPN\": \"172\",\n" +
        "      \"idAPIfootball\": \"108\",\n" +
        "      \"intLoved\": \"1\",\n" +
        "      \"strTeam\": \"Auxerre\",\n" +
        "      \"strTeamAlternate\": \"AJ Auxerre, Association de la Jeunesse Auxerroise, AJA\",\n" +
        "      \"strTeamShort\": \"\",\n" +
        "      \"intFormedYear\": \"1905\",\n" +
        "      \"strSport\": \"Soccer\",\n" +
        "      \"strLeague\": \"French Ligue 1\",\n" +
        "      \"idLeague\": \"4334\",\n" +
        "      \"strLeague2\": \"Coupe de France\",\n" +
        "      \"idLeague2\": \"4484\",\n" +
        "      \"strLeague3\": \"\",\n" +
        "      \"idLeague3\": null,\n" +
        "      \"strLeague4\": \"\",\n" +
        "      \"idLeague4\": null,\n" +
        "      \"strLeague5\": \"\",\n" +
        "      \"idLeague5\": null,\n" +
        "      \"strLeague6\": \"\",\n" +
        "      \"idLeague6\": null,\n" +
        "      \"strLeague7\": \"\",\n" +
        "      \"idLeague7\": null,\n" +
        "      \"strDivision\": null,\n" +
        "      \"idVenue\": \"16189\",\n" +
        "      \"strStadium\": \"Stade de l'Abbé Deschamps\",\n" +
        "      \"strKeywords\": \"Les diplomates\",\n" +
        "      \"strRSS\": \"\",\n" +
        "      \"strLocation\": \"Auxerre\",\n" +
        "      \"intStadiumCapacity\": \"21379\",\n" +
        "      \"strWebsite\": \"www.aja.fr\",\n" +
        "      \"strFacebook\": \"www.facebook.com/ajaofficiel\",\n" +
        "      \"strTwitter\": \"twitter.com/ajaofficiel\",\n" +
        "      \"strInstagram\": \"www.instagram.com/aja\",\n" +
        "      \"strDescriptionEN\": \"Association de la Jeunesse Auxerroise (French pronunciation: \u200B), commonly known as AJ Auxerre or simply Auxerre (), is a French football club based in the commune of Auxerre in Burgundy. The club was founded in 1905 and currently plays in Ligue 2, the second division of French football. Auxerre plays its home matches at the Stade l'Abbé-Deschamps on the banks of the Yonne River. The team is managed by Jean-Marc Furlan and captained by midfielder Birama Touré.\\r\\n\\r\\nAuxerre was founded in 1905 and made its debut in the first division of French football in the 1980–81 season and remained a fixture in the league until the 2011–12 season. The club has won the Ligue 1 title once, in the 1995–96 season. Two years prior, Auxerre achieved its first major honour by winning the Coupe de France in 1994. The club has since added three more Coupe de France titles, which ties the club for fifth-best among teams who have won the trophy.\\r\\n\\r\\nAuxerre has produced several notable players during its existence. The club has most notably served as a springboard for several prominent French football players such as Eric Cantona, Laurent Blanc, Stéphane Guivarc'h, Philippe Mexès, Basile Boli, and Djibril Cissé, among others, who all became French internationals, with Blanc playing on the teams that won the 1998 FIFA World Cup and UEFA Euro 2000. Guivarc'h, Bernard Diomède and Lionel Charbonnier were the three footballers from Auxerre who were world champions in 1998. From 1961 to 2005, the club was predominantly coached by Guy Roux. This included an uninterrupted period when Roux was in charge for 36 years between 1964 and 2000.\",\n" +
        "      \"strDescriptionDE\": null,\n" +
        "      \"strDescriptionFR\": \"L'Association de la jeunesse auxerroise (AJA) est un club de football français basé à Auxerre et fondé en 1905. Le club bourguignon est présidé depuis avril 2013 par Guy Cotret.\\r\\n\\r\\nL'AJ Auxerre évoluait au plus haut niveau (en Division 1 rebaptisée « Ligue 1 ») depuis la saison 1980-1981, mais a été relégué durant la saison 2011-2012 en Ligue 2. Le club a remporté quatre Coupes de France et un championnat de France. À ce jour, l'AJ Auxerre a disputé plus de mille matchs en première division et plus de cent matchs en Coupe d'Europe. Il fait partie des deux seuls clubs professionnels en France à être propriétaire de son stade avec l'AC Ajaccio.\\r\\n\\r\\nL'adjectif dérivé du club est « ajaïste », c'est ainsi que l'on surnomme les joueurs ou les supporters. Réputé pour son centre de formation, l'AJ Auxerre est indissociable de la figure emblématique Guy Roux qui en a été l'entraîneur pendant plus de quarante ans.\",\n" +
        "      \"strDescriptionCN\": null,\n" +
        "      \"strDescriptionIT\": \"L'Association de la Jeunesse Auxerroise, altresì noto come AJ Auxerre o semplicemente Auxerre, è una società calcistica francese con sede nella città di Auxerre, in Borgogna e fondata nel 1905.\",\n" +
        "      \"strDescriptionJP\": null,\n" +
        "      \"strDescriptionRU\": null,\n" +
        "      \"strDescriptionES\": \"La Association de la Jeunesse Auxerroise (conocido también como AJ Auxerre) es un club de fútbol francés, de la ciudad de Auxerre en Borgoña.\",\n" +
        "      \"strDescriptionPT\": null,\n" +
        "      \"strDescriptionSE\": null,\n" +
        "      \"strDescriptionNL\": null,\n" +
        "      \"strDescriptionHU\": null,\n" +
        "      \"strDescriptionNO\": null,\n" +
        "      \"strDescriptionIL\": null,\n" +
        "      \"strDescriptionPL\": null,\n" +
        "      \"strColour1\": \"#4087bf\",\n" +
        "      \"strColour2\": \"#ffffff\",\n" +
        "      \"strColour3\": \"\",\n" +
        "      \"strGender\": \"Male\",\n" +
        "      \"strCountry\": \"France\",\n" +
        "      \"strBadge\": \"https://r2.thesportsdb.com/images/media/team/badge/lzdtbf1658753355.png\",\n" +
        "      \"strLogo\": \"https://r2.thesportsdb.com/images/media/team/logo/epb8c41680949048.png\",\n" +
        "      \"strFanart1\": \"https://r2.thesportsdb.com/images/media/team/fanart/rjp48e1681058005.jpg\",\n" +
        "      \"strFanart2\": \"https://r2.thesportsdb.com/images/media/team/fanart/1i5mef1681058018.jpg\",\n" +
        "      \"strFanart3\": \"https://r2.thesportsdb.com/images/media/team/fanart/ukt4rf1681058033.jpg\",\n" +
        "      \"strFanart4\": \"https://r2.thesportsdb.com/images/media/team/fanart/l6vy9x1681058045.jpg\",\n" +
        "      \"strBanner\": \"https://r2.thesportsdb.com/images/media/team/banner/7jt5u61681058416.jpg\",\n" +
        "      \"strEquipment\": \"https://www.thesportsdb.com/images/media/team/equipment/7iqxkf1765921039.png\",\n" +
        "      \"strYoutube\": \"www.youtube.com/channel/UCSbKMu2GkvGa5-_K90SciAw\",\n" +
        "      \"strLocked\": \"unlocked\"\n" +
        "    }\n" +
        "  ]\n" +
        "}"

}