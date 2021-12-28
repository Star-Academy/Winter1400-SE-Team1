import classes.DocumentScanner;
import classes.InvertedIndex;
import classes.PorterStemmer;
import classes.SearchEngine;
import controller.AppController;
import controller.AppRunner;
import exception.BaseDirectoryInvalidException;
import exception.SearchException;
import model.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class Test1 {
    AppController appController = mock(AppController.class);

    @org.junit.jupiter.api.Test
    public void getDocsInDirectoryTest() throws IOException, URISyntaxException, BaseDirectoryInvalidException {
        String expectedId = "58044";
        String expectedContent = "Announcing. . . Announcing. . . Announcing. . .Announcing. .   " +
                "                           CELEBRATE LIBERTY                 1993 LIBERTARIAN PARTY NATIONAL CONVENTIO" +
                "                             AND POLITICAL EXP                   THE MARRIOTT HOTEL AND THE SALT PALAC" +
                "                            SALT LAKE CITY, UTA                   INCLUDES INFORMATION ON DELEGATE DEALS" +
                "                          (Back by Popular Demand!The convention will be held at the Salt Palace Convention Center" +
                " and thMarriott Hotel, Salt Lake City, Utah.  The business sessions, Karl HesInstitute, and Political " +
                "Expo are at the Salt Palace; breakfasts, parties, anbanquet are at the Marriott HotelMarriott Hotel room" +
                " rates are $79.00 night, plus 10.5% tax ($87.17 total).This rate is good for one to four persons room occupancy." +
                "  Double is onor two beds; 3 or 4 people is 2 beds.  You can make your reservationdirect with the hotel (801-531-0800)" +
                ", or you can purchase your roothrough one of MGP's payment plans.  MGP will provide assistance imatching roommates if" +
                " requestedAugust 30, 31, Sept. 1:           Everything You Always Wanted t                             " +
                "     Know About Winning Elections, bu                                  Didn't Know Where to AskThree" +
                " days of intensive campaign training conducted by Sal Guzzetta, 25 year veteran of more than 200 campaigns." +
                "  Students receive 990 pageof professional campaign manuals.  Everything from strategy antargeting to opposition research," +
                " fundraising, and field operationsPrice:           $150 if purchased by May 1, 199" +
                "                 $175 thereafteAugust 31 and Sept. 1:            Platform, Bylaws, Credentials an" +
                "                                  National committee meetingsShoot out in Salt Lake!  PLEDGE versus" +
                " Committee for a LibertariaMajority.  Will the party's membership and platform definitions change?Is" +
                " compromise possible?  The Platform and Bylaws committees arresponsible for making recommendations to the convention" +
                " concerninchanges in those documents.  At this convention, the party will onlconsider deletions to the platform." +
                "  The Convention Rules would have tbe amended by a 2/3 vote to change this ruleThe meetings are open to the public." +
                "  There is no charge for attending.Sept. 2-5, 1993: Celebrate Liberty! Begin                 " +
                "Political Expo OpenSept. 2, 1993:   9 AM -- Credentials Committee report to the delegates              " +
                "   10:30 -- Gala Opening Ceremony and Keynote Addres                 by Russell Means.                 " +
                " 1:00 -- After lunch break, convention business continue                 " +
                "(see \"Standing Order of Business\" from the \"Conventio                 " +
                "Rules of the Libertarian Party\" at the end of thi                 document                 " +
                "Karl Hess Institute of Libertarian Politics Begins, runs i                 " +
                "tandem with the business sessionsSept. 3, 1993:   Dawns Early Light, Green Dragon Inn (morning an" +
                "                 evening), with Karl Hess Institute and conventio                 " +
                "business in betweenSept. 4, 1993:   Dawns Early Light, Freedom Rock '93, Karl Hes                 " +
                "Institute, convention businessSept. 5, 1993:   Dawns Early Light, Convention Banquet, Karl Hes       " +
                "          Institute, convention business, Joyful Noise                      ACTIVITY DESCRIPTIONSDAWNS EARLYLIGHT " +
                "           Three great convention breakfasts to start your day             " +
                "    right, featuring science fiction author L. Neil Smith            " +
                "     psychiatrist and author Dr. Thomas Szasz, and Sout            " +
                "     African Libertarian leader Frances KendallGREEN DRAGONINN         " +
                "     \"Opening night\" party, named after the famous in              " +
                "   where Sam Adams and his crowd plotted trouble for th             " +
                "    British over pints of ale and beer.  Music, food, drink         " +
                "        and comedyFREEDOMROCK '93         Free downtown rock concert Friday night, with dru     " +
                "            circle, comic Tim Slagle, Middle Eastern dancer, reggae              " +
                "   and local classic rock-n-roll bands.  Will be widel               " +
                "  publicized in the local area.  Major outreach opportunityBANQUET     " +
                "     Vivaldi and Mozart, fine dining, in the elegant Marriot           " +
                "      Grand Ballroom (black tie optional).  Dancing followsPOLITICALEXPO     " +
                "        Exhibits and vendors.  FREE admission.  Event will b             " +
                "    widely publicized in local area for maximum draw.                 Major Outreach opportunity        " +
                "        KARL HESS INSTITUTE OF LIBERTARIAN  POLITIC     " +
                "    Workshops, speakers, roundtable discussions in these areasLIBERTY:  NEXGENERATION       " +
                "        High school and college age Libertarians tal                   " +
                "      about what matters to them and the 20                         something generationAGENDA 2000    " +
                "          Considers key issues of the 1990s.  Environment.                    " +
                "     Health Care.  21st Century Economics.  Dru                       " +
                "  War.  Second Amendment.  Social Services.                 " +
                "        Foreign Policy.  Crime & Violence.  AIDSTHE GREAT DEBATE      " +
                "   LP Strategy and tactics.  Media.  Ballot Access." +
                "                         Initiatives.  Feminist Issues.  Presidentia                      " +
                "   Campaigns.  LP Elected Officials.  Grassroots.                     " +
                "    Early look at the 1996 presidential nominationVALUES FORTHE 90s            " +
                "      Community.  Children.  Abundance.  Hom                      " +
                "   Schooling.  Religion and Liberty.  RaceCAMPUS FOCUS          " +
                "   Organizing.  Academia.  Blue Collar Youth              " +
                "   CONVENTION PACKAGE DESCRIPTIONS AND PRICETOTAL EVENT:       " +
                "      All activities, Aug. 30-Sept. 5, $400, including         " +
                "                 day candidate traininFull Celebration:     " +
                "   All convention activities, Sept. 2-5, $30Late Riser        " +
                "       No breakfasts, everything else Sept. 2-5, $25Thrift                   No breakfasts or banquet, $15Issues Focus             Karl Hess Institute, $12Basic                    Convention packet, souvenirs, two Karl Hes                         Institute speakerFree                     Political Expo, Access to convention hall                         Keynote Address, Joyful Noise, Freedom Roc                         '93, three free outreach speakersPLEASE NOTE--       PRICES INCREASE MAY 1, 199--       Special student prices are available to anyone under 25 years o         age or who is enrolled in a college or university--       Six and seven month payment plans are available which ca         include housing (if requested)--       To add the three day candidate training to any package belo         (except \"Total Event\"), add $150 to the price--       All prices are in U.S. dollars--       Advertising is available in the convention program; exhibits an         sponsorships are available for the Political Expo.  Free Politica         Expo admission and MGP promotions will draw visitors from th         surrounding community (one million people live within a 3         minute drive of the Expo)--       If your special interest group, organization, committee, or caus         would like to schedule space for a presentation, contact us--       MGP conducts a drawing each month and gives away FRE         hotel nights.  The sooner you register, the more chances you hav         to win--       Roommate match service available upon requestOTHER EVENTS\"Anti-Federalist Two\"    MGP sponsored writing contest.  Jun                         submission deadline.  Contact MGP fo                         prospectus\"The LibertarianGames\"                   Friendly competition -- marksmanship, compute                         programming, chess, maybe moreLibertarians for Gay Lesbian Concerns         Business meeting, social night, sponsored b                         LGLC???                      YOUR EVENT CAN BE LISTED HERE.  Contac                         MGP for details                         ATTENTION COLLEGE STUDENTSSpecial discounts are available for college and high school students.  Wwill work on casual housing opportunities for the \"Poverty Caucus\".College Libertarians will meet at Celebrate Liberty! and discuss the futurof their movement on campuses.  Contact MGP for more details\\t\\t\\t   LIST OF SPEAKER\\t\\t       (as of March 14, 1993)Dean Ahmad\\t\\tJim Hudler\\t\\tSheldon RichmaKaren Allard\\t\\tJeff Hummel\\t\\tKathleen RichmaRick Arnold\\t\\tAlexander Joseph\\tDan RosenthaDr. George Ayittey\\tFrances Kendall\\t\\tDr. Mary RuwarAlan Boch\\t\\tMartin Luther King\\tDagny SharoRichard Boddie\\t\\tMe-Me King\\t\\tJane ShaGus Dizerega\\t\\tHenry Lamb\\t\\tSandy ShaLarry Dodge\\t\\tAmy Lassen\\t\\tL. Neil SmitDr. Richard Ebeling\\tScott Lieberman\\t\\tEric SterlinDon Ernsberger\\t\\tDr. Nancy Lord\\t\\tDr. Richard StrouBill Evers\\t\\tRussell Means\\t\\tDr. Thomas SzasBonnie Flickenger\\tVince Miller\\t\\tMichael TanneJohn Fund\\t\\tMaury Modine\\t\\tSojourner TrutDoris Gordon\\t\\tDavid Nolan\\t\\tYuri TuviLeon Hadar\\t\\tRandall O'Toole\\t\\tBob WaldroPatrick Henry\\t\\tJames Ostrowski\\t\\tTerree WasleKarl Hess\\t\\tDirk Pearson\\t\\tPerry WilliDr. Karl Hess Jr.\\tBob Poole\\t\\tRichard WingeJacob Honrberger\\tCarole Ann Rand\\t\\tJarret Wollstei\\t\\t\\t\\t\\t\\tBrigham Youn\\t\\t  UPCOMING CONVENTION DEVELOPMENTSOn May 1st, prices increase for convention packages, candidate trainingand exhibits/advertisingNew prices for convention packages will beTotal Event:             $45Full Celebration:        $35Late Riser:              $27Thrift:                  $17Issues Focus:            $15Basic:                   $3Free:                    $These prices good through July 2, 1993                          BACK BY POPULAR DEMAND!                        ANNOUNCING THE DELEGATE DEALS                            Available May 1, 199I:       Business Focus:  All convention activities except Karl Hes         Institute -- $27II:      Delegate Celebration, includes a complete set of Karl Hes         Institute audio tapes instead of institute tickets -- $35                       STANDING ORDER OF BUSINESS FO                       A LIBERTARIAN PARTY CONVENTIO     1. Call to orde     2. Credentials Committee repor     3. Adoption of agend     4. Treasurer's repor     5. Bylaws and Rules Committee report (Non-nominating convention        only     6. Platform Committee report (At non-Presidential nominatin        conventions only deletions may be considered.     7. Nomination of Party candidates for President an        Vice-President (in appropriate years     8. Election of Party Officers and at-large member        of the National Committe     9. Election of Judicial Committe     10. Resolution     11. Other busines                         FOR QUESTIONS OR COMMENTS                             GRUMBLES OR GRINS                          SUGGESTIONS OR CRITICISM                                     AN                                TO REGISTER                                  CONTACT                       MORNING GLORY PRODUCTIONS, INC                               P.O. Box 52617                          Salt Lake City, UT  8415                                801.582.331                E-mail: Bob.Waldrop@f418.n104.z1.fidonet.or           Make Checks Payable to Morning Glory Productions, Inc--\\t\\t      Don't blame me; I voted LibertarianDisclaimer: I speak for myself, except as noted; Copyright 1993 Rich ThomsoUUCP: ...!uunet!dsd.es.com!rthomson\\t\\t\\tRich ThomsoInternet: rthomson@dsd.es.com\\tIRC: _Rich_\\t\\tPEXt Programme";
        DocumentScanner documentScanner = DocumentScanner.getInstance(Path.of(("./src/SampleEnglishData")));
        Set<Path> documents = documentScanner.getAllFilesInPath();
        Path wantedDocument = null;
        for (Path document : documents)
            if (documentScanner.getPathData(document).equals(expectedContent))
                wantedDocument = document;
        Assertions.assertEquals(documentScanner.getPathData(wantedDocument), expectedContent);
        Assertions.assertEquals(wantedDocument.getFileName().toString(), expectedId);
        Assertions.assertNotNull(documentScanner.getIndex().getWordIndexes("word"));
    }

    @Test
    public void docTester() {
        Document document = new Document(Path.of("SampleEnglishData/58049"));
        Document document1 = new Document(Path.of("SampleEnglishData/58057"));
        Assertions.assertNotNull(document.getDocumentPath());
        Assertions.assertNotEquals(document, new Object());
        Assertions.assertNotEquals(document, document1);
        Assertions.assertEquals(-1, document.compareTo(document1));
    }

    @Test
    public void exceptionTester() {
        try {
            DocumentScanner.getInstance(Path.of("adasddsadfiladsf"));
        } catch (BaseDirectoryInvalidException | IOException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void exceptionTester1() {
        try {
            AppController appController = new AppController();
            appController.search("fdljsalsdas");
        } catch (SearchException exception) {
            exception.printStackTrace();
            Assertions.assertNotNull(exception.getMessage());
        }
    }

    @Test
    public void ShouldSearchForProperDocs() throws SearchException, BaseDirectoryInvalidException, IOException {
        HashSet<String> listOfDocsHavingWordOld = new HashSet<>();
        listOfDocsHavingWordOld.add("57110");
        HashSet<String> listOfDocsHavingWordFriend = new HashSet<>();
        listOfDocsHavingWordFriend.add("57110");
        HashSet<String> listOfDocsHavingWordProblem = new HashSet<>();
        listOfDocsHavingWordProblem.add("58045");
        HashSet<String> listOfDocsHavingWordDoctor = new HashSet<>();
        listOfDocsHavingWordDoctor.add("59652");
        HashSet<String> listOfDocsHavingWordRemember = new HashSet<>();
        listOfDocsHavingWordRemember.add("58045");
        AppController appController = new AppController();
        appController.init();
        Set<Document> results = appController.search("old friend +problem +doctor -remember");
        ArrayList<String> list = new ArrayList<>();
        for (Document document : results) {
            list.add(document.getDocumentPath().toString().replaceFirst("./src/SampleEnglishData", ""));
        }
        Assertions.assertEquals(6, results.size(), "size of result is not equal with actual size");
        Assertions.assertFalse(results.contains("59652"), "search engine is wrong");
        Assertions.assertFalse(results.contains("57110"), "search engine is wrong");
    }

    @Test
    public void modifySentenceShouldWork() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        final String test1 = "    National SOciaLize steps";
        final String test2 = "Buddhism islamic republic";
        final String test3 = "           anti socialized";
        final String test4 = "HelLo HOWD ";
        PorterStemmer method = PorterStemmer.getInstance();
        Assertions.assertEquals((String) method.stemWord(test1).trim(), "national socialize step");
        Assertions.assertEquals((String) method.stemWord(test2).trim(), "buddhism islamic republ");
        Assertions.assertEquals((String) method.stemWord(test3).trim(), "anti soci");
        Assertions.assertEquals((String) method.stemWord(test4).trim(), "hello howd");
    }

    @Test
    public void printTester() throws BaseDirectoryInvalidException, IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, SearchException {
        AppRunner appRunner = AppRunner.getInstance();
        appController.init();
//        Method method = appRunner.getClass().getDeclaredMethod("searchAndDisplay", AppController.class, String.class);
//        method.setAccessible(true);
//        method.invoke(null,appController ,"hello");
        Set<Document> results = appController.search("hello");
        appController.printResults(results);
    }

    @Test
    public void printTester2() {
        try {
            Main main = new Main();
            AppController appController1 = new AppController();
            Method method = main.getClass().getDeclaredMethod("searchAndDisplay", AppController.class, String.class);
            method.setAccessible(true);
            method.invoke(null, appController1, "salam khobbi?");
            Set<Document> results = appController1.search("salam khobbi?");
        } catch (SearchException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void printTester3() {
        SearchEngine searchEngine = SearchEngine.getInstance(InvertedIndex.getInstance());
        try {
            searchEngine.search(null);
        } catch (SearchException e) {
            e.getMessage();
        }
    }

    @Test
    public void printTester4() throws SearchException {
        AppRunner appRunner = AppRunner.getInstance();
        appRunner.searchAndDisplay(appController, "+illness +disease -cough");
        Set<Document> results = appController.search("+illness +disease -cough");
        Mockito.verify(appController, times(1)).printResults(results);
    }

//    @Test
//    public void printTester5() throws SearchException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        SearchEngine searchEngine = SearchEngine.getInstance(InvertedIndex.getInstance());
//        Method method = searchEngine.getClass().getDeclaredMethod("getJointWordsIndexSet", String[].class);
//        String[] results = {"salam" , "khoobi", "majid"};
//        method.setAccessible(true);
//        Object riz = method.invoke(String[].class, results);
//        Assertions.assertNotNull(riz);
//    }
}

class Test2 {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    AppController appController = mock(AppController.class);

    private ByteArrayInputStream testIn;

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private OutputStream provideOutput() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        return baos;
    }


    @Test
    public void testCase1() throws SearchException {
        final String testString = "get help +illness +disease -cough";
        provideInput(testString);
        AppRunner.getInstance().searchAndDisplay(appController, testString);
        final String answer = "58090\n" +
                "58568\n" +
                "58953\n" +
                "59462\n" +
                "59459\n" +
                "59286\n" +
                "59165\n" +
                "59548\n" +
                "59199\n" +
                "59435\n" +
                "59284\n" +
                "58152\n" +
                "58578\n" +
                "58820\n" +
                "59040\n" +
                "59554\n" +
                "59488\n" +
                "59183\n" +
                "59456\n" +
                "59203\n" +
                "57110\n" +
                "59632\n" +
                "59629\n" +
                "58155\n" +
                "59255\n" +
                "59189\n" +
                "59181\n" +
                "59123\n" +
                "59637\n" +
                "58123\n" +
                "58109\n" +
                "59234";
        Set<Document> results = appController.search("get help +illness +disease -cough");
        Mockito.verify(appController, times(1)).printResults(results);
    }

    @Test
    public void testCase2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String testString = "*exit";
        provideInput(testString);
        AppRunner appRunner = AppRunner.getInstance();
        Method method = appRunner.getClass().getDeclaredMethod("getInput", Scanner.class);
        String str = (String) method.invoke(appRunner, new Scanner(System.in));
        Assertions.assertNull(str);
    }

    @Test
    public void testCase3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SearchException, BaseDirectoryInvalidException, IOException {
        final String testString = "get help +illness +disease -cough";
        OutputStream baos = provideOutput();
        appController.init();
        String expected = "59199\n" +
                "59548\n" +
                "58090\n" +
                "58568\n" +
                "58953\n" +
                "59286\n" +
                "59459\n" +
                "59462\n" +
                "59165\n" +
                "59234\n" +
                "57110\n" +
                "58155\n" +
                "59123\n" +
                "59255\n" +
                "59189\n" +
                "59181\n" +
                "59637\n" +
                "58123\n" +
                "58109\n" +
                "59456\n" +
                "59203\n" +
                "59629\n" +
                "59632\n" +
                "59488\n" +
                "59040\n" +
                "59183\n" +
                "59554\n" +
                "59284\n" +
                "59435\n" +
                "58152\n" +
                "58578\n" +
                "58820\n" +
                "59199\n" +
                "59548\n" +
                "58090\n" +
                "58568\n" +
                "58953\n" +
                "59286\n" +
                "59459\n" +
                "59462\n" +
                "59165\n" +
                "59234\n" +
                "57110\n" +
                "58155\n" +
                "59123\n" +
                "59255\n" +
                "59189\n" +
                "59181\n" +
                "59637\n" +
                "58123\n" +
                "58109\n" +
                "59456\n" +
                "59203\n" +
                "59629\n" +
                "59632\n" +
                "59488\n" +
                "59040\n" +
                "59183\n" +
                "59554\n" +
                "59284\n" +
                "59435\n" +
                "58152\n" +
                "58578\n" +
                "58820";
        Set<Document> results = appController.search(testString);
        appController.printResults(results);
        String actual = baos.toString();
        Assertions.assertNotNull(actual);
    }
}
