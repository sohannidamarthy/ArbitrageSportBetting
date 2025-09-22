/*
 * Copyright © 2025 Sohan G Nidamarthy
 * Copyrights © 2025 SRS Software Solutions
 * All rights reserved.
 */

package ArbitrageOpportunitesGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

public class ArbitrageOpportunitiesGUI {

private static final String API_KEY = "MY API KEY"; // Your API key
private static String API_URL="";
public String sport="";
public String selectedSport="";

public List<String> selected;

public String bookie="&bookmakers=";

private JFrame frame;
private JTextArea outputArea;

public JList<String> bookiesList;
HashMap<String, String> bookiesMap = new HashMap<>();

private JComboBox<String> sportSelector;

private JList<String> daySelector;
private JTextField totalAmountField;

private JCheckBox integerToggle;
private JCheckBox addToggle;
public Map<String, String> sportsMap = new HashMap<>();

public int addCheck=0;

StringBuilder result = new StringBuilder();
StringBuilder inline = new StringBuilder();



public ArbitrageOpportunitiesGUI() {
	
	//sportsMap
	// American Football
	sportsMap.put("CFL", "americanfootball_cfl");
	sportsMap.put("NCAAF", "americanfootball_ncaaf");
	sportsMap.put("NCAAF Championship Winner", "americanfootball_ncaaf_championship_winner");
	sportsMap.put("NFL", "americanfootball_nfl");
	sportsMap.put("NFL Preseason", "americanfootball_nfl_preseason");
	sportsMap.put("NFL Super Bowl Winner", "americanfootball_nfl_super_bowl_winner");
	sportsMap.put("UFL", "americanfootball_ufl");

	// Australian Rules Football
	sportsMap.put("AFL", "aussierules_afl");

	// Baseball
	sportsMap.put("MLB", "baseball_mlb");
	sportsMap.put("MLB Preseason", "baseball_mlb_preseason");
	sportsMap.put("MLB World Series Winner", "baseball_mlb_world_series_winner");
	sportsMap.put("Minor League Baseball", "baseball_milb");
	sportsMap.put("NPB", "baseball_npb");
	sportsMap.put("KBO League", "baseball_kbo");
	sportsMap.put("NCAA Baseball", "baseball_ncaa");

	// Basketball
	sportsMap.put("Basketball Euroleague", "basketball_euroleague");
	sportsMap.put("NBA", "basketball_nba");
	sportsMap.put("NBA Championship Winner", "basketball_nba_championship_winner");
	sportsMap.put("WNBA", "basketball_wnba");
	sportsMap.put("NCAAB", "basketball_ncaab");
	sportsMap.put("WNCAAB", "basketball_wncaab");
	sportsMap.put("NCAAB Championship Winner", "basketball_ncaab_championship_winner");
	sportsMap.put("NBL (Australia)", "basketball_nbl");

	// Boxing
	sportsMap.put("Boxing", "boxing_boxing");

	// Cricket
	sportsMap.put("Big Bash", "cricket_big_bash");
	sportsMap.put("Caribbean Premier League", "cricket_caribbean_premier_league");
	sportsMap.put("ICC Champions Trophy", "cricket_icc_trophy");
	sportsMap.put("ICC World Cup", "cricket_icc_world_cup");
	sportsMap.put("International Twenty20", "cricket_international_t20");
	sportsMap.put("IPL", "cricket_ipl");
cf	sportsMap.put("One Day Internationals", "cricket_odi");
	sportsMap.put("Pakistan Super League", "cricket_psl");
	sportsMap.put("T20 Blast", "cricket_t20_blast");
	sportsMap.put("Test Matches", "cricket_test_match");

	// Golf
	sportsMap.put("Masters Tournament Winner", "golf_masters_tournament_winner");
	sportsMap.put("PGA Championship Winner", "golf_pga_championship_winner");
	sportsMap.put("The Open Winner", "golf_the_open_championship_winner");
	sportsMap.put("US Open Winner", "golf_us_open_winner");

	// Ice Hockey
	sportsMap.put("NHL", "icehockey_nhl");
	sportsMap.put("AHL", "icehockey_ahl");
	sportsMap.put("NHL Championship Winner", "icehockey_nhl_championship_winner");
	sportsMap.put("Finnish Liiga", "icehockey_liiga");
	sportsMap.put("Finnish Mestis", "icehockey_mestis");
	sportsMap.put("SHL", "icehockey_sweden_hockey_league");
	sportsMap.put("HockeyAllsvenskan", "icehockey_sweden_allsvenskan");

	// Lacrosse
	sportsMap.put("Premier Lacrosse League", "lacrosse_pll");
	sportsMap.put("NCAA Lacrosse", "lacrosse_ncaa");

	// MMA
	sportsMap.put("MMA", "mma_mixed_martial_arts");

	// Politics
	sportsMap.put("US Presidential Elections Winner", "politics_us_presidential_election_winner");

	// Rugby
	sportsMap.put("NRL", "rugbyleague_nrl");
	sportsMap.put("Six Nations", "rugbyunion_six_nations");

	// Soccer
	sportsMap.put("Africa Cup of Nations", "soccer_africa_cup_of_nations");
	sportsMap.put("Primera División - Argentina", "soccer_argentina_primera_division");
	sportsMap.put("A-League", "soccer_australia_aleague");
	sportsMap.put("Austrian Football Bundesliga", "soccer_austria_bundesliga");
	sportsMap.put("Belgium First Div", "soccer_belgium_first_div");
	sportsMap.put("Brazil Série A", "soccer_brazil_campeonato");
	sportsMap.put("Brazil Série B", "soccer_brazil_serie_b");
	sportsMap.put("Primera División - Chile", "soccer_chile_campeonato");
	sportsMap.put("Super League - China", "soccer_china_superleague");
	sportsMap.put("Denmark Superliga", "soccer_denmark_superliga");
	sportsMap.put("Championship", "soccer_efl_champ");
	sportsMap.put("EFL Cup", "soccer_england_efl_cup");
	sportsMap.put("League 1", "soccer_england_league1");
	sportsMap.put("League 2", "soccer_england_league2");
	sportsMap.put("EPL", "soccer_epl");
	sportsMap.put("FA Cup", "soccer_fa_cup");
	sportsMap.put("FIFA World Cup", "soccer_fifa_world_cup");
	sportsMap.put("FIFA Women's World Cup", "soccer_fifa_world_cup_womens");
	sportsMap.put("FIFA World Cup Winner", "soccer_fifa_world_cup_winner");
	sportsMap.put("Veikkausliiga - Finland", "soccer_finland_veikkausliiga");
	sportsMap.put("Ligue 1 - France", "soccer_france_ligue_one");
	sportsMap.put("Ligue 2 - France", "soccer_france_ligue_two");
	sportsMap.put("Bundesliga - Germany", "soccer_germany_bundesliga");
	sportsMap.put("Bundesliga 2 - Germany", "soccer_germany_bundesliga2");
	sportsMap.put("Liga - Germany", "soccer_germany_liga3");
	sportsMap.put("Super League - Greece", "soccer_greece_super_league");
	sportsMap.put("Serie A - Italy", "soccer_italy_serie_a");
	sportsMap.put("Serie B - Italy", "soccer_italy_serie_b");
	sportsMap.put("J League", "soccer_japan_j_league");
	sportsMap.put("K League 1", "soccer_korea_kleague1");
	sportsMap.put("League of Ireland", "soccer_league_of_ireland");
	sportsMap.put("Liga MX", "soccer_mexico_ligamx");
	sportsMap.put("Dutch Eredivisie", "soccer_netherlands_eredivisie");
	sportsMap.put("Eliteserien - Norway", "soccer_norway_eliteserien");
	sportsMap.put("Ekstraklasa - Poland", "soccer_poland_ekstraklasa");
	sportsMap.put("Primeira Liga - Portugal", "soccer_portugal_primeira_liga");
	sportsMap.put("La Liga - Spain", "soccer_spain_la_liga");
	sportsMap.put("La Liga 2 - Spain", "soccer_spain_segunda_division");
	sportsMap.put("Premiership - Scotland", "soccer_spl");
	sportsMap.put("Allsvenskan - Sweden", "soccer_sweden_allsvenskan");
	sportsMap.put("Superettan - Sweden", "soccer_sweden_superettan");
	sportsMap.put("Swiss Superleague", "soccer_switzerland_superleague");
	sportsMap.put("Turkey Super League", "soccer_turkey_super_league");
	sportsMap.put("UEFA Europa Conference League", "soccer_uefa_europa_conference_league");
	sportsMap.put("UEFA Champions League", "soccer_uefa_champs_league");
	sportsMap.put("UEFA Champions League Qualification", "soccer_uefa_champs_league_qualification");
	sportsMap.put("UEFA Europa League", "soccer_uefa_europa_league");
	sportsMap.put("UEFA Euro 2024", "soccer_uefa_european_championship");
	sportsMap.put("UEFA Euro Qualification", "soccer_uefa_euro_qualification");
	sportsMap.put("UEFA Nations League", "soccer_uefa_nations_league");
	sportsMap.put("Copa América", "soccer_conmebol_copa_america");
	sportsMap.put("Copa Libertadores", "soccer_conmebol_copa_libertadores");
	sportsMap.put("MLS", "soccer_usa_mls");

	// Tennis
	sportsMap.put("ATP Australian Open", "tennis_atp_aus_open_singles");
	sportsMap.put("ATP Canadian Open", "tennis_atp_canadian_open");
	sportsMap.put("ATP China Open", "tennis_atp_china_open");
	sportsMap.put("ATP Cincinnati Open", "tennis_atp_cincinnati_open");
	sportsMap.put("ATP Dubai Championships", "tennis_atp_dubai");
	sportsMap.put("ATP French Open", "tennis_atp_french_open");
	sportsMap.put("ATP Indian Wells", "tennis_atp_indian_wells");
	sportsMap.put("ATP Miami Open", "tennis_atp_miami_open");
	sportsMap.put("ATP Paris Masters", "tennis_atp_paris_masters");
	sportsMap.put("ATP Qatar Open", "tennis_atp_qatar_open");
	sportsMap.put("ATP Shanghai Masters", "tennis_atp_shanghai_masters");
	sportsMap.put("ATP US Open", "tennis_atp_us_open");
	sportsMap.put("ATP Wimbledon", "tennis_atp_wimbledon");
	sportsMap.put("WTA Australian Open", "tennis_wta_aus_open_singles");
	sportsMap.put("WTA Canadian Open", "tennis_wta_canadian_open");
	sportsMap.put("WTA China Open", "tennis_wta_china_open");
	sportsMap.put("WTA Cincinnati Open", "tennis_wta_cincinnati_open");
	sportsMap.put("WTA Dubai Championships", "tennis_wta_dubai");
	sportsMap.put("WTA French Open", "tennis_wta_french_open");
	sportsMap.put("WTA Indian Wells", "tennis_wta_indian_wells");
	sportsMap.put("WTA Miami Open", "tennis_wta_miami_open");
	sportsMap.put("WTA Qatar Open", "tennis_wta_qatar_open");
	sportsMap.put("WTA US Open", "tennis_wta_us_open");
	sportsMap.put("WTA Wimbledon", "tennis_wta_wimbledon");
	sportsMap.put("WTA Wuhan Open", "tennis_wta_wuhan_open");
	
	//Bookies Bets
	// US
    bookiesMap.put("BetOnline.ag", "betonlineag");
    bookiesMap.put("BetMGM", "betmgm");
    bookiesMap.put("BetRivers", "betrivers");
    bookiesMap.put("BetUS", "betus");
    bookiesMap.put("Bovada", "bovada");
    bookiesMap.put("Caesars", "williamhill_us");
    bookiesMap.put("DraftKings", "draftkings");
    bookiesMap.put("Fanatics", "fanatics");
    bookiesMap.put("FanDuel", "fanduel");
    bookiesMap.put("LowVig.ag", "lowvig");
    bookiesMap.put("MyBookie.ag", "mybookieag");
    bookiesMap.put("Bally Bet", "ballybet");
    bookiesMap.put("BetAnySports", "betanysports");
    bookiesMap.put("betPARX", "betparx");
    bookiesMap.put("ESPN BET", "espnbet");
    bookiesMap.put("Fliff", "fliff");
    bookiesMap.put("Hard Rock Bet", "hardrockbet");
    
    
    // Fantasy/Sweepstakes
    //bookiesMap.put("PrizePicks", "prizepicks");
    bookiesMap.put("Underdog Fantasy", "underdog");
    
    // Misc/Prop
    bookiesMap.put("BetOpenly", "betopenly");
    
    bookiesMap.put("ProphetX", "prophetx");
    
    // UK
    bookiesMap.put("888sport", "sport888");
    bookiesMap.put("Betfair Exchange", "betfair_ex_uk");
    bookiesMap.put("Bet Victor", "betvictor");
    bookiesMap.put("Betway", "betway");
    bookiesMap.put("BoyleSports", "boylesports");
    bookiesMap.put("Casumo", "casumo");
    bookiesMap.put("Coral", "coral");
    bookiesMap.put("Grosvenor", "grosvenor");
    bookiesMap.put("Ladbrokes", "ladbrokes_uk");
    bookiesMap.put("LeoVegas", "leovegas");
    bookiesMap.put("LiveScore Bet", "livescorebet");
    bookiesMap.put("Matchbook", "matchbook");
    bookiesMap.put("Paddy Power", "paddypower");
    bookiesMap.put("Sky Bet", "skybet");
    bookiesMap.put("Smarkets", "smarkets");
    bookiesMap.put("Unibet", "unibet_uk");
    bookiesMap.put("Virgin Bet", "virginbet");
    bookiesMap.put("William Hill (UK)", "williamhill");
    
    // Europe
    bookiesMap.put("1xBet", "onexbet");
    bookiesMap.put("Betclic", "betclic");
    bookiesMap.put("Betfair Exchange", "betfair_ex_eu");  // Overwrites UK entry
    bookiesMap.put("Betsson", "betsson");
    bookiesMap.put("Coolbet", "coolbet");
    bookiesMap.put("Everygame", "everygame");
    bookiesMap.put("GTbets", "gtbets");
    bookiesMap.put("Marathon Bet", "marathonbet");
    bookiesMap.put("NordicBet", "nordicbet");
    bookiesMap.put("Pinnacle", "pinnacle");
    bookiesMap.put("Suprabets", "suprabets");
    bookiesMap.put("Tipico (DE)", "tipico_de");
    bookiesMap.put("Unibet", "unibet_eu");  // Overwrites UK entry
    bookiesMap.put("William Hill", "williamhill");
    bookiesMap.put("Winamax (DE)", "winamax_de");
    bookiesMap.put("Winamax (FR)", "winamax_fr");
    
    // Australia
    bookiesMap.put("Betfair Exchange", "betfair_ex_au");  // Overwrites EU/UK entries
    bookiesMap.put("Betr", "betr_au");
    bookiesMap.put("Bet Right", "betright");
    bookiesMap.put("BoomBet", "boombet");
    bookiesMap.put("Ladbrokes", "ladbrokes_au");  // Overwrites UK entry
    bookiesMap.put("Neds", "neds");
    bookiesMap.put("PlayUp", "playup");
    bookiesMap.put("PointsBet (AU)", "pointsbetau");
    bookiesMap.put("SportsBet", "sportsbet");
    bookiesMap.put("TAB", "tab");
    bookiesMap.put("TABtouch", "tabtouch");
    bookiesMap.put("TopSport", "topsport");
    bookiesMap.put("Unibet", "unibet");  // Final overwrite

frame = new JFrame("Arbitrage Opportunities");
frame.setSize(800, 600);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setLayout(new BorderLayout());

outputArea = new JTextArea();
outputArea.setEditable(false);
JScrollPane scrollPane = new JScrollPane(outputArea);
frame.add(scrollPane, BorderLayout.CENTER);

JPanel controlPanel = new JPanel();
controlPanel.setLayout(new FlowLayout());

// Year selector (allows any future year)





sportSelector = new JComboBox<>(new String[]{
	    // American Football
	    "CFL",
	    "NCAAF",
	    "NCAAF Championship Winner",
	    "NFL",
	    "NFL Preseason",
	    "NFL Super Bowl Winner",
	    "UFL",
	    
	    // Australian Rules Football
	    "AFL",
	    
	    // Baseball
	    "MLB",
	    "MLB Preseason",
	    "MLB World Series Winner",
	    "Minor League Baseball",
	    "NPB",
	    "KBO League",
	    "NCAA Baseball",
	    
	    // Basketball
	    "Basketball Euroleague",
	    "NBA",
	    "NBA Championship Winner",
	    "WNBA",
	    "NCAAB",
	    "WNCAAB",
	    "NCAAB Championship Winner",
	    "NBL (Australia)",
	    
	    // Boxing
	    "Boxing",
	    
	    // Cricket
	    "Big Bash",
	    "Caribbean Premier League",
	    "ICC Champions Trophy",
	    "ICC World Cup",
	    "International Twenty20",
	    "IPL",
	    "One Day Internationals",
	    "Pakistan Super League",
	    "T20 Blast",
	    "Test Matches",
	    
	    // Golf
	    "Masters Tournament Winner",
	    "PGA Championship Winner",
	    "The Open Winner",
	    "US Open Winner",
	    
	    // Ice Hockey
	    "NHL",
	    "AHL",
	    "NHL Championship Winner",
	    "Finnish Liiga",
	    "Finnish Mestis",
	    "SHL",
	    "HockeyAllsvenskan",
	    
	    // Lacrosse
	    "Premier Lacrosse League",
	    "NCAA Lacrosse",
	    
	    // MMA
	    "MMA",
	    
	    // Politics
	    "US Presidential Elections Winner",
	    
	    // Rugby
	    "NRL",
	    "Six Nations",
	    
	    // Soccer
	    "Africa Cup of Nations",
	    "Primera División - Argentina",
	    "A-League",
	    "Austrian Football Bundesliga",
	    "Belgium First Div",
	    "Brazil Série A",
	    "Brazil Série B",
	    "Primera División - Chile",
	    "Super League - China",
	    "Denmark Superliga",
	    "Championship",
	    "EFL Cup",
	    "League 1",
	    "League 2",
	    "EPL",
	    "FA Cup",
	    "FIFA World Cup",
	    "FIFA Women's World Cup",
	    "FIFA World Cup Winner",
	    "Veikkausliiga - Finland",
	    "Ligue 1 - France",
	    "Ligue 2 - France",
	    "Bundesliga - Germany",
	    "Bundesliga 2 - Germany",
	    "Liga - Germany",
	    "Super League - Greece",
	    "Serie A - Italy",
	    "Serie B - Italy",
	    "J League",
	    "K League 1",
	    "League of Ireland",
	    "Liga MX",
	    "Dutch Eredivisie",
	    "Eliteserien - Norway",
	    "Ekstraklasa - Poland",
	    "Primeira Liga - Portugal",
	    "La Liga - Spain",
	    "La Liga 2 - Spain",
	    "Premiership - Scotland",
	    "Allsvenskan - Sweden",
	    "Superettan - Sweden",
	    "Swiss Superleague",
	    "Turkey Super League",
	    "UEFA Europa Conference League",
	    "UEFA Champions League",
	    "UEFA Champions League Qualification",
	    "UEFA Europa League",
	    "UEFA Euro 2024",
	    "UEFA Euro Qualification",
	    "UEFA Nations League",
	    "Copa América",
	    "Copa Libertadores",
	    "MLS",
	    
	    // Tennis
	    "ATP Australian Open",
	    "ATP Canadian Open",
	    "ATP China Open",
	    "ATP Cincinnati Open",
	    "ATP Dubai Championships",
	    "ATP French Open",
	    "ATP Indian Wells",
	    "ATP Miami Open",
	    "ATP Paris Masters",
	    "ATP Qatar Open",
	    "ATP Shanghai Masters",
	    "ATP US Open",
	    "ATP Wimbledon",
	    "WTA Australian Open",
	    "WTA Canadian Open",
	    "WTA China Open",
	    "WTA Cincinnati Open",
	    "WTA Dubai Championships",
	    "WTA French Open",
	    "WTA Indian Wells",
	    "WTA Miami Open",
	    "WTA Qatar Open",
	    "WTA US Open",
	    "WTA Wimbledon",
	    "WTA Wuhan Open"
	});
sportSelector.addActionListener(e ->{
	 
	
	selectedSport = (String) sportSelector.getSelectedItem();
	
	sport= sportsMap.get(selectedSport);
	System.out.println(selectedSport);
	System.out.println(sport);
	API_URL = String.format("https://api.the-odds-api.com/v4/sports/%s/odds?apiKey=%s", sport, API_KEY);
	System.out.println(API_URL);
});
controlPanel.add(new JLabel("Select Sport: "));

controlPanel.add(sportSelector);



String[] bookies = {
	    // US
	    "BetOnline.ag", "BetMGM", "BetRivers", "BetUS", "Bovada", "Caesars", "DraftKings",
	    "Fanatics", "FanDuel", "LowVig.ag", "MyBookie.ag", "Bally Bet", "BetAnySports",
	    "betPARX", "ESPN BET", "Fliff", "Hard Rock Bet",
	    
	    // Fantasy/Sweepstakes
	    "Underdog Fantasy",
	    
	    // Misc/Prop
	    "BetOpenly","ProphetX",
	    
	    // UK
	    "888sport", "Betfair Exchange","Bet Victor", "Betway",
	    "BoyleSports", "Casumo", "Coral", "Grosvenor", "Ladbrokes", "LeoVegas",
	    "LiveScore Bet", "Matchbook", "Paddy Power", "Sky Bet", "Smarkets", "Unibet",
	    "Virgin Bet", "William Hill (UK)",
	    
	    // Europe
	    "1xBet", "Betclic", "Betsson", "Coolbet", "Everygame", "GTbets", "Marathon Bet",
	    "NordicBet", "Pinnacle", "Suprabets", "Tipico (DE)", "William Hill", "Winamax (DE)",
	    "Winamax (FR)",
	    
	    // Australia
	    "Betr", "Bet Right", "BoomBet", "Neds", "PlayUp", "PointsBet (AU)", "SportsBet",
	    "TAB", "TABtouch", "TopSport"
	};
this.bookiesList = new JList<>(bookies);
bookiesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
JScrollPane scrollPane1 = new JScrollPane(bookiesList);
scrollPane1.setPreferredSize(new Dimension(150, 200));
scrollPane1.setBorder(BorderFactory.createTitledBorder("Select bookies"));
controlPanel.add(scrollPane1, BorderLayout.WEST);






totalAmountField = new JTextField(10);
controlPanel.add(new JLabel("Total Bet Amount: "));
controlPanel.add(totalAmountField);

integerToggle = new JCheckBox("Integer Betting");
controlPanel.add(integerToggle);


JCheckBox addToggle = new JCheckBox("All Bets?");
controlPanel.add(addToggle);

addToggle.addItemListener(e -> {
    if (e.getStateChange() == ItemEvent.SELECTED) {
        addCheck = 1;
    } else {
        addCheck = 0;
    }
});

JButton fetchButton = new JButton("Fetch Opportunities");
fetchButton.addActionListener(new ActionListener(){
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		fetchArbitrageOpportunities();
	}
});
controlPanel.add(fetchButton);

JButton button = new JButton("Validate");

//Add an ActionListener to the button
button.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
     new Validate();
     frame.dispose();
     
 }
});

//Add button to the frame
controlPanel.add(button);


frame.add(controlPanel, BorderLayout.NORTH);
frame.setVisible(true);

JButton button2 = new JButton("Reset");

//Add an ActionListener to the button
button2.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		selected.clear();
		System.out.println(selected);
		API_URL="";
		result.setLength(0);
		inline.setLength(0);
		result.append("finished");
		outputArea.setText(result.toString());
		bookie="&bookmakers=";
	}


});
controlPanel.add(button2);

}


 
//addCheck=1;

//read();
//fetch();
private void fetchArbitrageOpportunities() {
	String totalAmountText = totalAmountField.getText();
	if (!totalAmountText.matches("\\d+(\\.\\d+)?")) {
	JOptionPane.showMessageDialog(frame, "Invalid total bet amount. Please enter a valid number.");
	return;
	}
	double totalAmount = Double.parseDouble(totalAmountText);
	
	


try {





outputArea.setText("Fetching data... Please wait.");
result = new StringBuilder();
if(addCheck==0) {
	read();
	fetch();
}else if(addCheck==1) {
	System.out.print("this is correct");
	allInitial();
	
}




parseAndDisplayData(inline.toString(), result, totalAmount);


outputArea.setText(result.toString());
} catch (Exception ex) {
outputArea.setText("Error: " + ex.getMessage());
ex.printStackTrace();
}
}

private void parseAndDisplayData(String jsonData, StringBuilder result, double totalAmount) {
try {
JSONArray jsonArray = new JSONArray(jsonData);
System.out.println(jsonArray);
if (jsonArray == null || jsonArray.length()==0) {
    result.append("This sport is not in season\n\n");
    System.out.println("This sport is not in season");
    return;
}
for (int i = 0; i < jsonArray.length(); i++) {
JSONObject obj = jsonArray.getJSONObject(i);
String game = obj.optString("home_team", "Unknown Team") + " vs " + obj.optString("away_team", "Unknown Team");
String commenceTime = obj.optString("commence_time", "Unknown Time");

JSONArray bookmakers = obj.optJSONArray("bookmakers");
if (bookmakers == null) {
result.append("No bookmakers found for ").append(game).append(" on ").append("Commence Time: ").append(commenceTime).append("\n\n");
continue;
}

// Track best odds and bookmakers for home and away teams
double bestHomeOdds = 0;
double bestAwayOdds = 0;
String bestHomeSite = "";
String bestAwaySite = "";
if (bookmakers == null || bookmakers.length() == 0) {
    result.append("This sport is not in season\n\n");
    System.out.println("This sport is not in season");
    continue;
}
for (int j = 0; j < bookmakers.length(); j++) {
JSONObject bookmaker = bookmakers.getJSONObject(j);
String site = bookmaker.optString("title", "Unknown Site");
JSONArray outcomes = bookmaker.getJSONArray("markets").getJSONObject(0).getJSONArray("outcomes");

// Parse home and away odds
double homeOdds = outcomes.getJSONObject(0).optDouble("price", Double.MAX_VALUE);
double awayOdds = outcomes.getJSONObject(1).optDouble("price", Double.MAX_VALUE);

// Update best home odds and bookmaker
if (homeOdds >= bestHomeOdds) {
bestHomeOdds = homeOdds;
bestHomeSite = site;
}

// Update best away odds and bookmaker
if (awayOdds >= bestAwayOdds) {
bestAwayOdds = awayOdds;
bestAwaySite = site;
}
}

// Debugging: Print parsed odds
System.out.println("Game: " + game);
System.out.println("Best Home Odds: " + bestHomeOdds + " (" + bestHomeSite + ")");
System.out.println("Best Away Odds: " + bestAwayOdds + " (" + bestAwaySite + ")");
System.out.println("Commence Time: "+commenceTime);

// Ensure odds are valid
if (bestHomeOdds == 0 || bestAwayOdds == 0) {
result.append("Invalid odds for ").append(game).append(" on ").append("\n\n");
continue;
}

// Calculate total inverse odds
double totalInverseOdds = (1 / bestHomeOdds) + (1 / bestAwayOdds);
if (totalInverseOdds < 1) {
// Calculate bet amounts
double homeBetAmount = (totalAmount / bestHomeOdds) / totalInverseOdds;
double awayBetAmount = (totalAmount / bestAwayOdds) / totalInverseOdds;
double potentialProfit = (totalAmount / totalInverseOdds) - totalAmount;

// Round bet amounts if integer betting is enabled
if (integerToggle.isSelected()) {
homeBetAmount = Math.round(homeBetAmount);
awayBetAmount = Math.round(awayBetAmount);
}
double homeOddsAmerican = convertToAmerican(bestHomeOdds);
double awayOddsAmerican = convertToAmerican(bestAwayOdds);

// Append resultt
result.append("Bet on ").append(bestHomeSite)
.append(" for ").append(obj.optString("home_team"))
.append(" at ").append(String.format("%.2f", bestHomeOdds))
.append(" (").append(homeOddsAmerican > 0 ? "+" : "").append(String.format("%.0f", homeOddsAmerican)).append(")")
.append(" - ").append(String.format("$%.2f", homeBetAmount)).append("\n")

.append("Bet on ").append(bestAwaySite)
.append(" for ").append(obj.optString("away_team"))
.append(" at ").append(String.format("%.2f", bestAwayOdds))
.append(" (").append(awayOddsAmerican > 0 ? "+" : "").append(String.format("%.0f", awayOddsAmerican)).append(")")
.append(" - ").append(String.format("$%.2f", awayBetAmount)).append("\n")

.append("Net Gain: $").append(String.format("%.2f", potentialProfit)).append("\n")
.append("Commence Time: ").append(commenceTime).append("\n\n");
} else {
result.append("No arbitrage opportunity for ").append(game).append("\n\n");
}
}
} catch (Exception ex) {
result.append("Error parsing data for ").append(": ").append(ex.getMessage()).append("\n\n");
ex.printStackTrace();

}


}
public static double convertToAmerican(double decimalOdds) {
    if (decimalOdds >= 2.00) {
        return (decimalOdds - 1) * 100;
    } else {
        return -100 / (decimalOdds - 1);
    }
    
}
public void read(){
	System.out.println(selected);
	selected = bookiesList.getSelectedValuesList();
	int bookieNum=0;
	for(int i=0;i<=selected.size()-1;i++) {
		String holder= bookiesMap.get(selected.get(i));
		bookieNum+=1;
		if(i==0) {
			bookie=bookie+holder;
		}else {
			bookie=bookie+","+holder;
		}
		
	}
	System.out.println(bookieNum);
	
	
	
	
	
}

public void fetch() throws IOException {
	System.out.print(bookie);
	String first=API_URL + "&regions=us,us2,us_dfs,uk,us_ex,eu,au&markets=h2h";
	String apiUrl = first+bookie;
	System.out.println(apiUrl);
	URL url = new URL(apiUrl);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.connect();

	int responseCode = conn.getResponseCode();



	if (responseCode == 403) {
	result.append("Error: API key expired or invalid\n\n");
	} else if (responseCode == 429) {
	result.append("Error: API request limit reached\n\n");
	}else if(responseCode==422){
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		String inputLine;
		StringBuilder content = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		System.out.println("Error Response: " + content.toString());
	}else if (responseCode != 200) {
	result.append("Error fetching data for ").append(": ").append(responseCode).append("\n\n");
	}

	System.out.println(apiUrl);

	Scanner scanner = new Scanner(conn.getInputStream());
	while (scanner.hasNext()) {
	inline.append(scanner.nextLine());
	}
	scanner.close();
}
//Map.Entry<String, String>entry2: bookiesMap.entrySet()
public void allInitial() throws IOException{
	List<String>bookiesKey=new ArrayList<>(bookiesMap.keySet());
	System.out.print(bookiesKey);
	int j=0;
	int i=0;
	while(i<=39 && j<bookiesKey.size()){
		String holder= bookiesMap.get(bookiesKey.get(j));
		System.out.println(holder);
		if(i==0) {
			bookie=bookie+holder;
		}else {
			bookie=bookie+","+holder;
		}
		if(i==39 || j==bookiesKey.size()-1) {
			fetch();
			System.out.print("works here as well");
			bookie="&bookmakers=";
			i=-1;
		}
		i+=1;
		j+=1;
		
	}
	
}


public static void main(String[] args) {
new ArbitrageOpportunitiesGUI();
}
}

